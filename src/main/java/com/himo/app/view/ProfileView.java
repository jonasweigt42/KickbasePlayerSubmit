package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.component.ChangePasswordDialog;
import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.service.user.UserService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "profile", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
@UIScope
@Component
public class ProfileView extends VerticalLayout
{

	private static final long serialVersionUID = -1468535064495134323L;

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChangePasswordDialog changePasswordDialog;

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
			TextField mailaddress = prepareUserNameTextField(user);
			
			Button changePassword = new Button("Passwort Ändern");
			changePassword.addClickListener(evt -> changePasswordDialog.open());

			Button save = new Button("Speichern");
			save.addClickListener(evt -> updateUser(mailaddress.getValue()));

			add(mailaddress, changePassword, save);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

	public TextField prepareUserNameTextField(User user)
	{
		TextField userName = new TextField();
		userName.setValue(user.getUserName());
		userName.setLabel(TextConstants.USERNAME);
		return userName;
	}

	private void updateUser(String username)
	{
		User user = userInfo.getLoggedInUser();
		user.setUserName(username);
		userService.update(user);
		Notification.show("Benutzer wurde aktualisiert!");
	}

}
