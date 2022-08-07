package main.Models.Subjects;

import main.Ulities.GenerateNumber;

import java.util.*;

public class Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String content;
    private Date sentAt;
    private User sender, receiver;
    private Group group;
    private List<File> files;

    public Message(String senderId,String receiverId,String content){
        this.messageId = GenerateNumber.generateMessageId();
        this.senderId = senderId;
        this.content = content;
        this.receiverId = receiverId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Group getGroup() {
        return group;
    }
    /* User can edit a message that had been sent, morever they can forward to other user or group chat */
    public List<File> getFiles() {
        return files;
    }
    /* Allow User to edit again their message */
    public void setContent(String content) {
        this.content = content;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
