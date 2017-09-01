package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysFile;
import com.codi.superman.base.result.model.SysFileModel;

import java.util.List;

/**
 * 上传文件
 *
 * @author spy
 * @date 2017-03-30 11:15
 */
public interface SysFileDao extends BaseDAO<SysFile> {

    Integer STATE_A = 1;
    Integer STATE_X = 0;

    /**
     * 通过主键删除文件
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据唯一的文件名删除文件
     *
     * @param uniqueFileName
     * @return
     */
    int deleteByUniqueFileName(String uniqueFileName);

    int insertSelective(SysFile record);

    SysFile selectByPrimaryKey(Long id);

    List<SysFileModel> queryFiles(Integer pageIndex, Integer pageSize);

    List<SysFileModel> queryPictures(Integer pageIndex, Integer pageSize, String mimeType);

    int queryPictureCount(String mimeType);

    Long queryFilesCount();


    int updateByPrimaryKeySelective(SysFile record);

    int updateByPrimaryKey(SysFile record);

    List<SysFileModel> queryFilesByFileName(String fileName, Integer pageIndex, Integer pageSize, String mimeType);

    int queryCountByFileName(String fileName, String mimeType);


}
