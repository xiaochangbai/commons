package cn.xdd.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.xdd.utils.db.DBUtils;
import cn.xdd.utils.db.handle.BeanHandle;
import cn.xdd.utils.db.handle.ListBeanHandle;
import cn.xdd.utils.db.handle.ListMapHandle;
import cn.xdd.utils.db.handle.MapHandle;
import cn.xdd.utils.po.Student;
import cn.xdd.utils.po.User;

/**
 *@author: xchb
 *@date: 2019年12月25日下午6:10:55
 *@description: good good study,day day up
 */
public class DBUtilsTest extends A{

	//@Override
	public void hello() {
		
	}
	
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
		String sql = "select * from student";
		Object[] param = {};
		List<Map<String, ?>> query = DBUtils.query(DBUtils.getConnection(), new ListMapHandle<>(), sql, param);
		System.out.println(query);
	}
	
	@Test
	public void t() {
		float [][] f1 = {{1.2f,2.4f},{4.5f,5.6f}};
		Object o = f1;
		f1[1] = (float[]) o;
		float a =1.3f;
	}
}
class A{
	private void hello() {
		
	}
}