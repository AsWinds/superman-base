package com.codi.superman.workflow.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 工作流总表
 *
 * @author spy
 * @date 2017-05-08 15:07
 */
@Accessors(chain = true)
@NoArgsConstructor
@Data
public class SysBizWorkFlow extends BaseDomain {
    private Long id;

    private Long userId;

    private String userCode;

    private Integer state;

    private String bizType;

    private String bizKey;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date stateDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String description;

    private static final long serialVersionUID = 1L;
}
