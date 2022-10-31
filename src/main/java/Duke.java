import java.util.Scanner;
import java.util.TimerTask;

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

    private static void markThisTask(String command) throws TaskNumberOutOfRange{
        int idx = Integer.parseInt(command)-1;
        if(idx >= taskNum || idx < 0) {
            throw new TaskNumberOutOfRange("   > task number out of range!!");
        }
        else {
            taskList[idx].markAsDone();
            System.out.println(line);
            System.out.println("   > Nice! I've marked this task as done:");
            System.out.println("   > "+taskList[idx].toString());
            System.out.println(line);
        }        
    }

    private static void unmarkThisTask(String command) throws TaskNumberOutOfRange{
        int idx = Integer.parseInt(command)-1;
        if(idx >= taskNum || idx < 0) {
            throw new TaskNumberOutOfRange("task number out of range!!");
        }
        else {
            taskList[idx].unmark();
            System.out.println(line);
            System.out.println("   > OK, I've marked this task as not done yet:");
            System.out.println("   > "+taskList[idx].toString());
            System.out.println(line);
        }
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
                    try {
                        markThisTask(splittedCommand[1]);
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    break;
                case "unmark":
                    try {
                        unmarkThisTask(splittedCommand[1]);
                    } catch (TaskNumberOutOfRange e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    } catch (NumberFormatException e) {
                        System.out.println("   > Please enter a valid NUMBER!");
                    }
                    break;
                case "bye":
                    isEnd = true;
                    break;
                case "todo":
                    try {
                        taskList[taskNum] = new Todo(splittedCommand[1]);
                        addTask(taskList[taskNum]);
                        taskNum++;
                    } catch(LackOfTaskDetail e) {
                        System.out.println("   > lack of task detail");
                    }
                    break;
                case "deadline":
                    try {
                        String splittedDiscription[] = splittedCommand[1].split("/",2);                    
                        taskList[taskNum] = new Deadline(splittedDiscription[0],splittedDiscription[1]);
                        addTask(taskList[taskNum]);
                        taskNum++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   > Please enter a task and a deadline behind the task seperated by \"/\" ");
                    } catch (LackOfTaskDetail e) {
                        System.out.println("   > lack of task detail!");
                    }
                    break;
                case "event":
                    try {
                        String[] splittedDiscription = splittedCommand[1].split("/",2);            
                        taskList[taskNum] = new Event(splittedDiscription[0],splittedDiscription[1]);
                        addTask(taskList[taskNum]);
                        taskNum++;   
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   > Please enter a task and a deadline behind the task seperated by \"/\" ");
                    } catch (LackOfTaskDetail e) {
                        System.out.println("   > lack of task detail!");
                    }
                    break;    
                default:
                    System.out.println(line);
                    System.out.println("   > Sorry, command not found");
                    System.out.println(line);    
                    break;       
                }
        }
        
        System.out.println(line);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(line);

        in.close();
    }
}