package commands;

import exceptions.LackOfTaskDetail;

public class Task {
    protected String taskDiscription;
    protected boolean isDone;

    public Task() throws LackOfTaskDetail {
        
    }

    public Task(String task) throws LackOfTaskDetail{
        if (task.equals("")) {
            throw new LackOfTaskDetail("No task discription!");
        }
        else{
            this.taskDiscription = task;
            this.isDone = false;
        }
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