package com.himo.app.service.stats;

import java.util.List;

import com.himo.app.entity.submit.PlayerSubmit;

public interface StatsService
{
	List<PlayerSubmit> getAllBeforeNow();
}
