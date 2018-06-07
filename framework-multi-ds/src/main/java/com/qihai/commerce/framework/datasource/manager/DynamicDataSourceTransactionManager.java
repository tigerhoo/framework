package com.qihai.commerce.framework.datasource.manager;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.qihai.commerce.framework.datasource.DynamicDataSourceHolder;
import com.qihai.commerce.framework.datasource.enums.DynamicDataSourceGlobal;

/**
 * @Description: 事务处理
 * 
 * @author zhugj
 * @date 2018年6月7日 下午2:03:29
 * @version 1.0.0
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

	private static final long serialVersionUID = 1316860354635994485L;

	/**
     * 只读事务到读库，读写事务到写库
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {

        //设置数据源
        boolean readOnly = definition.isReadOnly();
        if(readOnly) {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.READ);
        } else {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.WRITE);
        }
        super.doBegin(transaction, definition);
    }

    /**
     * 清理本地线程的数据源
     * @param transaction
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceHolder.clearDataSource();
    }
}
