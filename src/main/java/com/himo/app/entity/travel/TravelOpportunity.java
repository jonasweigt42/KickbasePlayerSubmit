package com.himo.app.entity.travel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVEL_OPPORTUNITY")
public class TravelOpportunity
{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	@Override
	public String toString()
	{
		return name;
	}
	
}
