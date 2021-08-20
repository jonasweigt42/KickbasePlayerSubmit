package com.himo.app.view.trip;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.userinfo.UserInfo;
import com.himo.app.view.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "searchTrip", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@UIScope
@Component
public class SearchTripView extends VerticalLayout
{

	private static final long serialVersionUID = 4153761837545371752L;

	@Autowired
	private UserInfo userInfo;

	@PostConstruct
	public void init()
	{
		addClassName("centered-content");

		loadContent();
	}

	public void loadContent()
	{
		removeAll();

		User user = userInfo.getLoggedInUser();
		if (user != null)
		{
			addFieldsForUser(user);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

	public void addFieldsForUser(User user)
	{
		H4 label = new H4("Hi " + user.getFirstName() + ", wo möchtest du hinfahren?");

		TextField start = new TextField("Start");
		TextField dest = new TextField("Ziel");

		DatePicker datePicker = new DatePicker();
		TimePicker timePicker = new TimePicker();

		Button nowButton = new Button("jetzt");
		nowButton.addClickListener(evt -> setTimeValuesForNow(datePicker, timePicker));

		Button letsGoButton = new Button(TextConstants.LETSGO);

		letsGoButton.addClickListener(evt -> saveAndNavigate(start.getValue(), dest.getValue(), datePicker.getValue(),
				timePicker.getValue()));

		add(label, start, dest, datePicker, timePicker, nowButton, letsGoButton);
	}

	private void setTimeValuesForNow(DatePicker datePicker, TimePicker timePicker)
	{
		datePicker.setValue(LocalDate.now());
		timePicker.setValue(LocalTime.now());
	}

	private void saveAndNavigate(String start, String dest, LocalDate date, LocalTime time)
	{
		UI.getCurrent().navigate(SearchTripOpportunityView.class);
	}

}
