package com.dragon.media.resourceinfo.entity;

import com.ibeetl.admin.core.entity.BaseEntity;

/**
 * @program admin
 * @description: 文件信息
 * @author: Gaofei
 * @create: 2019/01/08 16:04
 */

public class FileInfo extends BaseEntity {

    private String fileName;

    private Long fileSize;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
