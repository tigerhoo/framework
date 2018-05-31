package com.qihai.commerce.framework.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao
 * @author zhugj
 * @email zhuguojin@qihaiyun.com
 * @date 2017-08-17 0:00
 * @version 1.0.0 
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	void update(T t);
	
	int update(Map<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] id);

	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
