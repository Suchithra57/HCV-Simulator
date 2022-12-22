package com.genfare.hcv.clientrequest;

import java.util.Properties;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.genfare.hcv.main.EnvironmentSetting;
import com.genfare.hcv.util.PropertiesRetrieve;

import javax.ws.rs.client.Client;

public class Tokens {
	JSONObject json = null;

	public static final String AUTH_HEADER_PROPERTY = "Authorization";
	public static final String AUTH_HEADER_PREFIX = "Basic";
	PropertiesRetrieve propertiesRetrieve = new PropertiesRetrieve();
	Properties prop = propertiesRetrieve.getProperties();

	public JSONObject getToken() {
		
		String tenant=EnvironmentSetting.getTenant().toLowerCase();
		String auth_username = prop.getProperty(tenant+"."+EnvironmentSetting.getEnv()+".username");
		String auth_password = prop.getProperty(tenant+"."+EnvironmentSetting.getEnv()+".password");
		return this.cooCooAuthentication(auth_username, auth_password);
		}
	
	
	public JSONObject cooCooAuthentication(String auth_username, String auth_password) {
		byte[] authorizationBytes = (auth_username + ":" + auth_password).getBytes();
		String authorizationHeader = AUTH_HEADER_PREFIX + " " + new String(Base64.encodeBase64(authorizationBytes));
		String authUrlString;
		if(EnvironmentSetting.getEnv().equals("qe")) {
			authUrlString = "https://" + EnvironmentSetting.getEnvironment()
			+ "/authenticate/oauth/token?grant_type=client_credentials&client_id=" + auth_username;
		}

		
		else if (EnvironmentSetting.getEnv().equals("local")) {
			authUrlString = "http://" + EnvironmentSetting.getEnvironment()
					+ ":"+EnvironmentSetting.JBOSS_PORT+"/authenticate/oauth/token?grant_type=client_credentials&client_id=" + auth_username;
		}
		
		else {
			authUrlString = "https://" + EnvironmentSetting.getEnvironment()
		+ "/authenticate/oauth/token?grant_type=client_credentials&client_id=" + auth_username;
		}
		Client client = ClientBuilder.newClient();
		try {
			MultivaluedMap<String, Object> head = new MultivaluedHashMap<String, Object>();
			head.add(AUTH_HEADER_PROPERTY, authorizationHeader);
			head.add("Content-Type", "application/json");

			Response response = client.target(authUrlString).request().headers(head).get();
			if (response.getStatus() == 200) {
				String responseAsString = response.readEntity(String.class);
				json = new JSONObject(responseAsString);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return json;
	}

}
