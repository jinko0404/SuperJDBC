package com.kh.jdbc.day02.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberDAO {
	//JDBC를 이용해 Oracle DB에 접속하는 클래스
	//JDBC 코딩 절차
	
	/*
	 * 1. 드라이버 등록
	 * 2. DB 연결 생성
	 * 3. 쿼리문 실행 준비
	 * 4. 쿼리문 실행
	 * 5. 실행 결과 받기
	 * 6. 자원 해제(close();)
	 */
	//클래스 바로 밑에 코드 못씀
	//메소드로 감싸야한다.
	//필요할 때 마다 호출해서 쓴다.
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	final String username = "student";
	final String password = "student";
	List<Member> mList = null;
	
	public void updateMember(Member member) {
		String query = "UPDATE MEMBER_TBL "
				+ "SET MEMBER_PWD = '"+ member.getMemberPw() +"'"
				+ ", EMAIL = '"+ member.getEmail() +"'"
				+ ", PHONE = '" + member.getPhone() + "'"
				+ ", ADDRESS = '"+ member.getAddress() +"'"
				+ ", HOBBY = '"+ member.getHobby() +"' WHERE MEMBER_ID = '" + member.getMemberId() + "'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				//성공 커밋
			}
			else {
				//실패 롤백
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				//성공 커밋
			}
			else {
				//실패 롤백
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" +memberId + "'";
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			member = new Member();
			if(rset.next()) {
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0));//젠더는 한글자여서 문자 처리
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PWD"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			else {
				member = null;
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	public Member selectOneById2(String memberId, String memberPw) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?"
				+"AND MEMBER_PWD = ?";
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(query);//쿼리문 미리 컴파일
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			ResultSet rset = pstmt.executeQuery();//쿼리문 미리 컴파일하고
			//위치홀더 값 세팅후 실행 및 결과 받기
			member = new Member();
			if(rset.next()) {
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0));//젠더는 한글자여서 문자 처리
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PWD"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			else {
				member = null;
			}
			rset.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	public void insertMember(Member member) {
		try {
			final String query = "INSERT INTO MEMBER_TBL VALUES('"
					+ member.getMemberId() +"','"
					+ member.getMemberPw() +"','"
					+ member.getMemberName() +"','"
					+ member.getGender() +"',"
					+ member.getAge() +",'"
					+ member.getEmail() +"','"
					+ member.getPhone() +"','"
					+ member.getAddress() +"','"
					+ member.getHobby() + "'"
					+ ", SYSDATE)";
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);//DML의 경우 호출하는 메소드
			if(result > 0) {//autoCommit이 되기 때문에 안해도 된다.
				//insert 성공
			}
			else {
				//insert 실패
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Member> selectAll() {
		try {
			final String query = "SELECT * FROM MEMBER_TBL";
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query); //초록색 재생버튼 누름
			//rset에는 전부 다 담겨있기 때문에 한 행씩 꺼내 출력
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				Member member = new Member();
				String memberName = rset.getString("MEMBER_NAME");
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				member.setMemberName(memberName);
				member.setGender(rset.getString("GENDER").charAt(0));//젠더는 한글자여서 문자 처리
				member.setMemberId(memberId);
				member.setMemberPw(memberPwd);
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				mList.add(member);//누락 주의 ! 하나의 행 데이터를 LIST에 반복적으로 저장
				//후처리 : SELECT한 결과값을 자바영역인 List에다가 옮기는 작업
				//System.out.println("이름 : " + rset.getString("MEMBER_NAME"));
			}
			rset.close();
			stmt.close();
			conn.close();
			//오라클 상에서 커밋을 해두지 않으면 적용되지 않는다.
			
			return mList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}
}
