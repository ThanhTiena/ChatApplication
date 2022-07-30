package main.Services;

import main.Data.DataStorage;
import main.Models.User;
import main.Ulities.BryctEncoder;
import main.Ulities.UserException;

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

    /* Send Message */

    /* Send Invitation */

    /* Send Code */
}
