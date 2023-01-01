package duke.commands;

import duke.commands.Datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    Datetime due;

    static DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
    static DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String discription, int idx, Datetime due) {
        super(discription, idx);
        this.due = due;
    }

    public String getDue() {
        return due.getDate().format(dateformatter)+ (!due.hasTime() ? "" : (" "+due.getTime().format(timeformatter)));
    }

    public String toString() {
        return "[E]"+super.toString() + " ("+due.getDate().format(dateformatter)+ (!due.hasTime() ? "" : (" "+due.getTime().format(timeformatter)))+")";
    }
}