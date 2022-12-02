package duke.commands;

public class Deadline extends Task {
    protected String due;

    public Deadline(String discription, String due){
        super(discription);
        this.due = due;
    }

    public String getDue() {
        return this.due;
    }

    public String toString() {
        return "[D]"+super.toString() + " ("+due+")";
    }
}