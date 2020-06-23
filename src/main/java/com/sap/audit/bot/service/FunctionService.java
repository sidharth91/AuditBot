package com.sap.audit.bot.service;

import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.ControlFilterDTO;
import com.sap.audit.bot.model.FilterData;
import com.sap.audit.bot.model.JwtUser;
import com.sap.audit.bot.model.LicenceFilterDTO;
import com.sap.audit.bot.model.ReportDTO;
import com.sap.conn.jco.JCoException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface FunctionService {
  List<Map<String, Object>> getFilterTableData(JwtUser paramJwtUser) ;
  
  List<Map<String, Object>> getFilterResultTableData(JwtUser paramJwtUser, FilterData paramFilterData) throws JCoException;
  
  ReportDTO getRiskDetailReport(JwtUser jwtUser, String system, String client, String level, String riskType, String risklevel, String appclass, String risk, String user, String role) throws JCoException;
  
  ReportDTO getGRCReport(JwtUser paramJwtUser, FilterData paramFilterData) throws JCoException ;
  
  
  Map<String,List<Map<String, Object>>> getFilterResultTableDataMultiple(JwtUser paramJwtUser, FilterData paramFilterData) throws JCoException;
  
  
  Map<String,List<Map<String, Object>>> getLicenceFilterResultTableDataMultiple(JwtUser paramJwtUser, LicenceFilterDTO paramFilterData) throws JCoException;
  
  List<Map<String, Object>> gelicensetFilterTableData(JwtUser paramJwtUser) ;

ReportDTO getLicenceReport(JwtUser loginUser, LicenceFilterDTO data)throws JCoException ;

List<Map<String, Object>> getSidebarTableData(JwtUser user);

ReportDTO getGRCRiskTechReport(JwtUser loginUser, FilterData data) throws JCoException ;


List<Map<String, Object>> getControlsFilterTableData(JwtUser paramJwtUser) ;
Map<String,List<Map<String, Object>>> getControlFilterResultTableDataMultiple(JwtUser paramJwtUser, ControlFilterDTO paramFilterData) throws JCoException;
ReportDTO getControlReport(JwtUser loginUser, ControlFilterDTO data)throws JCoException ;
}

