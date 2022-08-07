package main.Models.Stuff;

import main.Models.Enums.ActionStatus;
import main.Models.Enums.ActionType;
import main.Models.Interfaces.IAction;
import main.Models.Subjects.User;
import main.Ulities.GenerateNumber;

import java.util.Date;

public class Protocol implements IAction<Protocol> {
    private String Id;
    private User sender;
    private User receiver;
    private ActionType actionType;
    private ActionStatus actionStatus;
    private String content;
    private String groupId;
    private Date createdAt;

    public Protocol(ActionType actionType) {
        this.Id = GenerateNumber.generateProtocolId();
        this.actionType = actionType;
        this.createdAt = new Date();
    }

    /* ACTION METHOD */
    @Override
    public Protocol request(User fromUser, User toUser, String groupId, String content) {
        this.sender = fromUser;
        this.receiver = toUser;
        this.content = content;
        this.groupId = groupId;
        return this;
    }

    @Override
    public Protocol response(User fromUser, User toUser, ActionStatus response) {
        this.sender = toUser;
        this.receiver = fromUser;
        return this;
    }
    /* ############# */

    public String getId() {
        return Id;
    }

    public User getSender() {
        return sender;
    }

    private void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    private void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getGroupId() {
        return groupId;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }
}
