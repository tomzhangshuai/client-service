package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// FileInfo,文件信息
public class FileInfo {
    //FileId,
    private String fileId;
    //FileName,
    private String fileName;
    //Extension,
    private String extension;
    //FileSize,
    private int fileSize;

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

}
