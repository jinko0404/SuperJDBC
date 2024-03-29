package com.kh.jdbc.day05.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day05.member.domain.vo.Member;
import com.kh.jdbc.day05.member.controller.MemberController;

public class MemberView {
	MemberController mCon;
	
	public MemberView(){
		mCon = new MemberController();
	}
	
	public void startProgram() {
		List<Member> mList = null;
		Member member = null;
		String memberId = "";
		String memberPw = "";
		end:
		while(true) {
			int choice = this.mainMenu();
			switch(choice) {
			case 0 : 
				memberId = this.inputMemberId();
				memberPw = this.inputMemberPw();
				member = mCon.printOneByLogin(memberId, memberPw);
				if(member != null) {
					this.printOneById(member);
				}
				else {
					System.out.println("존재하지 않는 아이디거나"
							+ " 비밀번호가 틀렸습니다.");
				}
				break;
			case 1 : 
				mList = mCon.printAll();
				this.printAllMember(mList);
				break;
			case 2 : 
				member = this.inputMember();
				if(member != null) {
					mCon.registerMember(member);	
					System.out.println("회원 등록이 완료되었습니다.");
				}
				else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			case 3 : 
				memberId = this.inputMemberId();
				member = mCon.printOneById(memberId);
				if(member != null) {
					member = this.modifyMember();
					member.setMemberId(memberId);//누락주의
					mCon.modifyMember(member);
					this.displayMessage("수정이 완료되었습니다.");
				}
				else {
					this.displayMessage("존재하지않는 아이디 입니다.");
				}
				break;
			case 4 : 
				memberId = this.inputMemberId();
				member = mCon.printOneById(memberId);
				if(member != null) {
					mCon.removeMember(memberId);
					this.displayMessage("삭제가 완료되었습니다.");
				}
				else {
					this.displayMessage("존재하지않는 아이디 입니다.");
				}
				break;
			case 9 :
				this.displayMessage("프로그램이 종료되었습니다.");
				break end;
			}
		}
	}
	
	private int mainMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 관리 프로그램 === ===");
		System.out.println("0. 회원 로그인");
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 회원 정보 등록");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 정보 삭제");
		System.out.println("9. 프로그램 종료");
		System.out.print(">>");

		int choice = sc.nextInt();
		return choice;
	}
	
	private String inputMemberId() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
	private String inputMemberPw() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		return memberPw;
	}
	
	private void displayMessage(String msg) {
		System.out.println(msg);
	}
	
	public void printOneById(Member member) {
		System.out.println("=== ===회원 정보 출력=== ===");
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %c"
					+ ", 이메일 : %s, 전화번호 : %s, 주소 : %s, 취미 : %s,"
					+ " 가입날짜 : %s\n"
					, member.getMemberName()
					, member.getAge()
					, member.getMemberId()
					, member.getGender()
					, member.getEmail()
					, member.getPhone()
					, member.getAddress()
					, member.getHobby()
					, member.getEnrollDate());
	}
	
	public void printAllMember(List<Member> mList) {
		System.out.println("=== ===회원 정보 전체 출력=== ===");
		for(Member member : mList) {
			System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %c"
					+ ", 이메일 : %s, 전화번호 : %s, 주소 : %s, 취미 : %s,"
					+ " 가입날짜 : %s\n"
					, member.getMemberName()
					, member.getAge()
					,member.getMemberId()
					,member.getGender()
					,member.getEmail()
					,member.getPhone()
					,member.getAddress()
					,member.getHobby()
					,member.getEnrollDate());
		}
	}
	
	public Member inputMember() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== ===회원 정보 입력=== ===");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M, F) : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호(- 없이) : ");
		String phone = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member =new Member(memberId, memberPw, memberName, gender, age, email, address, hobby, phone);
		return member;
	}
	
	public Member modifyMember() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원정보 수정 === ===");
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호(- 없이) : ");
		String phone = sc.next();
		sc.nextLine();
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member = new Member(memberPw, email, address, hobby, phone);
		return member;
	}
}























