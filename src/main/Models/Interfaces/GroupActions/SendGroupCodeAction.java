package main.Models.Interfaces.GroupActions;

import main.Models.Stuff.Protocol;
import main.Models.Subjects.User;

public interface SendGroupCodeAction {
    abstract Protocol sendGroupCode(User sender, User receiver);
}
