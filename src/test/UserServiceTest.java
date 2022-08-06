package test;

import main.Data.DataStorage;
import main.Models.Enums.Gender;
import main.Models.Subjects.User;
import main.Services.UserService;
import main.Ulities.BryctEncoder;
import main.Ulities.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.BCrypt;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    DataStorage dataStorage;
    UserService userService = new UserService();

    @BeforeEach
    public void setUp() {
        //generate data for user service
        userService.addNewUser("danh","123456","Danh","Dang", Gender.MALE, new Date());
        userService.addNewUser("tien","123456","Tien","Nguyen", Gender.MALE, new Date());
        userService.addNewUser("nhan","123456","Nhan","Nguyen", Gender.MALE, new Date());
    }

    @Test
    public void getUserExistedByUserNameTest() {
        User expectedUser = new User("Danh","Dang","danh", BryctEncoder.hashPassword("123456"), Gender.MALE, new Date());
        //Valid username
        User actualUser1 = userService.getUserExistedByUserName("danh");
        //Invalid username
        User actualUser2 = userService.getUserExistedByUserName("Hoang");
        assertEquals(expectedUser.getUserName(),actualUser1.getUserName());
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
        assertEquals(expectedUser.getFullName(), actualUser2.getFullName());
    }

    @Test
    public void addNewUserTest() {
        //same username
        Boolean actualResult1 = userService.addNewUser("danh","123456","Danh","Dang", Gender.MALE, new Date());
        //different username
        Boolean actualResult2 = userService.addNewUser("huy","123456","Huy","Pham", Gender.MALE, new Date());
        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }

    @Test
    public void removeUserTest() {
        User expectedUser1 = userService.getUserExistedByUserName("danh");
        User expectedUser2 = new User("Quan","Dang","quan", BryctEncoder.hashPassword("123456"), Gender.MALE, new Date());
        //User can find
        Boolean actualResult1 = userService.removeUser(expectedUser1);
        //User can not find
        Boolean actualResult2 = userService.removeUser(expectedUser2);
        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    public void login() {

    }

    @Test
    public void findFriendsByKeyWordInName() {

    }
}