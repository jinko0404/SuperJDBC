package com.kh.jdbc.day05.member.domain.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.*;

import com.kh.jdbc.day05.member.common.JDBCTemplate;
import com.kh.jdbc.day05.member.domain.vo.Member;

public class MemberDao {
	private Properties pro;
	private JDBCTemplate jdbcTemplate;

	public MemberDao() {
		jdbcTemplate = JDBCTemplate.getInstance();
		pro = new Properties();
		@SuppressWarnings("unused")
		Reader reader = null;
		try {
			reader = new FileReader("src/resources/query.properties");
			pro.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateMember(Member member) {
//		String query = "UPDATE MEMBER_TBL "
//				+ "SET MEMBER_PWD = ?"
//				+ ", EMAIL = ?"
//				+ ", PHONE = ?"
//				+ ", ADDRESS = ?"
//				+ ", HOBBY = ? WHERE MEMBER_ID = ?";
		final String query = pro.getProperty("updateMember");
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				//성공 커밋
			}
			else {
				//실패 롤백
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void deleteMember(String memberId) {
//		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		final String query = pro.getProperty("deleteMember");
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			int result = pstmt.executeUpdate();//pstmt는 실행할 때 전달 값이 필요없다.
			if(result > 0) {//자동으로 커밋(auto commit)이 되기 때문에 안해도 된다.
				//성공 커밋
			}
			else {
				//실패 롤백
			}
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		Member member = null;
//		JDBCTemplate jdbcTemplate = new JDBCTemplate();
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				member = this.rsetToMember(rset);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	public Member selectOneByLogin(String memberId, String memberPw) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?"
				+" AND MEMBER_PWD = ?";
		Member member = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);//쿼리문 미리 컴파일
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			ResultSet rset = pstmt.executeQuery();//쿼리문 미리 컴파일하고
			//위치홀더 값 세팅후 실행 및 결과 받기
			if(rset.next()) {
				member = this.rsetToMember(rset);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	public int insertMember(Member member) {
		try {
			//final String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
			final String query = pro.getProperty("insertMember");
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);//쿼리문 미리 컴파일
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, String.valueOf(member.getGender()));
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			int result = pstmt.executeUpdate();//DML의 경우 호출하는 메소드
			pstmt.close();
			conn.close();
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public List<Member> selectAll() {
		List<Member> mList = null;
		try {
			Member member = null;
//			final String query = "SELECT * FROM MEMBER_TBL";
			final String query = pro.getProperty("selectAllMember");
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery(); //초록색 재생버튼 누름
			//rset에는 전부 다 담겨있기 때문에 한 행씩 꺼내 출력
			mList = new ArrayList<Member>();
			while(rset.next()) {
				member = this.rsetToMember(rset);
				mList.add(member);//누락 주의 ! 하나의 행 데이터를 LIST에 반복적으로 저장
				//후처리 : SELECT한 결과값을 자바영역인 List에다가 옮기는 작업
				//System.out.println("이름 : " + rset.getString("MEMBER_NAME"));
			}
			rset.close();
			pstmt.close();
			conn.close();
			//오라클 상에서 커밋을 해두지 않으면 적용되지 않는다.
			
			return mList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	
	
	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
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
		return member;
	}
}
