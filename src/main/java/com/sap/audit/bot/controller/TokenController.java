package com.sap.audit.bot.controller;

import com.sap.audit.bot.config.DestinationConfig;
import com.sap.audit.bot.dao.DestinationSource;
import com.sap.audit.bot.exception.AuditBotAuthenticationException;
import com.sap.audit.bot.model.AuthDTO;
import com.sap.audit.bot.model.JwtUser;
import com.sap.audit.bot.model.LoginDefaultData;
import com.sap.audit.bot.security.JwtGenerator;
import com.sap.audit.bot.security.JwtValidator;
import com.sap.audit.bot.service.FunctionService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;







@RestController
@RequestMapping({"/token"})
public class TokenController
{
  @Autowired
  private JwtGenerator jwtGenerator;
  @Autowired
  private DestinationSource destinationSource;
  @Autowired
  private DestinationConfig destinationConfig;
  static String DESTINATION_NAME1 = "ABAP_AS_WITHOUT_POOL";
  @Autowired
  private JwtValidator jwtValidator;
  
  @Autowired
  private FunctionService functionService;
  
  
  @PostMapping
  @CrossOrigin
  public AuthDTO generate(@RequestBody JwtUser jwtUser) throws Exception {
    createProperties(jwtUser.getUserName(), jwtUser.getPassword(), jwtUser.getHost(), jwtUser.getSystem(), jwtUser.getClient(),jwtUser.getInstance());
    try {
      JCoDestination destination = JCoDestinationManager.getDestination(DESTINATION_NAME1);
      destination.getAttributes();
      this.destinationSource.setDestinationByUser(jwtUser, destination);
      AuthDTO dto = new AuthDTO();
      dto.setToken(this.jwtGenerator.generate(jwtUser));
      dto.setUserName(jwtUser.getUserName());
      return dto;
    } catch (Exception e) {
      if (e instanceof RuntimeException) {
        throw new RuntimeException();
      }
      throw new AuditBotAuthenticationException("User is not Valid", e.getMessage());
    } 
  }

  @GetMapping({"/authorization"})
  @CrossOrigin
  public Map<String, Object> getAttributes(@RequestHeader("Authorisation") String authorisation) throws JCoException, AuditBotAuthenticationException {
    JwtUser user = this.jwtValidator.validate(authorisation);
    if(destinationSource.getDestinationByUser(user)==null) {
   	 throw new AuditBotAuthenticationException("Authentication failed","Authentication failed");
    }
    List<Map<String, Object>> filter = this.functionService.getSidebarTableData(user);
    return filter.get(0);
  }

  
  private void createProperties(String username, String password, String host, String system, String client,String instance) {
    Properties connectProperties = new Properties();
    connectProperties.setProperty("jco.client.ashost", host);
    connectProperties.setProperty("jco.client.sysnr", instance);
    connectProperties.setProperty("jco.client.client", client);
    connectProperties.setProperty("jco.client.user", username);
    connectProperties.setProperty("jco.client.passwd", password);
    connectProperties.setProperty("jco.client.lang", this.destinationConfig.getJCO_LANG());
    createDestinationDataFile(DESTINATION_NAME1, connectProperties);
  }
  
  private void createDestinationDataFile(String destinationName, Properties connectProperties) {
    File destCfg = new File(destinationName + ".jcoDestination");
    
    try {
      FileOutputStream fos = new FileOutputStream(destCfg, false);
      connectProperties.store(fos, "for tests only !");
      fos.close();
    }
    catch (Exception e) {
      
      throw new RuntimeException("Unable to create the destination files", e);
    } 
  }

  
  @GetMapping
  @CrossOrigin
  public Map<String, List<LoginDefaultData>> loginDefaultData() throws Exception {
    Map<String, List<LoginDefaultData>> map = new HashMap<>();
    BufferedReader br = null;
    String line = "";
    try {
      br = new BufferedReader(new FileReader(this.destinationConfig.getSystem_Config_Path()));
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] splits = line.split(",");
        LoginDefaultData data = new LoginDefaultData(splits[2], splits[0], splits[1], splits[3],splits[4]);
        List<LoginDefaultData> list = new ArrayList<>();
        if (map.containsKey(splits[0])) {
          list = map.get(splits[0]);
          list.add(data);
          map.put(splits[0], list); continue;
        } 
        list.add(data);
        map.put(splits[0], list);
      }
    
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return map;
  }
}
