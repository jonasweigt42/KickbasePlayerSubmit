package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gatanaso.MultiselectComboBox;

import com.himo.app.component.Logo;
import com.himo.app.entity.travel.TravelOpportunity;
import com.himo.app.service.travel.TravelOpportunityService;
import com.himo.app.travel.TravelData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("way")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class WayView extends VerticalLayout
{

	@Autowired
	private Logo logo;
	
	@Autowired
	private TravelOpportunityService travelOpportunityService;
	
	private static final long serialVersionUID = 4333504967133586606L;

	@PostConstruct
	public void init()
	{
		H4 label = new H4();
		TravelData data = (TravelData) VaadinSession.getCurrent().getAttribute(TravelData.class.getName());
		addClassName("centered-content");
		
		label.setText("Wie möchtest du nach " + data.getDestination() + " kommen?");
		//TODO convert to strings
		MultiselectComboBox<TravelOpportunity> select = new MultiselectComboBox<>();
		select.setItems(travelOpportunityService.getTravelOpportunities());
		String text = "Der kürzeste Weg von " + data.getStart() + " nach " + data.getDestination()
				+ " wird berechnet und es wird nach Fahrern gesucht...";
		Button button = new Button("los geht's!");
		button.addClickListener(evt -> Notification.show(text));
		add(logo, label, select, button);

//		if (data.getUser().isFavoriteFahrer())
//		{
//			label.setText("Wie fährst du nach " + data.getDestination() + "?");
//			ComboBox<String> select = new ComboBox<>();
//			select.setItems(TravelOpportunityFactory.getTravelOpportunityList());
//			String text = "Es wird nach Mitfahrern gesucht...";
//			Button button = new Button("los geht's!");
//			TextField takePeople = new TextField("Freie Plätze");
//			button.addClickListener(evt -> Notification.show(text));
//			add(label, select, takePeople, button);
//		} else
//		{
//			label.setText("Wie möchtest du nach " + data.getDestination() + " kommen?");
//			MultiselectComboBox<String> select = new MultiselectComboBox<>();
//			select.setItems(TravelOpportunityFactory.getTravelOpportunityList());
//			String text = "Der kürzeste Weg von " + data.getStart() + " nach " + data.getDestination()
//					+ " wird berechnet und es wird nach Fahrern gesucht...";
//			Button button = new Button("los geht's!");
//			button.addClickListener(evt -> Notification.show(text));
//			add(label, select, button);
//		}

	}

}
