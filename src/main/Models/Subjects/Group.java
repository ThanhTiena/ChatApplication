package main.Models.Subjects;

import main.Models.Enums.GroupStatus;
import main.Models.Enums.GroupType;
import main.Models.Enums.RoleGroupChat;
import main.Models.Interfaces.IGroup;
import main.Ulities.GenerateNumber;

import java.util.*;

public abstract class Group implements IGroup {
    private String groupId;
    private String groupName;
    private GroupType groupType;
    private String groupCode;
    private GroupStatus groupStatus;
    private ArrayList<User> members = new ArrayList<User>();
    private ArrayList<File> files = new ArrayList<File>();
    private ArrayList<Message> messages = new ArrayList<>();

    /* Because in a group chat there may be more than an admin */
    /* So change to List contain user which is defined as an admin*/
    // private User admin;
    private List<User> admins = new ArrayList<User>();

    public Group(User admin, String groupName) {
        this.groupId = GenerateNumber.generateGroupId();
        this.groupName = groupName;
        this.groupCode = GenerateNumber.generateGroupCode();
        this.groupStatus = GroupStatus.Active;

        this.admins.add(admin);
        this.members.add(admin);
    }

    /* ############ GROUP METHOD ################ */

    @Override
    public List<Message> showMessage() {
        return this.messages;
    }

    @Override
    public List<File> showSentFiles() {
        return this.files;
    }

    @Override
    public List<User> showMembers() {
        return this.members;
    }

    @Override
    public User getUserInGroup(User user) {
        return this.members.stream().filter(u -> u.getUserId().equals(user.getUserId())).findFirst().get();
    }

    @Override
    public boolean checkUserJoined(User user) {
        try {
            return getUserInGroup(user) != null;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    @Override
    public boolean addMember(User user) {
        if (this.members.contains(user)) {
            return false;
        }
        this.members.add(user);
        return true;
    }

    @Override
    public boolean removeMember(User user) {
        if (getUserInGroup(user) != null) {
            this.members.remove(user);
            if (user.getRoleInGroupChats().get(this.groupId).equals(RoleGroupChat.ADMIN)) {
                this.admins.remove(user);
            }
        }
        return false;
    }

    @Override
    public void sendMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public void deleteMessage(Message message) {
        this.messages.remove(message);
    }

    @Override
    public void sendFile(File file) {
        this.files.add(file);
    }

    @Override
    public void deleteFile(File file) {
        this.files.remove(file);
    }
    /* ################################################ */

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /* If user want, they can change the group type by altering the GroupType attribute */
    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public GroupStatus getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(GroupStatus groupStatus) {
        this.groupStatus = groupStatus;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}