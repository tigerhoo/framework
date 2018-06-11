package com.qihai.job;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.cxytiandi.elasticjob.annotation.EnableElasticJob;

@SpringBootApplication
//该注解开启分布式调度
@EnableElasticJob
public class JobApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(JobApplication.class).run(args); try { new CountDownLatch(1).await(); } catch (InterruptedException e) { }
	}

}
