package com.madhu.serviceFactory;

import com.madhu.service.IStudentService;
import com.madhu.service.StudentServiceImpl;

public class StudentServiceFactory {
static IStudentService studentService;
private StudentServiceFactory() {}
public static IStudentService getIStudentService() {
	if(studentService==null) {
		studentService= new StudentServiceImpl();
	}
	return studentService;
}
}
