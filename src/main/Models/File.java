package main.Models;

import main.Models.Enums.FileType;

import java.util.Date;

public class File {
    private String id;
    private String fileName;
    private FileType fileType;
    private Date createdAt;

    public File(String id, String fileName, FileType fileType, Date createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.createdAt = createdAt;
    }

    public String getFileName() {
        return fileName;
    }

    public String getId() {
        return id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
