package tw.com.tibame.member.model;

import java.util.List;

public interface MemberDAOinterface {
	public void insert(MemberVO MemberVO);
	public void update(MemberVO MemberVO);
	public void delete(Integer number);
	public MemberVO findByPrimaryKey(Integer number);
	public List<MemberVO> getAll();
	public List<MemberVO> findByAccount(String account);
	public List<MemberVO> findByEmail(String email);
}
