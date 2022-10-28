package com.bridgeLabz;

public class Person {

	private String fname;
	private String lname;
	private long phonenumber;
	private Address address;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public  Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Person(String fname, String lname, long phonenumber, Address address) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.phonenumber = phonenumber;
		this.address = address;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Person [fname=" + fname + ", lname=" + lname + ", phonenumber=" + phonenumber + ", address=" + address
				+ "]";
	}
	

}
