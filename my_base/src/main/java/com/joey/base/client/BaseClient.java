package com.joey.base.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("my-user")
public interface BaseClient {

    @PostMapping("/user/sendSms/{phone}")
    public Result sendSms(@PathVariable("phone") String phone);

    @GetMapping("/user")
    public Result getAll();
}
