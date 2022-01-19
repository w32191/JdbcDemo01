package com.samwang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo1Connection {

  public static void main(String[] args) {
    try {
//      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;user=sa;password=Passw0rd!";
      String url2 = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB";
//      Connector conn = DriverManager.getConnection(url);
      Connection conn = DriverManager.getConnection(url2,"sa","Passw0rd!");

      boolean status = !conn.isClosed();
      System.out.println("status:" + status);
      conn.close();

    } catch (SQLException e) {
      System.out.println("Something Wrong!!");
      e.printStackTrace();
    }


  }
}
