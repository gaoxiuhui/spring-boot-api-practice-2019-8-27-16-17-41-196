package com.tw.apistackbase.modules;

public class Employee {
    private int id;
    private  String gender;
    
    public Employee() {}
    
	public Employee(int id) {		
		this.id = id;
	}
	
	public Employee(int id, String gender) {
		super();
		this.id = id;
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
