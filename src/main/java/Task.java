public class Task {
    protected String taskDiscription;
    protected boolean isDone;

    public Task(String task) throws NoTaskDiscription{
        if (task.equals("")) {
            throw new NoTaskDiscription("No task discription!");
        }
        else{
            this.taskDiscription = task;
            this.isDone = false;
        }
    }

    public String getTaskDiscription() {
        return this.taskDiscription;
    }

    public String getTaskStatus() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        return "["+getTaskStatus()+"] "+this.taskDiscription;
    }
}