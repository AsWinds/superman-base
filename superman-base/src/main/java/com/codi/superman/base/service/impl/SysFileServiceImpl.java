package com.codi.superman.base.service.impl;

import com.codi.base.domain.BaseServiceImpl;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.dao.SysFileBucketDao;
import com.codi.superman.base.dao.SysFileDao;
import com.codi.superman.base.domain.SysFile;
import com.codi.superman.base.domain.SysFileBucket;
import com.codi.superman.base.manager.FileUploadMgr;
import com.codi.superman.base.result.model.SysFileModel;
import com.codi.superman.base.service.SysDBService;
import com.codi.superman.base.service.SysFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-03-30 12:47
 */
@Service("sysFileService")
@Transactional(readOnly = true)
public class SysFileServiceImpl extends BaseServiceImpl implements SysFileService {

    @Resource(name = "sysFileDao")
    private SysFileDao sysFileDao;

    @Resource
    private SysFileBucketDao sysFileBucketDao;

    @Resource(name = "sysDBService")
    private SysDBService sysDBService;

    @Autowired
    private FileUploadMgr fileUploadMgr;

    @Override
    public SysFile query(Long fileId) {
        return sysFileDao.selectByPrimaryKey(fileId);
    }

    @Override
    @Transactional(readOnly = false)
    public SysFile save(SysFileModel sysFileModel) throws BaseAppException {
        Assert.notNull(sysFileModel);

        SysFile sysFile = new SysFile();

        Date now = new Date();
        BeanUtils.copyProperties(sysFileModel, sysFile);

        sysFile.setCreateDate(now).setUpdateDate(now).setState(SysFileDao.STATE_A);
        sysFileDao.insertSelective(sysFile);

        return sysFile;
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(Long id) throws BaseAppException {
        SysFile sysFile = sysFileDao.selectByPrimaryKey(id);
        Assert.notNull(sysFile);

        // 先删除资源
        logger.debug("going to delete file={}", sysFile);

        SysFileBucket bucket = sysFileBucketDao.selectByPrimaryKey(sysFile.getBucketId());
        logger.debug("file in bucket[{}]", bucket.getBucketCode());
        fileUploadMgr.deleteFile(bucket.getBucketCode(), sysFile.getFileName());

        // 再删除db信息
        sysFileDao.deleteByPrimaryKey(sysFile.getId());
//        sysLogAuditService.addLog(Const.LOG_TYPE_FILE_DELETE, userId, String.format("delete file {}", sysFile.getFileName()));

        logger.debug("file deleted.");
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String uniqueFileName) throws BaseAppException {
        Assert.notNull(uniqueFileName);

        return sysFileDao.deleteByUniqueFileName(uniqueFileName);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(SysFile sysFile) throws BaseAppException {
        Assert.notNull(sysFile);
        Assert.notNull(sysFile.getId());

        logger.debug("fileId={},desc={}", sysFile.getId(), sysFile.getDescription());

        //待更新字段
        SysFile record = new SysFile();
        record.setId(sysFile.getId());
        record.setDescription(sysFile.getDescription());
        record.setUpdateDate(new Date());

        return sysFileDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<SysFileModel> queryFiles(Integer pageIndex, Integer pageSize) throws BaseAppException {
        return sysFileDao.queryFiles(pageIndex, pageSize);
    }

    @Override
    public Long queryFilesCount() throws BaseAppException {
        return sysDBService.getCount("SYS_FILE", "ID");
    }

    @Override
    public List<SysFileModel> queryFilesByFileName(String fileName, Integer pageIndex, Integer pageSize) {
        String mimeType = "image";
        return sysFileDao.queryFilesByFileName(fileName, pageIndex, pageSize, mimeType);
    }

    @Override
    public int queryCountByFileName(String fileName) {
        String mimeType = "image";
        return sysFileDao.queryCountByFileName(fileName, mimeType);
    }

    @Override
    public List<SysFileModel> queryPictures(Integer pageIndex, Integer pageSize) {
        String mimeType = "image";
        return sysFileDao.queryPictures(pageIndex, pageSize, mimeType);
    }

    @Override
    public int queryPictureCount() {
        String mimeType = "image";
        return sysFileDao.queryPictureCount(mimeType);
    }
}
