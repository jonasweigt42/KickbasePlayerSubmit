package com.himo.app.view.trip;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.user.User;
import com.himo.app.service.travel.TravelOpportunityService;
import com.himo.app.travel.TravelData;
import com.himo.app.userinfo.UserInfo;
import com.himo.app.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "provideTripOpportunity", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
public class ProvideTripOpportunityView extends VerticalLayout
{

	@Autowired
	private TravelOpportunityService travelOpportunityService;

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
			TravelData data = userInfo.getTravelData();
			H4 label = new H4();
			label.setText("Wie fährst du nach " + data.getDestination() + "?");
			ComboBox<String> select = new ComboBox<>();
			select.setItems(travelOpportunityService.getTravelOpportunityNames());

			String text = "Der kürzeste Weg von " + data.getStart() + " nach " + data.getDestination()
					+ " wird berechnet und es wird nach Mitfahrern gesucht...";
			Button letsGo = new Button(TextConstants.LETSGO);
			letsGo.addClickListener(evt -> Notification.show(text));

			TextField takePeople = new TextField("Freie Plätze");
			add(label, select, takePeople, letsGo);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

}
