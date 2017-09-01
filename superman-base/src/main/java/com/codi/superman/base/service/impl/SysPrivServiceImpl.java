package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.ListUtil;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysPrivDao;
import com.codi.superman.base.dao.SysRolePrivDao;
import com.codi.superman.base.dao.SysUserRoleDao;
import com.codi.superman.base.domain.SysPriv;
import com.codi.superman.base.domain.SysUserRole;
import com.codi.superman.base.service.SysPrivService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统权限
 *
 * @author shi.pengyan
 * @date 2016-12-25 13:55
 */
@Service("privService")
@Transactional(readOnly = true)
public class SysPrivServiceImpl extends AbstractServiceImpl implements SysPrivService {

    @Resource(name = "sysPrivDao")
    private SysPrivDao sysPrivDao;

    @Resource(name = "sysRolePrivDao")
    private SysRolePrivDao sysRolePrivDao;

    @Resource(name = "sysUserRoleDao")
    private SysUserRoleDao sysUserRoleDao;

    @Override
    @Transactional(readOnly = false)
    public int addPriv(SysPriv sysPriv) throws BaseAppException {
        if (sysPriv == null) {
            logger.warn("sysPriv obj is null, please check!");
            ExceptionHandler.publish(ErrorConst.PRIV_IS_NULL);
        }

        //检查编码是否重复
        boolean isExist = sysPrivDao.checkPrivCode(sysPriv.getPrivCode(), null);
        if (isExist) {
            ExceptionHandler.publish(ErrorConst.PRIV_CODE_HAS_EXIST);
        }
        if (sysPriv.getParentPrivId() == null) {
            sysPriv.setParentPrivId(0L);
        }

        //
        Date now = new Date();
        sysPriv.setState(Const.STATE_A);
        sysPriv.setCreateDate(now);
        sysPriv.setUpdateTime(now);

        return sysPrivDao.insertPriv(sysPriv);
    }

    @Override
    @Transactional(readOnly = false)
    public int delPriv(Long privId) throws BaseAppException {
        //判断权限是否被ROLE应用，如果么有则可以删除
        Boolean exist = sysRolePrivDao.checkPrivExist(privId);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.PRIV_HAS_ROLE_PRIV_REF);
        }

        return sysPrivDao.deleteByPrivId(privId);
    }

    @Override
    @Transactional(readOnly = false)
    public int modifyPriv(SysPriv sysPriv) throws BaseAppException {

        if (sysPriv == null) {
            logger.warn("sysPriv obj is null, please check!");
            ExceptionHandler.publish(ErrorConst.PRIV_IS_NULL);
        }

        //TODO 检查编码是否重复,排他性检查
        boolean isExist = sysPrivDao.checkPrivCode(sysPriv.getPrivCode(), sysPriv.getPrivId());
        if (isExist) {
            ExceptionHandler.publish(ErrorConst.PRIV_CODE_HAS_EXIST);
        }
        if (sysPriv.getParentPrivId() == null) {
            sysPriv.setParentPrivId(0L);
        }

        //
        sysPriv.setUpdateTime(new Date());
        //code
        sysPriv.setPrivCode(null);
        sysPriv.setCreateDate(null);
        sysPriv.setState(null);

        return sysPrivDao.updateByPrimaryKeySelective(sysPriv);
    }

    @Override
    public SysPriv queryPriv(Long privId) throws BaseAppException {
        return sysPrivDao.selectByPrivId(privId);
    }

    @Override
    public List<SysPriv> queryRootPrivs() throws BaseAppException {
        return sysPrivDao.queryRootPrivs();
    }

    @Override
    public Long queryPrivsCount() throws BaseAppException {
        return sysPrivDao.queryPrivsCount();
    }

    @Override
    public List<SysPriv> queryPrivs(Integer pageIndex, Integer pageSize) throws BaseAppException {
        return sysPrivDao.queryPrivs(pageIndex, pageSize);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleIds(List<Long> roleIds) throws BaseAppException {
        return this.queryPrivsByRoleIds(roleIds, null);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleIds(List<Long> roleIds, Integer privType) throws BaseAppException {
        return sysPrivDao.queryPrivsByRoleIds(roleIds, privType);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleId(Long roleId) throws BaseAppException {
        return this.queryPrivsByRoleId(roleId, null);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleId(Long roleId, Integer privType) throws BaseAppException {
        List<Long> roleIds = Arrays.asList(roleId);
        return sysPrivDao.queryPrivsByRoleIds(roleIds, privType);
    }

    @Override
    public List<SysPriv> queryPrivsByUserId(Long userId) throws BaseAppException {
        return this.queryPrivsByUserId(userId, null);
    }

    @Override
    public List<SysPriv> queryPrivsByUserId(Long userId, Integer privType) throws BaseAppException {
        List<SysUserRole> userRoles = sysUserRoleDao.queryUserRole(userId);
        if (ListUtil.isEmpty(userRoles)) {
            return null;
        }

        List<Long> roleIds = new ArrayList<>(userRoles.size());
        for (SysUserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }
        return sysPrivDao.queryPrivsByRoleIds(roleIds, privType);
    }
}
