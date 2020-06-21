 package com.sap.audit.bot.controller;
 
 import com.sap.audit.bot.dao.DestinationSource;
import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.FilterData;
 import com.sap.audit.bot.model.JwtUser;
 import com.sap.audit.bot.model.ReportDTO;
 import com.sap.audit.bot.security.JwtValidator;
 import com.sap.audit.bot.service.FunctionService;
 import com.sap.conn.jco.JCoException;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.CrossOrigin;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestHeader;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;
 
 
 
 
 
 @RestController
 @RequestMapping({"/api"})
 public class DataController
 {
   @Autowired
   private JwtValidator jwtValidator;
   @Autowired
   private FunctionService functionService;
   
	  @Autowired
	  private DestinationSource destinationSource;

   
   @GetMapping({"/filter"})
   @CrossOrigin
   public List<Map<String, Object>> getAttributes(@RequestHeader("Authorisation") String authorisation) throws JCoException, AuditBotAuthenticationException {
     JwtUser user = this.jwtValidator.validate(authorisation);
     if(destinationSource.getDestinationByUser(user)==null) {
    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
     }
     List<Map<String, Object>> filter = this.functionService.getFilterTableData(user);
     return filter;
   }
   
   @PostMapping({"/JAVA_0002"})
   @CrossOrigin
   public ReportDTO getFilteredData(@RequestHeader("Authorisation") String authorisation, @RequestBody FilterData data) throws JCoException, AuditBotAuthenticationException {
     JwtUser user = this.jwtValidator.validate(authorisation);
     if(destinationSource.getDestinationByUser(user)==null) {
      	throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
      }
     List<Map<String, Object>> result = this.functionService.getFilterResultTableData(user, data);
     List<Object> header = new ArrayList(((Map)result.get(0)).keySet());
     ReportDTO filter = new ReportDTO();
     filter.setData(result);
     filter.setHeader(header);
     return filter;
   }
 
   
   @PostMapping({"/JAVA_0002N"})
   @CrossOrigin
   public Map<String,ReportDTO> getFilteredDataMultiple(@RequestHeader("Authorisation") String authorisation, @RequestBody FilterData data) throws JCoException, AuditBotAuthenticationException {
     JwtUser user = this.jwtValidator.validate(authorisation);
     if(destinationSource.getDestinationByUser(user)==null) {
    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
      }
     Map<String, List<Map<String, Object>>> result = this.functionService.getFilterResultTableDataMultiple(user, data);
     
     Map<String,ReportDTO> results=new HashMap<String, ReportDTO>();
     
     result.forEach((k,v)->{
    	  List<Object> header = new ArrayList(((Map)v.get(0)).keySet());
    	     ReportDTO filter = new ReportDTO();
    	     filter.setData(v);
    	     filter.setHeader(header);
    	     results.put(k, filter);
     });
     
   
     return results;
   }
 
 
 
 
 
 
   
   @GetMapping({"/JAVA_0003"})
   @CrossOrigin
   public ReportDTO getUserRiskReport(@RequestHeader("Authorisation") String authorisation, @RequestParam(value = "system", required = false) String system, @RequestParam(value = "client", required = false) String client, @RequestParam(value = "level", required = false) String level, @RequestParam(value = "risktype", required = false) String riskType, @RequestParam(value = "risklevel", required = false) String risklevel, @RequestParam(value = "appclass", required = false) String appclass, @RequestParam(value = "risk", required = false) String risk, @RequestParam(value = "user", required = false) String user, @RequestParam(value = "role", required = false) String role) throws JCoException, AuditBotAuthenticationException {
     JwtUser loginUser = this.jwtValidator.validate(authorisation);
     if(destinationSource.getDestinationByUser(loginUser)==null) {
    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
      }
     ReportDTO filter = this.functionService.getRiskDetailReport(loginUser, system, client, level, riskType, risklevel, appclass, risk, user, role);
     return filter;
   }
 
 
 
 
   
   @PostMapping({"/JAVA_MUL_0003"})
   @CrossOrigin
   public ReportDTO getUserRiskReport(@RequestHeader("Authorisation") String authorisation, @RequestBody FilterData data) throws JCoException, AuditBotAuthenticationException {
     JwtUser loginUser = this.jwtValidator.validate(authorisation);
     if(destinationSource.getDestinationByUser(loginUser)==null) {
    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
      }
     ReportDTO filter = this.functionService.getGRCReport(loginUser, data);
     return filter;
   }
   
   
   @PostMapping({"/JAVA_MUL_0004"})
   @CrossOrigin
   public ReportDTO getUserRiskTechReport(@RequestHeader("Authorisation") String authorisation, @RequestBody FilterData data) throws JCoException, AuditBotAuthenticationException {
     JwtUser loginUser = this.jwtValidator.validate(authorisation);
     if(destinationSource.getDestinationByUser(loginUser)==null) {
    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
      }
     ReportDTO filter = this.functionService.getGRCRiskTechReport(loginUser, data);
     return filter;
   }
 }


