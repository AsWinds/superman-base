package com.codi.superman.base.manager;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.result.model.UploadTokenModel;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-29 14:49
 */
public interface FileUploadMgr {


    /**
     * 获取系统默认的上传token（无回调方式）
     *
     * @return
     * @throws BaseAppException
     * @Param String suffix
     */
    UploadTokenModel getUploadToken(String suffix, Long bucketId) throws BaseAppException;

    /**
     * 删除文件
     *
     * @param  bucket
     * @param fileName
     * @throws BaseAppException
     */
    void deleteFile(String bucket, String fileName) throws BaseAppException;


    /**
     * 获取更多配置的token （无回调方式）
     *
     * @param accessKey
     * @param secretKey
     * @param bucket
     * @param fileName
     * @return
     */
    UploadTokenModel getUploadToken(String accessKey, String secretKey, String bucket, String bucketHost, String fileName);

    /**
     * 删除文件
     *
     * @param accessKey
     * @param secretKey
     * @param bucket
     * @param fileName
     * @throws BaseAppException
     */
    void deleteFile(String accessKey, String secretKey, String bucket, String fileName) throws BaseAppException;


    String makeFileName(String suffix);

    /**
     * 获取私有文件下载链接
     *
     * @param fileId
     * @return
     * @throws BaseAppException
     */
    String generatePrivUrl(Long fileId) throws BaseAppException;

}
