package com.codi.superman.base.controller;

import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysFile;
import com.codi.superman.base.manager.FileUploadMgr;
import com.codi.superman.base.result.model.SysFileModel;
import com.codi.superman.base.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文件上传模块
 *
 * @author shi.pengyan
 * @date 2017-03-29 14:49
 */
@RestController
@RequestMapping("/sys/upload")
public class SysFileUploadController {

    @Autowired
    private FileUploadMgr fileUploadMgr;

    @Autowired
    private SysFileService sysFileService;

    /**
     * 获取系统默认的上传token
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "token", method = RequestMethod.GET)
    @RequiresPermissions("sys:file:save")
    public BaseResult getUploadToken(@RequestParam(name = "suffix", defaultValue = "") String suffix,
                                     @RequestParam(name = "bucketId", defaultValue = "1") Long bucketId) throws BaseAppException {
        return BaseResult.success(fileUploadMgr.getUploadToken(suffix, bucketId));
    }

    /**
     * 保存上传的信息
     *
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("sys:file:save")
    public BaseResult saveFileInfo(SysFileModel sysFileModel) throws BaseAppException {
        return BaseResult.success(sysFileService.save(sysFileModel));
    }

    /**
     * 更新文件信息
     *
     * @param sysFile
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:file:update")
    public BaseResult updateFileInfo(SysFile sysFile) throws BaseAppException {
        return BaseResult.success(sysFileService.update(sysFile));
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:file:delete")
    public BaseResult deleteFile(@PathVariable(name = "id") Long id) throws BaseAppException {

        sysFileService.delete(id);

        return BaseResult.success();
    }


    /**
     * 分页获取文件资源
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "files", method = RequestMethod.GET)
    @RequiresPermissions("sys:file:query")
    public BaseResult queryFiles(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) throws BaseAppException {

        return BaseResult.success(sysFileService.queryFiles(pageIndex, pageSize));
    }

    /**
     * 分页获取图片资源
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "pictures", method = RequestMethod.GET)
    public BaseResult queryPictures(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) throws BaseAppException {

        return BaseResult.success(sysFileService.queryPictures(pageIndex, pageSize));
    }

    /**
     * 获取文件总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "filesCount", method = RequestMethod.GET)
    @RequiresPermissions("sys:file:query")
    public BaseResult queryFilesCount() throws BaseAppException {

        return BaseResult.success(sysFileService.queryFilesCount());
    }

    /**
     * 获取图片总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "picturesCount", method = RequestMethod.GET)
    public BaseResult queryPicturesCount() throws BaseAppException {

        return BaseResult.success(sysFileService.queryPictureCount());
    }


    /**
     * 获取私有文件下载链接
     *
     * @param fileId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "priv/{fileId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:file:query")
    public BaseResult queryPrivUrl(@PathVariable(name = "fileId") Long fileId) throws BaseAppException {
        return BaseResult.success(fileUploadMgr.generatePrivUrl(fileId));
    }

    /**
     * 通过文件名查询文件记录
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "searchFile", method = RequestMethod.GET)
    public BaseResult searchFile(@RequestParam(name = "fileName") String fileName,
                                 @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) throws BaseAppException {
        return BaseResult.success(sysFileService.queryFilesByFileName(fileName, pageIndex, pageSize));
    }

    /**
     * 通过文件名查询文件记录条数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "searchFileCount", method = RequestMethod.GET)
    public BaseResult searchFile(@RequestParam(name = "fileName") String fileName) throws BaseAppException {
        return BaseResult.success(sysFileService.queryCountByFileName(fileName));
    }
}
