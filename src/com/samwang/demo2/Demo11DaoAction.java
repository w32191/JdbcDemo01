package com.samwang.demo2;

import com.samwang.model.Member;
import com.samwang.model.MemberDAO;
import com.samwang.util.ConnectionFactory;
import java.sql.SQLException;
import java.util.List;


public class Demo11DaoAction {

  public static void main(String[] args) throws SQLException {
    MemberDAO dao = new MemberDAO();

//    Member m1 = new Member();
//    m1.setMemberName("Sam");
//    m1.setAddress("Taipei");
//    m1.setPhone("12345678");
//    dao.addMember(m1);
//
//    Member m2 = new Member();
//    m2.setMemberName("Marry");
//    m2.setAddress("Japan");
//    m2.setPhone("87654321");
//    dao.addMember(m2);

//    Member member = dao.findById(1);

    dao.deleteById(1);

    List<Member> list = dao.findAllMember();
    for (Member member : list) {
      System.out.println(member);
      System.out.println("===============");
    }



    ConnectionFactory.closeConn();

  }

}
