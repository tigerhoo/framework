package test;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qihai.commerce.framework.mq.BasicService;

@Component
@EnableScheduling
public class TestSend {

    @Resource
    private BasicService basicService;

    @Scheduled(fixedDelay = 60000)
    public void send() {
        TUserTest user = new TUserTest();
        user.setId(UUID.randomUUID().toString());
        user.setName("我是拯救世界的Programmer");
        basicService.sendMessage(user.getId(), user);
    }
}