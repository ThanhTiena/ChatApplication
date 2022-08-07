package main.Models.Subjects;

import main.Models.Enums.Gender;
import main.Models.Interfaces.IUser;
import main.Models.Stuff.Protocol;
import main.Ulities.BryctEncoder;
import main.Ulities.GenerateNumber;

import java.util.*;

public class User implements IUser {
    /* User Information */
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private Gender gender;
    private Date dateOfBirth;

    /* User Account Information*/
    private String userName;
    private String hashPassword;

    /* User Status */
    private boolean isOnline;
    private boolean isActivated;
    private Map<String, String> roleInGroupChats;
    /* Can change  */
    private Map<String, User> friends;

    public User(String userName, String password, String firstName, String lastName, Gender gender, Date dateOfBirth) {
        this.userId = GenerateNumber.generateUserId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.userName = userName;
        this.hashPassword = hashPassword(password);
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;

        this.isActivated = true;
        this.isOnline = true;

        this.roleInGroupChats = new HashMap<String, String>();
        this.friends = new HashMap<String, User>();
    }

    /* USER METHOD */
    @Override
    public String hashPassword(String rawPassword) {
        return BryctEncoder.hashPassword(rawPassword);
    }

    @Override
    public boolean checkAccount(String userName, String password) {
        if (this.userName.equals(userName) && BryctEncoder.checkPassword(password, this.hashPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFriend(User user) {
        return this.friends.containsKey(user.getUserId());
    }

    /*#############*/

    public String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    protected void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Map<String, User> getFriends() {
        return friends;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Map<String, String> getRoleInGroupChats() {
        return roleInGroupChats;
    }
}
