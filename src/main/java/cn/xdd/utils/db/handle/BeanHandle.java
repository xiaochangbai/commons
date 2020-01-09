package cn.xdd.utils.db.handle;

import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xdd.utils.db.DBUtils;

/**
 *	@author: xchb
 *  @param <T>
 *  @param <T>
 *	@date: 2019年12月25日下午6:27:31
 *	@description: JavaBean处理器
 */

public class BeanHandle<T> implements ResultHandle<T> {
	
	private static Logger logger = LoggerFactory.getLogger(BeanHandle.class);
	
	public Class<T> beanClass;
	
	public BeanHandle(Class<T> beanClass) {
		this.beanClass = beanClass;
	}
	
	@Override
	public T handel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		T newInstance = null;
		if(rs.next()) {
			//1、实例化（要求Bean类中必须要提供一个无参构造）
			newInstance = beanClass.newInstance();
			//结果映射
			ResultSetBeanMapping rbm = new ResultSetBeanMapping();
			rbm.mappring(newInstance, rs);
		}
		return newInstance;
	}
	
}
