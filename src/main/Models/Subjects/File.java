package main.Models.Subjects;

import main.Models.Enums.FileType;
import main.Ulities.GenerateNumber;

import java.util.Date;

public class File {
    private String id;
    private String fileName;
    private FileType fileType;
    private Date createdAt;
    private String senderId;
    private String receiverId;
    private String groupId;

    public File(String fileName, FileType fileType, Date createdAt) {
        this.id = GenerateNumber.generateFileId();
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
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
