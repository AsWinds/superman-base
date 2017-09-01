package com.codi.superman.base.service.impl;

import com.codi.base.domain.BaseServiceImpl;
import com.codi.base.util.Assert;
import com.codi.superman.base.dao.SysFileBucketDao;
import com.codi.superman.base.domain.SysFileBucket;
import com.codi.superman.base.service.SysFileBucketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-10 16:54
 */
@Service
@Transactional(readOnly = true)
public class SysFileBucketServiceImpl extends BaseServiceImpl implements SysFileBucketService {

    @Resource
    private SysFileBucketDao sysFileBucketDao;


    @Override
    @Transactional(readOnly = false)
    public SysFileBucket add(SysFileBucket record) {
        //validate
        Assert.notNull(record);
        Assert.notNull(record.getBucketCode());
        Assert.notNull(record.getHostName());

        Date now = new Date();
        record.setCreateDate(now);
        record.setUpdateDate(now);
        sysFileBucketDao.insertSelective(record);

        return record;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(SysFileBucket record) {
        Assert.notNull(record);
        Assert.notNull(record.getBucketId());
        Assert.notNull(record.getBucketCode());
        Assert.notNull(record.getHostName());

        record.setCreateDate(null);
        record.setUpdateDate(new Date());

        return sysFileBucketDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(Long bucketId) {
        return sysFileBucketDao.deleteByPrimaryKey(bucketId);
    }

    @Override
    public List<SysFileBucket> query() {
        return sysFileBucketDao.queryList();
    }

    @Override
    public SysFileBucket query(Long bucketId) {
        return sysFileBucketDao.selectByPrimaryKey(bucketId);
    }
}
