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
    private String filePath;

    public File(String fileName, Date createdAt, String receiverId, String senderId, String filePath) {
        this.id = GenerateNumber.generateFileId();
        this.fileName = fileName;
        this.createdAt = createdAt;
        this.filePath = filePath;
        this.senderId = senderId;
        this.receiverId = receiverId;

        if (filePath.endsWith("mp4")) {
            fileType = FileType.VIDEO;
        } else if (filePath.endsWith("mp3")) {
            fileType = FileType.AUDIO;
        } else if (filePath.endsWith("png") || filePath.endsWith("jpg")) {
            fileType = FileType.IMAGE;
        }
    }
    /* Once A File is sent, its sender, receiver and time can not change */
    /* So the set method of these attributes will be private */
    public String getFileName() {
        return fileName;
    }

    public String getId() {
        return id;
    }

    private void setFileName(String fileName) {
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

    private void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSenderId() {
        return senderId;
    }

    private void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    private void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getFilePath() {
        return filePath;
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
