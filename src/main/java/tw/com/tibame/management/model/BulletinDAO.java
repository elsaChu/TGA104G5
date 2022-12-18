package tw.com.tibame.management.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BulletinDAO implements BulletinDAOInterface{
	String driver = "com.mysql.cj.jdbc.Driver"; 
	String url = "jdbc:mysql://localhost:3306/TICK_IT_TEST?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	
	private static final String INSERT_STMT = 
			"INSERT INTO system_bulletin (bulletinName, bulletinContent, isTop, isOpen, bulletinDate, bulletinCreateDate) VALUES (?, ?, ?,?,?,?)";
	public void insert(BulletinVO bean) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps1 = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps1 = con.prepareStatement(INSERT_STMT);
			ps1.setString(1, bean.getBulletinName());
			ps1.setString(2, bean.getBulletinContent());
			ps1.setBoolean(3, bean.getIsTop());
			ps1.setBoolean(4, bean.getIsOpen());
			ps1.setTimestamp(5, bean.getBulletinDate());
			//get current time as a sql.Date object;
			java.sql.Timestamp createDate = new java.sql.Timestamp(System.currentTimeMillis());
			ps1.setTimestamp(6, createDate);
			ps1.executeUpdate();
			System.out.println("DAO: execute update, insert done");
//		Date convertToDateViaInstant(LocalDateTime dateToConvert) {
//    return java.util.Date
//      .from(dateToConvert.atZone(ZoneId.systemDefault())
//      .toInstant());
//			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(BulletinVO bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer bulletinID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BulletinVO findByPrimaryKey(Integer bulletinID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BulletinVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
