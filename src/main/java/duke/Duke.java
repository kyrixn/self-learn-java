package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.Ui;
import duke.Parser;
import duke.TaskList;
import duke.commands.*;
import duke.exceptions.*;

public class Duke {
    public static void main(String[] args) {        
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        Ui.printHello();

        while(!isEnd) {
            String command = in.nextLine();
            String commandtype = parser.parseCommand(command);
            int idx =0;

            switch (commandtype){
                case "list":
                    Ui.listTasks(tasks);
                    break;
                case "mark":
                    try {
                        idx = parser.getTaskIndex(tasks.getSize());
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    tasks.markThisTask(idx);
                    try {
                        Storage.autoSave(tasks.fullList());
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    Ui.showMark(tasks.getDescription(idx));
                    break;
                case "unmark":
                    try {
                        idx = parser.getTaskIndex(tasks.getSize());
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    tasks.unMarkThisTask(idx);
                    try {
                        Storage.autoSave(tasks.fullList());
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    Ui.showUnmark(tasks.getDescription(idx));
                    break;
                case "bye":
                    isEnd = true;
                    break;
                case "todo":
                    String tododetail = "";
                    try {
                        tododetail = parser.getToDoDescription();
                    } catch(LackOfTaskDetail e) {
                        System.out.println(e.getMessage());
                    }
                    tasks.addToDo(tododetail);
                    Ui.showAddTask(tasks.latesttask(),tasks.getSize());
                    try {
                        Storage.autoSave(tasks.fullList());
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                case "event":
                    {
                        String[] taskdetail = {};
                        try {
                            taskdetail = parser.getTaskWithTime(commandtype);
                        } catch (LackOfTaskDetail e) {
                            System.out.println(e.getMessage());
                        }
                        tasks.addTaskWithTime(taskdetail, commandtype);
                        Ui.showAddTask(tasks.latesttask(), tasks.getSize());
                        try {
                            Storage.autoSave(tasks.fullList());
                        } catch(IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "delete":
                    try {
                        idx = parser.getTaskIndex(tasks.getSize());
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    Ui.showDelete(tasks.getDescription(idx), tasks.getSize());
                    tasks.deleteThisTask(idx);
                    try {
                        Storage.autoSave(tasks.fullList());
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;                    
                default:
                    Ui.printNoCommand();
                    break;
                }
        }

        Ui.printBye();
        in.close();
    }
}