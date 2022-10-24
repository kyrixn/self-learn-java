public class Deadline extends Task {
    String due;
    
    public Deadline(String discription, String due) {
        super(discription);
        this.due = due;
    }

    public String toString() {
        return "[D]"+super.toString() + " ("+due+")";
    }
}