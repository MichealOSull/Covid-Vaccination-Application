package models;

import javax.persistence.*;

/**
 * Class to model a vaccine
 * @author Micheal O' Sullivan
 * 
 */

import javax.persistence.MappedSuperclass;

@MappedSuperclass


public class Vaccination {
	@Id
	private String vacName;
	private String efficacy;
	private String date;
	
	public Vaccination() {
		
	}
	
	public Vaccination(String vacName, String efficacy, String date) {
		this.vacName = vacName;
		this.efficacy = efficacy;
		this.date = date;
	}
	
	public String getVacName() {
		return vacName;
	}
	public void setVacName(String vacName) {
		this.vacName = vacName;
	}
	
	public String getEfficacy() {
		return efficacy;
	}
	
	public void setEfficacy(String efficacy) {
		this.efficacy = efficacy;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return "Vaccine Name: " + vacName + "Efficacy: " + efficacy + "Date: " + date;
	}

}
