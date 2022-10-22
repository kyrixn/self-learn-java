public class Task {
    protected String taskDiscription;
    protected boolean isDone;

    public Task(String task){
        this.taskDiscription = task;
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }
}