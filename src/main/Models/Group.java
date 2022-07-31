package main.Models;

import main.Models.Enums.GroupType;

import java.util.HashMap;
import java.util.*;

public abstract class Group {
    private ArrayList<User> memberList = new ArrayList<User>();
    private User admin;
    private String groupName;
    private String groupType;
    private String groupCode;
    //status: active, disband
    private String groupStatus;

    public Group(ArrayList<User> memberList, User admin, String groupName, String groupType, String groupCode, String groupStatus) {
        this.memberList = memberList;
        this.admin = admin;
        this.groupName = groupName;
        this.groupType = groupType;
        this.groupCode = groupCode;
        this.groupStatus = groupStatus;

    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public ArrayList<User> getMemberList() {
        return memberList;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void showMessage() {

    }

    public void showSentFiles() {

    }

    public void showMembers() {

    }

    public void updateGroupAdministrator() {

    }

    public void removeMember() {

    }

}
