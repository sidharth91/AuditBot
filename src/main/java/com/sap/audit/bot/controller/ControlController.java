package com.sap.audit.bot.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.sap.audit.bot.dao.DestinationSource;
import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.ControlFilterDTO;
import com.sap.audit.bot.model.JwtUser;
import com.sap.audit.bot.model.LicenceFilterDTO;
import com.sap.audit.bot.model.ReportDTO;
import com.sap.audit.bot.security.JwtValidator;
import com.sap.audit.bot.service.FunctionService;
import com.sap.conn.jco.JCoException;

@RestController
@RequestMapping({ "/api" })
public class ControlController {

	@Autowired
	private JwtValidator jwtValidator;
	@Autowired
	private FunctionService functionService;

	@Autowired
	private DestinationSource destinationSource;

	@GetMapping({ "/controlsfilter" })
	@CrossOrigin
	public List<Map<String, Object>> getAttributes(@RequestHeader("Authorisation") String authorisation)
			throws JCoException, AuditBotAuthenticationException {
		JwtUser user = this.jwtValidator.validate(authorisation);
		if (destinationSource.getDestinationByUser(user) == null) {
			throw new AuditBotAuthenticationException("Authentication failed", "Authentication failed");
		}
		List<Map<String, Object>> filter = this.functionService.getControlsFilterTableData(user);
		return filter;
	}
	
	
	   @PostMapping({"/JAVA_0007"})
	   @CrossOrigin
	   public Map<String,ReportDTO> getFilteredDataMultiple(@RequestHeader("Authorisation") String authorisation, @RequestBody ControlFilterDTO data) throws JCoException, AuditBotAuthenticationException {
	     JwtUser user = this.jwtValidator.validate(authorisation);
	     if(destinationSource.getDestinationByUser(user)==null) {
	    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
	      }
	     Map<String, List<Map<String, Object>>> result = this.functionService.getControlFilterResultTableDataMultiple(user, data);
	     
	     Map<String,ReportDTO> results=new HashMap<String, ReportDTO>();
	     
	     result.forEach((k,v)->{
	         ReportDTO filter = new ReportDTO();
    	     filter.setData(v);
    	     results.put(k, filter);
	    	  if(v.size()>0) {
	    	     List<Object> header = new ArrayList(((Map)v.get(0)).keySet());
	    	     filter.setHeader(header);
	    	  }
	    	
	     });
	     
	   
	     return results;
	   }
	   
	   
	   @PostMapping({"/JAVA_0009"})
	   @CrossOrigin
	   public ReportDTO getUserRiskReport(@RequestHeader("Authorisation") String authorisation, @RequestBody ControlFilterDTO data) throws JCoException, AuditBotAuthenticationException {
	     JwtUser loginUser = this.jwtValidator.validate(authorisation);
	     if(destinationSource.getDestinationByUser(loginUser)==null) {
	    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
	      }
	     ReportDTO filter = this.functionService.getControlReport(loginUser, data);
	     return filter;
	   }

	   @PostMapping({"/JAVA_0008"})
	   @CrossOrigin
	   public ReportDTO getUserSummaryControlReport(@RequestHeader("Authorisation") String authorisation, @RequestBody ControlFilterDTO data) throws JCoException, AuditBotAuthenticationException {
	     JwtUser loginUser = this.jwtValidator.validate(authorisation);
	     if(destinationSource.getDestinationByUser(loginUser)==null) {
	    	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
	      }
	     ReportDTO filter = this.functionService.getSummaryControlReport(loginUser, data);
	     return filter;
	   }

}