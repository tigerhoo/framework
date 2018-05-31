package com.qihai.commerce.framework.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.qihai.commerce.framework.dao.BaseDao;
import com.qihai.commerce.framework.exception.ServiceException;
import com.qihai.commerce.framework.service.BaseCrudService;

/**
 * 基础Service的抽象类
 * 
 * @author zhugj
 * @version 2017年8月18日
 */
@SuppressWarnings("rawtypes")
public abstract class BaseCrudServiceImpl implements BaseCrudService {

	private BaseDao baseDao;

	@PostConstruct
	private void initConfig() {
		this.baseDao = init();
	}

	public abstract BaseDao init();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T queryObject(Long id) throws ServiceException {
		try {
			return (T) baseDao.queryObject(id);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryList(Map<String, Object> map) {
		try {
			return baseDao.queryList(map);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int queryTotal(Map<String, Object> map) {
		try {
			return baseDao.queryTotal(map);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void save(T entity) {
		try {
			baseDao.save(entity);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void update(T entity) {
		try {
			baseDao.update(entity);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			baseDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void deleteBatch(Long[] ids) {
		try {
		    baseDao.deleteBatch(ids);
		} catch (Exception e) {
			throw new ServiceException("",e);
		}
	}
}
