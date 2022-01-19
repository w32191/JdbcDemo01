package com.samwang.model;

import com.samwang.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

  private Connection conn;

  //dependency injection(依賴注入)>>達到控制反轉
  public MemberDAO() {
    this.conn = ConnectionFactory.getConnection();
  }

  public void addMember(Member m) throws SQLException {
    String sql = "Insert member(member_name, member_address, member_phone) VALUES (?,?,?)";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setString(1, m.getMemberName());
    preState.setString(2, m.getAddress());
    preState.setString(3, m.getPhone());
    preState.execute();
    preState.close();
  }//end of addMember()


  public Member findById(int id) throws SQLException {
    String sql = "SELECT * FROM member WHERE id = ?";
    PreparedStatement preState = conn.prepareStatement(sql);
    preState.setInt(1, id);
    ResultSet rs = preState.executeQuery();

    Member member = new Member();

    if (rs.next()) {
      member.setId(rs.getInt("id"));
      member.setMemberName(rs.getString("member_name"));
      member.setAddress(rs.getString("member_address"));
      member.setPhone(rs.getString("member_phone"));
    }
    rs.close();
    preState.close();
    return member;
  }//end of findById()

  public List<Member> findAllMember() throws SQLException {
    String sql = "SELECT * FROM member";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    ResultSet rs = preparedStatement.executeQuery();
    List<Member> list = new ArrayList<>();
    while (rs.next()) {
      Member member = new Member();
      member.setId(rs.getInt("id"));
      member.setMemberName(rs.getString("member_name"));
      member.setAddress(rs.getString("member_address"));
      member.setPhone(rs.getString("member_phone"));
      list.add(member);
    }
    rs.close();
    preparedStatement.close();
    return list;
  }//end of findAllMember();

  public void deleteById(int id) throws SQLException {
    String sql = "DELETE FROM member WHERE id = ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setInt(1,id);

    try{
      preparedStatement.executeUpdate();
      System.out.println("--Delete Success!!--");
    }catch (SQLException e){
      e.printStackTrace();
    }
  }//end of deleteById()

}
