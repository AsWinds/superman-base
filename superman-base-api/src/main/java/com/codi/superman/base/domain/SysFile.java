package com.codi.superman.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 上传文件管理
 *
 * @author spy
 * @date 2017-03-30 11:15
 */
@Accessors(chain = true)
@Data
public class SysFile extends BaseDomain {
    private Long id;

    private String fileName;

    private String originFileName;

    private String hash;

    private String fileUrl;

    private String mimeType;

    private String fileSize;

    private Integer state;

    private Long bucketId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String description;
}
