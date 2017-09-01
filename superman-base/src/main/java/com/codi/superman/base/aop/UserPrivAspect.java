package com.codi.superman.base.aop;

import com.codi.base.config.ConfigurationMgr;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.EqualsUtil;
import com.codi.base.util.ListUtil;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.domain.SysPriv;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.SysPrivService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-01-10 15:06
 */
public class UserPrivAspect {

    private static Logger logger = LoggerFactory.getLogger(UserPrivAspect.class);

    @Autowired
    private SysPrivService sysPrivService;

    public void before(JoinPoint joinPoint) throws Throwable {
        boolean isDevelopment = ConfigurationMgr.getInstance().getBoolean("codi.isDevelopment", false);

        if (isDevelopment) {
            logger.warn("In DEVELOPMENT MODEL, not check user priv");
            return;
        }

        logger.debug("check user priv....");
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);

        if (requiresPermissions != null) {
            String permissions = requiresPermissions.value();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();

            Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);
            if (userObj == null) {
                logger.error("session user is null, plz check!");
                ExceptionHandler.publish(ErrorConst.USER_SESSION_TIMEOUT);
            }

            SysUser sysUser = (SysUser) userObj;
            if (ObjectUtils.isEmpty(sysUser) || ObjectUtils.isEmpty(sysUser.getUserId())) {
                logger.error("user info is null, plz check");
                ExceptionHandler.publish(ErrorConst.USER_SESSION_TIMEOUT);
            }

            //用户权限列表
            List<SysPriv> sysPrivs = sysPrivService.queryPrivsByUserId(sysUser.getUserId(), Const.PRIV_TYPE_DATA);
            //TODO maybe cache is nice.

            Boolean hit = false;
            if (ListUtil.isNotEmpty(sysPrivs)) {
                for (SysPriv sysPriv : sysPrivs) {
                    if (EqualsUtil.equals(sysPriv.getPrivCode(), permissions)) {
                        hit = true;
                        break;
                    }
                }
            }

            if (!hit) {
                ExceptionHandler.publish(ErrorConst.NO_PERMISSION);
            }
        } else {
            logger.warn("There is no @RequiresPermissions in method. Please check.");
        }
    }

}
