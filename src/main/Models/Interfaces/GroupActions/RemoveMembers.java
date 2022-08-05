package main.Models.Interfaces.GroupActions;

import main.Models.Subjects.User;

public interface RemoveMembers {
    abstract boolean removeMember(User user);
}
