package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;
import com.kh.jdbc.day04.pstmt.employee.common.JDBCTemplate;

public class EmployeeDAO {
	private JDBCTemplate jdbcTemplate;
	public EmployeeDAO() {
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 등록
	 * 3. Statement 생성
	 * 4. 쿼리문 실행
	 * 5. 결과 받기
	 * 6. 자원 해제(close메소드)
	 */
	
	public void updateEmployee(Employee emp) {
//		String query = "UPDATE EMPLOYEE" 
//				+ " SET EMAIL = '" + emp.getEmail() + "'"
//				+ ", PHONE = '" + emp.getPhone() + "'"
//				+ "WHERE EMP_ID = '" + emp.getEmpId() + "'";
		String query = "UPDATE EMPLOYEE" 
				+ " SET EMAIL = ?"
				+ ", PHONE = ?"
				+ "WHERE EMP_ID = ?";
		try {
			Connection conn = jdbcTemplate.getConnection();
			//Statement stmt = conn.createStatement();//워크시트 열기
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setString(3, emp.getEmpId());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				//성공 커밋
			}
			else {
				//실패 롤백
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Employee findEmployee(String empId){
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		try {
			Connection conn = jdbcTemplate.getConnection();
			//Statement stmt = conn.createStatement();//워크시트 열기
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			ResultSet rset = pstmt.executeQuery();
			Employee emp;
			if(rset.next()) {
				emp = this.rsetToEmployee(rset);
			}
			else {
				emp = null;
			}
			conn.close();
			pstmt.close();
			rset.close();
			return emp;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteEmployee(String empId){
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
		try {
			Connection conn = jdbcTemplate.getConnection();
			//Statement stmt = conn.createStatement();//워크시트 열기
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			int result = pstmt.executeUpdate();//쿼리문 실행 하고 결과값 result에 넣기
			if(result > 0) {
				//성공 -> commit
			}
			else {
				//실패 -> ROLLBACK
			}
			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertEmployee(Employee emp){
//		String query = "INSERT INTO EMPLOYEE VALUES('"
//				+ emp.getEmpId() +"','"
//				+ emp.getEmpName() +"','"
//				+ emp.getEmpNo() +"','"
//				+ emp.getEmail() +"','"
//				+ emp.getPhone() +"','"
//				+ emp.getDeptCode() +"','"
//				+ emp.getJobCode() +"','"
//				+ emp.getSalLevel() +"',"
//				+ emp.getSalary() +","
//				+ emp.getBonus() +",'"
//				+ emp.getManagerId() +"',SYSDATE,null,'N')";
		String query = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSDATE,null,'N')";
		try {
			Connection conn = jdbcTemplate.getConnection();
			//Statement stmt = conn.createStatement();//워크시트 열기
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setString(11, emp.getManagerId());
			int result = pstmt.executeUpdate();//쿼리문 실행 하고 결과값 result에 넣기
			if(result > 0) {
				//성공 -> commit
			}
			else {
				//실패 -> ROLLBACK
			}
			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Employee> selectAllEmployees(){
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> eList = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
			//Statement stmt = conn.createStatement();//워크시트 열기
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery(query);//쿼리문 실행 하고 결과값 rset에 넣기
			eList = new ArrayList<Employee>();
			while(rset.next()) {//rset에 저장된 행들이 더이상 안나올때 까지 반복
				Employee employee = this.rsetToEmployee(rset);
				eList.add(employee);//리스트에 모든 항목들 복사
			}
			//자원해제
			conn.close();
			pstmt.close();
			rset.close();
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
		return eList;
	}
	
	private Employee rsetToEmployee(ResultSet rset) throws SQLException{
		Employee emp = new Employee();
		
		emp.setEmpId(rset.getString("EMP_ID"));
		emp.setEmpName(rset.getString("EMP_NAME"));
		emp.setEmpNo(rset.getString("EMP_NO"));
		emp.setEmail(rset.getString("EMAIL"));
		emp.setPhone(rset.getString("PHONE"));
		emp.setDeptCode(rset.getString("DEPT_CODE"));
		emp.setJobCode(rset.getString("JOB_CODE"));
		emp.setSalLevel(rset.getString("SAL_LEVEL"));
		emp.setSalary(rset.getInt("SALARY"));
		emp.setBonus(rset.getDouble("BONUS"));
		emp.setManagerId(rset.getString("MANAGER_ID"));
		emp.setHireDate(rset.getDate("HIRE_DATE"));
		emp.setEntDate(rset.getDate("ENT_DATE"));
		emp.setEntYn(rset.getString("ENT_YN").charAt(0));
		
		return emp;
	}
}












