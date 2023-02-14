package com.madhu.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.madhu.JdbcUtil.JdbcUtil;
import com.madhu.dto.Student;

public class StudentDaoImpl implements IStudentDao {
private Connection connection=null;
private PreparedStatement prepatedstatement=null;
private ResultSet resultSet =null;
private Student student=null;
	@Override
	public String addStudent(Student student) {
		String sqlInsertQueryString ="insert into studnt(`name`,`age`,`address`)values(?,?,?)";
		try {
			connection=JdbcUtil.getJdbConnection();
			if(connection!=null)
				prepatedstatement=connection.prepareStatement(sqlInsertQueryString);
			if(prepatedstatement!=null) {
				prepatedstatement.setString(1, student.getName());
				prepatedstatement.setInt(2, student.getAge());
				prepatedstatement.setString(3,student.getAddress());
			}
			if(prepatedstatement!=null) {
				int rowsAffected= prepatedstatement.executeUpdate();
				System.out.println("rows affected:"+rowsAffected);
				if(rowsAffected==1)
					return "success";
			}
			} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer id) {
		String sqlSelectQuery="select id,name,age,address from studnt where id=?";
		try {
			connection= JdbcUtil.getJdbConnection();
			if(connection!=null)
				prepatedstatement= connection.prepareStatement(sqlSelectQuery);
			if(prepatedstatement!=null)
				prepatedstatement.setInt(1, id);
			if(prepatedstatement!=null) 
				resultSet=prepatedstatement.executeQuery();
				if(resultSet!=null) {
					if(resultSet.next()) {
						student = new Student();
						//System.out.println(student);
						student.setId(resultSet.getInt(1));
						student.setName(resultSet.getString(2));
						student.setAge(resultSet.getInt(3));
						student.setAddress(resultSet.getString(4));
						
						 return student;
					}
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		String sqlUpdateQuery="update studnt set name=?,age=?,address=? where id=?";
		try {
			connection= JdbcUtil.getJdbConnection();
			if(connection!=null)
				prepatedstatement= connection.prepareStatement(sqlUpdateQuery);
			if(prepatedstatement!=null)
				prepatedstatement.setString(1, student.getName());
				prepatedstatement.setInt(2, student.getAge());
				prepatedstatement.setString(3, student.getAddress());
				prepatedstatement.setInt(4, student.getId());
			if(prepatedstatement!=null) {
				int rowsAffected=prepatedstatement.executeUpdate();
				System.out.println("rowsAffected:"+rowsAffected);
				if(rowsAffected==1)
					return "success";
			}
				
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

	@Override
	public String deleteStudent(Integer id) {
		String sqlDeleteQuery="delete from studnt where id=?";
		try {
			connection= JdbcUtil.getJdbConnection();
			if(connection!=null)
				prepatedstatement= connection.prepareStatement(sqlDeleteQuery);
			if(prepatedstatement!=null)
				prepatedstatement.setInt(1, id);
			if(prepatedstatement!=null) {
				int rowsAffected=prepatedstatement.executeUpdate();
				if(rowsAffected==1) {
					return "success";
				}else {
					return "not found";
				}
			}
				
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

}
