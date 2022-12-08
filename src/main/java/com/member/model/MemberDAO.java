package com.member.model;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class MemberDAO implements MemberDAOinterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TICK_IT?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO MEMBER (account,password,email,birthday,name,phoneNumber)VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT account,password,email,birthday,name,phoneNumber FROM member";
	private static final String GET_EMAIL_STMT = "SELECT account,password,email,birthday,name,phoneNumber FROM member where upper(email) like upper(?)";
	@Override
	public void insert(MemberVO MemberVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, MemberVO.getAccount());
			pstmt.setString(2, MemberVO.getPassword());
			pstmt.setString(3, MemberVO.getEmail());
			pstmt.setDate(4, MemberVO.getBirthday());
			pstmt.setString(5, MemberVO.getName());
			pstmt.setString(6, MemberVO.getPhoneNumber());
			pstmt.executeUpdate();
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
	public void update(MemberVO MemberVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer number) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public MemberVO findByPrimaryKey(Integer number) {
		// TODO Auto-generated method stub
		return null;
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
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhoneNumber(rs.getString("phoneNumber"));
				list.add(memberVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}	catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public List<MemberVO> findByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MemberVO> findByEmail(String email) {
		 List<MemberVO> list = new ArrayList<MemberVO>();
		 MemberVO memberVO = null;
		  
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null; 
		  
		  try {
		   Class.forName(driver);
           con = DriverManager.getConnection(url, userid, passwd);

		   pstmt = con.prepareStatement(GET_EMAIL_STMT);
		   pstmt.setString(1, email);
		   rs = pstmt.executeQuery();

		   while (rs.next()) {
		    
			   memberVO = new MemberVO();
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPhoneNumber(rs.getString("phoneNumber"));
		    list.add(memberVO); // Store the row in the list
		   }

		   // Handle any driver errors
		  } catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
				// Handle any SQL errors
		}catch (SQLException se) {
		   throw new RuntimeException("A database error occured. "
		     + se.getMessage());
		   // Clean up JDBC resources
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
	
}
