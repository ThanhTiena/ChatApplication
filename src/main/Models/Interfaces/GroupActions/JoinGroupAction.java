package main.Models.Interfaces.GroupActions;

import main.Models.Stuff.Protocol;
import main.Models.Subjects.User;

public interface JoinGroupAction {
    abstract boolean accept(User user);
    abstract boolean reject();
}
