package com.himo.app.travel;

import java.util.ArrayList;
import java.util.List;

public class TravelOpportunityFactory
{

	private static List<String> travelOpportunityList = createData();

	public static List<String> getTravelOpportunityList()
	{
		return travelOpportunityList;
	}

	private static List<String> createData()
	{
		List<String> travelOpportunityList = new ArrayList<>();
		
		travelOpportunityList.add(("E-Carsharing"));
		travelOpportunityList.add(("Carsharing"));
		travelOpportunityList.add(("Bikesharing"));
		travelOpportunityList.add(("Bus"));
		travelOpportunityList.add(("Mitfahrgelegenheit"));
		travelOpportunityList.add(("autonomer Kleinbus"));
		return travelOpportunityList;
	}
}
