package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.GroupType;
import main.Models.Subjects.*;
import main.Ulities.GenerateNumber;
import main.Ulities.GroupException;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupService {
    private DataStorage dataStorage;
    private Group group;
    public GroupService(){
        this.dataStorage = DataStorage.getInstance();
    }

    public boolean createChat(User admin, String groupName, String groupType) throws Exception{
        try{
            Group group = initGroupChat(admin,groupName,groupType);
            dataStorage.groups.insert(group);
            return true;
        }
        catch (GroupException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
    private Group initGroupChat(User admin, String groupName, String groupType) throws GroupException {
        switch(groupType){
            case ("PRIVATE_GROUP"):
                return new PrivateGroup(admin,groupName);
            case ("PUBLIC_GROUP"):
                return new PublicGroup(admin,groupName);
            case ("INDIVIDUAL"):
                return new IndividualChat(admin,groupName);
            default:
                throw new GroupException("Group Type Invalid, Initial Group Chat Failed");
        }
    }
}
