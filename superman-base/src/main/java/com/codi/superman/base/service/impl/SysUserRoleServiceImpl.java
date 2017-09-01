package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysUserRoleDao;
import com.codi.superman.base.domain.SysUserRole;
import com.codi.superman.base.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * user role service
 *
 * @author shi.pengyan
 * @date 2016-12-25 13:02
 */
@Service("sysUserRoleService")
@Transactional(readOnly = true)
public class SysUserRoleServiceImpl extends AbstractServiceImpl implements SysUserRoleService {

    @Resource(name = "sysUserRoleDao")
    private SysUserRoleDao sysUserRoleDao;

    @Override
    @Transactional(readOnly = false)
    public int addUserRole(Long userId, Long roleId) throws BaseAppException {
        //判断是否已经绑定
        Boolean exist = sysUserRoleDao.checkUserRoleExist(userId, roleId);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.USERROLE_HAS_EXIST);
        }

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setState(Const.STATE_A);

        Date now = new Date();
        sysUserRole.setCreateDate(now);
        sysUserRole.setStateDate(now);

        return sysUserRoleDao.insert(sysUserRole);
    }

    @Override
    public int addUserRole(List<SysUserRole> userroles) throws BaseAppException {
        //TODO 如果有则忽略；如果没有则添加
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int disableUserRole(Long userId, Long roleId) throws BaseAppException {
        logger.debug("disableUserRole userId={},roleId={},state={}", userId, roleId, Const.STATE_X);
        return sysUserRoleDao.updateState(userId, roleId, Const.STATE_X);
    }

    @Override
    @Transactional(readOnly = false)
    public int enableUserRole(Long userId, Long roleId) throws BaseAppException {
        logger.debug("disableUserRole userId={},roleId={},state={}", userId, roleId, Const.STATE_A);
        return sysUserRoleDao.updateState(userId, roleId, Const.STATE_A);
    }

    @Override
    @Transactional(readOnly = false)
    public int delUserRole(Long userId, Long roleId) throws BaseAppException {
        logger.debug("delete user role userId={},roleId={}", userId, roleId);
        return sysUserRoleDao.delUserRole(userId, roleId);
    }

    @Override
    public List<SysUserRole> queryUserRole(Long userId) throws BaseAppException {
        return sysUserRoleDao.queryUserRole(userId);
    }
}
