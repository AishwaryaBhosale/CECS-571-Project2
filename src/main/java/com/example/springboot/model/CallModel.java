package com.example.springboot.model;

import java.util.Date;

public class CallModel {
	String itemNumber;
	String compliantText;
	String zip;
	String latitude;
	String longitude;
	String streetAddress;
	Date timeCreated;
	Date timeClosed;
	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getCompliantText() {
		return compliantText;
	}
	public void setCompliantText(String compliantText) {
		this.compliantText = compliantText;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	public Date getTimeClosed() {
		return timeClosed;
	}
	public void setTimeClosed(Date timeClosed) {
		this.timeClosed = timeClosed;
	}
}
