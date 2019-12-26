package cn.xdd.utils.db.handle;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: xchb
 * @param <T>
 * @date: 2019年12月26日下午2:34:04
 * @description: good good study,day day up
 * 
 * List<Map>结果处理器
 */
public class ListMapHandle<T extends List<Map<String, ?>>> implements ResultHandle<T> {

	private static Logger logger = LoggerFactory.getLogger(ListMapHandle.class);

	@Override
	public T handel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
		if (rs == null) {
			logger.error("结果集为空");
			return null;
		}

		List<Map<String, ?>> listMap = new ArrayList<Map<String, ?>>();
		Map<String, Object> map = null;
		ResultSetMetaData metaData = rs.getMetaData();
		// 获取列数
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			map = new HashMap<String, Object>();
			//设值
			for(int i=1;i<=columnCount;i++) {
				String columnName = metaData.getColumnName(i);
				map.put(columnName, rs.getObject(i));
			}
			listMap.add(map);
		}

		return (T) listMap;
	}

}
