 package com.sap.audit.bot.service;
 
 import com.sap.audit.bot.config.AuditBotConstants;
 import com.sap.audit.bot.dao.FunctionDao;
 import com.sap.audit.bot.helper.SapObjectToJavaConversion;
 import com.sap.audit.bot.model.FilterData;
 import com.sap.audit.bot.model.JwtUser;
 import com.sap.audit.bot.model.ReportDTO;
 import com.sap.conn.jco.JCoException;
 import com.sap.conn.jco.JCoTable;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.stream.Collectors;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 @Component
 public class FunctionServiceImpl
   implements FunctionService
 {
   @Autowired
   private FunctionDao functionDao;
   
   public List<Map<String, Object>> getFilterTableData(JwtUser user) {
     List<Map<String, Object>> filters = new ArrayList<>();
     Arrays.<AuditBotConstants.FilterTableMapping>asList(AuditBotConstants.FilterTableMapping.values()).forEach(param -> {
           try {
             JCoTable table = this.functionDao.getTableByFunctionModule(user, "/BOT/JAVA_0001", param.getValueString(), param.getTableNum(), param.getTableName());
             List<Map<String, Object>> list = SapObjectToJavaConversion.getTableParameter(table);
             Map<String, Object> map = new HashMap<>();
             map.put("name", param.getTableNamealias());
             map.put("value", list);
             map.put("id", Integer.valueOf(param.getTableNum()));
             filters.add(map);
           } catch (JCoException e) {
             e.printStackTrace();
           } 
         });
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     
     return filters;
   }
 
 
   
   public List<Map<String, Object>> getFilterResultTableData(JwtUser jwtUser, FilterData data) throws JCoException {
     JCoTable table = this.functionDao.getTableByFunctionModule(jwtUser, "/BOT/JAVA_0002", data);
     List<Map<String, Object>> list = SapObjectToJavaConversion.getTableParameter(table);
     return list;
   }
   
   
   
   public Map<String,List<Map<String, Object>>> getFilterResultTableDataMultiple(JwtUser jwtUser, FilterData data) throws JCoException {
	   	 Map<String,JCoTable> tables = this.functionDao.getTableByFunctionModuleMultiple(jwtUser, "/BOT/JAVA_0002N", data);
	   	 
	   	 Map<String,List<Map<String, Object>>> tableMap=new HashMap<String, List<Map<String,Object>>>();
	   	 
	   	 tables.forEach((k,v)->{
	   		tableMap.put(k,  SapObjectToJavaConversion.getTableParameter(v));
	   	 });
	   	 
	    
	     return tableMap;
	   }
	   
   
   

 
 
   
   public ReportDTO getRiskDetailReport(JwtUser jwtUser, String system, String client, String level, String riskType, String risklevel, String appclass, String risk, String user, String role) throws JCoException {
     Map<String, JCoTable> table = this.functionDao.getTableByFunctionModule(jwtUser, "/BOT/JAVA_0003N", system, client, level, riskType, risklevel, appclass, risk, user, role);
     List<Map<String, Object>> list = SapObjectToJavaConversion.getTableParameter(table.get("data"));
     List<Object> header = (List<Object>)SapObjectToJavaConversion.getTableParameter(table.get("header")).stream().map(p -> p.get("ZDESC")).collect(Collectors.toList());
     ReportDTO dto = new ReportDTO();
     dto.setData(list);
     dto.setHeader(header);
     return dto;
   }
 
 
   
   public ReportDTO getGRCReport(JwtUser loginUser, FilterData data) throws JCoException {
     Map<String, JCoTable> table = this.functionDao.getGRCTableByFunctionModule(loginUser, "/BOT/JAVA_0003N", data);
     List<Map<String, Object>> list = SapObjectToJavaConversion.getTableParameter(table.get("data"));
     List<Object> header = (List<Object>)SapObjectToJavaConversion.getTableParameter(table.get("header")).stream().map(p -> p.get("ZDESC")).collect(Collectors.toList());
     ReportDTO dto = new ReportDTO();
     dto.setData(list);
     dto.setHeader(header);
     return dto;
   }
 }
