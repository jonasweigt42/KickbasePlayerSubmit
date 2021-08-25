package com.himo.app.view;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.spieltag.Spieltag;
import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.entity.user.User;
import com.himo.app.service.spieltag.SpieltagService;
import com.himo.app.service.submit.PlayerSubmitService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.UIScope;

@RouteAlias(value = "", layout = MainView.class)
@Route(value = "start", layout = MainView.class)
@PageTitle("Submit")
@CssImport("./styles/shared-styles.css")
@UIScope
@Component
public class StartView extends VerticalLayout
{

	private static final long serialVersionUID = 1686035666342372757L;

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private SpieltagService spieltagService;

	@Autowired
	private PlayerSubmitService playerSubmitService;

	private ComboBox<String> select = new ComboBox<>();
	private TextField spieler = new TextField();
	private Label abgabeFrist = new Label();
	private Label fristZeit = new Label();
	private Button abgabeButton = new Button(TextConstants.ABGABE);

	private static final String SAISON = "2021/2022";

	@PostConstruct
	public void init()
	{
		addClassName("centered-content");

		loadContent();
	}

	public void loadContent()
	{
		removeAll();
		H4 personalLabel = preparePersonalLabel();

		add(personalLabel);
	}

	private H4 preparePersonalLabel()
	{
		H4 label = new H4();
		User user = userInfo.getLoggedInUser();
		if (user != null)
		{
			label.setText("Hi " + user.getUserName() + "! Submitte mal");
			select.setItems(spieltagService.findAll().stream().map(s -> s.getName()));
			select.setLabel("Spieltag");
			select.addValueChangeListener(evt -> calcLabels());
			spieler.setLabel("Spielername");
			abgabeFrist.setText("Abgabefrist: ");

			abgabeButton.addClickListener(evt -> saveSubmit());
			abgabeButton.setEnabled(false);
			add(label, select, abgabeFrist, fristZeit, spieler, abgabeButton);

		} else
		{
			label.setText(TextConstants.NOT_LOGGED_IN_MESSAGE);
		}
		return label;
	}

	private void calcLabels()
	{
		PlayerSubmit submit = playerSubmitService.find(userInfo.getLoggedInUser().getUserName(), select.getValue(),
				SAISON);
		Spieltag spieltag = spieltagService.findByName(select.getValue());
		if (spieltag == null)
		{
			fristZeit.setText("");
			abgabeButton.setEnabled(false);
		} else
		{
			LocalDateTime frist = spieltag.getStartDateTime();

			fristZeit.setText(formatFristDate(frist));
			fillComboBox(submit);
			enableSubmitButton(frist);
		}
	}

	private void fillComboBox(PlayerSubmit submit)
	{
		if (submit != null)
		{
			spieler.setValue(submit.getPlayerName());
		} else
		{
			spieler.setValue("");
		}
	}

	private void enableSubmitButton(LocalDateTime frist)
	{
		if (LocalDateTime.now().isAfter(frist))
		{
			abgabeButton.setEnabled(false);
		} else
		{
			abgabeButton.setEnabled(true);
		}
	}

	private String formatFristDate(LocalDateTime localDateTime)
	{
		int tag = localDateTime.getDayOfMonth();
		int month = localDateTime.getMonthValue();
		int year = localDateTime.getYear();
		int hour = localDateTime.getHour();
		int minute = localDateTime.getMinute();
		return tag + "." + month + "." + year + ", " + hour + ":" + minute + "Uhr";
	}

	private void saveSubmit()
	{
		String spielerName = spieler.getValue();
		Spieltag spieltag = spieltagService.findByName(select.getValue());
		PlayerSubmit existingSubmit = playerSubmitService.find(userInfo.getLoggedInUser().getUserName(),
				spieltag.getName(), SAISON);
		if (existingSubmit != null)
		{
			existingSubmit.setPlayerName(spielerName);
			playerSubmitService.update(existingSubmit);
		} else
		{
			PlayerSubmit newSubmit = createSubmit(spielerName, spieltag.getName());
			playerSubmitService.save(newSubmit);
			Notification.show(spielerName + " für Spieltag " + spieltag.getName() + " submitted");
		}

		spieler.clear();
		select.clear();
	}

	private PlayerSubmit createSubmit(String spielerName, String spieltag)
	{
		PlayerSubmit submit = new PlayerSubmit();
		submit.setPlayerName(spielerName);
		submit.setSpieltag(spieltag);
		submit.setSaison(SAISON);
		submit.setUserName(userInfo.getLoggedInUser().getUserName());
		return submit;
	}

}
