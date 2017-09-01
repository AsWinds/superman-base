package com.codi.superman.base.result;

import com.codi.base.domain.BaseResult;
import com.codi.superman.base.result.model.SysMenuModel;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-26 19:10
 */
public class SysMenuResult extends BaseResult {

    private List<SysMenuModel> result;

    @Override
    public List<SysMenuModel> getResult() {
        return result;
    }

    public void setResult(List<SysMenuModel> result) {
        this.result = result;
    }
}
