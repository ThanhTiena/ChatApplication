package main.Models;

import main.Models.Enums.GroupType;

import java.util.HashMap;
import java.util.*;

public abstract class Group {
    private Map<String, Object> members = new HashMap<>();
    private Map<String, User> admins = new HashMap<>();
    private GroupType groupType;
    private String accessCode;

}
