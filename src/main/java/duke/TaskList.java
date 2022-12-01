package duke;

import duke.commands.Task;
import java.util.ArrayList;
import duke.Ui;

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
}
