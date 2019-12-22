package com.joey.base.controller;

import com.joey.base.pojo.Label;
import com.joey.base.service.LabelService;
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
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @PostMapping("/save")
    public Result save(@RequestBody Label label) {
        if (label.getId() != null && !"".equals(label.getId())) {
            return new Result(true, StatusCode.ERROR, "新数据id将由系统自动生成");
        }
        String id = IdGenerator.generatorId();
        label.setId(id);
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping
    public Result update(@RequestBody Label label) {
        if (label.getId() == null || "".equals(label.getId())) {
            return new Result(true, StatusCode.ERROR, "更新数据必须传入id");
        }
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{labelId}")
    public Result deleteById(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

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
