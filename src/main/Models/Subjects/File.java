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
    
    public File( String fileName, Date createdAt, String receiverId, String senderId, String filePath) {
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

    public String getSenderId() { return senderId; }

    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }

    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getFilePath() { return filePath; }

    public void setFilePath(String filePath) { this.filePath = filePath; }
}
