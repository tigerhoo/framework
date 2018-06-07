package com.qihai.commerce.framework.datasource.strategy;


import javax.sql.DataSource;

import com.qihai.commerce.framework.datasource.enums.DynamicDataSourceGlobal;

/**
 * @Description: 负载均衡策略
 * 
 * @author zhugj
 * @date 2018年6月7日 下午2:03:29
 * @version 1.0.0
 */
public abstract class AbstractStrategy implements Strategy {

    public String select(java.util.List<DataSource> slaves, DataSource master) {
        if (slaves == null || slaves.isEmpty())
            return DynamicDataSourceGlobal.WRITE.name();
        if (slaves.size() == 1)
            return DynamicDataSourceGlobal.READ.name() + "_0";
        return doSelect(slaves,master);
    }

    /** 
      * @Description: 读的数据源为多个的时候
      * @Title doSelect
      * @Author  zhugj
      * @Date 2018/6/7 9:29
      * @param  slaves, master
      * @return String
      * @throws 
      */
    protected abstract String doSelect(java.util.List<DataSource> slaves, DataSource master);
}