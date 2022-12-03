package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.Ui;
import duke.Parser;
import duke.TaskList;
import duke.exceptions.*;

public class Duke {

    public static void run(String path) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList tasks = new TaskList(Storage.loadFile(path));
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        Ui.printHello();
        while(!isEnd) {
            String command = in.nextLine();
            String commandtype = parser.parseCommand(command);

            switch (commandtype){
                case "list":
                    Ui.listTasks(tasks.fullList());
                    break;
                case "mark":
                    try {
                        int idx = parser.getTaskIndex(tasks.getSize());
                        tasks.markThisTask(idx);
                        Ui.showMark(tasks.getDescription(idx));
                    } catch (TaskNumberOutOfRange e) {
                        System.out.print(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    try {
                        Storage.autoSave(tasks.fullList(), path);
                    } catch(IOException e) {
                        System.out.print(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        int idx = parser.getTaskIndex(tasks.getSize());
                        tasks.unMarkThisTask(idx);
                        Ui.showUnmark(tasks.getDescription(idx));
                    } catch (TaskNumberOutOfRange e) {
                        System.out.print(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    try {
                        Storage.autoSave(tasks.fullList(), path);
                    } catch(IOException e) {
                        System.out.print(e.getMessage());
                    }
                    break;
                case "bye":
                    isEnd = true;
                    break;
                case "todo":
                    try {
                        String tododetail = parser.getToDoDescription();
                        tasks.addToDo(tododetail);
                        Ui.showAddTask(tasks.latesttask(),tasks.getSize());
                    } catch(LackOfTaskDetail e) {
                        System.out.print(e.getMessage());
                    }
                    try {
                        Storage.autoSave(tasks.fullList(), path);
                    } catch(IOException e) {
                        System.out.print(e.getMessage());
                    }
                    break;
                case "deadline":
                case "event":
                    {
                        String[] taskdetail = {};
                        try {
                            taskdetail = parser.getTaskWithTime(commandtype);
                            tasks.addTaskWithTime(taskdetail, commandtype);
                            Ui.showAddTask(tasks.latesttask(), tasks.getSize());
                        } catch (LackOfTaskDetail e) {
                            System.out.print(e.getMessage());
                        }
                        try {
                            Storage.autoSave(tasks.fullList(), path);
                        } catch(IOException e) {
                            System.out.print(e.getMessage());
                        }
                    }
                    break;
                case "delete":
                    try {
                        int idx = parser.getTaskIndex(tasks.getSize());
                        Ui.showDelete(tasks.getDescription(idx), tasks.getSize());
                        tasks.deleteThisTask(idx);
                    } catch (TaskNumberOutOfRange e) {
                        System.out.print(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    try {
                        Storage.autoSave(tasks.fullList(), path);
                    } catch(IOException e) {
                        System.out.print(e.getMessage());
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

    public static void main(String[] args) {        
        run("duke/data/duke.txt");
    }
    
}