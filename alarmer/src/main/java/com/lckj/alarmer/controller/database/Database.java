package com.lckj.alarmer.controller.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.lckj.alarmer.controller.util.*;
import com.lckj.alarmer.controller.ApiController;

public class Database {

    Connection con;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/rule";
    String user = "root";
    String password = "wangshihao";



//    public void QueryAndUpdateID() throws ClassNotFoundException, SQLException {
//        Class.forName(driver);
//        con = DriverManager.getConnection(url, user, password);
//        String sql = "select * from rule";
//        PreparedStatement stmt = con.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery();
//        List list = new ArrayList();
//        while (rs.next()) {
//            AlarmRule rule = new AlarmRule();
//            rule.ruleName = rs.getString("ruleName");
//            rule.target = rs.getString("target");
//            rule.valueType = rs.getString("valueType");
//            rule.compare = rs.getString("compare");
//            rule.threshold = rs.getDouble("threshold");
//            rule.count = rs.getInt("count");
//            rule.r_target = rs.getString("r_target");
//            rule.r_valueType = rs.getString("r_valueType");
//            rule.r_compare = rs.getString("r_compare");
//            rule.r_threshold = rs.getDouble("r_threshold");
//            rule.r_count = rs.getInt("r_count");
//            rule.ruleId = rs.getString("ruleId");;
//            String temp = rule.ruleId;
//
//            String update_ruleID = rule.setRuleId(rule.ruleName, rule.target,rule.valueType,rule.compare, rule.threshold,rule.count,rule.r_target,rule.r_valueType,rule.r_compare,rule.r_threshold,rule.r_count);
//            String sql_update_ID_templet = "update rule set ruleID = '%s' where ruleID = '%s'";
//            String sql_update = String.format(sql_update_ID_templet,update_ruleID,temp);
//            database_ConnectionAndExec(sql_update);
//
//
//        }
//    }

    public void database_ConnectionAndExec(String sql){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            Statement statement = con.createStatement();
            //String sql_templet = "INSERT INTO rule (ruleName,target,valueType,compare,threshold,count,r_target,r_valueType,r_compare,r_threshold,r_count,ruleID) VALUES ('%s','%s','%s','%s',%f,%d,'%s','%s','%s',%f,%d,'%s')";
            //String sql = String.format(sql_templet, ruleName, target, valueType, compare, threshold, count, r_target, r_valueType, r_compare, r_threshold, r_count, ruleId);
            System.out.println(sql);
//               String sql = "select * from rule;";//表格叫rule
            boolean result = statement.execute(sql);
//               String rule    Name;
//               while (resultSet.next()) {
//                   ruleName = resultSet.getString("compare");
//                   System.out.println("ruleName：" + ruleName);
//                }
//                result.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            System.err.println("SQLState:" + ((SQLException) e).getSQLState());
            System.err.println("Error Code:" + ((SQLException) e).getErrorCode());
            System.err.println("Message:" + e.getMessage());
            System.out.println("数据库连接失败");
        }
    }

//   public String update_ruleID(String ruleName, String target, String valueType, String compare, double threshold, int count, String _target, String _valueType, String _compare, double _threshold, int _count){
//        String ruleId = Encryption.createPassword( ruleName + target + valueType + compare + threshold + count + _target + _valueType + _compare +_threshold + _count);
//        AlarmRule.setRuleId(ruleId);
//        return ruleId;
//    }

    public void insert_work(String ruleName, String target, String valueType, String compare, double threshold, int count, String r_target, String r_valueType, String r_compare, double r_threshold, int r_count, String ruleId) throws SQLException, ClassNotFoundException {
        String sql_templet = "INSERT INTO rule (ruleName,target,valueType,compare,threshold,count,r_target,r_valueType,r_compare,r_threshold,r_count,ruleID) VALUES ('%s','%s','%s','%s',%f,%d,'%s','%s','%s',%f,%d,'%s')";
        String sql = String.format(sql_templet, ruleName, target, valueType, compare, threshold, count, r_target, r_valueType, r_compare, r_threshold, r_count, ruleId);
        database_ConnectionAndExec(sql);
    }

//    public void update_work(String set_field, int set_item,String where_field,int where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = %d where %s = %d";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
//
//    }
//
//    public void update_work(String set_field, int set_item,String where_field,String where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = %d where %s = '%s'";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
//
//    }
//
//    public void update_work(String set_field, int set_item,String where_field,double where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = %d where %s = %f";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
//
//    }
//
//    public void update_work(String set_field, String set_item,String where_field,int where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = '%s' where %s = %d";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
//
//    }
//
//    public void update_work(String set_field, String set_item,String where_field,String where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = '%s' where %s = '%s'";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
//    }
//
//    public void update_work(String set_field, String set_item,String where_field,double where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = '%s' where %s = %f";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
////        QueryAndUpdateID();
//    }
//
//    public void update_work(String set_field, double set_item,String where_field,int where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = %f where %s = %d";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
////        QueryAndUpdateID();
//    }
//
//    public void update_work(String set_field, double set_item,String where_field,String where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = %f where %s = '%s'";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
////        QueryAndUpdateID();
//    }
//
//    public void update_work(String set_field, double set_item,String where_field,double  where_item) throws SQLException, ClassNotFoundException {
//        String sql_templet = "update rule set %s = %f where %s = %f";
//        String sql = String.format(sql_templet,set_field,set_item,where_field,where_item);
//        database_ConnectionAndExec(sql);
////        QueryAndUpdateID();
//    }
}

