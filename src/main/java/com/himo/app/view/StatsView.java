package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.entity.user.User;
import com.himo.app.service.submit.PlayerSubmitService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "stats", layout = MainView.class)
@CssImport("./styles/shared-styles.css")
@UIScope
@Component
public class StatsView extends VerticalLayout
{

	private static final long serialVersionUID = 444122528585600642L;

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private PlayerSubmitService playerSubmitService;
	
	@PostConstruct
	public void init()
	{
		loadContent();
	}

	public void loadContent()
	{
		removeAll();
		User user = userInfo.getLoggedInUser();
		if (user != null)
		{
			Grid<PlayerSubmit> grid = new Grid<>();
			grid.addColumn(PlayerSubmit::getUserName).setHeader("Name").setSortable(true);
			grid.addColumn(PlayerSubmit::getSpieltag).setHeader("Spieltag").setSortable(true);
			grid.addColumn(PlayerSubmit::getPlayerName).setHeader("Spielername").setSortable(true);
			grid.addColumn(PlayerSubmit::getPunkte).setHeader("Punkte").setSortable(true);
			grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);
			grid.setItems(playerSubmitService.findAll());
			add(grid);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

}
