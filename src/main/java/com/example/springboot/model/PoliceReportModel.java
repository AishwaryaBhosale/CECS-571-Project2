package com.example.springboot.model;

import java.util.Date;

public class PoliceReportModel {
	String itemNumber;
	String location;
	String disposition;
	String dipositionText;
	String charge;
	Date occuredAt;
	String offenderRace;
	String offenderAge;
	String offenderGender;
	String victimRace;
	String victimAge;
	String victimGender;
	String fatalStatus;
	String reportType;
	String victimNumber;
	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getDipositionText() {
		return dipositionText;
	}
	public void setDipositionText(String dipositionText) {
		this.dipositionText = dipositionText;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public Date getOccuredAt() {
		return occuredAt;
	}
	public void setOccuredAt(Date occuredAt) {
		this.occuredAt = occuredAt;
	}
	public String getOffenderRace() {
		return offenderRace;
	}
	public void setOffenderRace(String offenderRace) {
		this.offenderRace = offenderRace;
	}
	public String getOffenderAge() {
		return offenderAge;
	}
	public void setOffenderAge(String offenderAge) {
		this.offenderAge = offenderAge;
	}
	public String getOffenderGender() {
		return offenderGender;
	}
	public void setOffenderGender(String offenderGender) {
		this.offenderGender = offenderGender;
	}
	public String getVictimRace() {
		return victimRace;
	}
	public void setVictimRace(String victimRace) {
		this.victimRace = victimRace;
	}
	public String getVictimAge() {
		return victimAge;
	}
	public void setVictimAge(String victimAge) {
		this.victimAge = victimAge;
	}
	public String getVictimGender() {
		return victimGender;
	}
	public void setVictimGender(String victimGender) {
		this.victimGender = victimGender;
	}
	public String getFatalStatus() {
		return fatalStatus;
	}
	public void setFatalStatus(String fatalStatus) {
		this.fatalStatus = fatalStatus;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getVictimNumber() {
		return victimNumber;
	}
	public void setVictimNumber(String victimNumber) {
		this.victimNumber = victimNumber;
	}
	

}
