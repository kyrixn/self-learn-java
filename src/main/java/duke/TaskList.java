package duke;

import duke.commands.*;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasklist;
    protected int listsize;

    public TaskList() {
        this.tasklist = new ArrayList<Task>();
        this.listsize = 0;
    }

    public int getSize() {
        return listsize;
    }

    public String getDescription(int idx) {
        return tasklist.get(idx).toString();
    }

    public void markThisTask(int idx) {
        tasklist.get(idx).markAsDone();
    }

    public void unMarkThisTask(int idx) {
        tasklist.get(idx).unmark();
    }

    public String getTask(int idx) {
        return tasklist.get(idx).toString();
    }

    public void deleteThisTask(int idx) {
        tasklist.remove(idx);
        listsize--;
    }

    public void addToDo(String description) {
        Todo newtodo = new Todo(description);
        tasklist.add(newtodo);
        listsize++;
    }

    public void addTaskWithTime(String[] description, String tasktype) {
        if(tasktype.equals("deadline")) {
            Deadline newddl = new Deadline(description[0],description[1]);
            tasklist.add(newddl);
        }
        else {
            Event newevent = new Event(description[0],description[1]);
            tasklist.add(newevent);
        }
        listsize++;
    }

    public String latesttask() {
        return tasklist.get(listsize-1).toString();
    }

    public ArrayList<Task> fullList() {
        return this.tasklist;
    }
}
