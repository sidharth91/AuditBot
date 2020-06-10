package com.sap.audit.bot.dao;

import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.FilterData;
import com.sap.audit.bot.model.JwtUser;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



@Component
public class FunctionDaoImpl
  implements FunctionDao
{
  @Autowired
  private DestinationSource destinationSource;
  
  public JCoTable getTableByFunctionModule(JwtUser user, String functionName, String valueString, int value, String tableName) throws JCoException {
    JCoDestination destination = this.destinationSource.getDestinationByUser(user);
    JCoRepository repo = destination.getRepository();
    JCoFunction function = repo.getFunction(functionName);
    if (function == null)
      throw new RuntimeException(functionName + "not found in SAP."); 
    function.getImportParameterList().setValue(valueString, value);
    
    try {
      function.execute(destination);
    }
    catch (AbapException e) {
      
      throw new RuntimeException("not able to execute function");
    } 
    
    JCoTable table = function.getTableParameterList().getTable(tableName);
    
    return table;
  }

  
  public JCoTable getTableByFunctionModule(JwtUser user, String functionName, FilterData data) throws JCoException {
    JCoDestination destination = this.destinationSource.getDestinationByUser(user);
    JCoRepository repo = destination.getRepository();
    JCoFunction function = repo.getFunction(functionName);
    if (function == null)
      throw new RuntimeException(functionName + "not found in SAP."); 
    if (!StringUtils.isEmpty(data.getSapSystem()))
      function.getImportParameterList().setValue("I_SYS", data.getSapSystem()); 
    if (!StringUtils.isEmpty(data.getClient()))
      function.getImportParameterList().setValue("I_CLT", data.getClient()); 
    if (!StringUtils.isEmpty(Integer.valueOf(data.getLevel()))) {
      function.getImportParameterList().setValue("I_LEVEL", data.getLevel());
    }
    function.getImportParameterList().setValue("I_GROUPBY1", "1");
    function.getImportParameterList().setValue("I_GROUPBY2", "2");
    function.getImportParameterList().setValue("I_GROUPBY3", "3");

    
    if (data.getRiskType() != null && data.getRiskType().size() > 0) {
      JCoTable risktype = function.getTableParameterList().getTable("I_RISKTYPE");
      for (String riskType : data.getRiskType()) {
        risktype.appendRow();
        risktype.setValue("ZFIELD", riskType);
      } 
    } 
    
    if (data.getRiskType() != null && data.getRiskLevel().size() > 0) {
      JCoTable risklevel = function.getTableParameterList().getTable("I_RISKLEVEL");
      for (String riskLevel : data.getRiskLevel()) {
        risklevel.appendRow();
        risklevel.setValue("ZFIELD", riskLevel);
      } 
    } 
    
    if (data.getRiskType() != null && data.getBusinessModule().size() > 0) {
      JCoTable busTable = function.getTableParameterList().getTable("I_APPCLASS");
      for (String busMod : data.getBusinessModule()) {
        busTable.appendRow();
        busTable.setValue("ZFIELD", busMod);
      } 
    } 

    
    try {
      function.execute(destination);
    }
    catch (AbapException e) {
      
      throw new RuntimeException("not able to execute function");
    } 
    
    JCoTable table = function.getTableParameterList().getTable("E_RESULT");
    return table;
  }


  
  public Map<String, JCoTable> getTableByFunctionModule(JwtUser jwtUser, String functionName, String system, String client, String level, String riskType, String risklevel, String appclass, String risk, String user, String role) throws JCoException {
    Map<String, JCoTable> map = new LinkedHashMap<>();
    JCoDestination destination = this.destinationSource.getDestinationByUser(jwtUser);
    JCoRepository repo = destination.getRepository();
    JCoFunction function = repo.getFunction(functionName);
    if (function == null)
      throw new RuntimeException(functionName + "not found in SAP."); 
    if (!StringUtils.isEmpty(system))
      function.getImportParameterList().setValue("I_SYS", system); 
    if (!StringUtils.isEmpty(client))
      function.getImportParameterList().setValue("I_CLT", client); 
    if (!StringUtils.isEmpty(level)) {
      function.getImportParameterList().setValue("I_LEVEL", level);
    }

    
    if (!StringUtils.isEmpty(riskType)) {
      JCoTable risktypeTable = function.getTableParameterList().getTable("I_RISKTYPE");
      risktypeTable.appendRow();
      risktypeTable.setValue("ZFIELD", riskType);
    } 
    
    if (!StringUtils.isEmpty(risklevel)) {
      JCoTable risklevelTable = function.getTableParameterList().getTable("I_RISKLEVEL");
      risklevelTable.appendRow();
      risklevelTable.setValue("ZFIELD", risklevel);
    } 
    if (!StringUtils.isEmpty(appclass)) {
      JCoTable busTable = function.getTableParameterList().getTable("I_APPCLASS");
      busTable.appendRow();
      busTable.setValue("ZFIELD", appclass);
    } 

    
    try {
      function.execute(destination);
    }
    catch (AbapException e) {
      
      throw new RuntimeException("not able to execute function");
    } 
    
    map.put("header", function.getTableParameterList().getTable("E_HEADER"));
    if (Integer.parseInt(level) == 1) {
      JCoTable table = function.getTableParameterList().getTable("E_RESULT");
      map.put("data", table);
    } else {
      JCoTable table = function.getTableParameterList().getTable("E_RESULT01");
      map.put("data", table);
    } 
    
    return map;
  }

  
  public Map<String, JCoTable> getGRCTableByFunctionModule(JwtUser loginUser, String functionName, FilterData data) throws JCoException {
    Map<String, JCoTable> map = new LinkedHashMap<>();
    JCoDestination destination = this.destinationSource.getDestinationByUser(loginUser);
    JCoRepository repo = destination.getRepository();
    JCoFunction function = repo.getFunction(functionName);
    if (function == null)
      throw new RuntimeException(functionName + "not found in SAP."); 
    if (!StringUtils.isEmpty(data.getSapSystem()))
      function.getImportParameterList().setValue("I_SYS", data.getSapSystem()); 
    if (!StringUtils.isEmpty(data.getClient()))
      function.getImportParameterList().setValue("I_CLT", data.getClient()); 
    if (!StringUtils.isEmpty(Integer.valueOf(data.getLevel()))) {
      function.getImportParameterList().setValue("I_LEVEL", data.getLevel());
      if (!StringUtils.isEmpty(data.getUserInput())) {
        JCoTable userinput = null;
        if (data.getLevel() == 1) {
          userinput = function.getTableParameterList().getTable("I_USER");
        } else {
          userinput = function.getTableParameterList().getTable("I_ROLE");
        } 
        userinput.appendRow();
        userinput.setValue("ZFIELD", data.getUserInput());
      } 
    } 

    if (!StringUtils.isEmpty(data.getMitigation())) {
	      function.getImportParameterList().setValue("I_MIT", data.getMitigation());
	    } 
    
    if (!StringUtils.isEmpty(data.getDrillDown())) {
	      function.getImportParameterList().setValue("I_DRILL", data.getDrillDown());
	    } 






    
    if (data.getRiskType() != null && data.getRiskType().size() > 0) {
      JCoTable risktype = function.getTableParameterList().getTable("I_RISKTYPE");
      for (String riskType : data.getRiskType()) {
        risktype.appendRow();
        risktype.setValue("ZFIELD", riskType);
      } 
    } 
    
    if (data.getRiskType() != null && data.getRiskLevel().size() > 0) {
      JCoTable risklevel = function.getTableParameterList().getTable("I_RISKLEVEL");
      for (String riskLevel : data.getRiskLevel()) {
        risklevel.appendRow();
        risklevel.setValue("ZFIELD", riskLevel);
      } 
    } 
    
    if (data.getRiskType() != null && data.getBusinessModule().size() > 0) {
      JCoTable busTable = function.getTableParameterList().getTable("I_APPCLASS");
      for (String busMod : data.getBusinessModule()) {
        busTable.appendRow();
        busTable.setValue("ZFIELD", busMod);
      } 
    } 
    
    if (data.getRiskId() != null && data.getRiskId().size() > 0) {
      JCoTable busTable = function.getTableParameterList().getTable("I_RISK");
      for (String risk : data.getRiskId()) {
        busTable.appendRow();
        busTable.setValue("ZFIELD", risk);
      } 
    } 

    
    try {
      function.execute(destination);
    }
    catch (AbapException e) {
      
      throw new RuntimeException("not able to execute function");
    } 
    map.put("header", function.getTableParameterList().getTable("E_HEADER"));
    if (data.getLevel() == 1) {
      JCoTable table = function.getTableParameterList().getTable("E_RESULT");
      map.put("data", table);
    } else {
      JCoTable table = function.getTableParameterList().getTable("E_RESULT01");
      map.put("data", table);
    } 
    
    return map;
  }


@Override
public Map<String,JCoTable> getTableByFunctionModuleMultiple(JwtUser user, String functionName, FilterData data)
		throws JCoException {
	 JCoDestination destination = this.destinationSource.getDestinationByUser(user);
	    JCoRepository repo = destination.getRepository();
	    JCoFunction function = repo.getFunction(functionName);
	    if (function == null)
	      throw new RuntimeException(functionName + "not found in SAP."); 
	    if (!StringUtils.isEmpty(data.getSapSystem()))
	      function.getImportParameterList().setValue("I_SYS", data.getSapSystem()); 
	    if (!StringUtils.isEmpty(data.getClient()))
	      function.getImportParameterList().setValue("I_CLT", data.getClient()); 
	    if (!StringUtils.isEmpty(Integer.valueOf(data.getLevel()))) {
	      function.getImportParameterList().setValue("I_LEVEL", data.getLevel());
	    }
	    
	    if (!StringUtils.isEmpty(data.getReportType())) {
		      function.getImportParameterList().setValue("I_RPTYPE", data.getReportType());
		    }
	    if (!StringUtils.isEmpty(data.getMitigation())) {
		      function.getImportParameterList().setValue("I_MIT", data.getMitigation());
		    } 
	 
	   // function.getImportParameterList().setValue("I_GROUPBY1", "1");
	    //function.getImportParameterList().setValue("I_GROUPBY2", "2");
	   // function.getImportParameterList().setValue("I_GROUPBY3", "3");

	    
	    if (data.getRiskType() != null && data.getRiskType().size() > 0) {
	      JCoTable risktype = function.getTableParameterList().getTable("I_RISKTYPE");
	      for (String riskType : data.getRiskType()) {
	        risktype.appendRow();
	        risktype.setValue("ZFIELD", riskType);
	      } 
	    } 
	    
	    if (data.getRiskLevel() != null && data.getRiskLevel().size() > 0) {
	      JCoTable risklevel = function.getTableParameterList().getTable("I_RISKLEVEL");
	      for (String riskLevel : data.getRiskLevel()) {
	        risklevel.appendRow();
	        risklevel.setValue("ZFIELD", riskLevel);
	      } 
	    } 
	    
	    if (data.getBusinessModule() != null && data.getBusinessModule().size() > 0) {
	      JCoTable busTable = function.getTableParameterList().getTable("I_APPCLASS");
	      for (String busMod : data.getBusinessModule()) {
	        busTable.appendRow();
	        busTable.setValue("ZFIELD", busMod);
	      } 
	    } 
	    
	    if (data.getRiskId() != null && data.getRiskId().size() > 0) {
		      JCoTable busTable = function.getTableParameterList().getTable("I_RISK");
		      for (String riskId : data.getRiskId()) {
		        busTable.appendRow();
		        busTable.setValue("ZFIELD", riskId);
		      } 
		    } 

	    
	    try {
	      function.execute(destination);
	    }
	    catch (AbapException e) {
	      
	      throw new RuntimeException("not able to execute function");
	    } 
	    Map<String,JCoTable> tables=new HashMap<String, JCoTable>();
	    
	    JCoTable table1 = function.getTableParameterList().getTable("E_RESULT_01");
	    tables.put("E_RESULT_01",table1);
	    JCoTable table2 = function.getTableParameterList().getTable("E_RESULT_02");
	    tables.put("E_RESULT_02",table2);
	    JCoTable table3 = function.getTableParameterList().getTable("E_RESULT_03");
	    tables.put("E_RESULT_03",table3);
	    return tables;
}
}
