package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.superman.base.dao.SysFileBucketDao;
import com.codi.superman.base.domain.SysFileBucket;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-10 16:53
 */
@Repository
public class SysFileBucketDaoImpl extends BaseDAOImpl<SysFileBucket> implements SysFileBucketDao {
    @Override
    public int deleteByPrimaryKey(Long bucketId) {
        return this.delete(generateStatement("deleteByPrimaryKey"), bucketId);
    }

    @Override
    public int insertSelective(SysFileBucket record) {
        return this.insert(generateStatement("insertSelective"), record);
    }


    @Override
    public int updateByPrimaryKeySelective(SysFileBucket record) {
        return this.update(generateStatement("updateByPrimaryKeySelective"), record);
    }


    @Override
    public List<SysFileBucket> queryList() {
        return this.findList(generateStatement("queryList"));
    }

    @Override
    public SysFileBucket selectByPrimaryKey(Long bucketId) {
        return this.getObject(generateStatement("selectByPrimaryKey"), bucketId);
    }
}
