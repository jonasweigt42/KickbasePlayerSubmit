package com.himo.app.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.himo.app.component.Login;
import com.himo.app.component.Logo;
import com.himo.app.view.trip.ProvideTripView;
import com.himo.app.view.trip.SearchTripView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean. Use the @PWA
 * annotation make the application installable on phones, tablets and some
 * desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 */

@PWA(name = "High Mobility Application", shortName = "HiMo")
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/shared-styles.css")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@UIScope
public class MainView extends AppLayout
{

	private static final long serialVersionUID = 8986999742090476969L;

	private Tabs menu;

	@Autowired
	private Logo logo;

	@Autowired
	private Login login;

	@Autowired
	private Logger logger;
	
	private int zahl = 5;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		setPrimarySection(Section.DRAWER);
		addToNavbar(false, new DrawerToggle());
		addToNavbar(false, logo);
		addToNavbar(login.getLoginButton());
		menu = createMenuTabs();
		addToDrawer(menu);
		login.open();
	}

	@Scheduled(fixedRate = 300000)
	public void hoereAufHosesSeite()
	{
		logger.info("-------started------job----------");
		try
		{
			WebDriver driver;

			System.setProperty("webdriver.chrome.driver", "chromedriver");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");

			driver = new ChromeDriver(options);
			
			driver.get("https://netappoint.de/onlineticket-25/index.php?company=kreis-ostholstein&cur_cause=0");

			klickFahrzeugZulassung(driver);
			selektiere1xFahrzeugZulassung(driver);
			klickWeiter(driver);
			
			boolean terminImJuni = istImJuniWasFrei(driver);

			if(terminImJuni)
			{
				Thread.sleep(2000);
				Set<Integer> vergebeneTermine = new HashSet<>();
				Set<Integer> freieTermine = new HashSet<>();
				
				findeFreieTermine(driver, vergebeneTermine, freieTermine);
				
				for(Integer i : vergebeneTermine)
				{
					logger.info("freie Termine für den " + i + ". gefunden");
				}
				if (!freieTermine.isEmpty())
				{
					sendMail(freieTermine);
				}
			}
			
			driver.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			sendMailJonas(e.getMessage());
			logger.error("-------finished---job---with---errors-------", e);
		}
		logger.info("nicht vergessen, bei google die mailconfig wieder zu ändern! von unsicheren quellen mails senden");
		logger.info("-------finished------job----------");
	}

	public void findeFreieTermine(WebDriver driver, Set<Integer> logSet, Set<Integer> set)
	{
		List<WebElement> list4 = driver.findElements(By.className("nat_calendar_weekday_bookable"));
		for (WebElement element : list4)
		{
			int day = Integer.parseInt(element.getText());
			logSet.add(day);
			if (day < zahl)
			{
				set.add(day);
				zahl = day;
			}
		}
	}

	public boolean istImJuniWasFrei(WebDriver driver)
	{
		List<WebElement> list3 = driver.findElements(By.tagName("abbr"));
		for(WebElement element : list3)
		{
			if(element.getText().contains("Mai"))
			{
				WebElement clickNextMonth = driver.findElement(By.className("nat_navigation_button_img"));
				clickNextMonth.click();
				logger.info("zum Juni gewechselt");
				break;
			}
			if(element.getText().contains("Juli"))
			{
				logger.info("Nur Termine im Juli frei...");
				return false;
			}
		}
		return true;
	}

	public void klickWeiter(WebDriver driver)
	{
		List<WebElement> list2 = driver.findElements(By.tagName("input"));
		for (WebElement element : list2)
		{
			if (element.getAttribute("value").equals("Weiter"))
			{
				element.click();
				logger.info("Weiter geklickt");
			}
		}
	}

	public void selektiere1xFahrzeugZulassung(WebDriver driver)
	{
		Select zulassungNeuFahrzeige = new Select(driver.findElement(By.id("casetype_1013")));
		if (zulassungNeuFahrzeige != null)
		{
			zulassungNeuFahrzeige.selectByValue("1");
			logger.info("1x Fahrzeugzulassung ausgewählt");
		}
	}

	public void klickFahrzeugZulassung(WebDriver driver)
	{
		logger.info("schaue für Termine vor dem " + zahl + ". Juni");
		List<WebElement> list = driver.findElements(By.tagName("H3"));
		for (WebElement element : list)
		{
			if (element.getText().equals("Fahrzeugzulassung"))
			{
				element.click();
				logger.info("Fahrzeugzulassung geklickt");
			}
		}
	}

	private void sendMail(Set<Integer> set)
	{
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "jonas.weigt42@gmail.com";
		final String password = "Rastafari.142857";
		try
		{
			Session session = Session.getInstance(props, new Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("jonas.weigt42@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("hosenthienmarcel@gmail.com,jonas.weigt42@gmail.com", false));
			msg.setSubject("Breaking News");

			String numbers = "";

			for (Integer i : set)
			{
				numbers = numbers.concat("\n" + i + ". Juni");
			}
			msg.setText("Griaß di Häuptling,\n am " + numbers
					+ " ist ein Termin frei geworden. Schnapp zu wie eine Viper! Grüße, Sigis Code\n" 
					+ "Hier der Link: https://netappoint.de/onlineticket-25/index.php?company=kreis-ostholstein&cur_cause=0 ");
			msg.setSentDate(new Date());
			Transport.send(msg);
			logger.info("Message sent.");
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}

	}

	private void sendMailJonas(String text)
	{
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "jonas.weigt42@gmail.com";
		final String password = "Rastafari.142857";
		try
		{
			Session session = Session.getInstance(props, new Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("jonas.weigt42@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jonas.weigt42@gmail.com", false));
			msg.setSubject("Exception thrown :( ");
			msg.setText(text);
			msg.setSentDate(new Date());
			Transport.send(msg);
			logger.info("Message sent.");
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}

	}

	private Tabs createMenuTabs()
	{
		final Tabs tabs = new Tabs();
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
		tabs.setId("tabs");
		tabs.add(getAvailableTabs());
		return tabs;
	}

	private Tab[] getAvailableTabs()
	{
		final List<Tab> tabs = new ArrayList<>();
		tabs.add(createTab("Start", StartView.class));
		tabs.add(createTab("Fahrt finden", SearchTripView.class));
		tabs.add(createTab("Fahrt anbieten", ProvideTripView.class));
		tabs.add(createTab("Profil", ProfileView.class));
		return tabs.toArray(new Tab[tabs.size()]);
	}

	private Tab createTab(String title, Class<? extends Component> viewClass)
	{
		return createTab(populateLink(new RouterLink(null, viewClass), title));
	}

	private Tab createTab(Component content)
	{
		final Tab tab = new Tab();
		tab.add(content);
		return tab;
	}

	private <T extends HasComponents> T populateLink(T a, String title)
	{
		a.add(title);
		return a;
	}

	@Override
	protected void afterNavigation()
	{
		super.afterNavigation();
		selectTab();
	}

	private void selectTab()
	{
		String target = RouteConfiguration.forSessionScope().getUrl(getContent().getClass());
		Optional<Component> tabToSelect = menu.getChildren().filter(tab ->
		{
			Component child = tab.getChildren().findFirst().get();
			return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
		}).findFirst();
		tabToSelect.ifPresent(tab -> menu.setSelectedTab((Tab) tab));
	}

}
