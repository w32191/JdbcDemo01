package com.samwang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo3CreateStatement {

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

  public void queryDB1() throws SQLException {
    String sql = "SELECT * FROM product";
    Statement state = conn.createStatement();
    ResultSet rs = state.executeQuery(sql);

    System.out.println("Data Result:" + rs.next());
    rs.close();
    state.close();
  } //end of queryDB1()

  public void queryDB2() throws SQLException {
    String sql = "SELECT * FROM product";
    Statement state = conn.createStatement();
    ResultSet rs = state.executeQuery(sql);

    while (rs.next()) {
      System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
          rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
    }
    rs.close();
    state.close();

  }//end of queryDB2()

  public void queryTwoColumn() throws SQLException {
    String sql = "SELECT * FROM product";
    Statement state = conn.createStatement();
    ResultSet rs = state.executeQuery(sql);

    while (rs.next()) {
      System.out.println(rs.getString("productid") + " " + rs.getString("productprice"));
    }
    rs.close();
    state.close();

  }//end of queryTwoColumn();

  public void updateData() throws SQLException {
    String sql = "UPDATE product SET productprice = 70 WHERE productname = 'mask'";
    Statement state = conn.createStatement();

    int row = state.executeUpdate(sql);
    System.out.println("修改了" + row + "筆");

    state.close();
  }//end of updateData()

  public void insertData() throws SQLException {
    String sql = "INSERT INTO product VALUES(1005,'water',50,'2021-05-31','一瓶')";
    Statement state = conn.createStatement();
    int row = state.executeUpdate(sql);

    System.out.println("修改了" + row + "筆");

    state.close();
  }//end of insertData()

  public void deleteData() throws SQLException {
    String sql = "DELETE product WHERE productid = 1005";
    Statement state = conn.createStatement();
    int row = state.executeUpdate(sql);
    System.out.println("刪除了" + row + "筆");
    state.close();
  }//end of deleteData()

  public void updateDataHW() throws SQLException {
    String sql = "UPDATE product SET productprice = 15 WHERE productid = 1001";
    Statement state = conn.createStatement();
    int row = state.executeUpdate(sql);
    if (row != -1) {
      System.out.println("Update Product Success !!");
    }
    state.close();
  }// end of updateDataHW()

  public void insertDataHW() throws SQLException {
    String sql = "INSERT INTO product VALUES(1006,'Mac mini',25000,'2022-01-01','一台')";
    Statement state = conn.createStatement();
    int row = state.executeUpdate(sql);
    if (row != -1) {
      System.out.println("Insert Product Success !!");
    }
    state.close();
  }


  public static void main(String[] args) {
    Demo3CreateStatement demo = new Demo3CreateStatement();

    try {
      demo.createConnection();
//      demo.queryDB1();
//      demo.queryDB2();
//      demo.queryTwoColumn();
//      demo.updateData();
//      demo.insertData();
//      demo.insertDataHW();
      demo.deleteData();

    } catch (SQLException e) {
      System.out.println("Wrong !!");
      e.printStackTrace();
    } finally {
      try {
        demo.closeConnection();
        System.out.println("Closed Connector !!");
      } catch (SQLException e) {
        System.out.println("Close Wrong !!");
        e.printStackTrace();
      }
    } // end of try...catch

  }
}
