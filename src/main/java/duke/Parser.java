package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.commands.Datetime;
import duke.exceptions.*;

public class Parser {
    protected static String splittedCommand[]; 

    public Parser(){}

    private static Boolean hasInfo() {
        if (splittedCommand.length == 1 || splittedCommand[1].equals("")) return false;
        return true;
    }

    public String parseCommand(String com) {
        splittedCommand = com.split(" ",2); 
        return splittedCommand[0];
    }

    public int getTaskIndex(int tasksize) throws TaskNumberOutOfRange {
        if (!hasInfo()) {
            throw new TaskNumberOutOfRange("    > no task number!"+System.lineSeparator()+": ");
        }

        int idx = Integer.parseInt(splittedCommand[1])-1;
        if(idx < 0 || idx > tasksize) {
            throw new TaskNumberOutOfRange("    > task index out of range!"+System.lineSeparator()+": ");
        }
        else {
            return idx;
        }
    }

    public String getToDoDescription() throws LackOfTaskDetail {
        if(!hasInfo()) {
            throw new LackOfTaskDetail("    > no task detail!"+System.lineSeparator()+": ");
        }
        return splittedCommand[1];
    }

    private static String[] splitTime(String tasktype) throws LackOfTaskDetail {
        String splittedDiscription[];
        if(!hasInfo()) {
            throw new LackOfTaskDetail("    > no task detail!"+System.lineSeparator()+": ");
        }

        if (tasktype.equals("deadline")) {
            splittedDiscription = splittedCommand[1].split(" /by ",2);                    
        } else {
            splittedDiscription = splittedCommand[1].split(" /at ",2);
        }
        return splittedDiscription;
    }

    public static String getTaskDetail(String tasktype) throws LackOfTaskDetail{
        try {
            String splittedDiscription[] = splitTime(tasktype);
            return splittedDiscription[0];
        } catch(LackOfTaskDetail e) {
            throw new LackOfTaskDetail(e.getMessage());
        }
    }

    public static Datetime getTaskTime(String tasktype) throws LackOfTaskDetail {
        DateTimeFormatter inputformat = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        
        try {
            String splittedDiscription[] = splitTime(tasktype);
            if(splittedDiscription.length == 1 || splittedDiscription[1].equals("")) {
                throw new LackOfTaskDetail("    > wrong task format!"+System.lineSeparator()+": ");
            }
            String tasktime[] = splittedDiscription[1].split(" ");
            if(tasktime.length > 1) {
                return new Datetime(LocalDate.parse(tasktime[0]), LocalTime.parse(tasktime[1]));
            } else {
                System.out.println(tasktime[0]);
                return new Datetime(LocalDate.parse(tasktime[0]));
            }

        } catch(LackOfTaskDetail e) {
            throw new LackOfTaskDetail(e.getMessage());
        }
    }

    public static String getKeyword() throws LackOfTaskDetail {
        if(!hasInfo()) {
            throw new LackOfTaskDetail("    > no keyword to search!"+System.lineSeparator()+":");
        }
        return splittedCommand[1];
    }
}