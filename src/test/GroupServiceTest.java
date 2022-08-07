package test;

import main.Models.Enums.Gender;
import main.Models.Subjects.Group;
import main.Models.Subjects.User;
import main.Services.GroupService;
import main.Services.UserService;
import main.Ulities.GroupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceTest {
    static UserService userService = new UserService();
    static GroupService groupService = new GroupService();

    @BeforeEach
    public void setUp() throws GroupException {
        //generate data for testing
        userService.addNewUser("danh","123456","Danh","Dang", Gender.MALE, new Date());
        userService.addNewUser("tien","123456","Tien","Nguyen", Gender.MALE, new Date());
        userService.addNewUser("nhan","123456","Nhan","Nguyen", Gender.MALE, new Date());
    }

    @Test
    public void findGroupByGroupIdTest() throws GroupException {
        User user = userService.getUserExistedByUserName("danh");
        Group group = groupService.createChat(user,"Test Group Chat","PRIVATE_GROUP");
        Group resultGroup = groupService.findGroupByGroupId(group.getGroupId());

        assertEquals("Test Group Chat",resultGroup.getGroupName());
        assertNotEquals("Test Chat",resultGroup.getGroupName());
    }

    @Test
    public void createChatTest() throws GroupException {
        try {
            User user1 = userService.getUserExistedByUserName("danh");
            User user2 = userService.getUserExistedByUserName("tien");
            Group group1 = groupService.createChat(user1,"Test Public Group Chat","PUBLIC_GROUP");
            Group group2 = groupService.createChat(user2,"Test Private Group Chat","PRIVATE");

            assertEquals("Test Public Group Chat",group1.getGroupName());
            assertNotEquals("Test Group Chat",group1.getGroupName());
//          catch exception
            assertEquals("Test Group Chat",group2.getGroupName());
        } catch (Exception ex) {
            assertEquals("Create Group Chat Fail", ex.getMessage());
        }
    }

    @Test
    public void groupMemberDetailsTest() throws GroupException {
        User user1 = userService.getUserExistedByUserName("danh");
        User user2 = userService.getUserExistedByUserName("tien");
        Group group = groupService.createChat(user1,"Test Public Group Chat","PUBLIC_GROUP");
        groupService.inviteToJoinGroup(user1,user2,group.getGroupId());
        groupService.groupMemberDetails(group.getGroupId());
    }

    @Test
    public void groupMessageDetailsTest() {
    }

    @Test
    public void groupFileDetailsTest() {
    }

    @Test
    public void addMemberTest() throws GroupException {
        try {
            User user1 = userService.getUserExistedByUserName("danh");
            User user2 = userService.getUserExistedByUserName("tien");
            Group group1 = groupService.createChat(user1,"Test Public Group Chat","PUBLIC_GROUP");
            Group group2 = groupService.createChat(user1,"Test Private Group Chat","PRIVATE_GROUP");
            Boolean result = groupService.inviteToJoinGroup(user1,user2,group1.getGroupId());
            //catch exception
            groupService.inviteToJoinGroup(user1,user2,group2.getGroupId());

            assertTrue(result);
        } catch (Exception ex){
            assertEquals("This Group need to administrator's permission to join", ex.getMessage());
        }
    }

    @Test
    public void joinGroupByCodeTest() {
    }
}