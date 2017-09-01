package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysRoleDao;
import com.codi.superman.base.dao.SysRolePrivDao;
import com.codi.superman.base.dao.SysUserRoleDao;
import com.codi.superman.base.domain.SysRole;
import com.codi.superman.base.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * SysRole service impl
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:33
 */
@Service("sysRoleService")
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends AbstractServiceImpl implements SysRoleService {

    @Resource(name = "sysRoleDao")
    private SysRoleDao sysRoleDao;

    @Resource(name = "sysUserRoleDao")
    private SysUserRoleDao sysUserRoleDao;

    @Resource(name = "sysRolePrivDao")
    private SysRolePrivDao sysRolePrivDao;


    @Override
    @Transactional(readOnly = false)
    public int addRole(String roleCode, String roleName, String desc) throws BaseAppException {
        //唯一性校验
        boolean exist = sysRoleDao.checkRoleCode(roleCode);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.ROLE_CODE_HAS_EXIST);
        }

        SysRole sysRole = new SysRole();
        sysRole.setRoleCode(roleCode);
        sysRole.setRoleName(roleName);
        sysRole.setDescription(desc);

        Date now = new Date();
        sysRole.setCreateDate(now);
        sysRole.setUpdateDate(now);
        sysRole.setState(Const.STATE_A);

        sysRoleDao.insert(sysRole);
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int delRole(Long roleId) throws BaseAppException {
        //只有在改role不被user 和 priv引用时才可以删除
        Boolean exist = sysUserRoleDao.checkRoleExist(roleId);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.ROLE_HAS_USER_ROLE_REF);
        }
        exist = sysRolePrivDao.checkRoleExist(roleId);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.PRIV_HAS_ROLE_PRIV_REF);
        }

        return sysRoleDao.delRole(roleId);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateRole(SysRole sysRole) throws BaseAppException {
        return sysRoleDao.updateRole(sysRole);
    }

    @Override
    @Transactional(readOnly = false)
    public int disableRole(Long roleId) throws BaseAppException {
        return sysRoleDao.updateRoleState(roleId, Const.STATE_X);
    }

    @Override
    public SysRole getRole(Long roleId) throws BaseAppException {
        return sysRoleDao.selectByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = false)
    public int enableRole(Long roleId) throws BaseAppException {
        return sysRoleDao.updateRoleState(roleId, Const.STATE_A);
    }

    @Override
    public Boolean checkRoleCode(String roleCode) throws BaseAppException {
        return sysRoleDao.checkRoleCode(roleCode);
    }

    @Override
    public Long queryRolesCount() throws BaseAppException {
        return sysRoleDao.queryRolesCount();
    }

    @Override
    public List<SysRole> queryRoles(Integer pageIndex, Integer pageSize) throws BaseAppException {
        return sysRoleDao.queryRoles(pageIndex, pageSize);
    }

    @Override
    public List<SysRole> queryRoles(Long userId) throws BaseAppException {
        return sysRoleDao.queryRolesByUserId(userId);
    }


}
