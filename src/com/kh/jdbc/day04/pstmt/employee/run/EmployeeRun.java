package com.kh.jdbc.day04.pstmt.employee.run;

import com.kh.jdbc.day04.pstmt.employee.view.EmployeeView;

public class EmployeeRun {

	public static void main(String[] args) {
		EmployeeView view = new EmployeeView();
		try {
			view.StartProgram();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
