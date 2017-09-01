package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Job Service Controller
 *
 * @author shi.pengyan
 * @date 2017-01-12 13:42
 */
@RestController
@RequestMapping("/sys/jobservice")
public class SysJobServiceController extends BaseController {

    /**
     * 添加Job
     *
     * @throws BaseAppException
     */
    public BaseResult addJob() throws BaseAppException {

        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult updateJob() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult deleteJob() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult pasuseJob() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult resumeJob() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult stopJobOfRunning() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult triggerJob() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult pauseTrigger() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult resumeTrigger() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult deleteTrigger() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult start() throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

    public BaseResult shutdown(boolean waitForJobsToComplete) throws BaseAppException {
        BaseResult result = new BaseResult();
        return result;
    }

}
