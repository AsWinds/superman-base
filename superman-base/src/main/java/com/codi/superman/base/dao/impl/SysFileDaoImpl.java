package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.base.util.ListUtil;
import com.codi.base.util.MapUtils;
import com.codi.superman.base.dao.SysFileDao;
import com.codi.superman.base.domain.SysFile;
import com.codi.superman.base.result.model.SysFileModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-30 11:17
 */
@Repository("sysFileDao")
public class SysFileDaoImpl extends BaseDAOImpl<SysFile> implements SysFileDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.delete(generateStatement("deleteByPrimaryKey"), id);
    }

    @Override
    public int deleteByUniqueFileName(String uniqueFileName) {
        return this.delete(generateStatement("deleteByUniqueFileName"), uniqueFileName);
    }

    @Override
    public int insertSelective(SysFile record) {
        return this.insert(generateStatement("insertSelective"), record);
    }

    @Override
    public SysFile selectByPrimaryKey(Long id) {
        return this.getObject(generateStatement("selectByPrimaryKey"), id);
    }

    @Override
    public List<SysFileModel> queryFiles(Integer pageIndex, Integer pageSize) {
        PageView pageView = this.getPageView(pageIndex, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageView", pageView);
        List<Map<String, Object>> temp = getSqlSession().selectList(generateStatement("queryFiles"), map);

        List<SysFileModel> result = this.setResult(temp);

        return result;
    }

    @Override
    public List<SysFileModel> queryPictures(Integer pageIndex, Integer pageSize, String mimeType) {
        PageView pageView = this.getPageView(pageIndex, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageView", pageView);
        map.put("mimeType", mimeType);
        List<Map<String, Object>> temp = getSqlSession().selectList(generateStatement("queryPictures"), map);

        List<SysFileModel> result = this.setResult(temp);

        return result;
    }

    @Override
    public int queryPictureCount(String mimeType) {
        return this.getSqlSession().selectOne(generateStatement("queryPictureCount"), mimeType);
    }

    @Override
    public Long queryFilesCount() {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysFile record) {
        return this.update(generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public int updateByPrimaryKey(SysFile record) {
        return 0;
    }

    @Override
    public List<SysFileModel> queryFilesByFileName(String fileName, Integer pageIndex, Integer pageSize, String mimeType) {
        PageView pageView = this.getPageView(pageIndex, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageView", pageView);
        map.put("fileName", fileName);
        map.put("mimeType", mimeType);
        List<Map<String, Object>> list = this.getSqlSession().selectList(generateStatement("queryFilesByFileName"), map);
        List<SysFileModel> result = this.setResult(list);
        return result;
    }

    @Override
    public int queryCountByFileName(String fileName, String mimeType) {
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", fileName);
        map.put("mimeType", mimeType);
        return this.getSqlSession().selectOne(generateStatement("queryCountByFileName"), map);
    }

    List<SysFileModel> setResult(List<Map<String, Object>> list) {
        List<SysFileModel> result = null;
        if (ListUtil.isNotEmpty(list)) {
            result = new ArrayList<>(list.size());
            for (Map<String, Object> item : list) {
                SysFileModel model = new SysFileModel();

                model.setId(MapUtils.getLong(item, "ID"));
                model.setFileName(MapUtils.getStr(item, "FILE_NAME"));
                model.setOriginFileName(MapUtils.getStr(item, "ORIGIN_FILE_NAME"));
                model.setHash(MapUtils.getStr(item, "HASH"));
                model.setFileUrl(MapUtils.getStr(item, "FILE_URL"));
                model.setMimeType(MapUtils.getStr(item, "MIME_TYPE"));
                model.setFileSize(MapUtils.getStr(item, "FILE_SIZE"));
                model.setState(MapUtils.getInteger(item, "STATE"));
                model.setCreateDate(MapUtils.getDate(item, "CREATE_DATE"));
                model.setUpdateDate(MapUtils.getDate(item, "UPDATE_DATE"));
                model.setDescription(MapUtils.getStr(item, "DESCRIPTION"));
                model.setBucket(MapUtils.getStr(item, "BUCKET_NAME"));
//                Integer isPublic = MapUtils.getInteger(item, "IS_PUBLIC");
//                model.setIsPublic(EqualsUtil.equals(1, isPublic == null ? 1 : isPublic));
                model.setIsPublic(MapUtils.getBoolean(item, "IS_PUBLIC"));
                result.add(model);
            }
        }
        return result;
    }
}
