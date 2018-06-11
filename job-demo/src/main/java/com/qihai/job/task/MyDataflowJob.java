package com.qihai.job.task;

import java.util.ArrayList;
import java.util.List;

import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.qihai.job.entity.User;

/**
 * 流式业务处理
 * streamingProcess属性，是否流式处理数据，如果流式处理数据, 则fetchData不返回空结果将持续执行作业；如果非流式处理数据, 则处理数据完成后作业结束
 * @author fangping
 *
 */
@ElasticJobConf(name="MyDataflowJob", cron="0 0/1 * * * ?", 
                streamingProcess=false, overwrite=true, shardingItemParameters="0=A", 
                jobParameter="hehe", description="流式作业", shardingTotalCount=1)
public class MyDataflowJob implements DataflowJob<User> {

	/**
	 * 抓取待处理数据列表
	 */
	public List<User> fetchData(ShardingContext shardingContext) {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setName("张三");
		
		users.add(user);
		return users;
	}

	/**
	 * 处理数据
	 */
	public void processData(ShardingContext shardingContext, List<User> data) {
		if(data == null || data.size() <= 0) {
			return;
		}
		for(User user : data) {
			System.out.println("处理业务数据, 打印姓名：" + user.getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
