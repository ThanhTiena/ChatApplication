package main.Models.Interfaces;

public interface IGroup {
    public abstract void showMessage();
    public abstract void showSentFiles();
    public abstract void showMembers();
    public abstract void updateGroupAdministrator();
    public abstract void removeMember();
}
