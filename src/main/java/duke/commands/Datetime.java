package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

public class Datetime {
    protected LocalDate date;
    protected LocalTime time;
    protected Boolean hastime;

    public Datetime(LocalDate dd) {
        this.date = dd;
        this.hastime = false;
    }

    public Datetime(LocalDate dd, LocalTime tt) {
        this.date = dd;
        this.time = tt;
        hastime = true;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Boolean hasTime() {
        return hastime;
    }
}