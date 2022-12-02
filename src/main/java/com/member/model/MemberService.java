package com.member.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class MemberService {
	
	private MemberDAOinterface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	
	public MemberVO addMember(Integer number,String account,String password,String email,Date birthday,String name,
			String phoneNumber/*,Boolean subscription,Timestamp createDate,Boolean pass,
			String idNumber,String phone2,String postalCode,String address*/) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setEmail(email);
		memberVO.setBirthday(birthday);
		memberVO.setName(name);
		memberVO.setPhoneNumber(phoneNumber);
/*		memberVO.setSubscription(subscription);
		memberVO.setCreateDate(createDate);
		memberVO.setPass(pass);
		memberVO.setIDNumber(idNumber);
		memberVO.setPhone2(phone2);
		memberVO.setPostalCode(postalCode);
		memberVO.setAddress(address);	
		memberVO.setNumber(number);
*/	
		dao.insert(memberVO);
		
		return memberVO;
	}
	
	// 預留給 Struts 2 或 Spring MVC 用
	public void addMember(MemberVO memberVO) {
		dao.insert(memberVO);
	}

	public MemberVO updateMember(Integer number,String account,String password,String email,Date birthday,String name,
			String phoneNumber/*,Boolean subscription,Timestamp createDate,Boolean pass,
			String idNumber,String phone2,String postalCode,String address*/) {
		
		MemberVO memberVO = new MemberVO();

		memberVO.setNumber(number);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setEmail(email);
		memberVO.setBirthday(birthday);
		memberVO.setName(name);
		memberVO.setPhoneNumber(phoneNumber);
/*		memberVO.setSubscription(subscription);
		memberVO.setCreateDate(createDate);
		memberVO.setPass(pass);
		memberVO.setIDNumber(idNumber);
		memberVO.setPhone2(phone2);
		memberVO.setPostalCode(postalCode);
		memberVO.setAddress(address);
*/

//		return dao.findByPrimaryKey(number);
		dao.update(memberVO);
//		return memberVO;
		return dao.findByPrimaryKey(number);
	}

	// 預留給 Struts 2 用的
	public void updateMember(MemberVO memberVO) {
		dao.update(memberVO);
	}
	
	public void deleteMember(Integer number) {
		dao.delete(number);
	}
	
	public MemberVO getOneMember(Integer number) {
		return dao.findByPrimaryKey(number);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
}
