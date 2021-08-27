package com.himo.app.view.trip;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.spieltag.Spieltag;
import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.entity.user.User;
import com.himo.app.service.spieltag.SpieltagService;
import com.himo.app.service.submit.PlayerSubmitService;
import com.himo.app.userinfo.UserInfo;
import com.himo.app.view.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "debug", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@UIScope
@Component
public class DebugView extends VerticalLayout
{

	private static final long serialVersionUID = -6374720069983434221L;

	
	@Autowired
	private UserInfo userInfo;
	
	@Autowired
	private PlayerSubmitService playerSubmitService;
	
	@Autowired
	private SpieltagService spieltagService;


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
			addFieldsForUser(user);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

	public void addFieldsForUser(User user)
	{
		H4 label = new H4("aktuelle Zeit: " + LocalDateTime.now().toString());
		
		add(label);
		getAllBeforeNow();
	}
	
	public List<PlayerSubmit> getAllBeforeNow()
	{
		List<PlayerSubmit> all = playerSubmitService.findAll();
		List<PlayerSubmit> result = new ArrayList<>();
		for(PlayerSubmit submit : all)
		{
			Label label = new Label(submit.getPlayerName());
			add(label);
			Spieltag spieltag = spieltagService.findByName(submit.getSpieltag());
			Label label2 = new Label(spieltag.getStartDateTime().toString());
			add(label2);
			if(LocalDateTime.now().isAfter(spieltag.getStartDateTime()))
			{
				result.add(submit);
			}
		}
		return result;
	}


}
