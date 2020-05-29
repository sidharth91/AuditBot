package com.sap.audit.bot.model;


public class JwtUser
{
  private String userName;
  private String password;
  
  public String getUserName() {
    return this.userName;
  } private String host; private String client; private String system;
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getHost() {
    return this.host;
  }
  public void setHost(String host) {
    this.host = host;
  }
  public String getClient() {
    return this.client;
  }
  public void setClient(String client) {
    this.client = client;
  }
  public String getSystem() {
    return this.system;
  }
  public void setSystem(String system) {
    this.system = system;
  }
}

