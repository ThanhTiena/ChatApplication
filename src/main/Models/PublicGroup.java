package main.Models;

import main.Models.Enums.GroupType;

import java.util.ArrayList;

public class PublicGroup extends Group{
    public PublicGroup(ArrayList<User> memberList, User admin, String groupName, GroupType groupType, String accessCode, String status) {
        super(memberList, admin, groupName, groupType, accessCode, status);
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showSentFiles() {

    }

    @Override
    public void showMembers() {

    }

    @Override
    public void updateGroupAdministrator() {

    }

    @Override
    public void removeMember() {

    }
}
