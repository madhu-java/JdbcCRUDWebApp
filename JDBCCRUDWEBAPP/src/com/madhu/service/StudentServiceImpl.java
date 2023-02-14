package com.madhu.service;

import com.madhu.daoFactory.StudentDaoFactory;
import com.madhu.dto.Student;
import com.madhu.persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao studentDao;
	@Override
	public String addStudent(Student student) {
		 studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.addStudent(student);
	}

	@Override
	public Student searchStudent(Integer id) {
		studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(id);
		
	}

	@Override
	public String updateStudent(Student student) {
		studentDao=StudentDaoFactory.getStudentDao();
		
		return studentDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer id) {
		studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(id);
	}

}
