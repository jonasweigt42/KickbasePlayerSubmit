package com.himo.app.component;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Logo extends HorizontalLayout
{

	private static final long serialVersionUID = 1L;

	public Logo()
	{
		Icon icon = new Icon(VaadinIcon.HOME);
		Label label = new Label("HiMo");
		add(icon, label);
	}
	
}
