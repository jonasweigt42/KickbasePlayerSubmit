package com.himo.app.service.submit;

import java.util.List;

import com.himo.app.entity.submit.PlayerSubmit;

public interface PlayerSubmitService
{
	List<PlayerSubmit> findAll();

	void update(PlayerSubmit submit);

	void save(PlayerSubmit submit);
	
	PlayerSubmit find(String userName, String spieltag, String saison);
	
	PlayerSubmit find(String userName, String spieltag, String spielerName, String saison);
	
	
}
