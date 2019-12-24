package cn.xdd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@author: xchb
 *@date: 2019年12月16日下午3:26:01
 *@description: good good study,day day up
 *JDBC工具类
 */
public class DBUtils {
	
	private static Properties properties = null;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	private static Logger logger = LoggerFactory.getLogger(DBUtils.class);
	
	/**
	 * 事务专用连接
	 */
	private static ThreadLocal<Connection> con = new ThreadLocal<Connection>();
	
	/**
	 * 从配置文件中获取数据，并加载驱动
	 */
	static {
		try {
			InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
			properties = new Properties();
			properties.load(inputStream);
			String className = properties.getProperty("mysql.driverClassName");
			URL = properties.getProperty("mysql.url");
			USERNAME = properties.getProperty("mysql.userName");
			PASSWORD = properties.getProperty("mysql.password");			
			Class.forName(className);
			inputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
			
		} 
	}
	
	
	/**
	 * 获取连接
	 * 如果已经开启事务，则直接返回一个事务连接
	 * 若没有开启事务，则返回一个新的连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if(con.get() != null) {
			return con.get();
		}
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	
	
	/**
	 * 关闭资源
	 * @param rs
	 * @param st
	 * @param ct
	 * @return
	 */
	public static boolean close(ResultSet rs,Statement st,Connection ct) {
		try {
			if(rs != null)
				rs.close();
			if(st != null) 
				st.close();
			if(ct != null)
				ct.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 开启事务
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		if(con.get() == null) {
			con.set(getConnection());
			con.get().setAutoCommit(false);
		}
	}
	
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		if(con.get() != null) {
			con.get().commit();
			con.get().close();
			con.remove();
		}
	}
	
	/**
	 * 回滚异常
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		if(con != null) {
			con.get().rollback();
			con.get().close();
			con.remove();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		DBUtils.getConnection();
	}

}
