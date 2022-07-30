package main.Models;

import main.Models.Enums.GroupType;

import java.util.ArrayList;

public class PrivateGroup extends Group{
    public PrivateGroup(ArrayList<User> memberList, User admin, String groupName, GroupType groupType, String groupCode, String groupStatus) {
        super(memberList, admin, groupName, groupType, groupCode, groupStatus);
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

    /*
    * Methods incoming
    * */
}
