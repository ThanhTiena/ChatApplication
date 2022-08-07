package test;

import main.Data.DataStorage;
import main.Models.Enums.Gender;
import main.Models.Subjects.File;
import main.Models.Subjects.Group;
import main.Models.Subjects.Message;
import main.Models.Subjects.User;
import main.Services.GroupService;
import main.Services.MessageService;
import main.Services.UserService;
import main.Ulities.GroupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {
    private UserService userService = new UserService();
    private GroupService groupService = new GroupService();
    private MessageService messageService = new MessageService();

    @BeforeEach
    public void setUp() throws GroupException {
        User user1 = new User("tien", "123456", "Tien", "Nguyen", Gender.MALE, new Date());
        User user2 = new User("danh", "123456", "Danh", "Dang", Gender.MALE, new Date());

        //Generating data for testing
        userService.addNewUser(user1);
        userService.addNewUser(user2);
    }

    @Test
    public void sendMessageToChatTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());

        Message message = new Message(user1.getUserId(), group.getGroupId(), "AHAHAHA");
//        Valid parameter
        Boolean passResult = messageService.sendMessageToChat(message);
//        Invalid parameter
        Boolean failResult = messageService.sendMessageToChat(null);
        assertTrue(passResult);
        assertFalse(failResult);
    }

    @Test
    public void sendFileToChatTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());
        File file = new File("Test", new Date(), group.getGroupId(), user2.getUserId(), "D:/Desktop/Test/abc.png");

//        Valid input
        Boolean passResult = messageService.sendFileToChat(file);
//        Invalid Input
        Boolean failResult = messageService.sendFileToChat(null);
        assertTrue(passResult);
        assertFalse(failResult);

    }

    @Test
    public void deleteMessageTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());
        Message message = new Message(user1.getUserId(), group.getGroupId(), "AHAHAHA");
        messageService.sendMessageToChat(message);

//        Valid Input
        Boolean passResult = messageService.deleteMessage(message.getMessageId());
//        Invalid Input
        Boolean failResult = messageService.deleteMessage("");
        assertTrue(passResult);
        assertFalse(failResult);

    }

    @Test
    public void deleteFileTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());
        File file = new File("Test", new Date(), group.getGroupId(), user2.getUserId(), "D:/Desktop/Test/abc.png");
        messageService.sendFileToChat(file);

        Boolean passResult = messageService.deleteFile(file.getId());
        Boolean failResult = messageService.deleteFile("");
        assertTrue(passResult);
        assertFalse(failResult);
    }

    @Test
    public void showAllFilesSentToGroupTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());

        ArrayList<String> fileIds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            File file = new File("Test" + i, new Date(), group.getGroupId(), user1.getUserId(), "D:/Desktop/Test" + i + ".png");
            fileIds.add(file.getId());
            messageService.sendFileToChat(file);
        }

        List<File> testFileList = messageService.showAllFilesSentToGroup(group.getGroupId());
        assertEquals(testFileList.size(), fileIds.size());
        assertFalse(testFileList.size() != fileIds.size());
        testFileList.forEach(f -> {
            assertTrue(fileIds.contains(f.getId()));
            ;
        });
    }

    @Test
    public void findKLatestNotIncludeMLastestMessageTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());

        Message message = new Message(user1.getUserId(), group.getGroupId(), "ABCD");
        messageService.sendMessageToChat(message);
        Message message1 = new Message(user1.getUserId(), group.getGroupId(), "AAAA");
        messageService.sendMessageToChat(message1);
        Message message2 = new Message(user1.getUserId(), group.getGroupId(), "CCCC");
        messageService.sendMessageToChat(message2);

        List<Message> latestMess = messageService.findKLatestNotIncludeMLastestMessage(user1.getUserId(), group.getGroupId(), 1, 1);
        assertEquals(latestMess.get(0).getContent(), "AAAA");
        assertNotEquals(latestMess.get(0).getContent(), "CCCC");
    }

    @Test
    public void findMessageByKeywordTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("tien");
        User user2 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Group Chat", "INDIVIDUAL");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());

        Message message = new Message(user1.getUserId(), group.getGroupId(), "ABCD");
        messageService.sendMessageToChat(message);
        Message message1 = new Message(user1.getUserId(), group.getGroupId(), "AAAA");
        messageService.sendMessageToChat(message1);
        Message message2 = new Message(user1.getUserId(), group.getGroupId(), "CCCC");
        messageService.sendMessageToChat(message2);

        List<Message> results = messageService.findMessageByKeyword(user1.getUserId(), group.getGroupId(), "A");
        results.forEach(m -> {
            if (m.getContent().equals("AAAA")) {
                assertEquals(m.getContent(), "AAAA");
            } else if (m.getContent().equals("ABCD")) {
                assertEquals(m.getContent(), "ABCD");
            } else {
                assertNotEquals(m.getContent(), "CCCC");
            }

        });
    }
}