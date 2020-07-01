 package com.sap.audit.bot.model;
 
 import java.util.List;
 
 public class FilterData {
   private int level;
   private String sapSystem;
   private String client;
   private List<String> riskType;
   private List<String> riskLevel;
   private List<String> businessModule;
   private List<String> riskId;
   private String mitigation;
   private String reportType;
   private String drillDown;
   private String userInput;
   private String reportView;
   
   private List<Integer> breakDown;




   
   public List<String> getRiskType() {
     return this.riskType;
   }
   public void setRiskType(List<String> riskType) {
     this.riskType = riskType;
   }
   public String getSapSystem() {
     return this.sapSystem;
   }
   public void setSapSystem(String sapSystem) {
     this.sapSystem = sapSystem;
   }
   public String getClient() {
     return this.client;
   }
   public void setClient(String client) {
     this.client = client;
   }
   public List<String> getRiskLevel() {
     return this.riskLevel;
   }
   public void setRiskLevel(List<String> riskLevel) {
     this.riskLevel = riskLevel;
   }
   public List<String> getBusinessModule() {
     return this.businessModule;
   }
   public void setBusinessModule(List<String> businessModule) {
     this.businessModule = businessModule;
   }
   public int getLevel() {
     return this.level;
   }
   public void setLevel(int level) {
     this.level = level;
   }
   public List<Integer> getBreakDown() {
     return this.breakDown;
   }
   public void setBreakDown(List<Integer> breakDown) {
     this.breakDown = breakDown;
   }


public String getUserInput() {
	return userInput;
}
public void setUserInput(String userInput) {
	this.userInput = userInput;
}
public List<String> getRiskId() {
	return riskId;
}
public void setRiskId(List<String> riskId) {
	this.riskId = riskId;
}
public String getMitigation() {
	return mitigation;
}
public void setMitigation(String mitigation) {
	this.mitigation = mitigation;
}
public String getReportType() {
	return reportType;
}
public void setReportType(String reportType) {
	this.reportType = reportType;
}
public String getDrillDown() {
	return drillDown;
}
public void setDrillDown(String drillDown) {
	this.drillDown = drillDown;
}
public String getReportView() {
	return reportView;
}
public void setReportView(String reportView) {
	this.reportView = reportView;
}
   


   
 }

