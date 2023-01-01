package duke.commands;

public class Task {
    protected String taskDiscription;
    protected boolean isDone;
    protected int idx;

    public Task(){}

    public Task(String task, int idx) {
        this.taskDiscription = task;
        this.idx = idx;
        this.isDone = false;
    }

    public String getTaskDiscription() {
        return this.taskDiscription;
    }

    public int getIdx() {
        return this.idx;
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