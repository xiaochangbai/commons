package cn.xdd.utils.db.handle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xdd.utils.db.DBUtils;

/**
 *@author: xchb
 *@param <T>
 *@date: 2019年12月26日下午12:05:53
 *@description: good good study,day day up
 *
 *List<Bean>处理器
 */
public class ListBeanHandle<T extends List<?>> implements ResultHandle<T> {
	
	private static Logger logger = LoggerFactory.getLogger(ListBeanHandle.class);
	
	private Class<?> beanClass = null; 
	
	private T t;
	
	public ListBeanHandle(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	@Override
	public T handel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {		
		System.out.println(t);
		if(rs == null) {
			logger.error("结果集为空");
			return null;
		}
		Object bean = null;
		Collection<Object> collection = new ArrayList<Object>();
		ResultSetBeanMapping bsm = new ResultSetBeanMapping();
		
		while(rs.next()) {
			bean = beanClass.newInstance();
			//结果集映射
			bsm.mappring(bean, rs);
			collection.add(bean);
		}
		return (T) collection;
	}

}
