package main.Models.Subjects;

public class Alias {
    private String aliasName;
    private User assignor;
    private User assignee;

    public Alias(String aliasName, User assignor, User assignee) {
        this.aliasName = aliasName;
        this.assignor = assignor;
        this.assignee = assignee;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public User getAssignor() {
        return assignor;
    }

    public void setAssignor(User assignor) {
        this.assignor = assignor;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
}
