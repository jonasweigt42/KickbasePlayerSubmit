package com.himo.app.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.himo.app.component.Logo;
import com.himo.app.entity.user.User;
import com.himo.app.service.user.UserService;
import com.himo.app.travel.TravelData;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
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
@Route
@PWA(name = "High Mobility Application", shortName = "HiMo")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView extends VerticalLayout
{

	private static final long serialVersionUID = -4263611319380233369L;

	@Autowired
	private UserService userService;
	@Autowired
	private Logo logo;
	/**
	 * Construct a new Vaadin view.
	 * <p>
	 * Build the initial UI state for the user accessing the application.
	 *
	 * @param service The message service. Automatically injected Spring managed
	 *                bean.
	 */
	@PostConstruct
	private void init()
	{
		H4 headline = new H4("Eva" + ", Wo möchtest du hinfahren?");
		addClassName("centered-content");

		TextField start = new TextField("Start");
//		start.setPrefixComponent(new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT));
		start.setValue("Staig 3, Mauterndorf");
		TextField dest = new TextField("Ziel");
//		dest.setPrefixComponent(new Icon(VaadinIcon.ARROW_CIRCLE_LEFT));
		dest.setValue("Kirchweg 5, Tamsweg");

		DatePicker datePicker = new DatePicker();
		datePicker.setValue(LocalDate.of(2020, 04, 20));
		TimePicker timePicker = new TimePicker();
		timePicker.setValue(LocalTime.of(8, 30));

		Button nowButton = new Button("Jetzt");
		nowButton.addClickListener(evt -> setTimeValuesForNow(datePicker, timePicker));
		Button button = new Button("los geht's!");
		
		List<User> users = userService.getUsers();

		button.addClickListener(evt -> saveAndNavigate(users.get(0), start.getValue(), dest.getValue(), datePicker.getValue(),
				timePicker.getValue()));

		add(logo, headline, start, dest, nowButton, datePicker, timePicker, button);
	}

	private void setTimeValuesForNow(DatePicker datePicker, TimePicker timePicker)
	{
		datePicker.setValue(LocalDate.now());
		timePicker.setValue(LocalTime.now());
	}

	private void saveAndNavigate(User user, String start, String destination, LocalDate date, LocalTime time)
	{
		VaadinSession.getCurrent().setAttribute(TravelData.class.getName(), new TravelData(user, start, destination,
				date, time));
		UI.getCurrent().navigate(WayView.class);
	}

}
