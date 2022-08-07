package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.ActionStatus;
import main.Models.Enums.ActionType;
import main.Models.Enums.Gender;
import main.Models.Stuff.Protocol;
import main.Models.Subjects.*;
import main.Ulities.UserException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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

    public boolean addNewUser(User user) {
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

    public boolean login(String userName, String password) {
        User user = dataStorage.users.find(u -> u.getUserName().equals(userName));
        if (user == null) {
            return false;
        }
        return user.checkAccount(userName, password);
    }

    public void addRoleGroupChat(String userId, String groupId, String role) {
        user = dataStorage.users.find(u -> u.getUserId().equals(userId));
        Map<String, String> roleInGroups = user.getRoleInGroupChats();
        if (roleInGroups.containsKey(groupId) && roleInGroups.get(groupId).equalsIgnoreCase(role)) {
            roleInGroups.replace(groupId, role);
        } else {
            roleInGroups.put(groupId, role);
        }
    }

    public boolean sendFriendRequest(User invitor, User user, String note) {
        if (invitor != null && user != null) {
            if (!user.isFriend(invitor) && !invitor.isFriend(user)) {
                Protocol protocol = new Protocol(ActionType.INVITATION_FRIEND);
                protocol.request(invitor, user, "", note);
                protocol.setActionStatus(ActionStatus.WAITING);
                dataStorage.protocols.insert(protocol);
                return true;
            }
        }
        return false;
    }

    public boolean responseFriendRequest(User user, String reponse) throws UserException {
        Protocol protocol = dataStorage.protocols.find(p -> p.getReceiver().getUserId().equals(user.getUserId()));
        Protocol newResponseProtocol = null;
        if (user == null) {
            return false;
        }
        if (reponse.equalsIgnoreCase("accepted")) {
            newResponseProtocol = protocol.response(protocol.getSender(), protocol.getReceiver(), ActionStatus.ACCEPTED);
            /* Accepted -> addFriend() function is called */
            addFriend(protocol.getSender().getUserId(), user.getUserId());
        } else if (reponse.equalsIgnoreCase("rejected")) {
            newResponseProtocol = protocol.response(protocol.getSender(), protocol.getReceiver(), ActionStatus.REJECTED);
        } else {
            throw new UserException("Response is only Accepted or Rejected");
        }
        dataStorage.protocols.insert(newResponseProtocol);
        return true;
    }

    //add friend method
    public boolean addFriend(String userID, String friendId) {
        User user = getUserExistedByUserId(userID);
        User friend = getUserExistedByUserId(friendId);
        if ((user == null) || (friend == null)) {
            return false;
        }
        user.getFriends().put(friendId, friend);
        friend.getFriends().put(userID, user);
        return true;
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
    public boolean setAlias(User assignor, User assignee, String aliasName) {
        if (!aliasName.equals("") || assignee != null || assignor != null) {
            Alias alias = new Alias(aliasName, assignor, assignee);
            dataStorage.alias.insert(alias);
            return true;
        }
        return false;
    }
}
