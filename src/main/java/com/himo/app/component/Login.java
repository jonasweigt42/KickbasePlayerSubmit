package com.himo.app.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.entity.user.User;
import com.himo.app.service.user.UserService;
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
	private boolean loggedIn = false;
	@Autowired
	private UserService userService;

	@PostConstruct
	public void init()
	{
		loginButton.setText("Login");
		loginButton.addClickListener(e -> log());
		loginButton.setClassName("right");
		addLoginListener(new ComponentEventListener<LoginEvent>()
		{
			private static final long serialVersionUID = -50433215575229805L;

			@Override
			public void onComponentEvent(LoginEvent event)
			{
				String username = event.getUsername();
				User user = userService.getUserByUserName(username);
				if (event.getPassword().equals(user.getPassword()))
				{
					toggleButtonLabel();
					close();
					userService.setLoggenInUser(user);
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
		if (loggedIn)
		{
			toggleButtonLabel();
			userService.setLoggenInUser(null);
			setOpened(false);
		} else
		{
			setOpened(true);
		}
	}

	private void toggleButtonLabel()
	{
		loggedIn = !loggedIn;
		if (loggedIn)
		{
			loginButton.setText("Logout");
		} else
		{
			loginButton.setText("Login");
		}
	}

	public Button getButton()
	{
		return loginButton;
	}

	public boolean isLoggedIn()
	{
		return loggedIn;
	}

}
