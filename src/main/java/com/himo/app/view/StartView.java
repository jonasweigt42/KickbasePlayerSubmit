package com.himo.app.view;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@RouteAlias(value = "", layout = MainView.class)
@Route(value = "start", layout = MainView.class)
@PageTitle("Start")
@CssImport("./styles/shared-styles.css")
public class StartView extends VerticalLayout
{

	private static final long serialVersionUID = -5855957348824849235L;

	@PostConstruct
	public void init()
	{
		
//		LoginOverlay loginOverlay = new LoginOverlay();
//		loginOverlay.setTitle("HiMo");
//		loginOverlay.setDescription("Your application for high mobility");
//		Button open = new Button("Login",
//		    e -> loginOverlay.setOpened(true));
//
//		LoginI18n i18n = LoginI18n.createDefault();
//		i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
//		loginOverlay.setI18n(i18n);
		
		H4 label = new H4("Hallo Eva");
		addClassName("centered-content");

		add(label);

	}

}
