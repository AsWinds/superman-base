package com.codi.superman.base.util;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.domain.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Session Util
 *
 * @author shi.pengyan
 * @date 2017-01-10 17:46
 */
public class SessionUtil {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static SysUser getSessionUser() throws BaseAppException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);

        if (userObj != null) {
            return (SysUser) userObj;
        } else {
            throw new BaseAppException(ErrorConst.USER_SESSION_TIMEOUT);
        }
    }
}
