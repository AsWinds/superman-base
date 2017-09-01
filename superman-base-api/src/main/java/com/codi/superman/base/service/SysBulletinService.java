package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysBulletin;
import com.codi.superman.base.result.model.SysBulletinModel;

import java.util.List;
import java.util.Map;

/**
 * Created by 周翔 on 2017/4/7.
 */
public interface SysBulletinService {

    /**
     * 管理员得到所有Bulletin
     *
     * @return
     * @throws BaseAppException
     */
    List<SysBulletinModel> queryBulletinsAdmin(Long groupId, Integer pageIndex, Integer pageSize) throws BaseAppException;

    /**
     * 管理员得到公告总数
     *
     * @return
     * @throws BaseAppException
     */
    int selectBulletinCount(Long groupId) throws BaseAppException;

    /**
     * 管理员通过id得到公告信息
     *
     * @param id
     * @return
     * @throws BaseAppException
     */
    SysBulletin queryBulletinById(Long id) throws BaseAppException;

    /**
     * 添加Bulletin
     *
     * @return
     */
    int addBulletin(SysBulletin sysBulletin) throws BaseAppException;

    /**
     * 删除Bulletin
     *
     * @param id
     * @return
     */
    int deleteBulletin(Long id) throws BaseAppException;

    /**
     * 更新Bulletin
     *
     * @return
     */
    int updateBulletin(SysBulletin sysBulletin) throws BaseAppException;

    /**
     * 审核公告
     *
     * @param id
     * @param state
     * @return
     * @throws BaseAppException
     */
    int reviewBulletin(SysBulletin sysBulletin) throws BaseAppException;
    /**
     * APP端用户,通过不同的分组 获得公告标题,时间,id
     *
     * @return
     */
    Map<String, Object> queryBulletinAPP(String bizCode, Integer pageIndex, Integer pageSize);



    /**
     * APP端用户根据id获取公告内容
     *
     * @return
     */
    SysBulletin queryBulletinByIdAPP(Long id);


    /**
     * 从map中填入新的model的方法
     *
     * @param list
     * @return
     */
    List<SysBulletinModel> setModel(List<Map<String, Object>> list);

}
