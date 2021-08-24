package com.himo.app.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.event.Publisher;
import com.himo.app.event.UpdateLoginEvent;
import com.himo.app.service.user.UserService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;
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
public class Register extends Dialog
{

	private static final long serialVersionUID = -7750716192029688905L;

	private TextField firstName = new TextField();
	private TextField lastName = new TextField();
	private TextField userName = new TextField();
	private PasswordField password = new PasswordField();
	private PasswordField passwordRetype = new PasswordField();
	private Label errorLabel = new Label();
	private Button submit = new Button("Registrieren");

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private Publisher publisher;

	@Autowired
	private PasswordEncoder encoder;

	@PostConstruct
	public void init()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.addClassName("centered-content");

		H4 title = new H4("Neu hier?");
		prepareFields();

		setCloseOnEsc(true);
		setSizeFull();

		layout.add(title, firstName, lastName, userName, password, passwordRetype, errorLabel, submit);
		add(layout);

	}

	public void prepareFields()
	{
		userName = prepareEMailField();
		errorLabel.addClassName("text-red");
		password.setLabel(TextConstants.PASSWORD);
		passwordRetype.setLabel(TextConstants.PASSWORD_RETYPE);
		submit.addClickListener(evt -> validateRegistration());
	}

	public TextField prepareEMailField()
	{
		userName.setLabel(TextConstants.USERNAME);
		Binder<User> binder = new Binder<>();
		binder.forField(userName).withValidator(new EmailValidator("bitte gib eine gültige E-Mail Adresse ein"))
				.bind(User::getUserName, User::setUserName);
		return userName;
	}

	private void validateRegistration()
	{
		User user = userService.getUserByUserName(userName.getValue());

		if (user != null)
		{
			errorLabel.setText("Benutzer ist schon vorhanden");
		}
		if (user == null && !password.getValue().equals(passwordRetype.getValue()))
		{
			errorLabel.setText("Passwörter sind nicht gleich");
		}
		if (user == null && password.getValue().equals(passwordRetype.getValue()))
		{
			User newUser = createUser();
			userService.save(newUser);
			userInfo.login(newUser.getUserName(), passwordRetype.getValue());
			close();
			publisher.publishEvent(new UpdateLoginEvent(this));
		}
	}

	public User createUser()
	{
		User newUser = new User();
		newUser.setUserName(userName.getValue());

		String encodedPassword = encoder.encode(passwordRetype.getValue());

		newUser.setPassword(encodedPassword);
		return newUser;
	}

}
