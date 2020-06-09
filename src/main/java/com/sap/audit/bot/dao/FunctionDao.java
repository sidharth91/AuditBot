package com.sap.audit.bot.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.FilterData;
import com.sap.audit.bot.model.JwtUser;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoTable;

@Component
public interface FunctionDao {
  JCoTable getTableByFunctionModule(JwtUser paramJwtUser, String paramString1, String paramString2, int paramInt, String paramString3) throws JCoException;
  
  JCoTable getTableByFunctionModule(JwtUser paramJwtUser, String paramString, FilterData paramFilterData) throws JCoException;
  
  Map<String, JCoTable> getTableByFunctionModule(JwtUser jwtUser, String functionName, String system, String client, String level, String riskType, String risklevel, String appclass, String risk, String user, String role) throws JCoException, AuditBotAuthenticationException;
  
  Map<String, JCoTable> getGRCTableByFunctionModule(JwtUser paramJwtUser, String paramString, FilterData paramFilterData) throws JCoException, AuditBotAuthenticationException;
  
  Map<String,JCoTable> getTableByFunctionModuleMultiple(JwtUser paramJwtUser, String paramString, FilterData paramFilterData) throws JCoException;
}
