package com.madhu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.madhu.dto.Student;
import com.madhu.service.IStudentService;
import com.madhu.serviceFactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doProcess(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IStudentService studentService = StudentServiceFactory.getIStudentService();
		System.out.println("request uri::"+request.getRequestURI());
		System.out.println("pathInfo::"+request.getPathInfo());
		
		if(request.getRequestURI().endsWith("addform")) {
			
			Student student= new Student();
			student.setAge(Integer.parseInt(request.getParameter("sage")));
			student.setName(request.getParameter("sname"));
			
			student.setAddress(request.getParameter("saddr"));
			String statuString=studentService.addStudent(student);
			PrintWriter out = response.getWriter();
			if(statuString.equals("success")) {
				out.println("<h1 style='color:green;text-align:center;'>Registration successfull</h1>");
			}else {
				out.println("<h1 style='color:red;text-align:center;'>Registration "
						+ "unsuccessfull</h1>");
			}
			out.close();
		}
		if(request.getRequestURI().endsWith("searchform")) {
			Integer id= Integer.parseInt(request.getParameter("sid"));
			Student student=studentService.searchStudent(id);
			PrintWriter out = response.getWriter();
			if(student!=null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>Student Id</th><td>"+student.getId()+"</td></tr>");
				out.println("<tr><th>Student Name</th><td>"+student.getName()+"</td></tr>");
				out.println("<tr><th>Student Age</th><td>"+student.getAge()+"</td></tr>");
				out.println("<tr><th>Student Address</th><td>"+student.getAddress()+"</td></tr>");
				out.println("/table>");
				out.println("</center>");
				
				out.println("</body>");
              student=null;

			}else {
				out.println("There is no student with id:"+id);
			}
			out.close();
			
			
		}
		if(request.getRequestURI().endsWith("deleteform")) {
			int id=Integer.parseInt(request.getParameter("sid"));
			String statuString=studentService.deleteStudent(id);
			PrintWriter out = response.getWriter();
			if(statuString.equals("success")) {
				out.println("<body>");
				out.println("<h1 style='color:green;text-align:center;'>Record deleted successfully</h1>");
				out.println("</body>");
			}else if(statuString.equals("failure")) {
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record deletetion failed</h1>");
				out.println("</body>");
			}else {
				out.println("<body>");
				out.println("<h1 style='color:green;text-align:center;'>Record not found for deletion</h1>");
				out.println("</body>");
			}
			out.close();
		}
		if(request.getRequestURI().endsWith("editform")) {
			int id=Integer.parseInt(request.getParameter("sid"));
			Student student=studentService.searchStudent(id);
			PrintWriter out = response.getWriter();
			if(student!=null) {
				//display students data in the editable form
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>"+student.getId()+"</td></tr>");
				out.println("<input type='hidden' name='sid' value='"+student.getId()+"'/>");
				out.println("<tr><th>NAME</th><td> <input type='text' name='sname' value='"+student.getName()+"'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='"+student.getAge()+"'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='"+student.getAddress()+"'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
				
			}else {
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not available for the given id:"+id+"</h1>");
				out.println("</body>");
			}
			
		}
		if(request.getRequestURI().endsWith("updateRecord")) {
			String sid= request.getParameter("sid");
			String sname= request.getParameter("sname");
			String sage= request.getParameter("sage");
			String saddr= request.getParameter("saddr");
			
			Student student = new Student();
			student.setId(Integer.parseInt(sid));
			student.setName(sname);
			student.setAge(Integer.parseInt(sage));
			student.setAddress(saddr);
			String statuString=studentService.updateStudent(student);
			
			PrintWriter out = response.getWriter();
			if(statuString.equals("success")) {
				out.println("<h1 style='color:green;text-align:center;'>Record updated sucessfully</h1>");
			}else {
				out.println("<h1 style='color:red;text-align:center;'>Record updation is unsuccessful</h1>");
			}
			out.close();
			
			
		}
		
	}

}
