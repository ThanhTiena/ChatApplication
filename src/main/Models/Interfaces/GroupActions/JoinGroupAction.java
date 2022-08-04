package main.Models.Interfaces.GroupActions;

import main.Models.Subjects.Group;
import main.Models.Subjects.User;

public interface JoinGroupAction {
    abstract boolean joinGroup(User user);
}
