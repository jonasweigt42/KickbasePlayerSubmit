package com.himo.app.view.trip;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.userinfo.UserInfo;
import com.himo.app.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "provideTripOpportunity", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
public class ProvideTripOpportunityView extends VerticalLayout
{

	@Autowired
	private UserInfo userInfo;

	private static final long serialVersionUID = 4333504967133586606L;

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
			H4 label = new H4();
			ComboBox<String> select = new ComboBox<>();

			Button letsGo = new Button(TextConstants.ABGABE);

			TextField takePeople = new TextField("Freie Plätze");
			add(label, select, takePeople, letsGo);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

}
