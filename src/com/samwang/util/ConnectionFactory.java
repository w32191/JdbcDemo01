package com.samwang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private static final Connection conn = createConn();

  private static Connection createConn() {

    try {
      String url = "jdbc:sqlserver://localhost:1433;database=JDBCDemoDB";
      Connection conn = DriverManager.getConnection(url,"sa","Passw0rd!");
      boolean status = !conn.isClosed();
      if (status) {
        System.out.println("--Open Connection!!--");
      }
      return conn;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }//end of createConn()

  //getter
  public static Connection getConnection() {
    return conn;
  }//end of getConn()


  public static void closeConn() {
    try {
      if (conn != null) {
        System.out.println("--Connection Closed!!--");
        conn.close();
      }
    } catch (SQLException e) {
      System.out.println("--Close Error!!--");
      e.printStackTrace();
    }
  }//end of closeConn()

}
