package com.kh.jdbc.day03.employee.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.employee.model.vo.Employee;

public class EmployeeDAO {
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 등록
	 * 3. Statement 생성
	 * 4. 쿼리문 실행
	 * 5. 결과 받기
	 * 6. 자원 해제(close메소드)
	 */
	
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "student";
	final String PASSWORD = "student";
	
	public void updateEmployee(Employee emp) {
		String query = "UPDATE EMPLOYEE" 
				+ " SET EMAIL = '" + emp.getEmail() + "'"
				+ ", PHONE = '" + emp.getPhone() + "'"
				+ "WHERE EMP_ID = '" + emp.getEmpId() + "'";
		try {
			Class.forName(DRIVER_NAME);//드라이버 등록
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);//접속하기
			Statement stmt = conn.createStatement();//워크시트 열기
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				//성공 커밋
			}
			else {
				//실패 롤백
			}
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Employee findEmployee(String empId) {
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
		try {
			Class.forName(DRIVER_NAME);//드라이버 등록
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);//접속하기
			Statement stmt = conn.createStatement();//워크시트 열기
			ResultSet rset = stmt.executeQuery(query);
			Employee emp = new Employee();
			if(rset.next()) {
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
			}
			else {
				emp = null;
			}
			conn.close();
			stmt.close();
			rset.close();
			return emp;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteEmployee(String empId) {
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
		try {
			Class.forName(DRIVER_NAME);//드라이버 등록
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);//접속하기
			Statement stmt = conn.createStatement();//워크시트 열기
			int result = stmt.executeUpdate(query);//쿼리문 실행 하고 결과값 result에 넣기
			if(result > 0) {
				//성공 -> commit
			}
			else {
				//실패 -> ROLLBACK
			}
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertEmployee(Employee emp) {
		String query = "INSERT INTO EMPLOYEE VALUES('"
				+ emp.getEmpId() +"','"
				+ emp.getEmpName() +"','"
				+ emp.getEmpNo() +"','"
				+ emp.getEmail() +"','"
				+ emp.getPhone() +"','"
				+ emp.getDeptCode() +"','"
				+ emp.getJobCode() +"','"
				+ emp.getSalLevel() +"',"
				+ emp.getSalary() +","
				+ emp.getBonus() +",'"
				+ emp.getManagerId() +"',SYSDATE,null,'N')";
		try {
			Class.forName(DRIVER_NAME);//드라이버 등록
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);//접속하기
			Statement stmt = conn.createStatement();//워크시트 열기
			int result = stmt.executeUpdate(query);//쿼리문 실행 하고 결과값 result에 넣기
			if(result > 0) {
				//성공 -> commit
			}
			else {
				//실패 -> ROLLBACK
			}
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Employee> selectAllEmployees() {
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> eList = null;
		try {
			Class.forName(DRIVER_NAME);//드라이버 등록
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);//접속하기
			Statement stmt = conn.createStatement();//워크시트 열기
			ResultSet rset = stmt.executeQuery(query);//쿼리문 실행 하고 결과값 rset에 넣기
			eList = new ArrayList<Employee>();
			while(rset.next()) {//rset에 저장된 행들이 더이상 안나올때 까지 반복
				Employee employee = new Employee();
				String empName = rset.getString("EMP_NAME");
				String empId = rset.getString("EMP_ID");
				String empNo = rset.getString("EMP_NO");
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String deptCode = rset.getString("DEPT_CODE");
				String jobCode = rset.getString("JOB_CODE");
				String salLevel = rset.getString("SAL_LEVEL");
				int salary = rset.getInt("SALARY");
				double bonus = rset.getDouble("BONUS");
				String managerId = rset.getString("MANAGER_ID");
				Date hireDate = rset.getDate("HIRE_DATE");
				Date entDate = rset.getDate("ENT_DATE");
				char entYn = rset.getString("ENT_YN").charAt(0);
				employee.setEmpId(empId);
				employee.setEmpName(empName);
				employee.setEmpNo(empNo);
				employee.setEmail(email);
				employee.setPhone(phone);
				employee.setDeptCode(deptCode);
				employee.setJobCode(jobCode);
				employee.setSalLevel(salLevel);
				employee.setSalary(salary);
				employee.setBonus(bonus);
				employee.setManagerId(managerId);
				employee.setHireDate(hireDate);
				employee.setEntDate(entDate);
				employee.setEntYn(entYn);
				eList.add(employee);//리스트에 모든 항목들 복사
			}
			//자원해제
			conn.close();
			stmt.close();
			rset.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}
}












