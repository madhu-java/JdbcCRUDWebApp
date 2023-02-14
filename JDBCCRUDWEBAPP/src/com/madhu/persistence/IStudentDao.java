package com.madhu.persistence;

import com.madhu.dto.Student;

public interface IStudentDao {

	public String addStudent(Student student);
	public Student searchStudent(Integer id);
	public String updateStudent(Student student);
	public String deleteStudent(Integer id);
}
