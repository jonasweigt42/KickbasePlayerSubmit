package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.himo.app.entity.user.User;
import com.himo.app.service.user.UserService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "chooseDriver", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@UIScope
public class ChooseWayView extends VerticalLayout
{

	private static final long serialVersionUID = 4153761837545371752L;

	@Autowired
	private UserInfo userInfo;
	@Autowired
	private UserService userService;

	@PostConstruct
	public void init()
	{
		H4 label = new H4();
		Button button = new Button("los geht's!");
		ComboBox<String> comboBox = new ComboBox<>();
		
		addClassName("centered-content");
		User user = userInfo.getLoggedInUser();
		if(user != null)
		{
			label = new H4("Hi " + user.getFirstName() + "! Bist du Fahrer oder Mitfahrer?");
			comboBox.setItems("Fahrer", "Mitfahrer");

			button.addClickListener(evt ->
			{
				boolean bool = calcIsFahrer(comboBox.getValue());
				user.setFahrer(bool);
				user.setMitfahrer(!bool);
				userService.update(user);
				navigate();
			});
			
		}
//		else if(user == null)
//		{
//		}
		else
		{
			comboBox.setVisible(false);
			button.setVisible(false);
			label = new H4("Hi! Bitte log dich erst ein, bevor es weitergeht");

//			TextField start = new TextField("Start");
//			TextField dest = new TextField("Ziel");
//			
//			DatePicker datePicker = new DatePicker();
//	 		TimePicker timePicker = new TimePicker();
//	 		
//	 		Button nowButton = new Button("jetzt");
//	 		nowButton.addClickListener(evt -> setTimeValuesForNow(datePicker, timePicker));
		}
		
//		Checkbox checkBox = new Checkbox("als Favorit markieren");
//		checkBox.setEnabled(false);
//		
//		comboBox.addValueChangeListener(evt -> checkBox.setEnabled(true));
		add(label, comboBox, button);
	}
	
//	private void setTimeValuesForNow(DatePicker datePicker, TimePicker timePicker)
// 	{
// 		datePicker.setValue(LocalDate.now());
// 		timePicker.setValue(LocalTime.now());
// 	}

	private boolean calcIsFahrer(String comboBoxValue)
	{
		if (comboBoxValue.equals("Fahrer"))
		{
			return true;
		}
		return false;
	}

	private void navigate()
	{
		UI.getCurrent().navigate(WayView.class);
	}
}
