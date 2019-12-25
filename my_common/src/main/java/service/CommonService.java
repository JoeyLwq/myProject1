package service;

import dao.CommonDao;
import entity.common.CommonEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.QueryMaker;

import java.util.List;

@Service
@Transactional
public class CommonService<T extends CommonEntity, D> {

    @Autowired
    CommonDao<T, D> commonDao;

    @Autowired
    private RedisTemplate redisTemplate;

    public List<T> findAll() {
        return commonDao.findAll();
    }

    public void save(T entity) {
        commonDao.save(entity);
    }

    public void update(T entity) {
        //数据做了改动，应删除缓存中的数据
        redisTemplate.delete(entity.getClass().getCanonicalName() + entity.getId());
        commonDao.save(entity);
    }

    public void delete(T entity) {
        redisTemplate.delete(entity.getClass().getCanonicalName() + entity.getId());
        commonDao.deleteById((D) entity.getId());
    }
}
