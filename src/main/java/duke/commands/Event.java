package duke.commands;

public class Event extends Task{
    protected String due;

    public Event(String discription, String due)  {
        super(discription);
        this.due = due;
    }

    public String getDue() {
        return this.due;
    }

    public String toString() {
        return "[E]"+super.toString()+ " ("+due+")";
    }
}