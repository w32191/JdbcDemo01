package com.samwang.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo7Blob {

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

  public void storeFile() throws IOException, SQLException {
    File file = new File("/Users/samwang/testImage/002.jpg");
    FileInputStream fis = new FileInputStream(file);

    String sql = "INSERT INTO gallery(imageName,imageFile) VALUES(?,?)";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setString(1, "多拉Ａ夢");
    preState.setBinaryStream(2, fis);

    preState.execute();
    System.out.println("File store OK!");
    preState.close();
    fis.close();
  }//end of storeFile()

  public void retrieveImage() throws SQLException, IOException {
    String sql = "SELECT * FROM gallery WHERE id = ?";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setInt(1, 4);

    ResultSet rs = preState.executeQuery();
    rs.next();  //  把指標往下移一筆
    Blob blob = rs.getBlob(3);

    File file;
    FileOutputStream fio = new FileOutputStream("/Users/samwang/testImage/output/output.png");
    BufferedOutputStream bio = new BufferedOutputStream(fio);
    int length = (int)(blob.length()/2);
    bio.write(blob.getBytes(1,length));
    bio.close();
    fio.close();
    rs.close();
    preState.close();
    System.out.println("Get Image success!");
  }//end of retrieveImage()


  public static void main(String[] args) {
    Demo7Blob demo = new Demo7Blob();
    try {
      demo.createConnection();
      demo.storeFile();
//      demo.retrieveImage();
    } catch (SQLException | IOException e) {
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
