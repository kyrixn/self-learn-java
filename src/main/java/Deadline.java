import java.io.NotActiveException;

public class Deadline extends Task {
    String due;
    String discription;

    public Deadline() throws LackOfTaskDetail{
        super();
    }

    public Deadline(String discription, String due) throws LackOfTaskDetail {
        super(discription);
        if(due.equals("")) {
            throw new LackOfTaskDetail("No time information!");    
        }
        this.due = due;
    }

    public String toString() {
        return "[D]"+super.toString() + " ("+due+")";
    }
}