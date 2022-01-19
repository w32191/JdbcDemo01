package com.samwang.model;

import java.io.Serializable;

public class Member implements Serializable {

  private int id;
  private String memberName;
  private String address;
  private String phone;

  public Member() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "id: " + id + "\nname: " + memberName + "\naddress: " + address + "\nphone: " + phone;
  }
}
