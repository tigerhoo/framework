package com.qihai.commerce.framework.datasource.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qihai.commerce.framework.datasource.test.domain.RealtorAdmin;
import com.qihai.commerce.framework.datasource.test.manager.RealtorAdminManager;

/**
 * 用户控制层
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class UserRestController {

	@Autowired
    private RealtorAdminManager realtorAdminManager;

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void saveWithRollback() {
    	RealtorAdmin admin = new RealtorAdmin();
        admin.setUsername("save001");
        admin.setPassword("111");
        realtorAdminManager.save(admin);
        System.err.println(admin.getId());
    }
    
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void saveWithoutRollback() {
    	RealtorAdmin admin = new RealtorAdmin();
        admin.setUsername("save002");
        admin.setPassword("222");
        realtorAdminManager.save1(admin);
        System.err.println(admin.getId());
    }
    
    @RequestMapping(value = "/test1/{id}", method = RequestMethod.GET)
    public void findByName1() {
        RealtorAdmin admin = realtorAdminManager.get(2L);
        System.err.println(admin.getId());
    }

}
