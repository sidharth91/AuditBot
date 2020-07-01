package com.sap.audit.bot.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LicenceFilterDTO {
	  private List<String> sapSystem;
	  private List<String> client;
	   private String level;
	    private List<String> userType;
	    private List<String> userGroup;
	    private List<String> account;
	    private List<String> licenseType;
	    private List<String> userStatus;
	    private String activeUser;
	    private String tcodes;
	    private String criteria;
	    private String userId;
	    private String count;
	    private String logondays;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    
	    

		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getActiveUser() {
			return activeUser;
		}
		public void setActiveUser(String activeUser) {
			this.activeUser = activeUser;
		}
	
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public List<String> getSapSystem() {
			return sapSystem;
		}
		public void setSapSystem(List<String> sapSystem) {
			this.sapSystem = sapSystem;
		}
		public List<String> getClient() {
			return client;
		}
		public void setClient(List<String> client) {
			this.client = client;
		}

		public List<String> getUserType() {
			return userType;
		}
		public void setUserType(List<String> userType) {
			this.userType = userType;
		}
		public List<String> getUserGroup() {
			return userGroup;
		}
		public void setUserGroup(List<String> userGroup) {
			this.userGroup = userGroup;
		}
		public List<String> getAccount() {
			return account;
		}
		public void setAccount(List<String> account) {
			this.account = account;
		}
		public List<String> getLicenseType() {
			return licenseType;
		}
		public void setLicenseType(List<String> licenseType) {
			this.licenseType = licenseType;
		}
		public List<String> getUserStatus() {
			return userStatus;
		}
		public void setUserStatus(List<String> userStatus) {
			this.userStatus = userStatus;
		}

		public String getTcodes() {
			return tcodes;
		}
		public void setTcodes(String tcodes) {
			this.tcodes = tcodes;
		}
		public String getCriteria() {
			return criteria;
		}
		public void setCriteria(String criteria) {
			this.criteria = criteria;
		}
	
		public String getCount() {
			return count;
		}
		public void setCount(String count) {
			this.count = count;
		}
		public String getLogondays() {
			return logondays;
		}
		public void setLogondays(String logondays) {
			this.logondays = logondays;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}


}
