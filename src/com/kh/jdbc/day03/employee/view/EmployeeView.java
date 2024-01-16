package com.kh.jdbc.day03.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day03.employee.controller.EmployeeController;
import com.kh.jdbc.day03.employee.model.vo.Employee;

public class EmployeeView {
	private EmployeeController eCon;
	
	public EmployeeView() {
		eCon = new EmployeeController();
	}
	
	public void StartProgram() {
		int choice = 0;
		end:
		do {
			choice = this.printMainMenu();
			switch(choice) {
			case 1 : 
			//전체출력
				List<Employee> eList = eCon.printAllEmployees();
				this.displayAllEmployees(eList);
				break;
			case 2 : 
				String inputEmpId = this.InputEmployeeId();
				Employee emp = eCon.printOneEmployee(inputEmpId);
				if(emp != null) {
					this.printOneEmployee(emp);
				}
				else {
					System.out.println("존재하지 않는 사번 입니다.");
				}
				break;
			case 3 : 
				emp = this.inputEmployee();
				eCon.registerEmployee(emp);
				System.out.println("등록성공");
				break;
			case 4 : 
				inputEmpId = this.InputEmployeeId();
				emp = eCon.printOneEmployee(inputEmpId);
				if(emp != null) {
					Employee emp2 = this.modifyEmployee(inputEmpId);
					eCon.modifyEmployee(emp2);
					System.out.println(emp.getEmpName() + " 사원의 정보 수정 완료 !");
				}
				else {
					System.out.println("존재하지 않는 사번 입니다.");
				}
				break;
			case 5 : 
				inputEmpId = this.InputEmployeeId();
				emp = eCon.printOneEmployee(inputEmpId);
				if(emp != null) {
					eCon.removeEmployee(inputEmpId);
					System.out.println(emp.getEmpName() + " 사원의 정보 삭제 완료 !");
				}
				else {
					System.out.println("존재하지 않는 사번 입니다.");
				}
				break;
			case 6 :
				System.out.println("프로그램을 종료합니다.");
				break end;
			default :
				System.out.println("1 ~ 6 사이 숫자를 입력해주세요");
				break;
			}
		}while(choice != -1);
	}
	
	private int printMainMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 직원 관리 프로그램 === ===");
		System.out.println("1. 직원 정보 전체 조회");
		System.out.println("2. 직원 정보 검색 조회");
		System.out.println("3. 사원 정보 등록");
		System.out.println("4. 사원 정보 수정");
		System.out.println("5. 사원 정보 삭제");
		System.out.println("6. 프로그램 종료");
		System.out.print(">>");

		int choice = sc.nextInt();
		return choice;
	}
	
	private Employee modifyEmployee(String empId) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 사원 정보 수정 === ===");
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		
		Employee emp = new Employee(empId, email, phone);
		return emp;
	}
	
	private String InputEmployeeId() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("사번을 입력해주세요 : ");
		String empId = sc.next();
		return empId;
	}
	
	private Employee inputEmployee() {
		//View에서 입력받고 Controller 넘겨준 후 DAO에서 저장하도록 하기 위한 Start!!
		//->입력받은 값으로 저장하기 위함
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 사원 정보 등록 === ===");
		System.out.print("사번 : ");
		String empId = sc.next();
		System.out.print("사원명 : ");
		String empName = sc.next();
		System.out.print("주민번호 : ");
		String empNo = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		if (phone.length() > 12) {
		    // 전화번호가 12자를 초과하면 잘라냄
		    phone = phone.substring(0, 12);
		}
		System.out.print("부서코드 : ");
		String deptCode = sc.next();
		System.out.print("직급코드 : ");
		String jobCode = sc.next();
		System.out.print("급여등급 : ");
		String salLevel = sc.next();
		System.out.print("급여(단위 : 원) : ");
		int salary = sc.nextInt();
		System.out.print("보너스 : ");
		double bonus = sc.nextDouble();
		System.out.print("매니저 사번 : ");
		String managerId = sc.next();
		Employee emp = new Employee(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId);
		return emp;
	}
	
	private void printOneEmployee(Employee emp) {
		System.out.println("=== === 사원 정보 검색 출력 === ===");
		System.out.printf("직원명: %-10s\t 사원번호: %-5s\t 주민등록번호: %-14s\t 이메일: %-20s\t 전화번호: %-15s\t 부서코드: %-5s\t 직급코드: %-5s\t 급여등급: %-5s\t 급여: %-10d\t 보너스율: %-5f\t 관리자사번: %-5s\t 입사일: %-20s\t 퇴사일: %-20s\t 퇴직여부: %-2c\n"
				,emp.getEmpName()
				, emp.getEmpId()
				, emp.getEmpNo()
				, emp.getEmail()
				, emp.getPhone()
				, emp.getDeptCode()
				, emp.getJobCode()
				, emp.getSalLevel()
				, emp.getSalary()
				, emp.getBonus()
				, emp.getManagerId()
				, emp.getHireDate()
				, emp.getEntDate()
				, emp.getEntYn());
	}
	
	private void displayAllEmployees(List<Employee> eList) {
		System.out.println("=== === 사원 정보 전체 출력 === ===");
		for(Employee emp : eList) {
			System.out.printf("직원명: %-10s\t 사원번호: %-5s\t 주민등록번호: %-14s\t 이메일: %-20s\t 전화번호: %-15s\t 부서코드: %-5s\t 직급코드: %-5s\t 급여등급: %-5s\t 급여: %-10d\t 보너스율: %-5f\t 관리자사번: %-5s\t 입사일: %-20s\t 퇴사일: %-20s\t 퇴직여부: %-2c\n"
			,emp.getEmpName()
			, emp.getEmpId()
			, emp.getEmpNo()
			, emp.getEmail()
			, emp.getPhone()
			, emp.getDeptCode()
			, emp.getJobCode()
			, emp.getSalLevel()
			, emp.getSalary()
			, emp.getBonus()
			, emp.getManagerId()
			, emp.getHireDate()
			, emp.getEntDate()
			, emp.getEntYn());				
		}
	}
	
	
}














