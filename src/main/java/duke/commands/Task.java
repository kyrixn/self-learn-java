package duke.commands;

public class Task {
    protected String taskDiscription;
    protected boolean isDone;

    public Task(){}

    public Task(String task) {
        this.taskDiscription = task;
        this.isDone = false;
    }

    public String getTaskDiscription() {
        return this.taskDiscription;
    }

    public String getTaskStatus() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDue(){
        return "";
    }

    public String toString() {
        return "["+getTaskStatus()+"] "+this.taskDiscription;
    }
}