package com.himo.app.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.himo.app.entity.user.User;
import com.himo.app.service.user.UserService;
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

	private static final long serialVersionUID = 1686035666342372757L;

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init()
	{
		
		String string = "";
		List<User> users = userService.findAll();
		for(User user : users)
		{
			string = string.concat(user.getFirstName());
		}
		H4 label = new H4("Hallo " + string);
		addClassName("centered-content");

		add(label);

	}

}
