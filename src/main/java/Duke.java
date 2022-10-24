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
    
    private static void listTasks() {
        System.out.println(line);
        for(int i =0; i <taskNum; i++) {
            System.out.println("   > "+Integer.toString(i+1)+"." + taskList[i].toString());
        }
        System.out.println(line);
    }

    private static void markThisTask(int idx) {
        taskList[idx].markAsDone();
        System.out.println(line);
        System.out.println("   > Nice! I've marked this task as done:");
        System.out.println("   > "+taskList[idx].toString());
        System.out.println(line);        
    }

    private static void unmarkThisTask(int idx) {
        taskList[idx].unmark();
        System.out.println(line);
        System.out.println("   > OK, I've marked this task as not done yet:");
        System.out.println("   > "+taskList[idx].toString());
        System.out.println(line);
    }

    private static void addTask(Task taskDiscription) {
        System.out.println(line+System.lineSeparator()+"Got it. I've added this task:");
        System.out.println("   > "+taskDiscription.toString());
        System.out.println("Now you have "+Integer.toString(taskNum)+" tasks in the list."+System.lineSeparator()+line);
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
                    listTasks();
                    break;
                case "mark":
                    int idx = Integer.parseInt(splittedCommand[1])-1;
                    markThisTask(idx);
                    break;
                case "unmark":
                    idx = Integer.parseInt(splittedCommand[1])-1;
                    unmarkThisTask(idx);
                    break;
                case "bye":
                    isEnd = true;
                    break;
                case "todo":
                    taskList[taskNum++] = new Todo(splittedCommand[1]);
                    addTask(taskList[taskNum-1]);
                    break;
                case "deadline":
                    String splittedDiscription[] = splittedCommand[1].split("/",2);                    
                    taskList[taskNum++] = new Deadline(splittedDiscription[0],splittedDiscription[1]);
                    addTask(taskList[taskNum-1]);
                    break;
                case "event":
                    splittedDiscription = splittedCommand[1].split("/",2);            
                    taskList[taskNum++] = new Event(splittedDiscription[0],splittedDiscription[1]);
                    addTask(taskList[taskNum-1]);   
                    break;                 
            }

        }
        
        System.out.println(line);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(line);

        in.close();
    }
}