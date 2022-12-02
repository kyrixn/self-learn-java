package duke;

import duke.exceptions.*;
import duke.TaskList;

public class Parser {
    protected static String splittedCommand[]; 

    public Parser(){}

    public String parseCommand(String com) {
        splittedCommand = com.split(" ",2); 
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

    public String getToDoDescription() throws LackOfTaskDetail {
        if(splittedCommand.length == 1 || splittedCommand[1].equals("")) {
            throw new LackOfTaskDetail("    > no task detail!");
        }
        return splittedCommand[1];
    }

    public static String[] getTaskWithTime(String tasktype) throws LackOfTaskDetail {
        String splittedDiscription[];
        if(splittedCommand.length == 1 || splittedCommand[1].equals("")) {
            throw new LackOfTaskDetail("    > no task detail!");
        }

        if (tasktype.equals("deadline")) {
            splittedDiscription = splittedCommand[1].split("/by ",2);                    
        }
        else {
            splittedDiscription = splittedCommand[1].split("/at ",2);
        }

        if(splittedDiscription.length == 1 || splittedDiscription[1].equals("")) {
            throw new LackOfTaskDetail("    > please enter deadline/event time");
        }

        return splittedDiscription;
    }
}