package duke;

import duke.TaskList;

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
        System.out.println("   > Sorry, command not found");
        System.out.println(line);    
    }

    public static void listTasks(TaskList tasks) {
        System.out.println(line);
        for(int i =0; i <tasks.getSize(); i++) {
            System.out.println("   > "+Integer.toString(i+1)+"." + tasks.getDescription(i));
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