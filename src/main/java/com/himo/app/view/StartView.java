package com.himo.app.view;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@RouteAlias(value = "", layout = MainView.class)
@Route(value = "start", layout = MainView.class)
@PageTitle("Start")
@CssImport("./styles/shared-styles.css")
public class StartView extends VerticalLayout
{

	private static final long serialVersionUID = -5855957348824849235L;

	
	@PostConstruct
	public void init()
	{
		H4 label = new H4("Hallo Eva");
		addClassName("centered-content");

		add(label);

	}

}
