package com.himo.app.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.userinfo.UserInfo;
import com.himo.app.view.ProfileView;
import com.himo.app.view.StartView;
import com.himo.app.view.trip.ProvideTripView;
import com.himo.app.view.trip.SearchTripView;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class Login extends LoginOverlay
{

	private static final long serialVersionUID = -3124840772943883433L;

	private Button loginButton = new Button();
	private Label nameLabel = new Label();

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private SearchTripView searchTripView;
	
	@Autowired
	private StartView startView;
	
	@Autowired
	private ProvideTripView provideTripView;
	
	@Autowired
	private ProfileView profileView;

	@PostConstruct
	public void init()
	{
		prepareButtonLabel();
		prepareLoginButton();
		prepareLoginListener();
		
		setI18n(prepareI18n());
		setTitle(TextConstants.TITLE);
		setDescription("Willkommen! Bitte melde dich kurz an und dann kann es schon losgehen!");
		
		nameLabel.setVisible(false);
	}

	private void prepareLoginListener()
	{
		addLoginListener(new ComponentEventListener<LoginEvent>()
		{
			private static final long serialVersionUID = -50433215575229805L;

			@Override
			public void onComponentEvent(LoginEvent event)
			{
				userInfo.login(event.getUsername(), event.getPassword());
				if (userInfo.isLoggedIn())
				{
					prepareButtonLabel();
					addNameToLabel();
					close();
					updateViews();
				}
			}
		});
	}
	
	private void updateViews()
	{
		searchTripView.loadContent();
		startView.loadContent();
		provideTripView.loadContent();
		profileView.loadContent();
	}

	private void prepareLoginButton()
	{
		loginButton.addClickListener(e -> changeLoginState());
		loginButton.setClassName("right");
	}

	private LoginI18n prepareI18n()
	{
		LoginI18n i18n = LoginI18n.createDefault();
		i18n.getForm().setUsername(TextConstants.USERNAME);
		i18n.getForm().setPassword(TextConstants.PASSWORD);
		i18n.getForm().setForgotPassword(TextConstants.FORGET_PASSWORD);
		return i18n;
	}

	private void changeLoginState()
	{
		if (userInfo.isLoggedIn())
		{
			userInfo.logout();
			setOpened(false);
		} else
		{
			setOpened(true);
		}
	}

	private void prepareButtonLabel()
	{
		if (userInfo.isLoggedIn())
		{
			loginButton.setText("Logout");
		} else
		{
			loginButton.setText("Login");
		}
	}

	private void addNameToLabel()
	{
		nameLabel.setText(" - Eingeloggt als " + userInfo.getLoggedInUser().getFirstName());
		nameLabel.setVisible(true);
	}

	public Button getLoginButton()
	{
		return loginButton;
	}
	
	public Label getNameLabel()
	{
		return nameLabel;
	}

}
