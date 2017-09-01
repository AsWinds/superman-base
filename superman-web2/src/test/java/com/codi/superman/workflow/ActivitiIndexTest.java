package com.codi.superman.workflow;

import com.codi.superman.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 集成测试
 *
 * @author shi.pengyan
 * @date 2017-04-18 15:58
 */
@Slf4j
public class ActivitiIndexTest extends BaseTest {

    @Autowired
    private RepositoryService repositoryService;


    @Test
    public void activitiTest() {
        log.debug("repositoryService={}", repositoryService);
    }
}
