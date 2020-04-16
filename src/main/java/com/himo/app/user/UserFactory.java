package com.himo.app.user;

import java.util.ArrayList;
import java.util.List;

public class UserFactory
{
	private static List<User> userList = createData();

	public static List<User> getUserList()
	{
		return userList;
	}

	private static List<User> createData()
	{
		List<User> userList = new ArrayList<>();
		userList.add(new User("eva", "e", "Eva", "Weigt"));
		userList.add(new User("jonas", "j", "Jonas", "Weigt"));
		return userList;
	}
	
	public static User getUserByUserName(String userName)
	{
		for(User user : userList)
		{
			if(user.getUserName().equals(userName))
			{
				return user;
			}
		}
		return null;
	}
}
