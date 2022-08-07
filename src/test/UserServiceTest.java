package test;

import main.Data.DataStorage;
import main.Models.Enums.Gender;
import main.Models.Enums.RoleGroupChat;
import main.Models.Subjects.Alias;
import main.Models.Subjects.Group;
import main.Models.Subjects.PublicGroup;
import main.Models.Subjects.User;
import main.Services.UserService;
import main.Ulities.BryctEncoder;
import main.Ulities.GroupException;
import main.Ulities.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    static UserService userService = new UserService();
    static DataStorage dataStorage = DataStorage.getInstance();

    @BeforeEach
    public void setUp() {
        //generate data for user service
        userService.addNewUser("danh", "123456", "Danh", "Dang", Gender.MALE, new Date());
        userService.addNewUser("tien", "123456", "Tien", "Nguyen", Gender.MALE, new Date());
        userService.addNewUser("nhan", "123456", "Nhan", "Nguyen", Gender.MALE, new Date());
    }

    @Test
    public void getUserExistedByUserNameTest() {
        User expectedUser = new User("Danh", "Dang", "danh", BryctEncoder.hashPassword("123456"), Gender.MALE, new Date());
        //Valid username
        User actualUser1 = userService.getUserExistedByUserName("danh");
        //Invalid username
        User actualUser2 = userService.getUserExistedByUserName("Hoang");

        assertEquals(expectedUser.getUserName(), actualUser1.getUserName());
        assertEquals("danh", actualUser1.getUserName());
        assertNull(actualUser2);
    }

    @Test
    public void getUserExistedByUserIdTest() {
        User expectedUser = userService.getUserExistedByUserName("danh");
        //Invalid Id
        User actualUser1 = userService.getUserExistedByUserId("101");
        //Valid Id
        User actualUser2 = userService.getUserExistedByUserId(expectedUser.getUserId());

        assertNull(actualUser1);
        assertEquals("Danh Dang", actualUser2.getFullName());
        assertEquals(expectedUser.getFullName(), actualUser2.getFullName());
    }

    @Test
    public void addNewUserTest() {
        //Input invalid(same username)
        Boolean actualResult1 = userService.addNewUser("danh", "123456", "Danh", "Dang", Gender.MALE, new Date());
        //Input valid
        Boolean actualResult2 = userService.addNewUser("huy", "123456", "Huy", "Pham", Gender.MALE, new Date());

        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    public void removeUserTest() {
        User expectedUser1 = userService.getUserExistedByUserName("danh");
        User expectedUser2 = new User("Quan", "Dang", "quan", BryctEncoder.hashPassword("123456"), Gender.MALE, new Date());
        //User can find
        Boolean actualResult1 = userService.removeUser(expectedUser1);
        //User can not find
        Boolean actualResult2 = userService.removeUser(expectedUser2);

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    public void addRoleGroupChatTest() {
        User user = userService.getUserExistedByUserName("nhan");
        PublicGroup publicGroup = new PublicGroup(user, "nhanGroup");
        HashMap<String, String> expectedResult = new HashMap<String, String>();
        expectedResult.put(publicGroup.getGroupId(), "member");
        userService.addRoleGroupChat(user.getUserId(), publicGroup.getGroupId(), "member");

        assertEquals(expectedResult.values().toString(), user.getRoleInGroupChats().values().toString());
        assertNotEquals(expectedResult.values().toString(), "admin");
    }

    @Test
    public void addFriendTest() {
        User user = userService.getUserExistedByUserName("nhan");
        User friend = userService.getUserExistedByUserName("tien");
        userService.addFriend(user.getUserId(), friend.getUserId());

        assertEquals("Tien Nguyen", user.getFriends().get(friend.getUserId()).getFullName());
        assertNotEquals("Tien Nguyenn", user.getFriends().get(friend.getUserId()).getFullName());
    }

    @Test
    public void loginTest() {
        boolean login1stTime = userService.login("nhan", "1234");
        boolean login2ndTime = userService.login("nhan", "123456");

        assertAll("Result",
                () -> {
                    assertFalse(login1stTime);
                },
                () -> {
                    assertTrue(login2ndTime);
                }
        );
    }

    @Test
    public void findFriendsByKeyWordInNameTest() {
        User user = userService.getUserExistedByUserName("danh");
        User friend1 = userService.getUserExistedByUserName("tien");
        User friend2 = userService.getUserExistedByUserName("nhan");
        userService.addFriend(user.getUserId(), friend1.getUserId());
        userService.addFriend(user.getUserId(), friend2.getUserId());
        ArrayList<User> friendList = userService.findFriendsByKeyWordInName(user, "Nhan", new ArrayList<String>(), 3);

        assertEquals("Nhan Nguyen", friendList.get(0).getFullName());
        assertEquals(friend2.getFullName(), friendList.get(0).getFullName());
        assertNotEquals("Tien", friendList.get(0).getFullName());
    }

    @Test
    public void setAliasTest() {
        User assignor = userService.getUserExistedByUserName("tien");
        User assignee = userService.getUserExistedByUserName("nhan");
        userService.setAlias(assignor, assignee, "nhannhan");
        Alias alias = dataStorage.alias.find(user -> user.getAssignor().getUserName().equals(assignor.getUserName()));

        assertEquals("nhannhan", alias.getAliasName());
        assertNotEquals("nhandanh", alias.getAliasName());
    }

}