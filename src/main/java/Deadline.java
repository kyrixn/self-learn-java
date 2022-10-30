public class Deadline extends Task {
    String due;
    
    public Deadline(String discription, String due) throws NoTimeInformation {
        super(discription);
        this.due = due;
        if(due.equals("")) {
            throw new NoTimeInformation("No time information!");    
        }
    }

    public String toString() {
        return "[D]"+super.toString() + " ("+due+")";
    }
}