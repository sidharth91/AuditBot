 package com.sap.audit.bot.helper;
 
 import com.sap.conn.jco.JCoField;
 import com.sap.conn.jco.JCoFieldIterator;
 import com.sap.conn.jco.JCoTable;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 
 
 
 public class SapObjectToJavaConversion
 {
   public static List<Map<String, Object>> getTableParameter(JCoTable table) {
     List<Map<String, Object>> list = new ArrayList<>();
     for (int i = 0; i < table.getNumRows(); i++) {
       
       table.setRow(i);
       JCoFieldIterator iter = table.getFieldIterator();
       Map<String, Object> map = new LinkedHashMap<>();
       while (iter.hasNextField()) {
         
         JCoField f = iter.nextField();
         map.put(f.getName(), table.getValue(f.getName()));
       } 
       list.add(map);
     } 
     return list;
   }
   
   
   public static List<Map<String, Object>>  getTableParameterForLicence(JCoTable table) {
	     List<Map<String, Object>> list = new ArrayList<>();
	     for (int i = 0; i < table.getNumRows(); i++) {
	       
	       table.setRow(i);
	       JCoFieldIterator iter = table.getFieldIterator();
	       Map<String, Object> map = new LinkedHashMap<>();
	       while (iter.hasNextField()) {
	         
	         JCoField f = iter.nextField();
	         map.put(f.getName(), table.getString(f.getName()));
	       } 
	       list.add(map);
	     } 
	     return list;
	   }
 }

