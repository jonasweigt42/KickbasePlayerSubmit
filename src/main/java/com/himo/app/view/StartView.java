package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.himo.app.service.user.UserService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.shared.Registration;

@RouteAlias(value = "", layout = MainView.class)
@Route(value = "start", layout = MainView.class)
@PageTitle("Start")
@CssImport("./styles/shared-styles.css")
public class StartView extends VerticalLayout
{

	private static final long serialVersionUID = 1686035666342372757L;

	@Autowired
	private UserService userService;

	private H4 label = new H4();
	private String string = "";

	Registration broadcasterRegistration;

	@PostConstruct
	public void init()
	{
		addClassName("centered-content");

		updateLabel();

		add(label);
	}

	public void updateLabel()
	{
		if (userService.getLoggenInUser() == null)
		{
			string = "Hallo Gast";
		} else
		{
			string = "Hallo " + userService.getLoggenInUser().getFirstName();
		}
		label.setText(string);
	}


}
