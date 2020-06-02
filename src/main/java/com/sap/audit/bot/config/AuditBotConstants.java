 package com.sap.audit.bot.config;
 
 import org.springframework.stereotype.Component;
 
 @Component
 public class AuditBotConstants {
   public enum FilterTableMapping {
     TABLE1("SAP System", "ZTYPE", 1, "ZRESULT"),
     TABLE2("Client", "ZTYPE", 2, "ZRESULT"),
     TABLE3("Risk Type", "ZTYPE", 3, "ZRESULT"),
     TABLE4("Risk Level", "ZTYPE", 4, "ZRESULT"),
     TABLE5("Business Module", "ZTYPE", 5, "ZRESULT"),
     TABLE6("Risk Id", "ZTYPE", 6, "ZRESULT"),
     TABLE7("Mitigation", "ZTYPE", 7, "ZRESULT"),
	 TABLE8("Report Type", "ZTYPE", 8, "ZRESULT"),
	 TABLE9("Drill Down", "ZTYPE", 9, "ZRESULT"),
	 TABLE51("COLORS", "ZTYPE", 51, "ZRESULT");;
	 
     
     private String tableNamealias;
     private String valueString;
     private int tableNum;
     private String tableName;
     
     FilterTableMapping(String tableNamealias, String valueString, int tableNum, String tableName) {
       this.tableName = tableName;
       this.tableNum = tableNum;
       this.tableNamealias = tableNamealias;
       this.valueString = valueString;
     }
     
     public String getTableName() {
       return this.tableName;
     }
     
     public int getTableNum() {
       return this.tableNum;
     }
     
     public String getTableNamealias() {
       return this.tableNamealias;
     }
     
     public String getValueString() {
       return this.valueString;
     }
   }
 }


