package edu.kim.jsp.student.model.service;

import static edu.kim.jsp.common.JDBCTemplate.close;
import static edu.kim.jsp.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import edu.kim.jsp.student.model.dao.StudentDAO;
import edu.kim.jsp.student.model.vo.Student;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();

	public List<Student> selectDepartment(String inputDept) throws Exception{
		Connection conn = getConnection();
		
		List<Student> stdList = dao.selectDepartment(conn, inputDept);
		
		close(conn);
		
		return stdList;
	}

}
