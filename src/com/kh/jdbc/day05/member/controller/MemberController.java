package com.kh.jdbc.day05.member.controller;

import java.util.List;

import com.kh.jdbc.day05.member.domain.vo.Member;
import com.kh.jdbc.day05.member.domain.dao.MemberDao;

public class MemberController {
	MemberDao mDao;
	
	public MemberController() {
		mDao = new MemberDao();
	}
	
	public Member printOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	
	public Member printOneByLogin(String memberId, String memberPw) {
		Member member = mDao.selectOneByLogin(memberId, memberPw);
		return member;
	}
	
	public List<Member> printAll() {
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	
	public void registerMember(Member member) {
		mDao.insertMember(member);
	}
	
	public void modifyMember(Member member) {
		mDao.updateMember(member);
	}
	
	public void removeMember(String memberId) {
		mDao.deleteMember(memberId);
	}
}
