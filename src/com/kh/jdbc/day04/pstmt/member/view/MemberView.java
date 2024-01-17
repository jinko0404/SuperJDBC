package com.kh.jdbc.day04.pstmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.pstmt.member.controller.MemberController;
import com.kh.jdbc.day04.pstmt.member.model.vo.Member;

public class MemberView {
	MemberController mCon;
	
	public MemberView() {
		mCon = new MemberController();
	}
	
	public void startProgram() {
		int choice = 0;
		end:
		do {
			choice = this.printMainMenu();
			switch(choice) {
			case 0 :
				String memberId = this.inputMemberId();
				String memberPw = this.inputMemberPw();
				Member member = mCon.printOneById2(memberId, memberPw);
				if(member != null) {
					this.printOneById(member);					
				}
				else {
					System.out.println("존재하지 않는 아이디거나"
							+ " 비밀번호가 틀렸습니다.");
				}
				break;
			case 1 : 
				List<Member> mList = mCon.printAll();
				this.printAllMember(mList);				
				break;
			case 2 : 
				memberId = this.inputMemberId();
				member = mCon.printOneById(memberId);
				//mCon.registerMember(member);
				if(member != null) {
					this.printOneById(member);					
				}
				else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			case 3 : 
				member = this.inputMember();
				if(member != null) {
					mCon.registerMember(member);	
					System.out.println("회원 등록이 완료되었습니다.");
				}
				else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			case 4 :
				memberId = this.inputMemberId();
				member = mCon.printOneById(memberId);
				if(member != null) {
					member = this.modifyMember();
					member.setMemberId(memberId);//누락주의
					mCon.modifyMember(member);
					System.out.println("수정이 완료되었습니다.");
				}
				else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			//Member member = mCon.printOneById(memberId);
			case 5 : 
				memberId = this.inputMemberId();
				member = mCon.printOneById(memberId);
				if(member != null) {
					mCon.removeMember(memberId);
					System.out.println("회원 정보 삭제가 완료되었습니다.");
				}
				else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			case -1 : 
				System.out.println("프로그램이 종료됩니다.");
				break end;
			default :
				System.out.println("1 ~ 6 사이 문자를 입력해주세요");
				break;
			}
		}while(choice != -1) ;
	}
	
	public int printMainMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 관리 프로그램 === ===");
		System.out.println("0. 로그인 정보 조회");
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 회원 검색 조회");
		System.out.println("3. 회원 정보 등록");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("-1. 프로그램 종료");
		System.out.print(">>");

		int choice = sc.nextInt();
		return choice;
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
	
	public String inputMemberId() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
	public String inputMemberPw() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		return memberPw;
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
}
