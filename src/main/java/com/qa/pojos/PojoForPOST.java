package com.qa.pojos;

public class PojoForPOST {
private int id;
String name;
String email;
String gender;
String status;

public PojoForPOST() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public PojoForPOST(String name, String email, String gender, String status) {
	super();
	this.name = name;
	this.email = email;
	this.gender = gender;
	this.status = status;
}
@Override
public String toString() {
	return "PojoForPOST [id=" + id + ", name=" + name + ", email=" + email + ", gender=" + gender + ", status=" + status
			+ "]";
}



}
