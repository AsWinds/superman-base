package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.*;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysPrivDao;
import com.codi.superman.base.dao.SysUserDao;
import com.codi.superman.base.domain.SysPriv;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.SysPrivService;
import com.codi.superman.base.service.SysUserService;
import com.codi.superman.base.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * SysUser service impl
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午11:14:17
 */
@Service("sysUserService")
@Transactional(readOnly = true)
public class SysUserServiceImpl extends AbstractServiceImpl implements SysUserService {

    @Resource(name = "sysUserDao")
    private SysUserDao sysUserDao;

    @Resource(name = "sysPrivDao")
    private SysPrivDao sysPrivDao;

    @Autowired
    private SysPrivService sysPrivService;

    @Override
    @Transactional(readOnly = false)
    public int addUser(SysUser sysUser) throws BaseAppException {
        Assert.notNull(sysUser);
        Assert.notNull(sysUser.getUserCode(), "用户编码不能为空");
        String userCode = sysUser.getUserCode();

        if (userCode.trim().length() < 4) {
            ExceptionHandler.publish(ErrorConst.USER_CODE_IS_SHORT);
        }

        Boolean exist = sysUserDao.checkUserCode(userCode, null);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.USER_CODE_HAS_EXIST);
        }

        if (StringUtil.isEmpty(sysUser.getUserName())) {
            sysUser.setUserName(sysUser.getUserCode());
        }

        sysUser.setPwd(getInitPwd(userCode));
        sysUser.setState(Const.STATE_A);

        Date now = new Date();
        sysUser.setCreatedDate(now);
        sysUser.setStateDate(now);
        if (sysUser.getUserEffDate() == null) {
            sysUser.setUserEffDate(now);
        }

        //生效日期不能再过期日期之后
        if (sysUser.getUserEffDate() != null && sysUser.getUserExpDate() != null) {
            if (sysUser.getUserEffDate().getTime() > sysUser.getUserExpDate().getTime()) {
                ExceptionHandler.publish(ErrorConst.USER_BEGIN_END_DATE_ERROR);
            }
        }

        sysUser.setForceLogin(Const.YES);
        sysUser.setIsLocked(Const.NO);
        sysUser.setLoginFail(0);

        if (sysUser.getUserSrc() == null) {
            sysUser.setUserSrc(0L);
        }


        return sysUserDao.insert(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateUser(SysUser sysUser) throws BaseAppException {
        logger.debug("sysUser={}", sysUser);

        //userCode不能更新
        sysUser.setUserCode(null);
        sysUser.setPwd(null);
        sysUser.setCreatedDate(null);

        return sysUserDao.updateUser(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int delUser(Long userId, Long adminUserId) throws BaseAppException {
        if (EqualsUtil.equals(userId, adminUserId)) {
            ExceptionHandler.publish(ErrorConst.USER_DELETE_SELF_ERROR);
        }

        //注意，不能删除用户
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setState(Const.STATE_X);
        sysUser.setStateDate(new Date());

        return sysUserDao.updateUser(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int lockUser(Long userId, Long adminUserId) throws BaseAppException {
        if (EqualsUtil.equals(userId, adminUserId)) {
            ExceptionHandler.publish(ErrorConst.USER_LOCK_SELF_ERROR);
        }

//        SysUser sysUser = new SysUser();
//        sysUser.setUserId(userId);
//        sysUser.setIsLocked(Const.YES);
//        sysUser.setLoginFail(0);
//        sysUser.setUnlockDate(null);

        return sysUserDao.lockUser(userId);
    }

    @Override
    @Transactional(readOnly = false)
    public int unlockUser(Long userId) throws BaseAppException {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setIsLocked(Const.NO);

        return sysUserDao.updateUser(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int resetPwd(Long userId) throws BaseAppException {
        SysUser sysUser = sysUserDao.selectById(userId);

        Assert.notNull(sysUser);
        logger.debug("sysUser={}", sysUser);

        SysUser record = new SysUser();
        record.setUserId(sysUser.getUserId());
        record.setPwd(getInitPwd(sysUser.getUserCode()));

        return sysUserDao.updateUser(record);
    }

    @Override
    public SysUser getUser(Long userId) throws BaseAppException {
        SysUser sysUser = sysUserDao.selectById(userId);
        sysUser.setPwd(null); //禁止返回密码
        return sysUser;
    }

    @Override
    public SysUser getUser(String userCode) throws BaseAppException {
        return sysUserDao.selectByUserCode(userCode, null);
    }

    @Override
    public Long getUsersCount() throws BaseAppException {
        return sysUserDao.getUsersCount();
    }

    @Override
    public List<SysUser> getUsers(Integer pageIndex, Integer pageSize) throws BaseAppException {
        return sysUserDao.getUsers(pageIndex, pageSize);
    }

    @Override
    public List<SysUser> getUsersByRoleId(Long roleId) throws BaseAppException {
        return sysUserDao.getUsersByRoleId(roleId);
    }

    @Override
    public String getEncryptPwd(String userCode, String pwdText) {
        StringBuilder sb = new StringBuilder(pwdText);
        sb.append("#").append(userCode);
        return MD5.MD5Encode(sb.toString());
    }

    @Override
    @Transactional(readOnly = false)
    public int changePwd(String userCode, String oldPwdText, String newPwdText) throws BaseAppException {

        //原密码不能过于简单（不能相同）
        if (EqualsUtil.equals(oldPwdText, newPwdText)) {
            ExceptionHandler.publish(ErrorConst.USER_CHANGE_PWD_ORIGIN_AND_NEW_ARE_SAME_ERROR);
        }
        // 最基本的长度要求
        if (newPwdText.length() < 6 || newPwdText.length() > 20) {
            ExceptionHandler.publish(ErrorConst.USER_PWD_LENGTH_ERROR);
        }

        //TODO 复杂度要求

        //验证原密码
        String  encryptPwd = getEncryptPwd(userCode, oldPwdText);
        SysUser sysUser    = SessionUtil.getSessionUser();

        if (!EqualsUtil.equals(sysUser.getPwd(), encryptPwd)) {
            ExceptionHandler.publish(ErrorConst.USER_ORIGIN_PWD_ERROR);
        }


        //更新新密码
        String newEncryptPwd = getEncryptPwd(userCode, newPwdText);

        SysUser newUser = new SysUser();
        newUser.setUserId(sysUser.getUserId());
        newUser.setPwd(newEncryptPwd);
        return sysUserDao.updateUser(newUser);

    }

    @Override
    public Boolean checkUserMenuPriv(Long userId, String path) throws BaseAppException {
        Boolean hit = false;
        //TODO 全局菜单（不需要权限）


        // 所有菜单
        List<SysPriv> privs = sysPrivService.queryPrivsByUserId(userId, Const.PRIV_TYPE_MENU);

        if (ListUtil.isNotEmpty(privs)) {
            for (SysPriv priv : privs) {
                if (EqualsUtil.equals(priv.getPath(), path)) {
                    hit = true;
                    break;
                }
            }
        }

        return hit;
    }

    /**
     * 初始密码
     *
     * @param userCode
     * @return
     */
    private String getInitPwd(String userCode) {
        return getEncryptPwd(userCode, userCode);
    }
}
