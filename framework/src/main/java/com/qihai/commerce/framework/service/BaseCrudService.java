package com.qihai.commerce.framework.service;

import java.util.List;
import java.util.Map;

/**
 * crud对应Service接口
 * 
 * @author zhugj
 * @version 2017年8月18日
 */
public interface BaseCrudService {
	
    <T> T queryObject(Long id);
	
    <T> List<T> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	<T> void save(T modelType);
	
	<T> void update(T modelType);
	
	<T> void delete(Long id);
	
	<T> void deleteBatch(Long[] ids);
	
}
