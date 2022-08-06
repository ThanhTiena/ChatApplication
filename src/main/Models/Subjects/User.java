package main.Models.Subjects;

import main.Models.Stuff.Protocol;
import main.Ulities.GenerateNumber;

import java.util.*;

public class User {
    /* User Information */
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private Date dateOfBirth;

    /* User Account Information*/
    private String userName;
    private String hashPassword;

    /* User Status */
    private boolean isOnline;
    private boolean isActivated;
    private Map<String, String> roleInGroupChats;
    private Map<String, Protocol> actions;

    /* Can change  */
    private Map<String, User> friends;
    private Map<String, String> alias; // 1st String value present for groupId, 2nd is the alias name

    public User(String firstName, String lastName, String hashPassword, String gender, Date dateOfBirth) {
        this.userId = GenerateNumber.generateUserId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.hashPassword = hashPassword;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;

        this.isActivated = true;
        this.isOnline = true;

        this.roleInGroupChats = new HashMap<String, String>();
        this.actions = new HashMap<String, Protocol>();
        this.friends = new HashMap<String, User>();
        this.alias = new HashMap<String, String>();
    }

    public User() {
        this.userId = GenerateNumber.generateUserId();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public Map<String, Protocol> getActions() {
        return actions;
    }

    public void setActions(Map<String, Protocol> actions) {
        this.actions = actions;
    }

    public Map<String, User> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, User> friends) {
        this.friends = friends;
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

    public void setRoleInGroupChats(Map<String, String> roleInGroupChats) {
        this.roleInGroupChats = roleInGroupChats;
    }

    public Map<String, String> getAlias() {
        return alias;
    }

    public void setAlias(Map<String, String> alias) {
        this.alias = alias;
    }
}
