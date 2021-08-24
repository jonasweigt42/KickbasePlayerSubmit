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
	private Label errorLabel = new Label();

	@Autowired
	private UserService userService;

	@PostConstruct
	public void init()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.addClassName("centered-content");

		H2 title = new H2("Neues Passwort vergeben");
		mailAddress = prepareEMailField();

		errorLabel.addClassName("text-red");

		Button submit = new Button();
		submit.addClickListener(ent -> validate(mailAddress.getValue()));
		submit.setText("zurücksetzen");

		setCloseOnEsc(true);
		setSizeFull();

		layout.add(title, mailAddress, errorLabel, submit);
		add(layout);

	}

	public TextField prepareEMailField()
	{
		mailAddress.setLabel(TextConstants.USERNAME);
		Binder<User> binder = new Binder<>();
		binder.forField(mailAddress).withValidator(new EmailValidator("bitte gib eine gültige E-Mail Adresse ein"))
				.bind(User::getUserName, User::setUserName);
		return mailAddress;
	}

	private void validate(String mailAddress)
	{
		User user = userService.getUserByUserName(mailAddress);

		if (user == null)
		{
			errorLabel.setText("Benutzer ist nicht registriert");

		} else
		{
			// TODO add send mail and generate random password
//			user.setPassword(encoder.encode(randomPassword));
//			userService.update(user);
//			Notification.show("Passwort für " + user.getMailAddress() + " zurückgesetzt");
			Notification.show("not implemented yet");
			close();
		}
	}

}
