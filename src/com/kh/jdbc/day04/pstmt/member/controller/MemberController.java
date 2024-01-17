package com.kh.jdbc.day04.pstmt.member.controller;

import java.util.List;

import com.kh.jdbc.day04.pstmt.member.model.dao.MemberDao;
import com.kh.jdbc.day04.pstmt.member.model.vo.Member;

public class MemberController {
		//List<Member> memberList;
		MemberDao mDao;
		
		public MemberController() {
			mDao = new MemberDao();
		}
		
		public Member printOneById(String memberId) {
			Member member = mDao.selectOneById(memberId);
			return member;
		}
		public Member printOneById2(String memberId, String memberPw) {
			Member member = mDao.selectOneById2(memberId, memberPw);
			return member;
		}
		
		public List<Member> printAll() {
			List<Member> mList = mDao.selectAll();
			return mList;
		}
		
		public void registerMember(Member member) {
			mDao.insertMember(member);
		}
		
		public void removeMember(String memberId) {
			mDao.deleteMember(memberId);
		}
		
		public void modifyMember(Member member) {
			mDao.updateMember(member);
		}
}
