package com.codi.superman.base.intercept;

import com.codi.base.config.ConfigurationMgr;
import com.codi.base.exception.ExceptionHandler;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.SysUserService;
import com.codi.superman.base.service.SysUserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证Token
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:37:15
 */
public class TokenInteceptor extends HandlerInterceptorAdapter {
    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(TokenInteceptor.class);

    @Autowired
    private SysUserSessionService sysUserSessionService;

    @Autowired
    private SysUserService sysUserService;

    /**
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("request URL={}", request.getRequestURL().toString());

        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);

        if (userObj == null) {

            ConfigurationMgr config = ConfigurationMgr.getInstance();
            if (config.getBoolean(com.codi.base.common.Const.IS_DEVELOPMENT, false)) {
                logger.warn("create development sysUser session, this shuld not be existed.");
                //模拟创建session
                SysUser sysUser = sysUserService.getUser(1L);
                session.setAttribute(Const.SESSION_LOGIN_USER, sysUser);
                return true;
            }

            ExceptionHandler.publish(ErrorConst.USER_SESSION_TIMEOUT);
            return false;
        }

        return true;
    }
}
