package main.Services;

import main.Data.DataStorage;
import main.Models.Enums.GroupType;
import main.Models.Group;
import main.Models.PrivateGroup;
import main.Models.PublicGroup;
import main.Models.User;
import main.Ulities.GenerateNumber;

import java.util.ArrayList;

public class GroupService {
    private DataStorage dataStorage;
    private Group group;
    public GroupService(){

        this.dataStorage = DataStorage.getInstance();
    }

    public boolean createGroupChat(ArrayList<User> memberList, User admin, String groupName, String groupType) throws Exception{
        try{

            if(groupType == "Private"){
              PrivateGroup  newGroup = new PrivateGroup(memberList,admin,groupName,groupType,GenerateNumber.generateGroupId(),"active");
                dataStorage.groups.insert(newGroup);
            }
            else{
                if(groupType == "Public"){
                   PublicGroup newGroup = new PublicGroup(memberList,admin,groupName,groupType,GenerateNumber.generateGroupId(),"active");
                   dataStorage.groups.insert(newGroup);
                }
            }

            return true;

        }
        catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }
}
