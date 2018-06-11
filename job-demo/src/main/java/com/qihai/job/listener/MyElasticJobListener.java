package com.qihai.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

public class MyElasticJobListener implements ElasticJobListener {

	public void beforeJobExecuted(ShardingContexts shardingContexts) {
		System.out.println("-----执行job前处理逻辑----");
	}

	public void afterJobExecuted(ShardingContexts shardingContexts) {
		System.out.println("-----执行job后处理逻辑----");
	}

}
