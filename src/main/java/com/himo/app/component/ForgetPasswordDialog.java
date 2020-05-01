package com.himo.app.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.service.user.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.spring.annotation.UIScope;

@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@Component
@UIScope
public class ForgetPasswordDialog extends Dialog
{

	private static final long serialVersionUID = 2214540533394292409L;
	
	private TextField mailAddress = new TextField();
	private PasswordField newPassword = new PasswordField();
	private PasswordField newPasswordRetype = new PasswordField();
	private Label label = new Label();

	@Autowired
	private UserService userService;

	@PostConstruct
	public void init()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.addClassName("centered-content");
		
		H2 title = new H2("Neues Passwort vergeben");
		mailAddress = prepareEMailField();
		
		label.addClassName("text-red");

		newPassword.setLabel(TextConstants.NEW_PASSWORD);
		newPasswordRetype.setLabel(TextConstants.NEW_PASSWORD_RETYPE);
		
		Button submit = new Button();
		submit.addClickListener(ent -> validate(mailAddress.getValue()));
		submit.setText("zurücksetzen");

		setCloseOnEsc(true);
		setSizeFull();
		
		layout.add(title, mailAddress, newPassword, newPasswordRetype, label, submit);
		add(layout);

	}

	public TextField prepareEMailField()
	{
		mailAddress.setLabel(TextConstants.MAIL_ADDRESS);
		Binder<User> binder = new Binder<>();
		binder.forField(mailAddress).withValidator(new EmailValidator("bitte gib eine gültige E-Mail Adresse ein"))
				.bind(User::getMailAddress, User::setMailAddress);
		return mailAddress;
	}

	private void validate(String mailAddress)
	{
		User user = userService.getUserByMailAddress(mailAddress);

		if (user == null)
		{
			label.setText("Benutzer ist nicht registriert");
			
		}
		if(user != null && !newPassword.getValue().equals(newPasswordRetype.getValue()))
		{
			label.setText("Passwörter sind nicht gleich");
		}
		if(user != null && newPassword.getValue().equals(newPasswordRetype.getValue()))
		{
			user.setPassword(newPasswordRetype.getValue());
			userService.update(user);
			Notification.show("Passwort für " + user.getMailAddress() + " zurückgesetzt");
			close();
		}
	}

}
