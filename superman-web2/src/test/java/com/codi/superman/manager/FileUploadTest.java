package com.codi.superman.manager;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.manager.FileUploadMgr;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-29 15:51
 */
public class FileUploadTest extends BaseTest {


    @Autowired
    private FileUploadMgr fileUploadMgr;

    @Test
    public void generateToken() throws BaseAppException {
//        logger.debug("token=\n{}", fileUploadMgr.getUploadToken("png"));
    }
}
