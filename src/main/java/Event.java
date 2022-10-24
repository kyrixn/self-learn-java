public class Event extends Task{
    private String due;
    
    public Event(String discription, String due) {
        super(discription);
        this.due = due;
    }

    public String toString() {
        return "[E]"+super.toString()+ " ("+due+")";
    }
}