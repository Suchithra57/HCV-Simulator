package com.genfare.hcv.model;

import java.util.Date;

public class RecordTransaction {

	private float latitude;
	private float longitude;
	private String activationType;
	private Date chargeDate;
	private String activityType;
	private int orderIndex;
	private String equipmentSerial;
	
	public RecordTransaction() {
		super();
	}
	
	public RecordTransaction(float latitude, float longitude, String activationType, Date chargeDate,
			String activityType, int orderIndex, String equipmentSerial) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.activationType = activationType;
		this.chargeDate = chargeDate;
		this.activityType = activityType;
		this.orderIndex = orderIndex;
		this.equipmentSerial = equipmentSerial;
	}
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getActivationType() {
		return activationType;
	}
	public void setActivationType(String activationType) {
		this.activationType = activationType;
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public String getEquipmentSerial() {
		return equipmentSerial;
	}
	public void setEquipmentSerial(String equipmentSerial) {
		this.equipmentSerial = equipmentSerial;
	}

	@Override
	public String toString() {
		return "RecordTransaction [latitude=" + latitude + ", longitude=" + longitude + ", activationType="
				+ activationType + ", chargeDate=" + chargeDate + ", activityType=" + activityType + ", orderIndex="
				+ orderIndex + ", equipmentSerial=" + equipmentSerial + "]";
	}
	
	
}
