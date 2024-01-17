package com.kh.jdbc.day04.pstmt.employee.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {
	private static JDBCTemplate instance;
	private static Connection conn;
	private JDBCTemplate() {}
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "student";
	final String PASSWORD = "student";
	
	public Connection getConnection() throws Exception{
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);//드라이버 등록
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);//접속하기			
		}
		return conn;
	}
}
