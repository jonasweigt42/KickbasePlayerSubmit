package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.entity.user.User;
import com.himo.app.service.spieltag.SpieltagService;
import com.himo.app.service.submit.PlayerSubmitService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.UIScope;



@RouteAlias(value = "", layout = MainView.class)
@Route(value = "start", layout = MainView.class)
@PageTitle("Submit")
@CssImport("./styles/shared-styles.css")
@UIScope
@Component
public class StartView extends VerticalLayout
{

	private static final long serialVersionUID = 1686035666342372757L;
	
	@Autowired
	private UserInfo userInfo;
	
	@Autowired
	private SpieltagService spieltagService;
	
	@Autowired
	private PlayerSubmitService playerSubmitService;
	
	private ComboBox<String> select = new ComboBox<>();
	private TextField player = new TextField();

	@PostConstruct
	public void init()
	{
		addClassName("centered-content");
		
		loadContent();
	}

	public void loadContent()
	{
		removeAll();
		H4 personalLabel = preparePersonalLabel();
		
		add(personalLabel);
	}
	

	private H4 preparePersonalLabel()
	{
		H4 label = new H4();
		User user = userInfo.getLoggedInUser();
		if(user != null)
		{
			label.setText("Hi " + user.getFirstName() + "! Submitte mal");
			select.setItems(spieltagService.findAll().stream().map(s -> s.getName()));
			select.setLabel("Spieltag");
			select.addValueChangeListener(evt -> fillPlayerLabel());
			player.setLabel("Spielername");
			
			Button letsGo = new Button(TextConstants.LETSGO);
			letsGo.addClickListener(evt -> saveSubmit(player.getValue(), select.getValue()));
			add(label, select, player, letsGo);

		}
		else
		{
			label.setText(TextConstants.NOT_LOGGED_IN_MESSAGE);
		}
		return label;
	}

	private void fillPlayerLabel()
	{
		// TODO Auto-generated method stub
	}

	private void saveSubmit(String spielerName, String spieltag)
	{
		PlayerSubmit submit = createSubmit(spielerName, spieltag);
		playerSubmitService.save(submit);
		player.clear();
		select.clear();
	}

	private PlayerSubmit createSubmit(String spielerName, String spieltag)
	{
		PlayerSubmit submit = new PlayerSubmit();
		submit.setPlayerName(spielerName);
		submit.setSpieltag(spieltag);
		submit.setSaison("2021/2022");
		submit.setUserName(userInfo.getLoggedInUser().getFirstName());
		return submit;
	}
	
}
