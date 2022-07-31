package main.Services;

import main.Data.DataStorage;
import main.Models.User;
import main.Ulities.BryctEncoder;
import main.Ulities.UserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class UserService {

    private User user;
    private final DataStorage dataStorage;
    public UserService(){
        this.dataStorage = DataStorage.getInstance();
    }

    public User getUserExisted(User user){
        return dataStorage.users.find((Predicate<User>)u -> u.getUserName().equals(user.getUserName()));
    }
    public boolean addUser(User user) throws UserException {
        if(getUserExisted(user) != null){
            throw new UserException("This User have existed!");
        }
        dataStorage.users.insert(user);
        return true;
    }
    public boolean removeUser(User user) throws UserException{
        if(getUserExisted(user) == null){
            throw new UserException("This user is not existed in the system!");
        }
        dataStorage.users.delete(user);
        return true;
    }
    public boolean Login(String username , String password) {
        String passwordHashed = BryctEncoder.hashPassword(password);
        Predicate<User> predicate = user -> user.getUserName().equals(username) && user.getHashPassword().equals(passwordHashed);
        this.user = dataStorage.users.find(predicate); /* The return type is a USER */

        return this.user != null;
    }

    /*In method findFriendsByKeyWordInName, we just find friends have the name contained the keyword, and we find with the 3rd layer.
    Example: Find in your friendlist -> friends' friendlist -> friends' friendlist of friends who are have friendship with your friends */
    public ArrayList<User> findFriendsByKeyWordInName(User user, String keyword, ArrayList<String> checkedUserId, Integer depth){
        ArrayList<User> results = new ArrayList<User>();

        Map<String,User> friendList = user.getFriends();
        friendList.forEach((k,v)->{
           if(!checkedUserId.contains(k)){
               checkedUserId.add(k);
               if(v.getFullName().contains(keyword)){
                   results.add(v);
               }

               if(depth < 3){
                  results.addAll(findFriendsByKeyWordInName(v,keyword,checkedUserId,depth +1));
               }

           }
        });
        return results;
    }

    /* Send Message */

    /* Send Invitation */

    /* Send Code */
}
