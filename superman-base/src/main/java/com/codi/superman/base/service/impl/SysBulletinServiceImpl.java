package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.base.util.ListUtil;
import com.codi.base.util.MapUtils;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysBulletinDao;
import com.codi.superman.base.dao.SysCommonGroupDao;
import com.codi.superman.base.domain.SysBulletin;
import com.codi.superman.base.result.model.SysBulletinModel;
import com.codi.superman.base.service.SysBulletinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 周翔 on 2017/4/7.
 */
@Service("bulletinService")
@Transactional(readOnly = true)
public class SysBulletinServiceImpl extends AbstractServiceImpl implements SysBulletinService {
    @Resource(name = "sysBulletinDao")
    private SysBulletinDao sysBulletinDao;
    @Resource(name = "sysCommonGroupDao")
    private SysCommonGroupDao sysCommonGroupDao;


    @Override
    public List<SysBulletinModel> queryBulletinsAdmin(Long groupId, Integer pageIndex, Integer pageSize) throws BaseAppException {
        if (groupId == 0) {
            groupId = null;
        }
        List<Map<String, Object>> list = sysBulletinDao.selectAllBulletinAdmin(groupId, pageIndex, pageSize);
        return this.setModel(list);
    }

    @Override
    public int selectBulletinCount(Long groupId) throws BaseAppException {
        if (groupId == 0) {
            return sysBulletinDao.selectBulletinCount();
        } else {
            return sysBulletinDao.selectCountByGroupId(groupId);
        }

    }

    @Override
    public SysBulletin queryBulletinById(Long id) throws BaseAppException {
        return sysBulletinDao.selectByPrimaryKey(id);
    }
    @Override
    @Transactional(readOnly = false)
    public int addBulletin(SysBulletin sysBulletin) throws BaseAppException {
        Assert.notNull(sysBulletin.getGroupId());
        Assert.notNull(sysBulletin.getContent());
        Assert.notNull(sysBulletin.getTitle());
        Assert.notNull(sysBulletin.getType());
        sysBulletin.setState(Const.BULLETIN_USE_STATE);
        Date now = new Date();
        sysBulletin.setCreateDate(now);
        sysBulletin.setUpdateDate(now);
        return sysBulletinDao.insert(sysBulletin);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteBulletin(Long id) throws BaseAppException {
        return sysBulletinDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateBulletin(SysBulletin sysBulletin) throws BaseAppException {
        Date now = new Date();
        sysBulletin.setUpdateDate(now);
        return sysBulletinDao.updateByPrimaryKeySelective(sysBulletin);
    }

    @Override
    @Transactional(readOnly = false)
    public int reviewBulletin(SysBulletin sysBulletin) throws BaseAppException {
        Date now = new Date();
        sysBulletin.setUpdateDate(now);
        return sysBulletinDao.updateByPrimaryKeySelective(sysBulletin);
    }

    @Override
    public Map<String, Object> queryBulletinAPP(String bizCode, Integer pageIndex, Integer pageSize) {
        Long groupId = sysCommonGroupDao.selectGroupIdByBizCode(bizCode);
        if (pageSize > 50) {
            pageSize = 50;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        Map<String, Object> map = sysBulletinDao.selectBulletinByGroupId(groupId, pageIndex, pageSize);
        return map;
    }



    @Override
    public SysBulletin queryBulletinByIdAPP(Long id) {
        return sysBulletinDao.selectBulletinByIdUser(id);
    }


    @Override
    public List<SysBulletinModel> setModel(List<Map<String, Object>> list) {
        List<SysBulletinModel> result = null;
        if (ListUtil.isNotEmpty(list)) {
            result = new ArrayList<>(list.size());
            for (Map<String, Object> map : list) {
                SysBulletinModel model = new SysBulletinModel();
                model.setGroupName(MapUtils.getStr(map, "groupName"));
                model.setAuthor(MapUtils.getStr(map, "author"));
                model.setContent(MapUtils.getStr(map, "content"));
                model.setCreateDate(MapUtils.getDate(map, "createDate"));
                model.setDescription(MapUtils.getStr(map, "description"));
                model.setDeviceType(MapUtils.getInteger(map, "deviceType"));
                model.setEffectDate(MapUtils.getDate(map, "effectDate"));
                model.setExpireDate(MapUtils.getDate(map, "expireDate"));
                model.setGroupId(MapUtils.getLong(map, "groupId"));
                model.setHomeImgUrl(MapUtils.getStr(map, "homeImgUrl"));
                model.setId(MapUtils.getLong(map, "id"));
                model.setIntro(MapUtils.getStr(map, "intro"));
                model.setBizId(MapUtils.getStr(map, "bizId"));
                model.setState(MapUtils.getInteger(map, "state"));
                model.setTitle(MapUtils.getStr(map, "title"));
                model.setUpdateDate(MapUtils.getDate(map, "updateDate"));
                model.setUrl(MapUtils.getStr(map, "url"));
                model.setType(MapUtils.getInteger(map, "type"));
                result.add(model);
            }
        }
        return result;
    }
}
