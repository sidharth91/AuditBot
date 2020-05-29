package com.sap.audit.bot.model;

import java.util.List;
import java.util.Map;

public class ReportDTO {
  private List<Map<String, Object>> data;
  
  public List<Map<String, Object>> getData() {
    return this.data;
  } private List<Object> header;
  public void setData(List<Map<String, Object>> data) {
    this.data = data;
  }
  public List<Object> getHeader() {
    return this.header;
  }
  public void setHeader(List<Object> header) {
    this.header = header;
  }
}

