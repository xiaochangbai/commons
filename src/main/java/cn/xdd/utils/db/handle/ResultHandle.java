package cn.xdd.utils.db.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *@author: xchb
 *@param <T>
 *@date: 2019年12月25日下午6:20:48
 *@description: 结果处理器规范
 */
public interface ResultHandle<T>{

	T handel(ResultSet rs)  throws SQLException, InstantiationException, IllegalAccessException;
}
