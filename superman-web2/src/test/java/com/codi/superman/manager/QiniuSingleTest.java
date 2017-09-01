package com.codi.superman.manager;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-29 13:34
 */
@Slf4j
public class QiniuSingleTest {

    private static final String accessKey = "4B6wjMVbaIrR5t5vGXaB1mB7NfGWWk_LrSUqMPaQ";
    private static final String secretKey = "WaK_ECcqIC_yy_lS4KT2_2ryGINo-U9vFhG7eLaq";
    private static final String bucket = "file-store";
    private static final String key = "QQ截图20170312215050.png";


    @Test
    public void getSimpleUploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);

        log.debug("upload token is {}", uploadToken);
    }

    @Test
    public void getOverrideUploadToken() {
        StringBuilder returnBody = new StringBuilder();
        returnBody.append("{");
        returnBody.append("\"key\":\"$(key)\"").append(",");
        returnBody.append("\"hash\":\"$(etag)\"").append(",");
        returnBody.append("\"bucket\":\"$(bucket)\"").append(",");
        returnBody.append("\"fname\":\"$(fname)\"").append(",");
        returnBody.append("\"fsize\":$(fsize)").append(",");
        returnBody.append("\"mimeType\":\"${mimeType}\"").append(",");
        returnBody.append("}");


        StringMap putPolicy = new StringMap();
        //putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
        putPolicy.put("returnBody", returnBody.toString());
        putPolicy.put("callbackBodyType", "application/json");

        long expireSeconds = 10 * 60;//10min

        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);

        log.debug("upload token is {}", uploadToken);
    }

    @Test
    public void deleteFile() {
        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            log.error("fail to delete file", ex);
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
