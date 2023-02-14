package com.madhu.daoFactory;

import com.madhu.persistence.IStudentDao;
import com.madhu.persistence.StudentDaoImpl;

public class StudentDaoFactory {
static IStudentDao studentDao=null;
private StudentDaoFactory() {}
public  static IStudentDao getStudentDao() {
	if(studentDao==null) {
	studentDao=new StudentDaoImpl();
	}
	return studentDao; 
}

}
