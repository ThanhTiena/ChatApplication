package main.Services;

import main.Data.DataStorage;
import main.Models.Subjects.File;
import main.Models.Subjects.Group;
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
    public boolean sendMessageToChat(Message message) {

        if (message == null) {
            return false;
        }
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(message.getReceiverId()));
        group.sendMessage(message);
        dataStorage.messages.insert(message);
        return true;
    }

    /* Send File */
    public boolean sendFileToChat( File file) {
        if (file == null) {
            return false;
        }

        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(file.getReceiverId()));
        group.sendFile(file);
        dataStorage.files.insert(file);
        return true;
    }

    //    Delete Message
    public boolean deleteMessage (String messageId){
        if(messageId == null || messageId.isBlank()){
            return false;
        }
        Message deleteMessage = dataStorage.messages.find(message -> message.getMessageId().equals(messageId));
        if(deleteMessage == null){
            return false;
        }
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(deleteMessage.getReceiverId()));
        group.deleteMessage(deleteMessage);
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
        Group group = dataStorage.groups.find(g -> g.getGroupId().equals(deleteFile.getReceiverId()));
        group.deleteFile(deleteFile);
        dataStorage.files.delete(deleteFile);
        return true;
    }

    //    Show all files sent to group
    public List<File> showAllFilesSentToGroup(String groupId){
        if(groupId == null || groupId.equals("")){
            return null;
        }

        List<File> filesToGroup = dataStorage.groups.find(g -> g.getGroupId().equals(groupId)).getFiles() ;
        return filesToGroup;
    }

    //    Find K lastest message not include M lastest message
    public List<Message> findKLatestNotIncludeMLastestMessage(String senderId,String receiverId, int k, int m){
        List<Message> kLatestMessage = new ArrayList<>();
        try{
            List<Message> messageToReceivers = dataStorage.groups.find(g -> g.getGroupId().equals(receiverId)).getMessages();
            int startPoint = messageToReceivers.size() - m - 1;
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
            List<Message> messageToReceivers = dataStorage.groups.find(g -> g.getGroupId().equals(receiverId)).getMessages();
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