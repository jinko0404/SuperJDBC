package com.kh.jdbc.day01.basic;

import java.sql.*;

public class JDBCRun {

	public static void main(String[] args) {
		/*
		 * JDBC 코딩 절차
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. statement 객체 생성(쿼리문 실행 준비)
		 * 4. SQL 전송(쿼리문 실행)
		 * 5. 결과 받기(ResultSet으로 받음)
		 * 6. 자원 해제(close())
		 */
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "KH";
		String password = "KH";
		String query = "SELECT * FROM DEPARTMENT";
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DBMS 연결 생성(sqldeveloper 접속 버튼누른 것과 같다.)
			Connection conn = DriverManager.getConnection(url, username, password);
			
			//3.statement 객체 생성(쿼리문 실행 준비)
			Statement stmt = conn.createStatement();
			
			//4.SQL 전송(명령문 실행, 실행 버튼누른 것과 같다.)
			//5.결과 받기(ResultSet으로 받음)
			ResultSet rset = stmt.executeQuery(query);
			//후처리 필요(ResultSet에서 꺼내 써야한다.)
			while(rset.next()) {
				/*System.out.println("직원명 : " + rset.getString("EMP_NAME")
				+ "\t\t사원번호 : " + rset.getInt("EMP_ID")
				+ "\t\t주민번호 : " + rset.getString("EMP_NO"));*/
				System.out.print("부서코드 : " + rset.getString("DEPT_ID"));
				System.out.print(", 지역코드 : " + rset.getString("LOCATION_ID"));
				System.out.println(", 부서명 : " + rset.getString("DEPT_TITLE"));
			}
			
			//6.자원해제(접속해제와 같다.)
			rset.close();
			stmt.close();
			conn.close();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
