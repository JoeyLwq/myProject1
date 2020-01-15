package com.joey.base.client;

import com.joey.base.client.clientImpl.BaseClientImpl;
import config.FeignSupportConfig;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "my-user",fallback = BaseClientImpl.class,configuration = FeignSupportConfig.class)
public interface BaseClient {

    @PostMapping("/user/sendSms/{phone}")
    public Result sendSms(@PathVariable("phone") String phone);

    @GetMapping("/user")
    public Result getAll();
}
