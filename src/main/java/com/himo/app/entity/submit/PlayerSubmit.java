package com.himo.app.entity.submit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER_SUBMIT")
public class PlayerSubmit implements Serializable{

	private static final long serialVersionUID = -6203364119548878619L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String playerName;
	@Column
	private String userName;
	@Column
	private String spieltag;
	@Column
	private String saison;
	
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
	public String getSaison()
	{
		return saison;
	}
	public void setSaison(String saison)
	{
		this.saison = saison;
	}
	
	
}
