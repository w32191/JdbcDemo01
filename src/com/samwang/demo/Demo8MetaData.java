package com.samwang.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Demo8MetaData {

  private Connection conn;


  public void createConnection() throws SQLException {
    String url = "jdbc:sqlserver://localhost:1433;database=JDBCDemoDB;user=sa;password=Passw0rd!";
    conn = DriverManager.getConnection(url);
    boolean status = !conn.isClosed();
    if (status) {
      System.out.println("Open Connector!!");
    }
  } //end of createConnection()

  public void closeConnection() throws SQLException {
    if (conn != null) {
      conn.close();
    }
  } //end of closeConnection()

  public void databaseMetaData() throws SQLException {
    DatabaseMetaData dbmd = conn.getMetaData();
    System.out.println(dbmd.getDatabaseProductName());
    System.out.println(dbmd.getDatabaseMajorVersion());
    System.out.println(dbmd.getDriverName());
    System.out.println(dbmd.getDriverVersion());
    System.out.println(dbmd.getURL());
    System.out.println(dbmd.getUserName());

  }//end of databaseMetaData();

  public void resultSetMetaDate() throws SQLException {
    String sql = "SELECT productid AS id,productname,productprice FROM product";
    PreparedStatement preState = conn.prepareStatement(sql);
    ResultSet rs = preState.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    System.out.println("getColumnCount(): " + rsmd.getColumnCount());
    System.out.println("getColumnName(1): " + rsmd.getColumnName(1));
    System.out.println("getColumnLabel(1): " + rsmd.getColumnLabel(1));
    System.out.println("getColumnName(1): " + rsmd.getColumnName(2));
    System.out.println("getColumnLabel(1): " + rsmd.getColumnLabel(2));
    System.out.println("getColumnTypeName(1): " + rsmd.getColumnTypeName(1));
    System.out.println("getColumnTypeName(2): " + rsmd.getColumnTypeName(2));
    System.out.println("getColumnDisplaySize(1): " + rsmd.getColumnDisplaySize(1));
    System.out.println("getColumnDisplaySize(2): " + rsmd.getColumnDisplaySize(2));

    rs.close();
  }

  public static void main(String[] args) {
    Demo8MetaData demo8MetaData = new Demo8MetaData();
    try {
      demo8MetaData.createConnection();
//      demo8MetaData.databaseMetaData();
      demo8MetaData.resultSetMetaDate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        demo8MetaData.closeConnection();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
