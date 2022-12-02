package duke;

import duke.commands.Task;
import java.util.ArrayList;

public class Ui {
    protected static String line = "---------------------------------------------------------";

    public Ui(){}

    public static void printHello() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke"+System.lineSeparator()+"What can I do for you?");
        System.out.println(line);        
    }

    public static void printBye() {
        System.out.println(line);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(line); 
    }

    public static void printNoCommand() {
        System.out.println(line);
        System.out.print("   > Sorry, command not found" + System.lineSeparator()+"enter again: ");
        System.out.println(line);    
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println(line);
        if(tasks.size() == 0) {
            System.out.println("No task");
            System.out.println(line);
            return;
        }
        for(int i =0; i <tasks.size(); i++) {
            System.out.println("   > "+Integer.toString(i+1)+"." + tasks.get(i).toString());
        }
        System.out.println(line);
    }

    public static void showMark(String task) {
        System.out.println(line);
        System.out.println("   > Nice! I've marked this task as done:");
        System.out.println("   > " + task);
        System.out.println(line);            
    }

    public static void showUnmark(String task) {
        System.out.println(line);
        System.out.println("   > Noted. I've unmarked this task:");
        System.out.println("   > " + task);
        System.out.println(line);            
    }

    public static void showDelete(String task, int size) {
        System.out.println(line+System.lineSeparator()+"Noted. I've removed this task:");
        System.out.println("   > "+ task);
        System.out.println("Now you have "+Integer.toString(size)+" tasks in the list."+System.lineSeparator()+line);
    }

    public static void showAddTask(String taskDiscription, int size) {
        System.out.println(line+System.lineSeparator()+"Got it. I've added this task:");
        System.out.println("   > "+taskDiscription);
        System.out.println("Now you have "+Integer.toString(size)+" tasks in the list."+System.lineSeparator()+line);
    }
}