public class Event extends Task{
    private String due;
    
    public Event(String discription, String due) throws LackOfTaskDetail {
        super(discription);
        this.due = due;
        if(due.equals("")) {
            throw new LackOfTaskDetail("No time information!");
        }
    }

    public String toString() {
        return "[E]"+super.toString()+ " ("+due+")";
    }
}