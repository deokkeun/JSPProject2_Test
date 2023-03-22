package edu.kim.jsp.student.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kim.jsp.common.JDBCTemplate.*;
import edu.kim.jsp.student.model.vo.Student;

public class StudentDAO {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public StudentDAO() {
		try {
		prop = new Properties();
		
		String filePath = StudentDAO.class.getResource("/edu/kim/jsp/sql/student-sql.xml").getPath();
		
		prop.loadFromXML(new FileInputStream(filePath));
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> selectDepartment(Connection conn, String inputDept) throws Exception{
		List<Student> stdList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectDept");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputDept);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				stdList.add(new Student(studentNo, studentName, studentAddress, departmentName));
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return stdList;
	}

}
