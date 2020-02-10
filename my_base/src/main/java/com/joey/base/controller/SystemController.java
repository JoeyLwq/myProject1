package com.joey.base.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.joey.base.pojo.System;
import com.joey.base.service.LabelService;
import com.joey.base.service.SystemService;
import controller.CommonController;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController extends CommonController<System> {
    @Autowired
    private SystemService systemService;

    @PostMapping("/saveExcel")
    public Result saveExcel(@RequestParam(value = "file")MultipartFile file){
        return systemService.saveExcel(file);
    }

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        systemService.exportExcel(response);
    }

}
