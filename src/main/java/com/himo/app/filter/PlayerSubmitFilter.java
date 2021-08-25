package com.himo.app.filter;

import org.apache.commons.lang3.StringUtils;

import com.himo.app.entity.submit.PlayerSubmit;
import com.vaadin.flow.function.SerializablePredicate;

public class PlayerSubmitFilter implements SerializablePredicate<PlayerSubmit>
{

	private static final long serialVersionUID = -4389416869956669515L;

	private String playerName = "";
	private String userName = "";
	private String spieltag = "";
	private String punkte = "";

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getSpieltag()
	{
		return spieltag;
	}

	public void setSpieltag(String spieltag)
	{
		this.spieltag = spieltag;
	}

	public String getPunkte()
	{
		return punkte;
	}

	public void setPunkte(String punkte)
	{
		this.punkte = punkte;
	}

	@Override
	public boolean test(PlayerSubmit playerSubmit)
	{
		if (playerName.length() > 0 && !StringUtils.containsIgnoreCase(playerSubmit.getPlayerName(), playerName))
		{
			return false;
		}
		if (userName.length() > 0 && !StringUtils.containsIgnoreCase(playerSubmit.getUserName(), userName))
		{
			return false;
		}
		if (spieltag.length() > 0 && !StringUtils.containsIgnoreCase(playerSubmit.getSpieltag(), spieltag))
		{
			return false;
		}

		if (punkte.length() > 0 && !StringUtils.containsIgnoreCase(String.valueOf(playerSubmit.getPunkte()), punkte))
		{
			return false;
		}
		return true;
	}

}
