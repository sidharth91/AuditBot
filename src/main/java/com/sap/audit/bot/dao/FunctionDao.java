package com.sap.audit.bot.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.ControlFilterDTO;
import com.sap.audit.bot.model.FilterData;
import com.sap.audit.bot.model.JwtUser;
import com.sap.audit.bot.model.LicenceFilterDTO;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoTable;

@Component
public interface FunctionDao {
  JCoTable getTableByFunctionModule(JwtUser paramJwtUser, String paramString1, String paramString2, int paramInt, String paramString3) throws JCoException;
  
  JCoTable getTableByFunctionModule(JwtUser paramJwtUser, String paramString, FilterData paramFilterData) throws JCoException;
  
  Map<String, JCoTable> getTableByFunctionModule(JwtUser jwtUser, String functionName, String system, String client, String level, String riskType, String risklevel, String appclass, String risk, String user, String role) throws JCoException;
  
  Map<String, JCoTable> getGRCTableByFunctionModule(JwtUser paramJwtUser, String paramString, FilterData paramFilterData) throws JCoException;
  
  Map<String,JCoTable> getTableByFunctionModuleMultiple(JwtUser paramJwtUser, String paramString, FilterData paramFilterData) throws JCoException;

Map<String, JCoTable> getLicenceTableByFunctionModuleMultiple(JwtUser jwtUser, String paramString,LicenceFilterDTO paramFilterData) throws JCoException;

Map<String, JCoTable> getLicenceTableByFunctionModule(JwtUser loginUser, String string, LicenceFilterDTO data)throws JCoException;

Map<String, JCoTable> getGRCRiskTechTableByFunctionModule(JwtUser loginUser, String string, FilterData data)throws JCoException;

Map<String, JCoTable> getControlByFunctionModuleMultiple(JwtUser jwtUser, String paramString,ControlFilterDTO paramFilterData) throws JCoException;

Map<String, JCoTable> getControlTableByFunctionModule(JwtUser loginUser, String string, ControlFilterDTO data)throws JCoException;
}
