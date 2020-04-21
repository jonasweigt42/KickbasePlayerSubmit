package com.himo.app.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class Login extends LoginOverlay
{

	private static final long serialVersionUID = -3124840772943883433L;

	private Button loginButton = new Button();
	private Button registerButton = new Button();

	@Autowired
	private UserInfo userInfo;

	@PostConstruct
	public void init()
	{
		setButtonsLabel();
		loginButton.addClickListener(e -> log());
		loginButton.setClassName("floated");
		registerButton.setClassName("floated");
		addLoginListener(new ComponentEventListener<LoginEvent>()
		{
			private static final long serialVersionUID = -50433215575229805L;

			@Override
			public void onComponentEvent(LoginEvent event)
			{
				userInfo.login(event.getUsername(), event.getPassword());
				if (userInfo.isLoggedIn())
				{
					setButtonsLabel();
					close();
				}
			}
		});
		setTitle("HiMo");
		setDescription("Willkommen! Bitte melde dich kurz an und dann kann es schon losgehen!");
		LoginI18n i18n = LoginI18n.createDefault();
		setI18n(i18n);
	}

	private void log()
	{
		if (userInfo.isLoggedIn())
		{
			userInfo.logout();
			setButtonsLabel();
			setOpened(false);
		} else
		{
			setOpened(true);
		}
	}

	private void setButtonsLabel()
	{
		if (userInfo.isLoggedIn())
		{
			registerButton.setEnabled(false);
			loginButton.setText("Logout");
		} else
		{
			registerButton.setText("Registrieren");
			loginButton.setText("Login");
		}
	}

	public Button getLoginButton()
	{
		return loginButton;
	}

	public Button getRegisterButton()
	{
		return registerButton;
	}

}
