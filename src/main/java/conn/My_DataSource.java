package conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class My_DataSource {
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TICKIT");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}	
//		
//	public static DataSource getDataSource() throws SQLException {
//			return ds;
//	}
}
