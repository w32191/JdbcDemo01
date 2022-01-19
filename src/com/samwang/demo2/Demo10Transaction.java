package com.samwang.demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo10Transaction {

  private Connection conn;

  public Connection createConnection() throws SQLException {
    String url = "jdbc:sqlserver://localhost:1433;database=JDBCDemoDB;user=sa;password=Passw0rd!";
    conn = DriverManager.getConnection(url);
    boolean status = !conn.isClosed();
    if (status) {
      System.out.println("Open Connector!!");
      return conn;
    }
    return null;
  } //end of createConnection()

  public void closeConnection() throws SQLException {
    if (conn != null) {
      conn.close();
    }
  } //end of closeConnection()

  public static void main(String[] args) throws SQLException {
    Demo10Transaction demo = new Demo10Transaction();
    Connection conn = null;
    try {
      conn = demo.createConnection();
      conn.setAutoCommit(false);
      String sql = "UPDATE product set remark = ? WHERE productid = ?";

      PreparedStatement preState = conn.prepareStatement(sql);
      preState.setInt(2, 1001);
      preState.setString(1, "因疫情關係，不在24h保證內");
      preState.execute();

      preState.setInt(2, 1002);
      preState.setString(1, "因疫情關係，不在24h保證內11");
      preState.execute();

      conn.commit();

    } catch (SQLException e) {
      System.out.println("Wrong:" + e.getMessage());
      conn.rollback();
    } finally {
      conn.close();
    }

  }

}
