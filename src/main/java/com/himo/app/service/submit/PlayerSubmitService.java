package com.himo.app.service.submit;

import java.util.List;

import com.himo.app.entity.submit.PlayerSubmit;

public interface PlayerSubmitService
{
	List<PlayerSubmit> findAll();

	void update(PlayerSubmit submit);

	void save(PlayerSubmit submit);
}
