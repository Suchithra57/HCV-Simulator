package com.genfare.hcv.optionsImpl;

import java.util.Properties;

import com.genfare.hcv.clientrequest.DeviceAuthentication;
import com.genfare.hcv.main.EnvironmentSetting;
import com.genfare.hcv.util.PropertiesRetrieve;


public class DeviceAuthOptImpl {

	
	public String authenticate() {
		
		PropertiesRetrieve propertiesRetrieve = new PropertiesRetrieve();
		Properties property = propertiesRetrieve.getProperties(); 
		String tenant=EnvironmentSetting.getTenant().toLowerCase();
		String serialNumber = property.getProperty(tenant+"."+EnvironmentSetting.getEnv()+".fbxno");
		String password = property.getProperty(tenant+"."+EnvironmentSetting.getEnv()+".pwd");
		
		
		DeviceAuthentication deviceAuthentication = new DeviceAuthentication();
		String deviceAuthToken = deviceAuthentication.authenticate(serialNumber,password);
		if(deviceAuthToken != null)
		{
			EnvironmentSetting.setTenant(tenant);
			EnvironmentSetting.setFbSerialNumber(serialNumber);
			EnvironmentSetting.setFbPassword(password);
			return "authentication successfull";
		}
		return "failed to authenticate";
	}
	
	
	
}
