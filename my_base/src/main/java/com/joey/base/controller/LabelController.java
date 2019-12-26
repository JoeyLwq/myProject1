package com.joey.base.controller;

import com.joey.base.pojo.Label;
import com.joey.base.service.LabelService;
import controller.CommonController;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import utils.IdGenerator;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController extends CommonController<Label> {

    @Autowired
    private LabelService labelService;

    @PostMapping("/search")
    public Result findSerch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    /*用Int来接收是因为int初始值为0，不怕有空指针异常*/
    @PostMapping("/search/{page}/{size}")
    public Result findPage(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }
}
