package main.Models.Interfaces.GroupActions;

import main.Models.Enums.RoleGroupChat;
import main.Models.Subjects.User;

public interface UpdateMemberRole {
    abstract boolean updateRoleInGroup(User user, RoleGroupChat role);
}
