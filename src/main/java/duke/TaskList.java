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

    // private static void markThisTask(String command) throws TaskNumberOutOfRange{
    //     int idx = Integer.parseInt(command)-1;
    //     if(idx >= taskList.size() || idx < 0) {
    //         throw new TaskNumberOutOfRange("   > task number out of range!!");
    //     }
    //     else {
    //         taskList.get(idx).markAsDone();
    //         System.out.println(line);
    //         System.out.println("   > Nice! I've marked this task as done:");
    //         System.out.println("   > "+taskList.get(idx).toString());
    //         System.out.println(line);
    //     }        
    // }
}
