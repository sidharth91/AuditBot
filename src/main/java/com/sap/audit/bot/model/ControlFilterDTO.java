package com.sap.audit.bot.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ControlFilterDTO {
	  private String sapSystem;
	  private String client;
	 private List<String> controls;
	 private String mitigation;
	 private String drillDown;
	 private String control;
	 
	public String getSapSystem() {
		return sapSystem;
	}
	public void setSapSystem(String sapSystem) {
		this.sapSystem = sapSystem;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public List<String> getControls() {
		return controls;
	}
	public void setControls(List<String> controls) {
		this.controls = controls;
	}
	public String getMitigation() {
		return mitigation;
	}
	public void setMitigation(String mitigation) {
		this.mitigation = mitigation;
	}
	public String getDrillDown() {
		return drillDown;
	}
	public void setDrillDown(String drillDown) {
		this.drillDown = drillDown;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	  
	

}
