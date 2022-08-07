package test;

import main.Models.Enums.Gender;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
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

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceTest {
    private UserService userService = new UserService();
    private MessageService messageService = new MessageService();
    private GroupService groupService = new GroupService();

    @BeforeEach
    public void setUp() throws GroupException {
        //generate data for user service
        User user1 = new User("danh", "123456", "Danh", "Dang", Gender.MALE, new Date());
        User user2 = new User("tien", "123456", "Tien", "Nguyen", Gender.MALE, new Date());
        User user3 = new User("nhan", "123456", "Nhan", "Nguyen", Gender.MALE, new Date());
        userService.addNewUser(user1);
        userService.addNewUser(user2);
        userService.addNewUser(user3);
    }

    @Test
    public void findGroupByGroupIdTest() throws GroupException {
        User user = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user, "Test Group Chat", "PRIVATE_GROUP");
        Group resultGroup = groupService.findGroupByGroupId(group.getGroupId());

        assertEquals("Test Group Chat", resultGroup.getGroupName());
        assertNotEquals("Test Chat", resultGroup.getGroupName());
    }

    @Test
    public void createGroupTest() throws GroupException {
        try {
            User user1 = userService.getUserExistedByUserName("danh");
            User user2 = userService.getUserExistedByUserName("tien");
            Group group1 = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
            Group group2 = groupService.createGroup(user2, "Test Private Group Chat", "PRIVATE");

            assertEquals("Test Public Group Chat", group1.getGroupName());
            assertNotEquals("Test Group Chat", group1.getGroupName());
//          catch exception
            assertEquals("Test Group Chat", group2.getGroupName());
        } catch (Exception exception) {
            assertEquals("Create Group Chat Fail", exception.getMessage());
        }
    }

    @Test
    public void getGroupMemberDetailsTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("danh");
        User user2 = userService.getUserExistedByUserName("tien");
        Group group = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());
        List<User> members = groupService.getGroupMemberDetails(group.getGroupId());

        assertEquals("Danh Dang", members.get(0).getFullName());
        assertNotEquals("Nhan Dang", members.get(1).getFullName());
    }

    @Test
    public void getGroupMessageDetailsTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("danh");
        User user2 = userService.getUserExistedByUserName("tien");
        Group group = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());

        Message message = new Message(user1.getUserId(), group.getGroupId(), "Hi tien");
        messageService.sendMessageToChat(message);
        List<Message> messages = groupService.getGroupMessageDetails(group.getGroupId());

        assertEquals("Hi tien", messages.get(0).getContent());
        assertNotEquals("Hi danh", messages.get(0).getContent());
    }

    @Test
    public void getGroupFileDetailsTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("danh");
        User user2 = userService.getUserExistedByUserName("tien");
        Group group = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
        groupService.inviteToJoinGroup(user1, user2, group.getGroupId());

        File file = new File("Test", new Date(), group.getGroupId(), user2.getUserId(), "D:/Desktop/Test/abc.png");
        messageService.sendFileToChat(file);
        List<File> files = groupService.getGroupFileDetails(group.getGroupId());

        assertEquals("Test", files.get(0).getFileName());
        assertNotEquals("Wrong", files.get(0).getFileName());
    }

    @Test
    public void inviteToJoinGroupTest() throws GroupException {
        try {
            User user1 = userService.getUserExistedByUserName("danh");
            User user2 = userService.getUserExistedByUserName("tien");
            Group group1 = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
            Group group2 = groupService.createGroup(user1, "Test Private Group Chat", "PRIVATE_GROUP");
            Boolean result = groupService.inviteToJoinGroup(user1, user2, group1.getGroupId());
            //catch exception
            groupService.inviteToJoinGroup(user1, user2, group2.getGroupId());

            assertTrue(result);
        } catch (Exception exception) {
            assertEquals("This Group need to administrator's permission to join", exception.getMessage());
        }
    }
    @Test
    public void sendInvitationTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("danh");
        User user2 = userService.getUserExistedByUserName("tien");

        Group group1 = groupService.createGroup(user1,"Test Public Group Chat","PUBLIC_GROUP");
        Group group2 = groupService.createGroup(user1,"Test Private Group Chat", GroupType.PRIVATE_GROUP.toString());
        Boolean result1 = groupService.sendInvitation(user1,user2,group1.getGroupId());
        Boolean result2 = groupService.sendInvitation(user1,user2,group2.getGroupId());

        assertTrue(result1);
        assertFalse(result2);
        assertThrows(NoSuchElementException.class,() -> {
            groupService.sendInvitation(user1,user2,"this group invalid");
        });
    }

    @Test
    public void joinGroupByCodeTest() throws GroupException {
        try {
            User user1 = userService.getUserExistedByUserName("danh");
            User user2 = userService.getUserExistedByUserName("nhan");

            Group group1 = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
            Group group2 = groupService.createGroup(user1, "Test Public Group Chat", "PRIVATE_GROUP");
            Boolean result1 = groupService.joinGroupByCode(group1.getGroupCode(), group1.getGroupId(), user2);
            //catch exception
            groupService.joinGroupByCode(group2.getGroupCode(), group2.getGroupId(), user2);
            assertTrue(result1);
        } catch (Exception exception) {
            assertEquals("Action only valid with public group", exception.getMessage());
        }
    }

    @Test
    public void changeUserRoleInGroupTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user1, "Test Public Group Chat", "PUBLIC_GROUP");
        groupService.changeUserRoleInGroup(user1, group.getGroupId(), RoleGroupChat.MEMBER);

        assertEquals("MEMBER", user1.getRoleInGroupChats().get(group.getGroupId()));
        assertNotEquals("ADMIN", user1.getRoleInGroupChats().get(group.getGroupId()));
    }

    @Test
    public void getGroupsOfUserTest() throws GroupException {
        User user = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user, "Test Public Group Chat", "PUBLIC_GROUP");
        List<String> groupList = groupService.getGroupsOfUser(user);

        assertEquals(group.getGroupId(), groupList.get(groupList.size() - 1));
        assertNotEquals("1001", groupList.get(0));
    }

    @Test
    public void getContactsOfUserTest() throws GroupException {
        User user = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user, "Test Public Group Chat", "PUBLIC_GROUP");

        Message message = new Message(user.getUserId(), group.getGroupId(), "Hi tien");
        messageService.sendMessageToChat(message);
        List<String> messageList = groupService.getContactsOfUser(user);

        assertEquals(message.getMessageId(), messageList.get(messageList.size() - 1));
        assertNotEquals("Hi Long", messageList.get(messageList.size() - 1));
    }

    @Test
    public void getConversationsTest() throws GroupException {
        User user = userService.getUserExistedByUserName("danh");
        Group group = groupService.createGroup(user, "Test Public Group Chat", "PUBLIC_GROUP");

        Message message = new Message(user.getUserId(), group.getGroupId(), "Hi tien");
        messageService.sendMessageToChat(message);
        List<String> conservations = groupService.getConversations(user);

        assertEquals(group.getGroupId(), conservations.get(0));
        assertEquals(message.getMessageId(), conservations.get(conservations.size()-1));
        assertNotEquals("101", conservations.get(conservations.size()-1));
    }

//    @Test
//    public void removeUserFromGroupTest() throws GroupException {
//        User user1 = userService.getUserExistedByUserName("danh");
//        User user2 = userService.getUserExistedByUserName("tien");
//        Group group = groupService.createGroup(user1,"Test Public Group Chat","PUBLIC_GROUP");
//        groupService.inviteToJoinGroup(user1,user2,group.getGroupId());
//    }
}