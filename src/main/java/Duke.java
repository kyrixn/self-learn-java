import java.util.Scanner;

public class Duke {
    private static String line = "---------------------------------------------------------";
    private static Task taskList[] = new Task[100];
    private static int taskNum = 0;

    private static void init() {        
        System.out.println(line);
        System.out.println("Hello! I'm Duke"+System.lineSeparator()+"What can I do for you?");
        System.out.println(line);
    }

    private static void showTasks() {
        System.out.println(line);
        for(int i =0; i <taskNum; i++) {
            System.out.println("   > "+Integer.toString(i+1)+". "+taskList[i].getTaskDiscription());
        }
        System.out.println(line);
    }
    public static void main(String[] args) {
        init();
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        
        while(!command.equals("bye")) {
            if (command.equals("list")) {
                showTasks();
            }
            else {
                System.out.println(line);
                System.out.println("   > add: "+command);
                System.out.println(line);

                taskList[taskNum++] = new Task(command);
            }

            command = in.nextLine();
        }
        
        System.out.println(line);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(line);

        in.close();
    }
}