package com.samwang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo5SQLInjection {

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


  public boolean checkLogin(String username, String password) throws SQLException {
    String sql =
        "SELECT * FROM users WHERE username = '" + username + "' AND pwd = '" + password + "'";
    Statement state = conn.createStatement();
    ResultSet rs = state.executeQuery(sql);
    boolean checkOK = rs.next();
    rs.close();
    state.close();

    return checkOK;
  }//end of checkLogin()

  public boolean checkLogin2(String username, String password) throws SQLException {
    String sql = "SELECT * FROM users WHERE username = ? AND pwd = ? ";
    PreparedStatement preState = conn.prepareStatement(sql);

    preState.setString(1, username);
    preState.setString(2, password);
    ResultSet rs = preState.executeQuery();
    boolean checkOK = rs.next();
    rs.close();
    preState.close();

    return checkOK;
  }//end of checkLogin2()

  public static void main(String[] args) {
    Demo5SQLInjection demo = new Demo5SQLInjection();

    try {
      demo.createConnection();
//      boolean result = demo.checkLogin("' or 1=1 --", "password2222");
//      boolean result = demo.checkLogin2("jack","password");

//      boolean result = demo.checkLogin2("' or 1=1 --","password123123");
      boolean result = demo.checkLogin2("jack","qq");


      if (result) {
        System.out.println("login Success!!");
      } else {
        System.out.println("login fail!");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        demo.closeConnection();
        System.out.println("Close Connection!");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }
}
