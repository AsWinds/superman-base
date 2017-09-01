package com.codi.superman.base.result;

import com.codi.base.domain.BaseResult;
import com.codi.superman.base.domain.SysParam;

import java.util.List;

/**
 * 系统参数
 *
 * @author shi.pengyan
 * @date 2016-12-26 20:48
 */
public class SysParamResult extends BaseResult {

    private List<SysParam> result;

    @Override
    public List<SysParam> getResult() {
        return result;
    }

    public void setResult(List<SysParam> result) {
        this.result = result;
    }
}
