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
            System.out.println("   > "+Integer.toString(i+1)+".["+taskList[i].getTaskStatus()+"] "+taskList[i].getTaskDiscription());
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        init();
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        while(!isEnd) {
            String command = in.nextLine();
            String splittedCommand[] = command.split(" ",2); 

            switch (splittedCommand[0]){
                case "list":
                    showTasks();
                    break;
                case "mark":
                    int idx = Integer.parseInt(splittedCommand[1])-1;
                    taskList[idx].markAsDone();
                    System.out.println(line);
                    System.out.println("   > Nice! I've marked this task as done:");
                    System.out.println("   > ["+taskList[idx].getTaskStatus()+"] "+taskList[idx].getTaskDiscription());
                    System.out.println(line);
                    break;
                case "unmark":
                    idx = Integer.parseInt(splittedCommand[1])-1;
                    taskList[idx].unmark();
                    System.out.println(line);
                    System.out.println("   > OK, I've marked this task as not done yet:");
                    System.out.println("   > ["+taskList[idx].getTaskStatus()+"] "+taskList[idx].getTaskDiscription());
                    System.out.println(line);
                    break;
                case "bye":
                    isEnd = true;
                    break;
                default:
                    System.out.println(line);
                    System.out.println("   > add: "+command);
                    System.out.println(line);

                    taskList[taskNum++] = new Task(command);
                    break;
            }

        }
        
        System.out.println(line);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(line);

        in.close();
    }
}