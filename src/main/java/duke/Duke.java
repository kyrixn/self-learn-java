package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.Ui;
import duke.Parser;
import duke.TaskList;
import duke.commands.Datetime;
import duke.exceptions.*;

public class Duke {

    public static void run(String path) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        // TaskList tasks = new TaskList();
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
                        tasks.addToDo(tododetail, tasks.getSize()+1);
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
                        try {
                            tasks.addTaskWithTime(parser.getTaskDetail(commandtype), parser.getTaskTime(commandtype) ,commandtype, tasks.getSize()+1);
                            Ui.showAddTask(tasks.latesttask(), tasks.getSize());
                        } catch (LackOfTaskDetail e) {
                            System.out.print(e.getMessage());
                        } catch (DateTimeParseException e) {
                            System.out.println("Time format wrong!");
                            System.out.println("correct format is yyyy-MM-dd hh:mm (time can be ommitted)");
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
                case "find":
                    try {
                        String keyword = parser.getKeyword();
                        ui.listTasks(tasks.searchRelaventTask(keyword));
                    } catch(LackOfTaskDetail e) {
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

    public static void main(String[] args) {        
        run("duke/data/duke.txt");
    }
    
}