package main.Models.Interfaces.GroupActions;

import main.Models.Stuff.Protocol;
import main.Models.Subjects.Group;
import main.Models.Subjects.User;

public interface InviteGroupAction {
    abstract Protocol sendInvitationToGroup(User invitor,User user);
}
