package com.samwang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo4PreparedStatement {

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

  public void createTable() throws SQLException {
    //language=TSQL
    String sql = "CREATE TABLE profiles([id] int not null primary key identity(1,1),"
        + "[name] varchar(30) not null,address varchar(50) not null," +
        "phone varchar(20))";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.execute();
    System.out.println("create table Success!!");
    preState.close();
  }//end of createTable()

  public void insertData(String name, String address, String phone) throws SQLException {
    String sql = "INSERT INTO profiles([name],address,phone) VALUES(?,?,?)";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setString(1, name);
    preState.setString(2, address);
    preState.setString(3, phone);
    preState.executeUpdate();

    System.out.println("insert Success!!");
    preState.close();
  }//end of insertData()

  public void updateAddressByName(String profileName, String newAddress) throws SQLException {
    String sql = "UPDATE profiles SET address = ? WHERE name = ?";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setString(1, newAddress);
    preState.setString(2, profileName);
    int row = preState.executeUpdate();
    System.out.println("Update " + row + " rows");
    preState.close();
  }//end of updateAddressByName()

  public void queryByAddress(String address) throws SQLException {
    String sql = "SELECT * FROM profiles WHERE address = ?";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setString(1, address);

    ResultSet rs = preState.executeQuery();
    while (rs.next()) {
      System.out.println(
          rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("address"));
    }

    rs.close();
    preState.close();
  }//end of queryByAddress()


  public void deleteByID(int id) throws SQLException {
    String sql = "DELETE profiles WHERE id = ?";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setInt(1, id);
    int row = preState.executeUpdate();

    System.out.println("delete " + row + " rows!");
    preState.close();
  }//end of deleteByID()


  public static void main(String[] args) {
    Demo4PreparedStatement demo = new Demo4PreparedStatement();
    try {
      demo.createConnection();
//      demo.createTable();
//      demo.insertData("Jay", "USA", "1234567");
//      demo.updateAddressByName("roger", "Taipei");
      demo.queryByAddress("USA");

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        demo.closeConnection();
        System.out.println("Closed Connection !!");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }//end of main try()

  }
}
