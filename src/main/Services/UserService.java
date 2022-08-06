package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.Gender;
import main.Models.Subjects.Group;
import main.Models.Subjects.Message;
import main.Models.Subjects.File;
import main.Models.Subjects.User;
import main.Ulities.BryctEncoder;
import main.Ulities.UserException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.function.Predicate;

public class UserService {

    private User user;
    private final DataStorage dataStorage;

    public UserService() {
        this.dataStorage = DataStorage.getInstance();
    }

    public User getUserExistedByUserName(String userName) {
        return dataStorage.users.find(user -> user.getUserName().equals(userName));
    }

    public User getUserExistedByUserId(String userId) {
        return dataStorage.users.find(user -> user.getUserId().equals(userId));
    }

    public boolean addNewUser(String userName, String password, String firstName, String lastName, Gender gender, Date dateOfBirth) throws UserException {

        User user = new User(firstName, lastName, userName, BryctEncoder.hashPassword(password), gender, dateOfBirth);
        if (getUserExistedByUserName(user.getUserName()) != null) {
            throw new UserException("This User have existed!");
        }
        dataStorage.users.insert(user);
        return true;
    }

    public boolean removeUser(User user) throws UserException {
        if (getUserExistedByUserName(user.getUserName()) == null) {
            throw new UserException("This user is not existed in the system!");
        }
        dataStorage.users.delete(user);
        return true;
    }

    public boolean login(String username, String password) {
        String passwordHashed = BryctEncoder.hashPassword(password);
        Predicate<User> predicate = user -> user.getUserName().equals(username) && user.getHashPassword().equals(passwordHashed);

        return dataStorage.users.find(predicate) != null;
    }

    public void addRoleGroupChat(String groupId, String role) {
        Map<String, String> roleInGroups = this.user.getRoleInGroupChats();
        if (roleInGroups.containsKey(groupId) && roleInGroups.get(groupId).equalsIgnoreCase(role)) {
            roleInGroups.replace(groupId, role);
        } else {
            roleInGroups.put(groupId, role);
        }
        this.user.setRoleInGroupChats(roleInGroups); /* Need to update user in data storage factory */

    }

    /*In method findFriendsByKeyWordInName, we just find friends have the name contained the keyword, and we find with the 3rd layer.
    Example: Find in your friendlist -> friends' friendlist -> friends' friendlist of friends who are have friendship with your friends */
    public ArrayList<User> findFriendsByKeyWordInName(User user, String keyword, ArrayList<String> checkedUserId, Integer depth) {
        ArrayList<User> results = new ArrayList<User>();

        Map<String, User> friendList = user.getFriends();
        friendList.forEach((k, v) -> {
            if (!checkedUserId.contains(k)) {
                checkedUserId.add(k);
                if (v.getFullName().contains(keyword)) {
                    results.add(v);
                }

                if (depth < 3) {
                    results.addAll(findFriendsByKeyWordInName(v, keyword, checkedUserId, depth + 1));
                }
            }
        });
        return results;
    }

    /* Send Message */
    public boolean sendMessageToUser(String senderId, String content, String receiverId) {
        Message message = new Message(senderId, content);
        if (receiverId == null) {
            return false;
        }
        message.setReceiverId(receiverId);
        return true;
    }
    public boolean sendMessageToGroup(String senderId, String content, String receiverGroupId) {
        Message message = new Message(senderId, content);
        if (receiverGroupId == null) {
            return false;
        }
        message.setGroupId(receiverGroupId);
        return true;
    }

    /* Send File */
    public boolean sendFileToUser(String senderId, File file, String receiverId) {
        if (receiverId == null) {
            return false;
        }
        file.setReceiverId(receiverId);
        return true;
    }
    public boolean sendFileToGroup(String senderId, File file, String receiverGroupId) {
        if (receiverGroupId == null) {
            return false;
        }
        file.setGroupId(receiverGroupId);
        return true;
    }
    /* Send Invitation */
    public boolean sendInvitation() {
        return true;
    }

    /* Send Code */
    /* Set Alias */
    public boolean setAlias(String setterId, String userId, String alias) {
        boolean flag = false;
        if (!alias.equals("") || !userId.equals("") || !setterId.equals("")) {
            User user = getUserExistedByUserId(userId);
            if (user.getAlias().containsKey(setterId)) {
                user.getAlias().replace(setterId, alias);
            } else {
                user.getAlias().put(setterId, alias);
            }
            flag = true;
        }
        return flag;
    }
}
