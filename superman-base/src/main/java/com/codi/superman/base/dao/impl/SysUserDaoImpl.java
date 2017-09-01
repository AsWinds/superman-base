package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.dao.SysUserDao;
import com.codi.superman.base.domain.SysUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shi.pengyan
 * @date 2016年11月8日 上午11:21:27
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDAOImpl<SysUser> implements SysUserDao {

    @Override
    public Boolean checkUserCode(String userCode, Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserCode(userCode);
        sysUser.setUserId(userId);

        return this.getSqlSession().selectOne("checkUserCode", sysUser);
    }

    @Override
    public int insert(SysUser record) {
        return this.insert(generateStatement("insertSelective"), record);
    }

    @Override
    public int updateUser(SysUser record) {
        return this.update(generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public int delUser(Long userId) {
        return this.delete(generateStatement("delUser"), userId);
    }

    @Override
    public SysUser selectByUserCode(String userCode, String state) {
        SysUser sysUser = new SysUser();
        sysUser.setUserCode(userCode);
        sysUser.setState(state);

        return getObject(generateStatement("selectUser"), sysUser);
    }

    @Override
    public SysUser selectById(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);

        return getObject(generateStatement("selectUser"), sysUser);
    }

    @Override
    public Long getUsersCount() {
        return this.getSqlSession().selectOne(generateStatement("getUsersCount"));
    }

    @Override
    public List<SysUser> getUsers(Integer pageIndex, Integer pageSize) {
        PageView pageView = this.getPageView(pageIndex, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageView", pageView);

        return this.findList(generateStatement("getUsers"), map);
    }

    @Override
    public List<SysUser> getUsersByRoleId(Long roleId) {
        return this.findList(generateStatement("getUsersByRoleId"), roleId);
    }

    @Override
    public int lockUser(Long userId) {
        return this.update(generateStatement("lockUser"), userId);
    }
}
