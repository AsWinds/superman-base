package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.DateUtils;
import com.codi.base.util.EqualsUtil;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysUserDao;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.SysLogAuditService;
import com.codi.superman.base.service.SysLoginService;
import com.codi.superman.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Login Module
 *
 * @author shi.pengyan
 * @date 2016-12-26 10:06
 */
@Service("sysLoginService")
@Transactional(readOnly = true)
public class SysLoginServiceImpl extends AbstractServiceImpl implements SysLoginService {


    @Resource(name = "sysUserDao")
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLogAuditService sysLogAuditService;

    @Override
    @Transactional(readOnly = false)
    public SysUser login(String userCode, String pwd) throws BaseAppException {
        //TODO  校验UserCode

        SysUser sysUser = sysUserDao.selectByUserCode(userCode, SysUserDao.USER_STATE_A);
        if (sysUser == null) {
            logger.debug("sysUser [{}] not exist", userCode);
            ExceptionHandler.publish(ErrorConst.USER_NAME_OR_PWD_ERROR);
        }

        logger.debug("user={}", sysUser);

        SysUser newUser = new SysUser();
        newUser.setUserId(sysUser.getUserId());

        // 判断用户状态
        if (EqualsUtil.equals(sysUser.getIsLocked(), Const.YES)) {
            logger.debug("user has been locked");
            //判断是否到达解锁时间
            long delta = DateUtils.differDate(new Date(), sysUser.getUnlockDate(), DateUtils.DIFFER_IN_SECOND);

            if (sysUser.getUnlockDate() == null || delta > 0) {
                ExceptionHandler.publish(ErrorConst.USER_HAS_LOCKED);
            } else {
                //对用户进行解锁
                newUser.setLoginFail(0);
                newUser.setIsLocked(Const.NO);
                newUser.setUnlockDate(null);
                sysUserDao.update(newUser);
            }
        }


        // 验证密码
        String encryptPwd = sysUserService.getEncryptPwd(sysUser.getUserCode(), pwd);


        if (EqualsUtil.equalsIgnoreCase(encryptPwd, sysUser.getPwd())) {
            //清空尝试次数
            newUser.setIsLocked(Const.NO);
            newUser.setLoginFail(0);
            newUser.setUnlockDate(null);
            sysUserDao.update(newUser);

            sysLogAuditService.addLog(Const.LOG_TYPE_LOGIN, sysUser.getUserId(), "Login Success.");
        } else {
            logger.debug("sysUser[{}] pwd is wrong", sysUser.getUserCode());

            //TODO 尝试6次数锁定用户
            if (sysUser.getLoginFail() > 6) {
                //lock user
                newUser.setIsLocked(Const.YES);
                newUser.setUnlockDate(DateUtils.addMinute(new Date(), LOCK_USER_MINTUE));
                sysUserDao.updateUser(newUser);
            } else {
                newUser.setLoginFail(sysUser.getLoginFail() + 1);
                sysUserDao.updateUser(newUser);
            }
            ExceptionHandler.publish(ErrorConst.USER_NAME_OR_PWD_ERROR);
        }

        //TODO 其他校验

        return sysUser;
    }

    @Override
    @Transactional(readOnly = false)
    public void logout(SysUser loginUser) throws BaseAppException {
        if (loginUser == null) {
            logger.warn("login user is null.");
            return;
        }
        sysLogAuditService.addLog(Const.LOG_TYPE_LOGOUT, loginUser.getUserId(), "Logout successfully.");
    }
}
