package com.joey.base.client.clientImpl;

import com.joey.base.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result sendSms(String phone) {
        return new Result(false, StatusCode.ERROR, "触发熔断器");
    }

    @Override
    public Result getAll() {
        return new Result(false, StatusCode.ERROR, "触发熔断器");
    }
}