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
        try {
            User user = dataStorage.users.find(u -> u.getUserName().equals(userName));
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    public User getUserExistedByUserId(String userId) {
        try {
            User user = dataStorage.users.find(u -> u.getUserId().equals(userId));
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean addNewUser(String userName, String password, String firstName, String lastName, Gender gender, Date dateOfBirth) {
        User user = new User(firstName, lastName, userName, BryctEncoder.hashPassword(password), gender, dateOfBirth);
        if (getUserExistedByUserName(user.getUserName()) != null) {
            return false;
        }
        dataStorage.users.insert(user);
        return true;
    }

    public boolean removeUser(User user) {
        if (getUserExistedByUserName(user.getUserName()) == null) {
            return false;
        }
        dataStorage.users.delete(user);
        return true;
    }

    public boolean login(String username, String password) {
        String passwordHashed = BryctEncoder.hashPassword(password);
        Predicate<User> predicate = user -> user.getUserName().equals(username) && user.getHashPassword().equals(passwordHashed);

        return dataStorage.users.find(predicate) != null;
    }

    public void addRoleGroupChat(String userId,String groupId, String role) {
        user = dataStorage.users.find(u -> u.getUserId().equals(userId));
        Map<String, String> roleInGroups = user.getRoleInGroupChats();
        if (roleInGroups.containsKey(groupId) && roleInGroups.get(groupId).equalsIgnoreCase(role)) {
            roleInGroups.replace(groupId, role);
        } else {
            roleInGroups.put(groupId, role);
        }
//        user.setRoleInGroupChats(roleInGroups); /* Need to update user in data storage factory */

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
