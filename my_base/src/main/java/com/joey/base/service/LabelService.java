package com.joey.base.service;

import com.joey.base.dao.LabelDao;
import com.joey.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.QueryMaker;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private RedisTemplate redisTemplate;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public void save(Label label) {
        labelDao.save(label);
    }

    public void update(Label label) {
        //数据做了改动，应删除缓存中的数据
        redisTemplate.delete("label" + label.getId());
        labelDao.save(label);
    }

    public void deleteById(String id) {
        redisTemplate.delete("label" + id);
        labelDao.deleteById(id);
    }

    public Specification<Label> commonQueryMaker(Label label) {
        return new QueryMaker<>(label).lk("labelname", label.getLabelname()).eq("state", label.getState())
                .eq("id", label.getId())
                .eq("state", label.getState())
                .getSpecification();
    }

    /*使用redis缓存数据库，先用键到redis中查看是否有值，有则取出，无则从mysql数据库中取数据，再存入redis*/
    public List<Label> findSearch(Label label) {
        List<Label> labelList;
        if (label.getId() != null && !"".equals(label.getId())) {
            labelList = (List<Label>) redisTemplate.opsForValue().get("label" + label.getId());
            if (labelList == null) {
                labelList = labelDao.findAll(commonQueryMaker(label));
                redisTemplate.opsForValue().set("label" + label.getId(), labelList);
            }
        } else {
            labelList = labelDao.findAll(commonQueryMaker(label));
        }
        return labelList;
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelDao.findAll(commonQueryMaker(label), pageable);
    }

}
