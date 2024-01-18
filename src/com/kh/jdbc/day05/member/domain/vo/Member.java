package com.kh.jdbc.day05.member.domain.vo;

import java.sql.Date;

	public class Member {
		private String memberId;
		private String memberPw;
		private String memberName;
		private char gender;
		private int age;
		private String email;
		private String address;
		private String hobby;
		private Date enrollDate;
		private String phone;
		
		public Member() {}
		
		public Member(String memberId, String memberPw, String memberName, char gender, int age, String email,
				String address, String hobby, String phone) {
			super();
			this.memberId = memberId;
			this.memberPw = memberPw;
			this.memberName = memberName;
			this.gender = gender;
			this.age = age;
			this.email = email;
			this.address = address;
			this.hobby = hobby;
			this.phone = phone;
		}
		
		
	
	
	
		public Member(String memberPw, String email, String address, String hobby, String phone) {
			super();
			this.memberPw = memberPw;
			this.email = email;
			this.address = address;
			this.hobby = hobby;
			this.phone = phone;
		}
	
		public String getMemberId() {
			return memberId;
		}
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		public String getMemberPw() {
			return memberPw;
		}
		public void setMemberPw(String memberPw) {
			this.memberPw = memberPw;
		}
		public String getMemberName() {
			return memberName;
		}
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		public char getGender() {
			return gender;
		}
		public void setGender(char gender) {
			this.gender = gender;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getHobby() {
			return hobby;
		}
		public void setHobby(String hobby) {
			this.hobby = hobby;
		}
		public Date getEnrollDate() {
			return enrollDate;
		}
		public void setEnrollDate(Date enrollDate) {
			this.enrollDate = enrollDate;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
	}
}
