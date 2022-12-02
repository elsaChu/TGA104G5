package com.member.model;

import java.util.List;

public interface MemberDAOinterface {
	public void insert(MemberVO MemberVO);
	public void update(MemberVO MemberVO);
	public void delete(Integer number);
	public MemberVO findByPrimaryKey(Integer number);
	public List<MemberVO> getAll();
}
