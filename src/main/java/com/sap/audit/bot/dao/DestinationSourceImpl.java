 package com.sap.audit.bot.dao;
 
 import com.sap.audit.bot.model.JwtUser;
 import com.sap.conn.jco.JCoDestination;
 import java.util.HashMap;
 import java.util.Map;
 import org.springframework.stereotype.Component;
 
 @Component
 public class DestinationSourceImpl
   implements DestinationSource
 {
   private Map<String, Map<String, Map<String, JCoDestination>>> map = new HashMap<>();
 
   
   public JCoDestination getDestinationByUser(JwtUser jwtUser) {
     if (this.map.containsKey(jwtUser.getSystem())) {
       if (((Map)this.map.get(jwtUser.getSystem())).containsKey(jwtUser.getClient())) {
         if (((Map)((Map)this.map.get(jwtUser.getSystem())).get(jwtUser.getClient())).containsKey(jwtUser.getUserName())) {
           return (JCoDestination)((Map)((Map)this.map.get(jwtUser.getSystem())).get(jwtUser.getClient())).get(jwtUser.getUserName());
         }
       } else {
         
         return null;
       } 
     } else {
       return null;
     } 
     return null;
   }
 
   
   public void setDestinationByUser(JwtUser jwtUser, JCoDestination destination) {
     Map<String, JCoDestination> userDestination = new HashMap<>();
     userDestination.put(jwtUser.getUserName(), destination);
     Map<String, Map<String, JCoDestination>> clientMap = new HashMap<>();
     clientMap.put(jwtUser.getClient(), userDestination);
     this.map.put(jwtUser.getSystem(), clientMap);
   }
 }

