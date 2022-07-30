package main.Services;

import main.Data.DataStorage;
import main.Models.Group;

public class GroupService {
    private DataStorage dataStorage;
    private Group group;

    public GroupService(){
        this.dataStorage = DataStorage.getInstance();
    }

}
