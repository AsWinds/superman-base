package com.codi.superman.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文件桶管理
 *
 * @author spy
 * @date 2017-04-10 16:41
 */
@Data
@Accessors(chain = true)
public class SysFileBucket extends BaseDomain {
    private Long bucketId;

    private String bucketName;

    private String bucketCode;

    private String hostName;

    private Boolean isPublic;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String description;

    private static final long serialVersionUID = 1L;
}
