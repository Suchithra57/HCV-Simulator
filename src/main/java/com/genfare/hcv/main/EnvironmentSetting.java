package com.genfare.hcv.main;

public class EnvironmentSetting {
	private static String fbSerialNumber = "002220" ;
	private static String fbPassword = "02cbb638-8026-46e4-bbf8-5ea94820f897" ;
//	private static String environment = "cdta-intg.gfcp.io";
	private static String environment = "cdta-qe.gfcp.io";
	private static String tenant = "CDTA";
//	private static String env = "intg";
	private static String env = "qe";
	private static String dateofusage;
	private static String deviceAuthToken = null;
//	private static String propertiesFilePath = "C:/pavan/farebox simulator/project/fareboxsimulator/src/main/resources/farebox.properties";
	private static String propertiesFilePath = System.getProperty("user.dir")+"/src/main/resources/hcv.properties";
	public static final String JBOSS_PORT = "8080";
	public static final String FUSE_PORT = "8184";

	
	
	
	public static String getFbSerialNumber() {
		return fbSerialNumber;
	}
	public static void setFbSerialNumber(String fbSerialNumber) {
		EnvironmentSetting.fbSerialNumber = fbSerialNumber;
	}
	public static String getFbPassword() {
		return fbPassword;
	}
	public static String getDeviceAuthToken() {
		return deviceAuthToken;
	}
	public static void setDeviceAuthToken(String deviceAuthToken) {
		EnvironmentSetting.deviceAuthToken = deviceAuthToken;
	}
	public static void setFbPassword(String fbPassword) {
		EnvironmentSetting.fbPassword = fbPassword;
	}
	public static String getEnvironment() {
		environment = tenant.toLowerCase()+"-"+env+".gfcp.io";
		return environment;
	}

	
	public static String getTenant() {
		return tenant;
	}

	public static void setTenant(String tenant) {
		EnvironmentSetting.tenant = tenant.toUpperCase();
	}
	public static String getEnv() {
		return env;
	}
	public static String getDateofusage() {
		return dateofusage;
	}
	public static void setDateofusage(String dateofusage) {
		EnvironmentSetting.dateofusage = dateofusage;
	}
	public static void setEnvironment(String environment) {
		EnvironmentSetting.environment = environment;
	}
	public static void setEnv(String env) {
		EnvironmentSetting.env = env.toLowerCase();
	}
	public static String getPropertiesFilePath() {
		return propertiesFilePath;
	}
	public static void setPropertiesFilePath(String propertiesFilePath) {
		EnvironmentSetting.propertiesFilePath = propertiesFilePath;
	}
	
	
	

}
