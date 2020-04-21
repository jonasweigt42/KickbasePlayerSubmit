package com.himo.app.view;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.UIScope;

@RouteAlias(value = "", layout = MainView.class)
@Route(value = "start", layout = MainView.class)
@PageTitle("Start")
@CssImport("./styles/shared-styles.css")
@UIScope
public class StartView extends VerticalLayout
{

	private static final long serialVersionUID = 1686035666342372757L;

	Registration broadcasterRegistration;

	@PostConstruct
	public void init()
	{
		addClassName("centered-content");
	}

}
