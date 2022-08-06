package main.Services;

import main.Data.DataStorage;
import main.Models.Subjects.File;
import main.Models.Subjects.Message;

import java.util.*;

public class MessageService {
    UserService userService;
    GroupService groupService;
    DataStorage dataStorage;

    public MessageService() {
        this.userService = new UserService();
        this.groupService = new GroupService();
        this.dataStorage = DataStorage.getInstance();
    }

    /* Send Message */
    public boolean sendMessageToChat(String senderId, String content, String receiverId) {
        Message message = new Message(senderId,receiverId, content);
        if (receiverId == null) {
            return false;
        }
        message.setReceiverId(receiverId);
        dataStorage.messages.insert(message);
        return true;
    }

    /* Send File */
    public boolean sendFileToChat(String senderId, File file, String receiverId) {
        if (receiverId == null) {
            return false;
        }
        file.setReceiverId(receiverId);
        dataStorage.files.insert(file);
        return true;
    }

//    Delete Message
    public boolean deleteMessage (String messageId){
        if(messageId == null || messageId.equals("")){
            return false;
        }
        Message deleteMessage = dataStorage.messages.find(message -> message.getMessageId().equals(messageId));
        if(deleteMessage == null){
            return false;
        }
        dataStorage.messages.delete(deleteMessage);
        return true;
    }

//    Delete File
    public boolean deleteFile(String fileId){
        if(fileId == null || fileId.equals("")){
            return false;
        }
        File deleteFile = dataStorage.files.find(file -> file.getId().equals(fileId));
        if(deleteFile == null){
            return false;
        }

        dataStorage.files.delete(deleteFile);
        return true;
    }

//    Show all files sent to group
    public List<File> showAllFilesSentToGroup(String groupId){
        if(groupId == null || groupId.equals("")){
            return null;
        }

        List<File> filesToGroup = dataStorage.files.get(file -> file.getReceiverId().equals(groupId),file -> false).stream().toList();

        return filesToGroup;
    }

//    Find K lastest message not include M lastest message
    public List<Message> findKLatestNotIncludeMLastestMessage(String senderId,String receiverId, int k, int m){
        List<Message> kLatestMessage = new ArrayList<>();
        try{
            List<Message> messageToReceivers = dataStorage.messages.get(mess -> mess.getReceiverId().equals(receiverId) && mess.getSenderId().equals(senderId),message -> false).stream().toList();
            int startPoint = messageToReceivers.size() - m;
            for(int i = startPoint ; i >= 0; i--){
                kLatestMessage.add(messageToReceivers.get(i));
                k--;
                if(k == 0){
                   break;
                }
            }
            return kLatestMessage;
        }
        catch (Exception ex){
            return null;
        }
    }

//    Find messages which contains the keyword
    public List<Message> findMessageByKeyword(String senderId, String receiverId, String keywords){
        List<Message> messagesList = new ArrayList<>();
        try{
            List<Message> messageToReceivers = dataStorage.messages.get(mess -> mess.getReceiverId().equals(receiverId) && mess.getSenderId().equals(senderId),message -> false).stream().toList();
            messageToReceivers.forEach(m -> {
                if(m.getContent().contains(keywords)){
                    messagesList.add(m);
                }
            });
            return messagesList;
        }
        catch (Exception ex){
            return null;
        }
    }
}
