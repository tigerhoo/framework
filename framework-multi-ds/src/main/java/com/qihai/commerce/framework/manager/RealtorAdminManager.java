package com.qihai.commerce.framework.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihai.commerce.framework.dao.RealtorAdminMapper;
import com.qihai.commerce.framework.domain.RealtorAdmin;

/**
 * Author lv bin
 * @date 2016/11/4 13:58
 * version V1.0.0
 */
@Service
public class RealtorAdminManager {

    @Autowired
    private RealtorAdminMapper realtorAdminMapper;

    @Transactional
    public void save(RealtorAdmin admin){
        realtorAdminMapper.insert(admin);
    }

    @Transactional
    public void add(RealtorAdmin admin){
        realtorAdminMapper.insert(admin);
    }

    public RealtorAdmin get(Long id){
        return realtorAdminMapper.selectByPrimaryKey(id);
    }
}
