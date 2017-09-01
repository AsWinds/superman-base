package com.codi.superman.base.manager.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.base.util.MD5;
import com.codi.base.util.StringUtil;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.domain.SysFile;
import com.codi.superman.base.domain.SysFileBucket;
import com.codi.superman.base.domain.SysParam;
import com.codi.superman.base.manager.FileUploadMgr;
import com.codi.superman.base.result.model.UploadTokenModel;
import com.codi.superman.base.service.SysFileBucketService;
import com.codi.superman.base.service.SysFileService;
import com.codi.superman.base.service.SysParamService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-29 14:49
 */
@Component
@Slf4j
public class FileUploadMgrImpl implements FileUploadMgr {

    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private SysFileBucketService sysFileBucketService;

    @Autowired
    private SysFileService sysFileService;


    @Override
    public UploadTokenModel getUploadToken(String suffix, Long bucketId) throws BaseAppException {
        SysParam accessKeyParam = sysParamService.queryParam(Const.QINIU_ACCESS_KEY);
        SysParam secretKeyParam = sysParamService.queryParam(Const.QINIU_SECRET_KEY);

        SysFileBucket fileBucket = sysFileBucketService.query(bucketId);


        Assert.notNull(accessKeyParam);
        Assert.notNull(secretKeyParam);
        Assert.notNull(fileBucket);

        log.debug("accessKey={}\n secretKey={}", accessKeyParam.getParamValue(), secretKeyParam.getParamValue());
        log.debug("bucket={},bucketHost={}", fileBucket.getBucketCode(), fileBucket.getHostName());

        String filename = makeFileName(suffix);

        return getUploadToken(
            accessKeyParam.getParamValue(),
            secretKeyParam.getParamValue(),
            fileBucket.getBucketCode(),
            fileBucket.getHostName(),
            filename);

    }

    @Override
    public void deleteFile(String bucket, String fileName) throws BaseAppException {
        SysParam accessKeyParam = sysParamService.queryParam(Const.QINIU_ACCESS_KEY);
        SysParam secretKeyParam = sysParamService.queryParam(Const.QINIU_SECRET_KEY);

        Assert.notNull(accessKeyParam);
        Assert.notNull(secretKeyParam);
        Assert.notNull(bucket);
        deleteFile(
            accessKeyParam.getParamValue(),
            secretKeyParam.getParamValue(),
            bucket,
            fileName);
    }


    @Override
    public UploadTokenModel getUploadToken(String accessKey, String secretKey, String bucket, String bucketHost, String fileName) {

        StringBuilder returnBody = new StringBuilder();
        returnBody.append("{");
        returnBody.append("\"key\":\"$(key)\"").append(",");
        returnBody.append("\"hash\":\"$(etag)\"").append(",");
        returnBody.append("\"bucket\":\"$(bucket)\"").append(",");
        returnBody.append("\"fname\":\"$(fname)\"").append(",");
        returnBody.append("\"fsize\":$(fsize)").append(",");
        returnBody.append("\"mimeType\":\"${mimeType}\"");
        returnBody.append("}");


        StringMap putPolicy = new StringMap();
        //TODO 采用手动提交方式
        //putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
        putPolicy.put("returnBody", returnBody.toString());
        putPolicy.put("callbackBodyType", "application/json");

        long expireSeconds = 10 * 60;//10min

        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket, fileName, expireSeconds, putPolicy);

        log.info("upload token is {}", uploadToken);

        UploadTokenModel model = new UploadTokenModel(uploadToken, bucketHost, fileName);
        return model;
    }


    @Override
    public void deleteFile(String accessKey, String secretKey, String bucket, String fileName) throws BaseAppException {
        //TODO 可配置？
        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            log.error("fail to delete file", ex);

            //如果遇到异常，说明删除失败
            log.error("errrCode={}", ex.code());
            log.error("errorMsg={}", ex.response.toString());
//            System.err.println(ex.code());
//            System.err.println(ex.response.toString());

            //最好不要再抛异常了
//            ExceptionHandler.publish(ErrorConst.SYS_UPLOAD_DEL_ERROR);
        }
    }

    @Override
    public String makeFileName(String suffix) {
        String uuid = UUID.randomUUID().toString();
        String filename = MD5.MD5Encode(uuid);

        log.info("filename={}", filename);

        StringBuilder builder = new StringBuilder();
//        String prefix = DateUtil.format(new Date(), "yyyyMMdd");
//        builder.append(prefix);

        builder.append(filename);
        if (StringUtil.isNotEmpty(suffix)) {
            builder.append(".").append(suffix);
        }

        return builder.toString();
    }

    @Override
    public String generatePrivUrl(Long fileId) throws BaseAppException {
        SysParam accessKeyParam = sysParamService.queryParam(Const.QINIU_ACCESS_KEY);
        SysParam secretKeyParam = sysParamService.queryParam(Const.QINIU_SECRET_KEY);
        SysFile sysFile = sysFileService.query(fileId);


        return makePrivUrl(accessKeyParam.getParamValue(),
            secretKeyParam.getParamValue(),
            sysFile.getFileUrl());
    }

    private String makePrivUrl(String accessKey, String secretKey, String baseUrl) {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.privateDownloadUrl(baseUrl);
    }

}
