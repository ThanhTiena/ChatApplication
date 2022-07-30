package main.Models;

import main.Models.Enums.GroupType;

import java.util.HashMap;
import java.util.*;

public abstract class Group {
    private ArrayList<User> memberList = new ArrayList<User>();
    private User admin;
    private String groupName;
    private GroupType groupType;
    private String groupCode;
    //status: active, disband
    private String status;

    public Group(ArrayList<User> memberList, User admin, String groupName, GroupType groupType, String accessCode, String status) {
        this.memberList = memberList;
        this.admin = admin;
        this.groupName = groupName;
        this.groupType = groupType;
        this.groupCode = accessCode;
        this.status = status;
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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getAccessCode() {
        return groupCode;
    }

    public ArrayList<User> getMemberList() {
        return memberList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
