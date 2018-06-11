package com.qihai.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthDataRangeDao;
import com.qihai.permission.dao.AuthDimensionDao;
import com.qihai.permission.dao.AuthDimensionValueDao;
import com.qihai.permission.dao.AuthDimensionValueScopeDao;
import com.qihai.permission.dto.AuthDataRangeDTO;
import com.qihai.permission.dto.AuthDimensionValueDTO;
import com.qihai.permission.dto.AuthDimensionValueScopeDTO;
import com.qihai.permission.entity.AuthDataRangeEntity;
import com.qihai.permission.entity.AuthDimensionEntity;
import com.qihai.permission.entity.AuthDimensionValueEntity;
import com.qihai.permission.entity.AuthDimensionValueScopeEntity;
import com.qihai.permission.service.AuthDataRangeService;

@Service("authDataRangeService")
public class AuthDataRangeServiceImpl extends ServiceImpl<AuthDataRangeDao, AuthDataRangeEntity>
		implements AuthDataRangeService {

	@Autowired
	private AuthDimensionDao authDimensionDao;

	@Autowired
	private AuthDataRangeDao authDataRangeDao;

	@Autowired
	private AuthDimensionValueDao authDimensionValueDao;

	@Autowired
	private AuthDimensionValueScopeDao authDimensionValueScopeDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthDataRangeEntity authDataRange) {
		Page<AuthDataRangeEntity> page = this.selectPage(new Query<AuthDataRangeEntity>(params).getPage(),
				new EntityWrapper<AuthDataRangeEntity>(authDataRange));

		return new PageUtils(page);
	}

	@Override
	@Transactional
	public void saveOrUpdate(AuthDataRangeDTO authDataRangeDTO) {
		Long dataRangeId = authDataRangeDTO.getId();
		saveOrUpdate(authDataRangeDTO, dataRangeId);
	}

	@Override
	public AuthDataRangeDTO selectDataRangeById(Long id) {
		AuthDataRangeEntity dataRangeEntity = authDataRangeDao.selectById(id);
		AuthDataRangeDTO authDataRangeDTO = new AuthDataRangeDTO();
		authDataRangeDTO.setId(dataRangeEntity.getId());
		authDataRangeDTO.setDataRangeName(dataRangeEntity.getDataRangeName());
		authDataRangeDTO.setDescription(dataRangeEntity.getDescription());

		List<AuthDimensionValueDTO> authDimensionValues = authDataRangeDTO.getAuthDimensionValue();
		// 获取所有维度定义
		List<AuthDimensionEntity> listAuthDimensions = authDimensionDao.selectList(new EntityWrapper<AuthDimensionEntity>());
		for (AuthDimensionEntity authDimensionEntity : listAuthDimensions) {
			System.out.println(authDimensionEntity.getDimensionName());
			AuthDimensionValueDTO authDimensionValueDTO = new AuthDimensionValueDTO();
			authDimensionValueDTO.setAuthDimensionId(authDimensionEntity.getId());
			authDimensionValueDTO.setDimensionName(authDimensionEntity.getDimensionName());
			// 获取某个维度的所有的取值
			List<String> dimensionValue = authDimensionValueScopeDao.getDimensionValue(id, authDimensionEntity.getId());
			AuthDimensionValueScopeDTO authDimensionValueScopeDTO = new AuthDimensionValueScopeDTO();
			authDimensionValueScopeDTO.setDimensionValue(dimensionValue);
			authDimensionValueDTO.getDimensionValueScopes().add(authDimensionValueScopeDTO);
			authDimensionValues.add(authDimensionValueDTO);
		}

		return authDataRangeDTO;

	}

	// 解除数据范围与维度之间的关联，逻辑删除
	private void relieveDataRangeDimessionRelation(Long dataRangeId) {
		AuthDimensionValueEntity authDimensionValueEntityTemp = new AuthDimensionValueEntity();
		authDimensionValueEntityTemp.setDataRangeId(dataRangeId);
		List<AuthDimensionValueEntity> list = authDimensionValueDao
				.selectList(new EntityWrapper<AuthDimensionValueEntity>(authDimensionValueEntityTemp));

		List<Long> authDimensionValueIds = new ArrayList<Long>();
		List<Long> authDimensionValueScopeIds = new ArrayList<Long>();
		for (AuthDimensionValueEntity authDimensionValueEntity : list) {
			Long authDimensionValueId = authDimensionValueEntity.getId();
			authDimensionValueIds.add(authDimensionValueId);
			AuthDimensionValueScopeEntity authDimensionValueScopeEntityTemp = new AuthDimensionValueScopeEntity();
			authDimensionValueScopeEntityTemp.setDimensionValueId(authDimensionValueId);
			List<AuthDimensionValueScopeEntity> selectList = authDimensionValueScopeDao
					.selectList(new EntityWrapper<AuthDimensionValueScopeEntity>(authDimensionValueScopeEntityTemp));
			for (AuthDimensionValueScopeEntity authDimensionValueScopeEntity : selectList) {
				Long authDimensionValueScopeId = authDimensionValueScopeEntity.getId();
				authDimensionValueScopeIds.add(authDimensionValueScopeId);
			}
		}

		if (authDimensionValueIds != null && authDimensionValueIds.size() > 0) {
			authDimensionValueDao.deleteBatchIds(authDimensionValueIds);
		}

		if (authDimensionValueScopeIds != null && authDimensionValueScopeIds.size() > 0) {
			authDimensionValueScopeDao.deleteBatchIds(authDimensionValueScopeIds);
		}

	}

	// 添加数据范围
	private void saveOrUpdate(AuthDataRangeDTO authDataRangeDTO, Long dataRangeId) {
		AuthDataRangeEntity authDataRangeEntity = new AuthDataRangeEntity();
		authDataRangeEntity.setDataRangeName(authDataRangeDTO.getDataRangeName());
		authDataRangeEntity.setDescription(authDataRangeDTO.getDescription());
		if (dataRangeId == null) {
			// 添加数据范围
			authDataRangeDao.insert(authDataRangeEntity);
			dataRangeId=authDataRangeEntity.getId();
		} else {
			// 修改数据范围，先修改数据范围表，再解除关联关系（逻辑删除关联关系），再重新插入关联关系
			authDataRangeEntity.setId(dataRangeId);
			// 修改数据范围表
			authDataRangeDao.updateById(authDataRangeEntity);
			// 逻辑删除关联关系表
			relieveDataRangeDimessionRelation(dataRangeId);
		}
		// 保存关联关系
		saveDataRangeDimensionRelation(authDataRangeDTO, dataRangeId);

	}

	// 插入维度和数据范围之间的关联
	private void saveDataRangeDimensionRelation(AuthDataRangeDTO authDataRangeDTO, Long dataRangeId) {
		// 获取传入的关联的维度，可能会与多个维度相关联
		List<AuthDimensionValueDTO> authDimensionValue = authDataRangeDTO.getAuthDimensionValue();
		if (authDimensionValue != null) {
			for (AuthDimensionValueDTO authDimensionValueDTO : authDimensionValue) {
				Long authDimensionId = authDimensionValueDTO.getAuthDimensionId();
				AuthDimensionValueEntity authDimensionValueEntity = new AuthDimensionValueEntity();
				authDimensionValueEntity.setDataRangeId(dataRangeId);
				authDimensionValueEntity.setAuthDimensionId(authDimensionId);
				// 插入数据范围和维度关联表
				authDimensionValueDao.insert(authDimensionValueEntity);
				Long authDimensionValueId = authDimensionValueEntity.getId();
				// 获取某个维度下传入的维度的值，一个维度下可能会有多个值传过来
				List<AuthDimensionValueScopeDTO> dimensionValueScopes = authDimensionValueDTO.getDimensionValueScopes();
				if (dimensionValueScopes != null) {
					for (AuthDimensionValueScopeDTO authDimensionValueScopeDTO : dimensionValueScopes) {
						List<String> dimensionValues = authDimensionValueScopeDTO.getDimensionValue();
						for (String dimensionValue : dimensionValues) {
							AuthDimensionValueScopeEntity authDimensionValueScopeEntity = new AuthDimensionValueScopeEntity();
							authDimensionValueScopeEntity.setDimensionValueId(authDimensionValueId);
							authDimensionValueScopeEntity.setDimensionValue(dimensionValue);
							// 插入维度关联值表
							authDimensionValueScopeDao.insert(authDimensionValueScopeEntity);
						}
					}
				}
			}
		}
	}

}
