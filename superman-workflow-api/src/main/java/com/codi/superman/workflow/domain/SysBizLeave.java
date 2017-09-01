package com.codi.superman.workflow.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 请假模型
 * @author spy
 * @date 2017-04-27 17:30
 */
@Accessors(chain = true)
@NoArgsConstructor
@Data
public class SysBizLeave extends BaseDomain {
    private Long id;

    private Long userId;

    private String userCode;

    private Integer type;

    private String reason;

    private Integer state;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date stateDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private String description;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}
