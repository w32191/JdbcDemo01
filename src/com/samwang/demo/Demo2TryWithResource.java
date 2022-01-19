package com.samwang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo2TryWithResource {

  public static void main(String[] args) {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;user=sa;password=Passw0rd!";

    try (Connection conn = DriverManager.getConnection(url)) {
      System.out.println("Connector!!");

    } catch (SQLException e) {
      System.out.println("Wrong!!");
      e.printStackTrace();
    }
  }
}
