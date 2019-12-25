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
 *@author: xchb
 * @param <T>
 * @param <T>
 *@date: 2019年12月25日下午6:27:31
 *@description: good good study,day day up
 */
public class BeanHandle<T> implements ResultHandle<T> {
	
	private static Logger logger = LoggerFactory.getLogger(DBUtils.class);
	
	public Class<?> beanClass;
	
	public BeanHandle(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
	
	@Override
	public T handel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		T newInstance = null;
		if(rs.next()) {
			//1、实例化（要求Bean类中必须要提供一个无参构造）
			newInstance = (T) beanClass.newInstance();
			
			//2、根据setXxx()方法获取所有属性名称和类型
			Field[] declaredFields = beanClass.getDeclaredFields();
			List<Param> params = new ArrayList<BeanHandle<T>.Param>();
			for(int i=0;i<declaredFields.length;i++) {
				String name = declaredFields[i].getName();
				//3、根据属性名从结果集ResultSet中获取值
				Object object = null;
				try {
					object = rs.getObject(name);
				}catch(SQLException e) {
					logger.error(name+"属性在数据库中没有对应的列\n"+e.getMessage());
				}
				BeanHandle<T>.Param param = new Param(name,declaredFields[i].getType(),object);
				params.add(param);
			}
			System.out.println(params);
			
			//4、调用属性对应的set方法进行赋值
			Iterator<BeanHandle<T>.Param> iterator = params.iterator();
			while(iterator.hasNext()) {
				BeanHandle<T>.Param p = iterator.next();
				if(p.getValue() != null && p.getName() != null) {
					Method method = null ;
					//拼装setXxx方法
					String setMethodName = "set"+p.getName().substring(0, 1).toUpperCase()+p.getName().substring(1);
					try {
						method = beanClass.getMethod(setMethodName, p.getType());
					} catch (NoSuchMethodException | SecurityException e) {
						logger.debug(p.getName()+"属性的set方法没有发现【"+setMethodName+"】\n"+e.getMessage());
						//拼装isXxx方法
						String isMethodName = "is"+p.getName().substring(0, 1).toUpperCase()+p.getName().substring(1);
						try {
							method = beanClass.getMethod(isMethodName, p.getType());
						} catch (NoSuchMethodException | SecurityException e1) {
							logger.debug(p.getName()+"属性的is方法没有发现【"+isMethodName+"】\n"+e1.getMessage());
						}
					}
					
					//执行方法
					if(method == null) {
						logger.error(p.getName()+"属性，没有对应的处理方法，请检查属性是否采用的是包装类");
						continue;
					}
					try {
						method.invoke(newInstance, p.getValue());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						logger.error(method.getName()+"方法执行异常");
					}
				}
			}
		}
		return newInstance;
	}
	
	/**
	 * Bean属性类
	 * @author XDD
	 */
	private class Param{
		/**
		 * 属性名
		 */
		private String name;
		/**
		 * 属性类型
		 */
		private Class<?> type;
		
		/**
		 * 属性值
		 */
		private Object value;
		
		

		public Param() {
			super();
		}
		
		

		public Param(String name, Class<?> type) {
			super();
			this.name = name;
			this.type = type;
		}
		
		



		public Param(String name, Class<?> type, Object value) {
			super();
			this.name = name;
			this.type = type;
			this.value = value;
		}



		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Class<?> getType() {
			return type;
		}

		public void setType(Class<?> type) {
			this.type = type;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Param [name=" + name + ", type=" + type + ", value=" + value + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		String a = "asdfasf";
		String aa= "set"+a.substring(0, 1).toUpperCase()+a.substring(1);
		System.out.println(aa);
	}

}
