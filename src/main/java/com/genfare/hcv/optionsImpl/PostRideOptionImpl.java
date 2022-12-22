package com.genfare.hcv.optionsImpl;

import java.time.Instant;
import java.util.Date;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.genfare.hcv.main.EnvironmentSetting;
import com.genfare.hcv.model.RecordTransaction;
import com.genfare.hcv.util.PropertiesRetrieve;

public class PostRideOptionImpl {
	private PropertiesRetrieve propertiesRetrieve = new PropertiesRetrieve();
	private Properties property = propertiesRetrieve.getProperties();
	JSONObject json = null;

	public void process(String[] arguments) {
		switch (arguments[0]) {
		case "post":
			switch (arguments[1]) {
				case "ride":
					String deviceAuthToken = EnvironmentSetting.getDeviceAuthToken();
					if (deviceAuthToken != null) {
						postRidership(deviceAuthToken);
					}
					break;
			}
			break;
		
		case "record":
			switch (arguments[1]) {
			case "ride":
				String cardPrintId = arguments[2];
				if (cardPrintId != null) {
					String deviceAuthToken = EnvironmentSetting.getDeviceAuthToken();
					if (deviceAuthToken != null) {
						buildAndPostRecordRidership(deviceAuthToken, cardPrintId);
					}
				} else {
					System.out.println("Invalid printid");
				}
				break;

			}
			break;
		case "validate":
			String deviceAuthToken = EnvironmentSetting.getDeviceAuthToken();
			if (deviceAuthToken != null) {
				getRidershipActivity(deviceAuthToken, arguments[1]);
				if (json != null) {
					long activityTime = (long) json.get("result") / 1000;

					Instant instant = Instant.ofEpochSecond(activityTime);
					Date date = Date.from(instant); // convert Instant to Date

					System.out.println("Last Ridership Date & Time:" + date);
					Date currentDate = new Date();
					System.out.println("Current Date & Time :" + currentDate);

					differenceCal(date, currentDate);

				}
			}
			break;

		}
	}
	
