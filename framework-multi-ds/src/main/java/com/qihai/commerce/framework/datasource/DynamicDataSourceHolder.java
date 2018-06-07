package com.qihai.commerce.framework.datasource;


import com.qihai.commerce.framework.datasource.enums.DynamicDataSourceGlobal;

/**
 * @Description: 动态数据源持有
 * 
 * @author zhugj
 * @date 2018年6月7日 下午2:03:29
 * @version 1.0.0
 */
public final class DynamicDataSourceHolder {

    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<DynamicDataSourceGlobal>();

    private DynamicDataSourceHolder() {
        //
    }

    public static void putDataSource(DynamicDataSourceGlobal dataSource){
        holder.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource(){
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }

}
