package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysUser;

import java.util.List;

/**
 * 用户DAO
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午11:22:01
 */
public interface SysUserDao extends BaseDAO<SysUser> {

    /**
     * 在用
     */
    String USER_STATE_A = "A";

    /**
     * 已删除
     */
    String USER_SATE_X = "X";


    /**
     * 检查用户编码是否存在
     *
     * @param userCode
     * @param userId
     * @return
     */
    Boolean checkUserCode(String userCode, Long userId);


    /**
     * 插入用户
     *
     * @param record
     * @return
     */
    int insert(SysUser record);

    /**
     * 更新用户
     *
     * @param record
     * @return
     */
    int updateUser(SysUser record);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    int delUser(Long userId);

    /**
     * 查询用户
     *
     * @param userCode 用户编码
     * @param state    用户状态
     * @return
     */
    SysUser selectByUserCode(String userCode, String state);

    /**
     * 查询用户
     *
     * @param userId 用户ID
     * @return
     */
    SysUser selectById(Long userId);

    /**
     * 获取用户总数
     *
     * @return
     */
    Long getUsersCount();

    /**
     * 分页获取用户
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<SysUser> getUsers(Integer pageIndex, Integer pageSize);

    /**
     * 通过roleId查询用户
     *
     * @param roleId
     * @return
     */
    List<SysUser> getUsersByRoleId(Long roleId);


    /**
     * 锁定用户
     *
     * @param userId
     * @return
     */
    int lockUser(Long userId);


}
