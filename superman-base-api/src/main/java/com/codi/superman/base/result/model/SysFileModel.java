package com.codi.superman.base.result.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-11 10:13
 */
@Data
@Accessors(chain = true)
public class SysFileModel extends BaseDomain {

    private Long id;

    private String fileName;

    private String originFileName;

    private String hash;

    private String fileUrl;

    private String mimeType;

    private String fileSize;

    private Integer state;

    private String bucket;
    private Long bucketId;

    private Boolean isPublic;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String description;

}
