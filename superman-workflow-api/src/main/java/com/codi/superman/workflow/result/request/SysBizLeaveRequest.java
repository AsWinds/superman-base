package com.codi.superman.workflow.result.request;

import com.codi.superman.workflow.domain.SysBizLeave;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-05-05 13:08
 */
@Data
@Accessors(chain = true)
public class SysBizLeaveRequest extends SysBizLeave {
    private String assignee;
    private String pid;
}
