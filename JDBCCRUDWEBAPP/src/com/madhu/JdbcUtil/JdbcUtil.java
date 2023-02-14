package com.madhu.JdbcUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jdk.internal.dynalink.beans.StaticClass;

public class JdbcUtil {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static Connection getJdbConnection() throws IOException, SQLException {
		//return physicalConnection();
		
		HikariConfig config = new HikariConfig("C:\\Users\\madha\\git\\repository3\\JDBCCRUDWEBAPP\\src\\com\\madhu\\properties\\application.properties");
		HikariDataSource hikariDataSource = new HikariDataSource(config);
		return hikariDataSource.getConnection();
	}

	private static Connection physicalConnection() throws FileNotFoundException, IOException, SQLException {
		FileInputStream fis = new FileInputStream("C:\\Users\\madha\\git\\repository3\\JDBCCRUDWEBAPP\\src\\com\\madhu\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"), properties.getProperty("password"));
		//Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
		System.out.println("Connection established...");
		return connection;
	}

	public static void closeResources(Connection connection, Statement statement, ResultSet resultSet)
			throws SQLException {

		if (connection != null)
			connection.close();

		if (statement != null)
			statement.close();

		if (resultSet != null)
			resultSet.close();

	}

}
