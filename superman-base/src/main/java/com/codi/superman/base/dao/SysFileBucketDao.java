package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysFileBucket;

import java.util.List;

/**
 * 亲，写个类注释呗
 *
 * @author spy
 * @date 2017-04-10 16:41
 */
public interface SysFileBucketDao extends BaseDAO<SysFileBucket> {

    int deleteByPrimaryKey(Long bucketId);

    int insertSelective(SysFileBucket record);

    int updateByPrimaryKeySelective(SysFileBucket record);

    List<SysFileBucket> queryList();

    SysFileBucket selectByPrimaryKey(Long bucketId);
}
