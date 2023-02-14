 package com.madhu.dto;

import java.io.Serializable;

import sun.util.logging.resources.logging;

public class Student implements Serializable {
	private static final long serialVersionUID=1;
private String name;
private int age;
private String address;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
@Override
public String toString() {
	return "Student [name=" + name + ", age=" + age + ", address=" + address + "]";
}

}
