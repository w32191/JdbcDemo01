package com.samwang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo9AddBatch {

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

  public void couponGenerator() throws SQLException {
    List<String> usernames = new ArrayList<>();
    usernames.add("Tom");
    usernames.add("Tina");
    usernames.add("Gina");
    usernames.add("Mike");

    String sql = "INSERT INTO coupon(username,couponCode) VALUES(?,?)";
    PreparedStatement preState = conn.prepareStatement(sql);
    for (String name : usernames) {
      preState.setString(1, name);
      preState.setString(2, "GoodCode666");
      preState.addBatch();
    }
    int[] rows = preState.executeBatch();
    System.out.println("sql count:" + rows.length);

    preState.close();

  }// end of couponGenerator()

  public static void main(String[] args) {
    Demo9AddBatch demo = new Demo9AddBatch();

    try {
      demo.createConnection();
      demo.couponGenerator();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        demo.closeConnection();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
