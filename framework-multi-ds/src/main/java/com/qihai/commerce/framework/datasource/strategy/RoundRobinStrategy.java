package com.qihai.commerce.framework.datasource.strategy;


import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.qihai.commerce.framework.datasource.enums.DynamicDataSourceGlobal;

/**
 * @Description: 轮询策略---随机轮询
 * 
 * @author zhugj
 * @date 2018年6月7日 下午2:03:29
 * @version 1.0.0
 */
public class RoundRobinStrategy extends AbstractStrategy {

    private Byte[] lock = new Byte[0];

    @Override
    protected String doSelect(List<DataSource> slaves, DataSource master) {
        int _index = 0;
        synchronized (lock) {
            Random random = new Random();
            _index = random.nextInt(slaves.size());
        }

        return DynamicDataSourceGlobal.READ.name() + "_" + _index;
    }
}