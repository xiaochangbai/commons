package cn.xdd.utils;

import java.sql.SQLException;

import org.junit.Test;

import cn.xdd.utils.db.DBUtils;
import cn.xdd.utils.db.handle.BeanHandle;
import cn.xdd.utils.po.Student;
import cn.xdd.utils.po.User;

/**
 *@author: xchb
 *@date: 2019年12月25日下午6:10:55
 *@description: good good study,day day up
 */
public class DBUtilsTest {

	@Test
	public void testUpdate() throws SQLException {
		String sql = "update student set password=? where id=?";
		Object[] param = {22222,2};
		DBUtils.beginTransaction();
		int update = DBUtils.update(sql, param);
		DBUtils.commitTransaction();
		System.out.println("影响行数："+update);
	}
	
	@Test
	public void testQuery() throws InstantiationException, IllegalAccessException, SQLException {
		String sql = "select * from student where id = ?";
		Object[] param = {2};
		Student query = DBUtils.query(DBUtils.getConnection(), new BeanHandle<Student>(Student.class), sql, param);
		System.out.println(query);
	}
}
