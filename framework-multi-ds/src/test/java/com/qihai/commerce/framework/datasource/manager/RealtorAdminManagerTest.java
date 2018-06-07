package com.qihai.commerce.framework.datasource.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qihai.commerce.framework.datasource.JunitBaseTest;
import com.qihai.commerce.framework.domain.RealtorAdmin;
import com.qihai.commerce.framework.manager.RealtorAdminManager;

/**
 * @Description: 测试主从
 * 
 * @author zhugj
 * @date 2018年6月7日 下午2:03:29
 * @version 1.0.0
 */
public class RealtorAdminManagerTest extends JunitBaseTest {

    @Autowired
    private RealtorAdminManager realtorAdminManager;

    @Test
    public void testSave(){
        RealtorAdmin admin = new RealtorAdmin();
        admin.setUsername("save001");
        admin.setPassword("111");
        realtorAdminManager.save(admin);
        System.err.println(admin.getId());
    }

    @Test
    public void testAdd(){
        // 带事务插入
        RealtorAdmin admin = new RealtorAdmin();
        admin.setUsername("add001");
        admin.setPassword("111");
        realtorAdminManager.save(admin);
        System.err.println(admin.getId());
    }



    @Test
    public void testGet(){
    	System.err.println(realtorAdminManager.get(1L).getUsername());
    }

    @Test
    public void testGetBatch(){
        for (int i = 0; i < 2; i++) {
        	System.err.println(realtorAdminManager.get(1L).getUsername());
        }
    }
}
