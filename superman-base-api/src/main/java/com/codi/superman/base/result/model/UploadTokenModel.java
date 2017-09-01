package com.codi.superman.base.result.model;

import com.codi.base.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-30 9:23
 */
@Data
@AllArgsConstructor
public class UploadTokenModel extends BaseDomain {

    private String token;
    private String bucketHost;
    private String fileName;
}
