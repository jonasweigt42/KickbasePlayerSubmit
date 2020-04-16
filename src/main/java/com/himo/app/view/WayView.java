package com.himo.app.view;

import com.himo.app.travel.TravelData;
import com.himo.app.travel.TravelOpportunityFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("way")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class WayView extends VerticalLayout
{

	private static final long serialVersionUID = 4333504967133586606L;

	public WayView()
	{
		H4 label = new H4();
		TravelData data = (TravelData) VaadinSession.getCurrent().getAttribute(TravelData.class.getName());
		addClassName("centered-content");
		
		label.setText("Wie fährst du nach " + data.getDestination() + "?");
		ComboBox<String> select = new ComboBox<>();
		select.setItems(TravelOpportunityFactory.getTravelOpportunityList());
		String text = "Es wird nach Mitfahrern gesucht...";
		Button button = new Button("los geht's!");
		TextField takePeople = new TextField("Freie Plätze");
		button.addClickListener(evt -> Notification.show(text));
		add(label, select, takePeople, button);

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
