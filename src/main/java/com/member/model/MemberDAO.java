package com.member.model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberDAO implements MemberDAOinterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO MEMBER (number,account"
			+ ",password,email,birthday,name,phoneNumber,subscription"
			+ ",createDate,pass,IDNumber,phone2,postalCode,address)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT number,account,password,email,birthday,name,phoneNumber,"
			+ "subscription,createDate,pass,IDNumber,phone2,postalCode,address FROM MEMBER";
	private static final String GET_ONE_STMT = "SELECT number,account,password,email,birthday,name,phoneNumber,"
			+ "subscription,createDate,pass,IDNumber,phone2,postalCode,address FROM MEMBER where number = ?";
	private static final String DELETE = "DELETE FROM MEMBER where number = ?";
	private static final String UPDATE = "UPDATE MEMBER setaccount=? password=? email=? birthday=? name=? phoneNumber=? "
			+"subscription=? createDate=? pass=? IDNumber=? phone2=? postalCode=? address=? ";
	@Override
	public void insert(MemberVO MemberVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, MemberVO.getNumber());
			pstmt.setString(2, MemberVO.getAccount());
			pstmt.setString(3, MemberVO.getPassword());
			pstmt.setString(4, MemberVO.getEmail());
			pstmt.setDate(5, MemberVO.getBirthday());
			pstmt.setString(6, MemberVO.getName());
			pstmt.setString(7, MemberVO.getPhoneNumber());
			pstmt.setBoolean(8, MemberVO.getSubscription());
			pstmt.setTimestamp(9, MemberVO.getCreateDate());
			pstmt.setBoolean(10, MemberVO.getPass());
			pstmt.setString(11, MemberVO.getIDNumber());
			pstmt.setString(12, MemberVO.getPhone2());
			pstmt.setString(13, MemberVO.getPostalCode());
			pstmt.setString(14, MemberVO.getAddress());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public MemberVO findByPrimaryKey(Integer userId) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setNumber(rs.getInt("number"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setName(rs.getString("mame"));
				memberVO.setPhoneNumber(rs.getString("phoneNumber"));
				memberVO.setSubscription(rs.getBoolean("subscription"));
				memberVO.setCreateDate(rs.getTimestamp("createDate"));
				memberVO.setPass(rs.getBoolean("pass"));
				memberVO.setIDNumber(rs.getString("idNumber"));
				memberVO.setPhone2(rs.getString("phone2"));
				memberVO.setPostalCode(rs.getString("postalCode"));
				memberVO.setAddress(rs.getString("address"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setNumber(rs.getInt("number"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhoneNumber(rs.getString("phoneNumber"));
				memberVO.setSubscription(rs.getBoolean("subscription"));
				memberVO.setCreateDate(rs.getTimestamp("createDate"));
				memberVO.setPass(rs.getBoolean("pass"));
				memberVO.setIDNumber(rs.getString("idNumber"));
				memberVO.setPhone2(rs.getString("phone2"));
				memberVO.setPostalCode(rs.getString("postalCode"));
				memberVO.setAddress(rs.getString("address"));
				list.add(memberVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public void update(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, MemberVO.getNumber());
			pstmt.setString(2, MemberVO.getAccount());
			pstmt.setString(3, MemberVO.getPassword());
			pstmt.setString(4, MemberVO.getEmail());
			pstmt.setDate(5, MemberVO.getBirthday());
			pstmt.setString(6, MemberVO.getName());
			pstmt.setString(7, MemberVO.getPhoneNumber());
			pstmt.setBoolean(8, MemberVO.getSubscription());
			pstmt.setTimestamp(9, MemberVO.getCreateDate());
			pstmt.setBoolean(10, MemberVO.getPass());
			pstmt.setString(11, MemberVO.getIDNumber());
			pstmt.setString(12, MemberVO.getPhone2());
			pstmt.setString(13, MemberVO.getPostalCode());
			pstmt.setString(14, MemberVO.getAddress());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}
	@Override
	public void delete(Integer number) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, number);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
}
