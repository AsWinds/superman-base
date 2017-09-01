package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysFile;
import com.codi.superman.base.result.model.SysFileModel;

import java.util.List;

/**
 * 上传文件管理
 *
 * @author shi.pengyan
 * @date 2017-03-30 11:23
 */
public interface SysFileService {

    SysFile query(Long fileId);

    SysFile save(SysFileModel sysFileModel) throws BaseAppException;

    int delete(Long id) throws BaseAppException;

    int delete(String uniqueFileName) throws BaseAppException;

    int update(SysFile sysFile) throws BaseAppException;

    List<SysFileModel> queryFiles(Integer pageIndex, Integer pageSize) throws BaseAppException;

    Long queryFilesCount() throws BaseAppException;

    List<SysFileModel> queryFilesByFileName(String fileName, Integer pageIndex, Integer pageSize);

    int queryCountByFileName(String fileName);

    List<SysFileModel> queryPictures(Integer pageIndex, Integer pageSize);

    int queryPictureCount();
}
