package com.himo.app.travel;

import java.time.LocalDate;
import java.time.LocalTime;

public class TravelData
{

	private String start;
	private String destination;
	private LocalDate date;
	private LocalTime time;
	
	public TravelData(String start, String destination, LocalDate date, LocalTime time)
	{
		this.start = start;
		this.destination = destination;
		this.date = date;
		this.time = time;
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
