package com.kh.jdbc.day05.member.common;

import java.sql.*;

public class JDBCTemplate {
	private static JDBCTemplate instance;
	private static Connection conn;
	private JDBCTemplate() {}// private로 아무나 객체 생성 못하도록 한다.
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {//객체가 만들어져 있는지 체크 후
			instance = new JDBCTemplate();//없으면 만들어준다.
		}
		return instance;//만들어 놓은 것을 사용할 수 있도록 리턴해준다.
	}
	
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	final String username = "student";
	final String password = "student";
	
	//DBMS 연결을 한번 연결해놓고 저장해서 쓰는 개념
	//DBCP(DataBase Connection Pool)
	public Connection getConnection() throws Exception{
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
}
