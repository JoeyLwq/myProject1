package controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import entity.common.CommonEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import service.CommonService;
import utils.IdGenerator;

import java.util.List;

@RestController
@CrossOrigin
public class CommonController<T extends CommonEntity, D> {

    @Autowired
    CommonService<T, D> commonService;

    @GetMapping
    public Result getAll(){
        return new Result(true,StatusCode.OK,"查询成功",commonService.findAll());
    }

    @PostMapping
    public Result save(@RequestBody T entity) {
        if (StringUtils.isNotEmpty(entity.getId())) {
            return new Result(true, StatusCode.ERROR, "新数据id将由系统自动生成");
        }
        String id = IdGenerator.generatorId();
        entity.setId(id);
        commonService.save(entity);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping
    public Result update(@RequestBody T entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            return new Result(true, StatusCode.ERROR, "更新数据必须传入id");
        }
        commonService.update(entity);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping
    public Result delete(@RequestBody T entity) {
        if (StringUtils.isEmpty(entity.getId())){
            return new Result(true,StatusCode.ERROR,"删除数据必须传入id");
        }
        commonService.delete(entity);
        return new Result(true, StatusCode.OK, "删除成功");
    }


}
