package com.qihai.job.task;

import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 简单类型job
 * 如果注解与配置文件里同时存在相同属性配置，以配置文件为准
 * @author fangping
 *
 */
@ElasticJobConf(name="MySimpleJob", cron="0/20 * * * * ?", 
                shardingItemParameters="0=A,1=B", jobParameter="hehe", 
                description="简单任务", shardingTotalCount=2, misfire=false, failover=false,
                eventTraceRdbDataSource="druiddatasource", listener="com.qihai.job.listener.MyElasticJobListener")
public class MySimpleJob implements SimpleJob {

	/**
	 * 一般根据表主键id % 分片总数 = 当前分片 来获取待处理数据列表
	 * 建议重要的job表里有状态字段：未处理、处理中、已处理
	 */
	public void execute(ShardingContext context) {
		System.out.println("job名称：" + context.getJobName());
		System.out.println("当前分片：" + context.getShardingItem());
		System.out.println("分片总数：" + context.getShardingTotalCount());
		System.out.println("当前分片参数：" + context.getShardingParameter());
		System.out.println("所有分片共用参数：" + context.getJobParameter());
		
//		if(context.getShardingItem() == 1) {
//		try {
//			int random = (int) (Math.random() * 10) + 20;
//			System.out.println("随机睡眠" + random);
//			Thread.sleep(random * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		}
		System.out.println("item_" + context.getShardingItem() + " done");
	}
	
}
