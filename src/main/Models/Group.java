package main.Models;

import main.Models.Enums.GroupType;
import main.Models.Interfaces.IGroup;

import java.util.HashMap;
import java.util.*;

public abstract class Group implements IGroup {
    private String groupId;
    private String groupName;
    private GroupType groupType;
    private String groupCode;

    private List<User> groupMembers;
}
