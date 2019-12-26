package cn.xdd.utils.db.handle;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xdd.utils.db.DBUtils;

/**
 *@author: xchb
 *@param <T>
 *@date: 2019年12月26日下午12:34:57
 *@description: good good study,day day up
 * Map结果处理器
 */
public class MapHandle<T extends Map<String, ?>> implements ResultHandle<T> {


	private static Logger logger = LoggerFactory.getLogger(MapHandle.class);
	
	@Override
	public T handel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		if(rs == null) {
			logger.error("结果集为空");
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		ResultSetMetaData metaData = rs.getMetaData();
		//获取列数
		int columnCount = metaData.getColumnCount();
		if(rs.next()) {
			//设值
			for(int i=1;i<=columnCount;i++) {
				String columnName = metaData.getColumnName(i);
				map.put(columnName, rs.getObject(i));
			}
		}
		return (T) map;
	}

}
