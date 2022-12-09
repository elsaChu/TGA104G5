package tw.com.tibame.member.model;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.*;
import java.sql.Date;

public class MemberService {
	
	private MemberDAOinterface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	
	public MemberVO insertMember(String account,String password,String email,Date birthday,String name,String phoneNumber) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setEmail(email);
		memberVO.setBirthday(birthday);
		memberVO.setName(name);
		memberVO.setPhoneNumber(phoneNumber);
	
		dao.insert(memberVO);
		
		return memberVO;
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
	
	public List<MemberVO> findByAccount(String account) {
		// TODO Auto-generated method stub
		return dao.getAll().stream()
				.filter(e -> e.getAccount().equals(account))
				.collect(Collectors.toList());
	}
	public List<MemberVO> findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.getAll().stream()
				.filter(e -> e.getEmail().equals(email))
				.collect(Collectors.toList());
	}
	
	public List<MemberVO> findByPhoneNumber (String phoneNumber) {
		// TODO Auto-generated method stub
		return dao.getAll().stream()
				.filter(e -> e.getPhoneNumber().equals(phoneNumber))
				.collect(Collectors.toList());
	}

}
