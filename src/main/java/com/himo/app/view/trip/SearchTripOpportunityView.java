package com.himo.app.view.trip;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gatanaso.MultiselectComboBox;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.userinfo.UserInfo;
import com.himo.app.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "searchTripOpportunity", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
public class SearchTripOpportunityView extends VerticalLayout
{


	@Autowired
	private UserInfo userInfo;

	private static final long serialVersionUID = 4333504967133586606L;

	@PostConstruct
	public void init()
	{
		addClassName("centered-content");

		loadContent();

//		if(user.isFahrer())
//		{
//			label.setText("Wie fährst du nach Tamsweg?");
//			ComboBox<String> select = new ComboBox<>();
//			select.setItems(travelOpportunityService.getTravelOpportunityNames());
//			String text = "Es wird nach Mitfahrern gesucht...";
//			Button button = new Button("los geht's!");
//			TextField takePeople = new TextField("Freie Plätze");
//			button.addClickListener(evt -> Notification.show(text));
//			add(label, select, takePeople, button);
//		}
//		else
//		{
//			
//		}
	}

	public void loadContent()
	{
		removeAll();
		User user = userInfo.getLoggedInUser();
		if (user != null)
		{
			H4 label = new H4();
			MultiselectComboBox<String> select = new MultiselectComboBox<>();

			Button letsGo = new Button(TextConstants.LETSGO);
			add(label, select, letsGo);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

}
