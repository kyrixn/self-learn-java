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

    public static void listTasks(TaskList tasks) {
        System.out.println(line);
        for(int i =0; i <tasks.getSize(); i++) {
            System.out.println("   > "+Integer.toString(i+1)+"." + tasks.getDescription(i));
        }
        System.out.println(line);
    }

    public static void showMark(String task) {;
        System.out.println(line);
        System.out.println("   > Nice! I've marked this task as done:");
        System.out.println("   > " + task);
        System.out.println(line);            
    }
}