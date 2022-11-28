package com.supermarket.utilities;



import org.testng.annotations.DataProvider;

public class DataProviderUtil {
  

	
	@DataProvider(name="loginData")
	 public Object[][] getData() {
		 return new Object[][] {
		      new Object[] { "username1", "password1" },
		      new Object[] { "username2", "password2" },
		    };


	}
}
