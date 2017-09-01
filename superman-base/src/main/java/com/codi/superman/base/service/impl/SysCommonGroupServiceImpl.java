package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.Assert;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysCommonGroupDao;
import com.codi.superman.base.domain.SysCommonGroup;
import com.codi.superman.base.service.SysCommonGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by 周翔 on 2017/4/12.
 */
@Service("commonGroupService")
@Transactional(readOnly = true)
public class SysCommonGroupServiceImpl extends AbstractServiceImpl implements SysCommonGroupService {
    @Resource(name = "sysCommonGroupDao")
    private SysCommonGroupDao sysCommonGroupDao;

    @Override
    public int selectGroupCount() throws BaseAppException {
        return sysCommonGroupDao.selectGroupCount();
    }

    @Override
    public List<SysCommonGroup> queryGroupAdmin(Integer pageIndex, Integer pageSize) throws BaseAppException {
        return sysCommonGroupDao.selectAllGroup(pageIndex, pageSize);
    }

    @Override
    public List<SysCommonGroup> queryGroupByBizGroupCode(String bizGroupCode) throws BaseAppException {
        return sysCommonGroupDao.selectByBizGroupCode(bizGroupCode);
    }

    @Override
    @Transactional(readOnly = false)
    public int addGroup(SysCommonGroup sysCommonGroup) throws BaseAppException {
        Assert.notNull(sysCommonGroup.getBizCode());
        if (!this.checkBizCode(sysCommonGroup.getBizCode())) {
            ExceptionHandler.publish(ErrorConst.GROUP_BIZ_CODE_ERROR);
        }
        Assert.notNull(sysCommonGroup.getBizGroupCode());
        Assert.notNull(sysCommonGroup.getGroupName());
        Date now = new Date();
        sysCommonGroup.setCreateDate(now);
        sysCommonGroup.setUpdateDate(now);
        return sysCommonGroupDao.insert(sysCommonGroup);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteGroup(Long groupId) throws BaseAppException {
        return sysCommonGroupDao.deleteByPrimaryKey(groupId);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateGroup(Long groupId, String groupName, String description, String bizCode, String bizGroupCode) throws BaseAppException {
        Date now = new Date();
        SysCommonGroup sysCommonGroup = new SysCommonGroup();
        sysCommonGroup.setDescription(description);
        sysCommonGroup.setGroupId(groupId);
        sysCommonGroup.setGroupName(groupName);
        sysCommonGroup.setBizCode(bizCode);
        sysCommonGroup.setBizGroupCode(bizGroupCode);
        sysCommonGroup.setUpdateDate(now);
        return sysCommonGroupDao.updateByPrimaryKeySelective(sysCommonGroup);
    }

    @Override
    public Boolean checkBizCode(String bizCode) {
        int count = sysCommonGroupDao.selectCountByBizCode(bizCode);
        if (count > 0) {
            return false;
        }
        return true;
    }
}
