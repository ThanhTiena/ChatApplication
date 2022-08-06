package main.Models.Stuff;

import main.Models.Enums.ActionType;
import main.Models.Interfaces.IAction;
import main.Models.Subjects.User;
import main.Ulities.GenerateNumber;

import java.util.Date;
import java.util.Locale;

public class Protocol implements IAction<Protocol> {
    private String Id;
    private User sender;
    private User receiver;
    private ActionType actionType;
    private String content;
    private Date createdAt;

    public Protocol(ActionType actionType) {
        this.Id = GenerateNumber.generateProtocolId();
        this.actionType = actionType;
        this.createdAt = new Date();
    }

    /* ACTION METHOD */
    @Override
    public Protocol request(User fromUser, User toUser, Object entity) {
        this.sender = fromUser;
        this.receiver = toUser;
        this.content = String.valueOf(entity);
        return this;
    }

    @Override
    public Protocol response(User fromUser, User toUser, Object entity) {
        this.sender = fromUser;
        this.receiver = toUser;
        this.content = String.valueOf(entity).toUpperCase(Locale.ROOT);
        return this;
    }
    /* ############# */

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
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

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
