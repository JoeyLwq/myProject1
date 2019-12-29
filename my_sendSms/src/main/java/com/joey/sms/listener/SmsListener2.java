package com.joey.sms.listener;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


/*这个消费者用来接收非189开头的电话号码消息*/
@Component
@RabbitListener(queues = "sms2")
public class SmsListener2 {

    @RabbitHandler
    public void executeSms(Map<String, String> map) {
        System.out.println("===========监听者2收到信息===========");
        System.out.println("手机号：" + map.get("phone"));
        System.out.println("验证码：" + map.get("checkCode"));
    }
}
