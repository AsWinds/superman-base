package com.codi.superman.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.base.util.StringUtil;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysParamDao;
import com.codi.superman.base.domain.SysParam;
import com.codi.superman.base.service.SysParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysParam Service
 *
 * @author shi.pengyan
 * @date 2016-12-25 17:24
 */
@Service("sysParamService")
@Transactional(readOnly = true)
public class SysParamServiceImpl extends AbstractServiceImpl implements SysParamService {

    @Resource(name = "sysParamDao")
    private SysParamDao sysParamDao;

    @Override
    @Transactional(readOnly = false)
    public SysParam addParam(SysParam sysParam) throws BaseAppException {
        Assert.notNull(sysParam);

        //
        Date now = new Date();
        sysParam.setCreateDate(now);
        sysParam.setUpdateDate(now);
        sysParam.setState(Const.STATE_A);

        return sysParamDao.insert(sysParam);
    }

    @Override
    @Transactional(readOnly = false)
    public int delParam(Long paramId) throws BaseAppException {
        return sysParamDao.deleteByParamId(paramId);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateParam(SysParam sysParam) throws BaseAppException {
        //TODO
        sysParam.setUpdateDate(new Date());

        return sysParamDao.updateParam(sysParam);
    }

    @Override
    @Transactional(readOnly = false)
    public int enableParam(Long paramId) throws BaseAppException {
        return sysParamDao.updateParamState(paramId, Const.STATE_A);
    }

    @Override
    @Transactional(readOnly = false)
    public int disableParam(Long paramId) throws BaseAppException {
        return sysParamDao.updateParamState(paramId, Const.STATE_X);
    }

    @Override
    public SysParam queryParam(String paramCode) throws BaseAppException {
        return sysParamDao.selectParam(paramCode);
    }

    @Override
    public List<SysParam> queryParams(String paramCode) throws BaseAppException {
        return sysParamDao.selectParams(paramCode, Const.STATE_A);
    }

    @Override
    public List<SysParam> queryParams(String paramCode, String state) throws BaseAppException {
        return sysParamDao.selectParams(paramCode, state);
    }

    @Override
    public Long queryParamsCount() throws BaseAppException {
        return sysParamDao.selectParamsCount();
    }

    @Override
    public List<SysParam> queryParams(Integer pageIndex, Integer pageSize) throws BaseAppException {
        return sysParamDao.selectParams(pageIndex, pageSize);
    }

    @Override
    public Map<String, String> queryParamMoreValueMap(SysParam sysParam) {

        String value = sysParam.getParamMoreValue();

        logger.debug("value={}", value);

        if (StringUtil.isEmpty(value)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String[] values = value.split(";");
        for (String item : values) {
            String[] kv = item.split("=");
            if (kv.length == 2) {
                map.put(kv[0], kv[1]);
            }
        }

        logger.debug("map={}", JSON.toJSONString(map));

        return map;
    }


}
