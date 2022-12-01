package duke;

import duke.exceptions.*;
import duke.TaskList;

public class Parser {
    protected String splittedCommand[]; 

    public Parser(){}

    public String parseCommand(String com) {
        String splittedCommand[] = com.split(" ",2); 
        return splittedCommand[0];
    }

    public int getTaskIndex(int tasksize) throws TaskNumberOutOfRange {
        if (splittedCommand.length == 1 || splittedCommand[1].equals("")) {
            throw new TaskNumberOutOfRange("    > no task number!");
        }

        int idx = Integer.parseInt(splittedCommand[1])-1;
        if(idx < 0 || idx > tasksize) {
            throw new TaskNumberOutOfRange("    > task index out of range!");
        }
        else {
            return idx;
        }
    }

    // public static String getTaskDesciption() {

    // }
}