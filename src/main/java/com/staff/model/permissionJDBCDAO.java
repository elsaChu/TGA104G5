package com.staff.model;

import static conn.DBConnection.PASSWORD;
import static conn.DBConnection.URL;
import static conn.DBConnection.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class permissionJDBCDAO implements permissionDAO_interface {

	private static final String INSERT_PERMISSION = "";

	@Override
	public permissionVO findByPrimaryKey(Integer permissionNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<permissionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
