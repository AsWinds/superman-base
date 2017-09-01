package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysRolePrivDao;
import com.codi.superman.base.domain.SysRolePriv;
import com.codi.superman.base.service.SysRolePrivService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * role priv service impl
 *
 * @author shi.pengyan
 * @date 2016-12-25 15:53
 */
@Service("rolePrivService")
public class SysRolePrivServiceImpl extends AbstractServiceImpl implements SysRolePrivService {

    @Resource(name = "sysRolePrivDao")
    private SysRolePrivDao sysRolePrivDao;

    @Override
    @Transactional
    public int addRolePriv(Long roleId, Long privId) throws BaseAppException {
        Boolean exist = sysRolePrivDao.checkRolePrivExist(roleId, privId);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.ROLEPRIV_HAS_EXIST);
        }
        return sysRolePrivDao.insert(roleId, privId);
    }

    @Override
    public int addRolePriv(List<SysRolePriv> rolePrivs) throws BaseAppException {
        //TODO 如果存在则pass；如果不存在则添加
        return 0;
    }

    @Override
    @Transactional
    public int disableRolePriv(Long roleId, Long privId) throws BaseAppException {
        return sysRolePrivDao.updateState(roleId, privId, Const.STATE_X);
    }

    @Override
    @Transactional
    public int enableRolePriv(Long roleId, Long privId) throws BaseAppException {
        return sysRolePrivDao.updateState(roleId, privId, Const.STATE_A);
    }

    @Override
    @Transactional
    public int delRolePriv(Long roleId, Long privId) throws BaseAppException {
        return sysRolePrivDao.delRolePriv(roleId, privId);
    }
}
