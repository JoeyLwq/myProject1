package com.joey.base.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.joey.base.dao.SystemDao;
import com.joey.base.pojo.System;
import entity.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import service.CommonService;
import utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SystemService extends CommonService<System> {
    @Autowired
    private SystemDao systemDao;

    public Result saveExcel(MultipartFile file) {
        /*创建导入对象并设置表头行和是否进行验证等*/
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerfiy(true);
        importParams.setHeadRows(1);
        /*获取excel内容*/
        List<System> systems = new ArrayList<>();
        try {
            systems = ExcelImportUtil.importExcel(file.getInputStream(), System.class, importParams);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        systemDao.saveAll(systems);
        return Result.success();
    }

    public void exportExcel(HttpServletResponse response) {
        /*从数据库获取数据*/
        List<System> systems = systemDao.findAll();
        /*将数据导出*/
        if (!CollectionUtils.isEmpty(systems)) {
            ExcelUtils.exportExcel(systems, systems.get(0).getClass(), "excel导出", "sheet1", response);
        }
    }
}

