package com.sap.audit.bot.service;

import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.FilterData;
import com.sap.audit.bot.model.JwtUser;
import com.sap.audit.bot.model.ReportDTO;
import com.sap.conn.jco.JCoException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface FunctionService {
  List<Map<String, Object>> getFilterTableData(JwtUser paramJwtUser);
  
  List<Map<String, Object>> getFilterResultTableData(JwtUser paramJwtUser, FilterData paramFilterData) throws JCoException;
  
  ReportDTO getRiskDetailReport(JwtUser jwtUser, String system, String client, String level, String riskType, String risklevel, String appclass, String risk, String user, String role) throws JCoException, AuditBotAuthenticationException;
  
  ReportDTO getGRCReport(JwtUser paramJwtUser, FilterData paramFilterData) throws JCoException, AuditBotAuthenticationException;
  
  
  Map<String,List<Map<String, Object>>> getFilterResultTableDataMultiple(JwtUser paramJwtUser, FilterData paramFilterData) throws JCoException;
}

