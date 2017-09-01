package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.i18n.ResUtil;
import com.codi.base.web.RequestUtils;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.domain.SysUserSession;
import com.codi.superman.base.result.model.LoginModel;
import com.codi.superman.base.service.SysLoginService;
import com.codi.superman.base.service.SysUserService;
import com.codi.superman.base.service.SysUserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆相关
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:26:57
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserSessionService sysUserSessionService;

    @Autowired
    private SysLoginService sysLoginService;

    /**
     * 判断是否登陆
     *
     * @param request  req
     * @param response resp
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/isLogin")
    public BaseResult isLogin(HttpServletRequest request, HttpServletResponse response) throws BaseAppException {

        BaseResult result = new BaseResult();
        LoginModel loginModel = new LoginModel();


        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Const.SESSION_LOGIN_USER);

        if (obj == null) {
            loginModel.setLogin(false);
        } else {
            SysUser sysUser = (SysUser) obj;
            loginModel.setLogin(true);
            loginModel.setUserName(sysUser.getUserName());
            loginModel.setUserCode(sysUser.getUserCode());
            loginModel.setToken(session.getId());
        }

        result.setResult(loginModel);

        return result;
    }

    /**
     * 登陆
     *
     * @param request  req
     * @param response resp
     * @param userCode userCode
     * @param pwd      pwd
     * @return
     * @throws BaseAppException
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "userCode") String userCode, @RequestParam(value = "pwd") String pwd)
        throws BaseAppException {
        BaseResult result = new BaseResult();
        LoginModel loginModel = new LoginModel();

        SysUser sysUser = sysLoginService.login(userCode, pwd);

        HttpSession session = request.getSession();
        logger.debug("sessionId={}", session.getId());


        if (sysUser == null) {
            logger.debug("sysUser is invalid, please check!");
            loginModel.setLogin(false);
            result.setErrorCode(ErrorConst.USER_NOT_LOGIN);
            result.setErrorMessage(ResUtil.get(ErrorConst.USER_NOT_LOGIN));
        } else {
            logger.debug("sysUser is valid, then will generator token");

            String ua = RequestUtils.getHeader(request, Const.HTTP_HEADER_USER_AGENT, "No SysUser Agent");
            SysUserSession sysUserSession = sysUserSessionService.addSession(sysUser.getUserId(), sysUser.getUserCode(), ua, session.getId());

            session.setAttribute(Const.SESSION_LOGIN_USER, sysUser);
            session.setAttribute(Const.SESSION_LOGIN_USER_SESSION, sysUserSession);

            loginModel.setLogin(true);
            loginModel.setUserCode(sysUser.getUserCode());
            loginModel.setUserName(sysUser.getUserName());
            loginModel.setToken(session.getId());
        }

        result.setResult(loginModel);

        return result;
    }


    @RequestMapping("/logout")
    public BaseResult logout(HttpServletRequest request, HttpServletResponse response) throws BaseAppException {
        logger.debug("logout begin");
        HttpSession session = request.getSession();

        if (session != null) {
            Object userObj = session.getAttribute(Const.SESSION_LOGIN_USER);
            if (userObj != null) {
                sysLoginService.logout((SysUser) userObj);

                session.removeAttribute(Const.SESSION_LOGIN_USER);
                session.invalidate();
            }
        }
        return new BaseResult(true, null);
    }

}
