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
    UserService userService = new UserService();

    @BeforeEach
    public void setUp() {
        userService.addNewUser("danh","123456","Danh","Dang", Gender.MALE, new Date());
        userService.addNewUser("tien","123456","Tien","Nguyen", Gender.MALE, new Date());
        userService.addNewUser("nhan","123456","Nhan","Nguyen", Gender.MALE, new Date());
    }

    @Test
    public void getUserExistedByUserNameTest() {
        User expectedUser = new User("Danh","Dang","Danh", BryctEncoder.hashPassword("123456"), Gender.MALE, new Date());
        User actualUser = userService.getUserExistedByUserName("danh");
        assertEquals(expectedUser.getFullName(),actualUser.getFullName());
    }

    @Test
    public void addUser() {
        assertEquals(DataStorage.getInstance().users.findAll().size(),3);
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void login() {
    }

    @Test
    public void findFriendsByKeyWordInName() {
    }
}