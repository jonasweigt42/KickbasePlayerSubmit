package com.himo.app;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.himo.app.view.MainView;

@Configuration
@EnableScheduling
public class AppConfig
{

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Logger myLogger()
	{
		return LoggerFactory.getLogger(MainView.class);
	}

	@PostConstruct
	public void init()
	{
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Vienna"));
	}
}
