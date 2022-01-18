package com.samwang.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Demo6CallableStatement {

  private Connection conn;

  public void createConnection() throws SQLException {
    String url = "jdbc:sqlserver://localhost:1433;database=JDBCDemoDB;user=sa;password=Passw0rd!";
    conn = DriverManager.getConnection(url);
    boolean status = !conn.isClosed();
    if (status) {
      System.out.println("Open Connection!!");
    }
  } //end of createConnection()

  public void closeConnection() throws SQLException {
    if (conn != null) {
      conn.close();
    }
  } //end of closeConnection()

  public void callProcedure() throws SQLException {
    CallableStatement callState = conn.prepareCall("{call productProc(?,?)}");
    callState.setInt(1, 1003);
    callState.registerOutParameter(2, Types.VARCHAR);
    callState.execute();

    String productname = callState.getString(2);
    System.out.println("productname: " + productname);
    callState.close();
  }//end of callProcedure()


  public static void main(String[] args) {
    Demo6CallableStatement demo = new Demo6CallableStatement();
    try {
      demo.createConnection();
      demo.callProcedure();

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        demo.closeConnection();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