	private void postRidership(String deviceAuthToken)
	{
		String uploadURL;
//		https://api.qe.gfcp.io/services/data-api/hcv/riderships?tenant=CDTA
		if (EnvironmentSetting.getEnv().equals("qe")) {
			uploadURL = property
					.getProperty(EnvironmentSetting.getTenant().toLowerCase() + "." + EnvironmentSetting.getEnv() + "."
							+ "domain.url")
					+ "/services/data-api/hcv/riderships?tenant="
					+ EnvironmentSetting.getTenant();
		} else if (EnvironmentSetting.getEnv().equals("local")) {
			uploadURL = "http://" + EnvironmentSetting.getEnvironment() + ":" + EnvironmentSetting.FUSE_PORT
					+ "/services/data-api/hcv/riderships?tenant="
					+ EnvironmentSetting.getTenant();
		} else {
			uploadURL = "https://" + EnvironmentSetting.getEnvironment()
					+ "/services/data-api/hcv/riderships?tenant="
					+ EnvironmentSetting.getTenant();
		}
		System.out.println(uploadURL);
		String postData=property.getProperty("hcv.postRequest");
		Client client = ClientBuilder.newClient();
		try {
			MultivaluedMap<String, Object> head = new MultivaluedHashMap<String, Object>();
			head.add("Authorization", deviceAuthToken);
//			head.add("Content-Type", "application/json");

			Response response = client.target(uploadURL).request().headers(head).post(Entity.text(postData));

			System.out.println("response status: " + response.getStatus());
			String responseAsString = response.readEntity(String.class);
			System.out.println(responseAsString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void postRecordRide(String deviceAuthToken, RecordTransaction recordTransaction, String cardPrintId) {
		String uploadURL;
		if (EnvironmentSetting.getEnv().equals("qe")) {
			uploadURL = property
					.getProperty(EnvironmentSetting.getTenant().toLowerCase() + "." + EnvironmentSetting.getEnv() + "."
							+ "domain.url")
					+ "/services/data-api/hcv/recordTransaction/printid/" + cardPrintId + "?tenant="
					+ EnvironmentSetting.getTenant();
		} else if (EnvironmentSetting.getEnv().equals("local")) {
			uploadURL = "http://" + EnvironmentSetting.getEnvironment() + ":" + EnvironmentSetting.FUSE_PORT
					+ "/services/data-api/hcv/recordTransaction/printid/" + cardPrintId + "?tenant="
					+ EnvironmentSetting.getTenant();
		} else {
			uploadURL = "https://" + EnvironmentSetting.getEnvironment()
					+ "/services/data-api/hcv/recordTransaction/printid/" + cardPrintId + "?tenant="
					+ EnvironmentSetting.getTenant();
		}
		System.out.println(uploadURL);
		Client client = ClientBuilder.newClient();
		try {
			MultivaluedMap<String, Object> head = new MultivaluedHashMap<String, Object>();
			head.add("Authorization", deviceAuthToken);
			head.add("Content-Type", "application/json");

			Response response = client.target(uploadURL).request().headers(head).post(Entity.json(recordTransaction));

			System.out.println("response status: " + response.getStatus());
			String responseAsString = response.readEntity(String.class);
			System.out.println(responseAsString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public JSONObject getRidershipActivity(String deviceAuthToken, String cardId) {
		String uploadURL;
		if (EnvironmentSetting.getEnv().equals("qe")) {
			uploadURL = property
					.getProperty(EnvironmentSetting.getTenant().toLowerCase() + "." + EnvironmentSetting.getEnv() + "."
							+ "domain.url")
					+ "/services/data-api/hcv/ridershipactivity/printid/" + cardId + "?tenant="
					+ EnvironmentSetting.getTenant();
		} else if (EnvironmentSetting.getEnv().equals("local")) {
			uploadURL = "http://" + EnvironmentSetting.getEnvironment() + ":" + EnvironmentSetting.FUSE_PORT
					+ "/services/data-api/hcv/ridershipactivity/printid/" + cardId + "?tenant="
					+ EnvironmentSetting.getTenant();
		} else {
			uploadURL = "https://" + EnvironmentSetting.getEnvironment()
					+ "/services/data-api/hcv/ridershipactivity/printid/" + cardId + "?tenant="
					+ EnvironmentSetting.getTenant();
		}
		System.out.println(uploadURL);
		Client client = ClientBuilder.newClient();
		try {
			MultivaluedMap<String, Object> head = new MultivaluedHashMap<String, Object>();
			head.add("Authorization", deviceAuthToken);
			head.add("Content-Type", "application/json");

			Response response = client.target(uploadURL).request().headers(head).get();

			System.out.println("response status: " + response.getStatus());
			String responseAsString = response.readEntity(String.class);
			System.out.println(responseAsString);
			json = new JSONObject(responseAsString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return json;
	}

	private void differenceCal(Date d1, Date d2) 
	{
		long difference_In_Time = d2.getTime() - d1.getTime();
		long difference_In_Seconds = (difference_In_Time / 1000) % 60;
		long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
		long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
		long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
		long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

		System.out.print("Difference " + "between two dates is: ");

		System.out.println(difference_In_Years + " years, " + difference_In_Days + " days, " + difference_In_Hours
				+ " hours, " + difference_In_Minutes + " minutes, " + difference_In_Seconds + " seconds");

		if (difference_In_Years == 0 && difference_In_Days == 0 && difference_In_Hours == 0) {
			if (difference_In_Minutes > 30) {
				System.out.println("No Ridership taken, you need to post ridership");
			} else {
				System.out.println("Ridership recently happened");
			}
		} else {
			System.out.println("No Ridership taken, you need to post ridership");
		}
	}

	private void buildAndPostRecordRidership(String deviceAuthToken, String cardPrintId) {

		RecordTransaction recordTransaction = new RecordTransaction();
		recordTransaction.setLatitude(Float.parseFloat(property.getProperty("hcv.latitude")));
		recordTransaction.setLongitude(Float.parseFloat(property.getProperty("hcv.longitude")));
		recordTransaction.setActivationType(property.getProperty("hcv.activationType"));
		recordTransaction.setChargeDate(new Date());
		recordTransaction.setActivityType(property.getProperty("hcv.activityType"));
		recordTransaction.setOrderIndex(Integer.parseInt(property.getProperty("hcv.orderIndex")));
		recordTransaction.setEquipmentSerial(property.getProperty("hcv.equipmentSerial"));

		postRecordRide(deviceAuthToken, recordTransaction, cardPrintId);

	}
	
}
