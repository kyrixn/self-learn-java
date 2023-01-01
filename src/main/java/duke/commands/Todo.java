package duke.commands;

public class Todo extends Task {
    public Todo(String task, int idx){
        super(task, idx);
    }

    public String toString() {
        return "[T]"+super.toString();
    }
}