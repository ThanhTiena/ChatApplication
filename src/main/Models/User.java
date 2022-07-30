package main.Models;

import main.Ulities.GenerateNumber;

import java.util.*;

public class User {
    /* User Information */
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String hashPassword;
    private String gender;
    private Date dateOfBirth;

    /* User Status */
    private boolean isOnline;
    private boolean isActivated;
    private Map<String, Boolean> isSendRequests;

    /* Can change  */
    private Map<String, User> friends;

    public User(String firstName, String lastName, String hashPassword, String gender, Date dateOfBirth) {
        this.userId = GenerateNumber.generateUserId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.hashPassword = hashPassword;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
        this.userId = GenerateNumber.generateUserId();
    }

    public String getUserId() {
        return userId;
    }
    /* May uncomment if necessary */
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

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

    public Map<String, Boolean> getIsSendRequests() {
        return isSendRequests;
    }

    public void setIsSendRequests(Map<String, Boolean> isSendRequests) {
        this.isSendRequests = isSendRequests;
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

}
