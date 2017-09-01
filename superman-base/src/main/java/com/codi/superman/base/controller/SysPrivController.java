package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.EqualsUtil;
import com.codi.base.util.ListUtil;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysUserRoleDao;
import com.codi.superman.base.domain.SysPriv;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.domain.SysUserRole;
import com.codi.superman.base.result.SysMenuResult;
import com.codi.superman.base.result.model.SysMenuModel;
import com.codi.superman.base.service.SysPrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 *
 * @author shi.pengyan
 * @date 2016-12-26 9:39
 */
@RestController
@RequestMapping("/sys/priv")
public class SysPrivController extends BaseController {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysPrivService sysPrivService;

    /**
     * 新增权限
     *
     * @param sysPriv
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:priv:add")
    public BaseResult addPriv(SysPriv sysPriv) throws BaseAppException {

        logger.debug("add priv begin");
        sysPrivService.addPriv(sysPriv);

        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 更新权限
     *
     * @param sysPriv
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:priv:update")
    public BaseResult updatePriv(SysPriv sysPriv) throws BaseAppException {

        sysPrivService.modifyPriv(sysPriv);

        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 删除权限
     *
     * @param privId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete/{privId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:priv:delete")
    public BaseResult delPriv(@PathVariable(value = "privId") Long privId) throws BaseAppException {

        sysPrivService.delPriv(privId);

        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 查询权限
     *
     * @param privId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "{privId}", method = RequestMethod.GET)
    public BaseResult getPriv(@PathVariable(value = "privId") Long privId) throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.queryPriv(privId));
        return result;
    }


    /**
     * 获取指定Role下的权限
     *
     * @param roleId
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "rolepriv")
    public BaseResult getRolePrivs(@RequestParam(value = "roleId") Long roleId,
                                   @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "20") String pageSize) throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.queryPrivsByRoleId(roleId));
        return result;
    }

    /**
     * 查询用户所有权限
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "userpriv/{userId}", method = RequestMethod.GET)
    public BaseResult getUserPrivsByUserId(@PathVariable(value = "userId") Long userId) throws BaseAppException {

        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.queryPrivsByUserId(userId));

        return result;
    }

    /**
     * 获取用户菜单
     *
     * @param request
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/menu")
    public SysMenuResult getUserMenu(HttpServletRequest request) throws BaseAppException {
        SysMenuResult result = new SysMenuResult();

        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute(Const.SESSION_LOGIN_USER);

        //查询用户所有的角色
        List<SysUserRole> sysUserRoles = sysUserRoleDao.queryUserRole(sysUser.getUserId());

        if (ListUtil.isEmpty(sysUserRoles)) {
            logger.warn("userId={},userCode={} has no role", sysUser.getUserId(), sysUser.getUserCode());
            return result;
        }

        //查询所有角色的权限
        List<Long> roleIds = new ArrayList<>(sysUserRoles.size());
        for (SysUserRole sysUserRole : sysUserRoles) {
            roleIds.add(sysUserRole.getRoleId());
        }

        List<SysPriv> sysPrivs = sysPrivService.queryPrivsByRoleIds(roleIds);
        if (ListUtil.isEmpty(sysPrivs)) {
            logger.warn("userId={},userCode={} has no sysPrivs", sysUser.getUserId(), sysUser.getUserCode());
            return result;
        }

        //组装成菜单
        List<SysMenuModel> menus = makeMenu(sysPrivs);
        result.setResult(menus);

        return result;
    }

    /**
     * 查询权限总数
     *
     * @return
     * @throws BaseAppException
     */

    @RequestMapping("/privsCount")
    public BaseResult getPrivsCount() throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.queryPrivsCount());
        return result;
    }


    /**
     * 查询所有菜单【扁平列表】
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "privs", method = RequestMethod.GET)
    public BaseResult getPrivs(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.queryPrivs(pageIndex, pageSize));

        return result;
    }

    /**
     * 获取所有权限【树形结构】
     *
     * @param request
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/privsTree")
    public SysMenuResult getAllMenu(HttpServletRequest request) throws BaseAppException {
        SysMenuResult result = new SysMenuResult();

        List<SysPriv> sysPrivs = sysPrivService.queryPrivs(null, null);
        if (ListUtil.isEmpty(sysPrivs)) {
            logger.warn("there is no sysPrivs in system db.");
            return result;
        }

        //组装成菜单
        List<SysMenuModel> menus = makePriv(sysPrivs, false);
        result.setResult(menus);

        return result;
    }

    /**
     * 获取更目录权限
     *
     * @return
     */
    @RequestMapping(value = "/rootPrivs", method = RequestMethod.GET)
    public BaseResult getRootPrivs() throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.queryRootPrivs());
        return result;
    }

    /**
     * 菜单权限
     *
     * @param sysPrivs
     * @return
     */
    private List<SysMenuModel> makeMenu(List<SysPriv> sysPrivs) {
        return makePriv(sysPrivs, true);
    }

    /**
     * 所有权限
     *
     * @param sysPrivs
     * @param filterDataPriv 是否要过滤数据权限
     * @return
     */
    private List<SysMenuModel> makePriv(List<SysPriv> sysPrivs, Boolean filterDataPriv) {
        if (filterDataPriv == null) {
            filterDataPriv = false;
        }
        List<SysMenuModel> menus = new ArrayList<>(sysPrivs.size());

        for (SysPriv sysPriv : sysPrivs) {
            logger.debug("sysPriv={}", sysPriv);
            SysMenuModel menuModel = new SysMenuModel();

            menuModel.setId(sysPriv.getPrivId());
            menuModel.setCode(sysPriv.getPrivCode());
            menuModel.setName(sysPriv.getPrivName());
            menuModel.setType(sysPriv.getType());
            menuModel.setUrl(sysPriv.getUrl());
            menuModel.setPath(sysPriv.getPath());
            menuModel.setDescription(sysPriv.getDescription());

            if (EqualsUtil.equals(sysPriv.getType().intValue(), Const.PRIV_TYPE_DIRECTORY.intValue())) {
                List<SysMenuModel> subMenus = new ArrayList<>();
                menuModel.setChildren(subMenus);
                menus.add(menuModel);

            } else if (EqualsUtil.equals(sysPriv.getType().intValue(), Const.PRIV_TYPE_MENU.intValue())) {
                //如果是菜单则查找父层目录，如果没有，则添加到列表末尾
                boolean hit = false;
                for (SysMenuModel menu : menus) {
                    if (EqualsUtil.equals(menu.getId().longValue(), sysPriv.getParentPrivId().longValue())) {
                        menu.getChildren().add(menuModel);
                        hit = true;
                        break;
                    }
                }
                if (!hit) {
                    menus.add(menuModel);
                }
            } else if (EqualsUtil.equals(sysPriv.getType().intValue(), Const.PRIV_TYPE_DATA.intValue())) {
                if (!filterDataPriv) {
                    menus.add(menuModel);
                }
            } else {
                logger.warn("unkown type sysPriv,{}", sysPriv);
            }

        }

        return menus;
    }

}
