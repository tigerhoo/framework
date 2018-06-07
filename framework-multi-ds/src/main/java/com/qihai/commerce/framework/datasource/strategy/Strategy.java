package com.qihai.commerce.framework.datasource.strategy;
import javax.sql.DataSource;

/**
 * @Description: 负载均衡策略
 * 
 * @author zhugj
 * @date 2018年6月7日 下午2:03:29
 * @version 1.0.0
 */
public interface Strategy {
    String select(java.util.List<DataSource> Slaves, DataSource master);
}

