package com.himo.app.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himo.app.constants.TextConstants;
import com.himo.app.entity.submit.PlayerSubmit;
import com.himo.app.entity.user.User;
import com.himo.app.filter.PlayerSubmitFilter;
import com.himo.app.service.stats.StatsService;
import com.himo.app.userinfo.UserInfo;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
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
	private StatsService statsService;

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
			Grid<PlayerSubmit> grid = prepareGrid();
			add(grid);
		} else
		{
			H4 label = new H4(TextConstants.NOT_LOGGED_IN_MESSAGE);
			add(label);
		}
	}

	private Grid<PlayerSubmit> prepareGrid()
	{
		Grid<PlayerSubmit> grid = new Grid<>();

		ListDataProvider<PlayerSubmit> dataProvider = new ListDataProvider<>(statsService.getAllBeforeNow());
		grid.setDataProvider(dataProvider);

		Grid.Column<PlayerSubmit> userNameColumn = grid.addColumn(PlayerSubmit::getUserName).setHeader("Name")
				.setSortable(true);
		Grid.Column<PlayerSubmit> spieltagColumn = grid.addColumn(PlayerSubmit::getSpieltag).setHeader("Spieltag")
				.setSortable(true);
		Grid.Column<PlayerSubmit> playerNameColumn = grid.addColumn(PlayerSubmit::getPlayerName)
				.setHeader("Spielername").setSortable(true);
		Grid.Column<PlayerSubmit> punkteColumn = grid.addColumn(PlayerSubmit::getPunkte).setHeader("Punkte")
				.setSortable(true);

		HeaderRow filterRow = grid.appendHeaderRow();

		PlayerSubmitFilter filterObject = new PlayerSubmitFilter();
		dataProvider.setFilter(person -> filterObject.test(person));

		// First filter
		TextField userNameFilterField = new TextField();
		userNameFilterField.addValueChangeListener(event ->
		{
			filterObject.setUserName(event.getValue());
			dataProvider.refreshAll();
		});
		userNameFilterField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(userNameColumn).setComponent(userNameFilterField);
		userNameFilterField.setSizeFull();
		userNameFilterField.setPlaceholder("Filter");
		userNameFilterField.getElement().setAttribute("focus-target", "");

		// Second filter
		TextField spieltagFilterField = new TextField();
		spieltagFilterField.addValueChangeListener(event ->
		{
			filterObject.setSpieltag(event.getValue());
			dataProvider.refreshAll();
		});
		spieltagFilterField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(spieltagColumn).setComponent(spieltagFilterField);
		spieltagFilterField.setSizeFull();
		spieltagFilterField.setPlaceholder("Filter");
		spieltagFilterField.getElement().setAttribute("focus-target", "");

		// Third filter
		TextField playerNameFilterField = new TextField();
		playerNameFilterField.addValueChangeListener(event ->
		{
			filterObject.setPlayerName(event.getValue());
			dataProvider.refreshAll();
		});
		playerNameFilterField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(playerNameColumn).setComponent(playerNameFilterField);
		playerNameFilterField.setSizeFull();
		playerNameFilterField.setPlaceholder("Filter");
		playerNameFilterField.getElement().setAttribute("focus-target", "");

		// Fourth filter
		TextField punkteFilterField = new TextField();
		punkteFilterField.addValueChangeListener(event ->
		{
			filterObject.setPunkte(event.getValue());
			dataProvider.refreshAll();
		});
		punkteFilterField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(punkteColumn).setComponent(punkteFilterField);
		punkteFilterField.setSizeFull();
		punkteFilterField.setPlaceholder("Filter");
		punkteFilterField.getElement().setAttribute("focus-target", "");

		grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);
		return grid;
	}

}
