package com.himo.app.travel;

import java.time.LocalDate;
import java.time.LocalTime;

import com.himo.app.user.User;

public class TravelData
{

	private User user;
	private String start;
	private String destination;
	private LocalDate date;
	private LocalTime time;
	
	public TravelData(User user, String start, String destination, LocalDate date, LocalTime time)
	{
		this.user = user;
		this.start = start;
		this.destination = destination;
		this.date = date;
		this.time = time;
	}
	
	public User getUser()
	{
		return user;
	}
	public String getStart()
	{
		return start;
	}
	public String getDestination()
	{
		return destination;
	}
	public LocalDate getDate()
	{
		return date;
	}
	public LocalTime getTime()
	{
		return time;
	}
}
