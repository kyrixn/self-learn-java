package commands;

import exceptions.LackOfTaskDetail;

public class Todo extends Task {
    public Todo(String task) throws LackOfTaskDetail{
        super(task);
    }

    public String toString() {
        return "[T]"+super.toString();
    }
}