package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

import duke.Ui;
import duke.Parser;
import duke.TaskList;
import duke.commands.*;
import duke.exceptions.*;

public class Duke {
    private static String line = "---------------------------------------------------------";

    //done
    private static void markThisTask(String command) throws TaskNumberOutOfRange{
        int idx = Integer.parseInt(command)-1;
        if(idx >= taskList.size() || idx < 0) {
            throw new TaskNumberOutOfRange("   > task number out of range!!");
        }
        else {
            taskList.get(idx).markAsDone();
            System.out.println(line);
            System.out.println("   > Nice! I've marked this task as done:");
            System.out.println("   > "+taskList.get(idx).toString());
            System.out.println(line);
        }        
    }

    private static void unmarkThisTask(String command) throws TaskNumberOutOfRange{
        int idx = Integer.parseInt(command)-1;
        if(idx >= taskList.size() || idx < 0) {
            throw new TaskNumberOutOfRange("task number out of range!!");
        }
        else {
            taskList.get(idx).unmark();
            System.out.println(line);
            System.out.println("   > OK, I've marked this task as not done yet:");
            System.out.println("   > "+taskList.get(idx).toString());
            System.out.println(line);
        }
    }

    private static void deleteThisTask(String command) throws TaskNumberOutOfRange{
        int idx = Integer.parseInt(command)-1;
        System.out.println(idx);
        if(idx >= taskList.size() || idx < 0) {
            throw new TaskNumberOutOfRange("   > task number out of range!!");
        }
        Task taskDiscription = taskList.get(idx);
        taskList.remove(idx);
        System.out.println(line+System.lineSeparator()+"Noted. I've removed this task:");
        System.out.println("   > "+taskDiscription.toString());
        System.out.println("Now you have "+Integer.toString(taskList.size())+" tasks in the list."+System.lineSeparator()+line);
    }

    private static void showAddTask(Task taskDiscription) {
        System.out.println(line+System.lineSeparator()+"Got it. I've added this task:");
        System.out.println("   > "+taskDiscription.toString());
        System.out.println("Now you have "+Integer.toString(taskList.size())+" tasks in the list."+System.lineSeparator()+line);
    }

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

            switch (commandtype){
                case "list":
                    Ui.listTasks(tasks);
                    break;
                case "mark":
                    try {
                        int idx = parser.getTaskIndex(tasks);
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    tasks.markThisTask(idx);
                    try {
                        Storage.autoSave();
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    Ui.showMark(tasks.getDescription(idx));
                    break;
                case "unmark":
                    try {
                        idx = parser.getTaskIndex(tasks);
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    tasks.unMarkThisTask(idx);
                    try {
                        Storage.autoSave();
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    Ui.showUnmark(tasks.getDescription(idx));
                    break;
                case "bye":
                    isEnd = true;
                    break;
                case "todo":
                    try {
                        Todo newtodo = new Todo(splittedCommand[1]);
                        taskList.add(newtodo);
                        showAddTask(newtodo);
                    } catch(LackOfTaskDetail e) {
                        System.out.println("   > lack of task detail");
                    }
                    try {
                        Storage.autoSave();
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String splittedDiscription[] = splittedCommand[1].split("/by ",2);                    
                        Deadline newddl = new Deadline(splittedDiscription[0],splittedDiscription[1]);
                        taskList.add(newddl);
                        showAddTask(newddl);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   > Please enter a task and a deadline behind the task seperated by \"/by\" ");
                    } catch (LackOfTaskDetail e) {
                        System.out.println("   > lack of task detail!");
                    }
                    try {
                        Storage.autoSave();
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        String[] splittedDiscription = splittedCommand[1].split("/at ",2);            
                        Event newevent= new Event(splittedDiscription[0],splittedDiscription[1]);
                        taskList.add(newevent);
                        showAddTask(newevent);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   > Please enter a task and a time behind the task seperated by \"/at\" ");
                    } catch (LackOfTaskDetail e) {
                        System.out.println("   > lack of task detail!");
                    }
                    try {
                        Storage.autoSave();
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        idx = parser.getTaskIndex(tasks);
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    Ui.showDelete(tasks.getDescription(idx), tasks.getSize());
                    tasks.deleteThisTask(idx);
                    try {
                        Storage.autoSave();
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