package com.himo.app.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.event.UpdateRegisterEvent;
import com.himo.app.service.user.UserService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
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
public class Register extends Dialog implements ApplicationListener<UpdateRegisterEvent>
{

	private static final long serialVersionUID = -7750716192029688905L;

	private TextField firstName = new TextField();
	private TextField lastName = new TextField();
	private TextField mailAddress = new TextField();
	private PasswordField newPassword = new PasswordField();
	private PasswordField newPasswordRetype = new PasswordField();
	private Label errorLabel = new Label();
	private Button registerButton = new Button("Registrieren");

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfo userInfo;

	@PostConstruct
	public void init()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.addClassName("centered-content");
		prepareRegisterButton();

		H2 title = new H2("Neu hier?");
		mailAddress = prepareEMailField();

		errorLabel.addClassName("text-red");

		firstName.setLabel(TextConstants.FIRSTNAME);
		lastName.setLabel(TextConstants.LASTNAME);
		newPassword.setLabel(TextConstants.NEW_PASSWORD);
		newPasswordRetype.setLabel(TextConstants.NEW_PASSWORD_RETYPE);

		Button submit = new Button();
		submit.addClickListener(ent -> validate());
		submit.setText("Registrieren");

		setCloseOnEsc(true);
		setSizeFull();

		layout.add(title, firstName, lastName, mailAddress, newPassword, newPasswordRetype, errorLabel, submit);
		add(layout);

	}
	
	private void prepareRegisterButton()
	{
		registerButton.addClickListener(e -> changeRegisterState());
		registerButton.setClassName("button");
	}

	private void changeRegisterState()
	{
		if(!userInfo.isLoggedIn())
		{
			open();
		}
	}

	public TextField prepareEMailField()
	{
		mailAddress.setLabel(TextConstants.MAIL_ADDRESS);
		Binder<User> binder = new Binder<>();
		binder.forField(mailAddress).withValidator(new EmailValidator("bitte gib eine gültige E-Mail Adresse ein"))
				.bind(User::getMailAddress, User::setMailAddress);
		return mailAddress;
	}

	private void validate()
	{
		User user = userService.getUserByMailAddress(mailAddress.getValue());

		if (user != null)
		{
			errorLabel.setText("Benutzer ist schon vorhanden");
		}
		if (user != null && !newPassword.getValue().equals(newPasswordRetype.getValue()))
		{
			errorLabel.setText("Passwörter sind nicht gleich");
		}
		if (user == null && newPassword.getValue().equals(newPasswordRetype.getValue()))
		{
			User newUser = createUser();
			userService.save(newUser);
			userInfo.login(mailAddress.getValue(), newPasswordRetype.getValue());
			close();
		}
	}

	public User createUser()
	{
		User newUser = new User();
		newUser.setFirstName(firstName.getValue());
		newUser.setLastName(lastName.getValue());
		newUser.setMailAddress(mailAddress.getValue());
		newUser.setPassword(newPasswordRetype.getValue());
		return newUser;
	}
	
	public Button getRegisterButton()
	{
		return registerButton;
	}
	
	@Override
	public void onApplicationEvent(UpdateRegisterEvent event)
	{
		registerButton.setVisible(false);
		System.out.println("HALLO HEIMO");
	}

}
