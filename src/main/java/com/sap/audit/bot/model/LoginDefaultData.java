 package com.sap.audit.bot.model;
 
 
 public class LoginDefaultData
 {
   private String client;
   private String sysId;
   private String sysDesc;
   private String ipAddress;
   
   public LoginDefaultData(String client, String sysId, String sysDesc, String ipAddress) {
     this.client = client;
     this.sysId = sysId;
     this.sysDesc = sysDesc;
     this.ipAddress = ipAddress;
   }
   public String getClient() {
     return this.client;
   }
   public void setClient(String client) {
     this.client = client;
   }
   public String getSysId() {
     return this.sysId;
   }
   public void setSysId(String sysId) {
     this.sysId = sysId;
   }
   public String getSysDesc() {
     return this.sysDesc;
   }
   public void setSysDesc(String sysDesc) {
     this.sysDesc = sysDesc;
   }
   public String getIpAddress() {
     return this.ipAddress;
   }
   public void setIpAddress(String ipAddress) {
     this.ipAddress = ipAddress;
   }
 }
