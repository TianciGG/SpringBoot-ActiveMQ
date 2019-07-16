package chauncy.producter;

import java.util.UUID;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import chauncy.entity.UserEntity;

/**
 * 
 * @classDesc: 功能描述(点对点通信模式生产者代码)
 * @author: ChauncyWang
 * @createTime: 2019年7月16日 下午4:37:00
 * @version: 1.0
 */
@Component
@EnableScheduling
public class Producter {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;

	private int age = 18;

	@Scheduled(fixedDelay = 5000)
	public void send() {
		age++;
		UserEntity userEntity = new UserEntity(System.currentTimeMillis(), UUID.randomUUID().toString(), age);
		String jsonString = new JSONObject().toJSONString(userEntity);
		System.out.println("json:" + jsonString);
		jmsMessagingTemplate.convertAndSend(queue, jsonString);
	}
}
