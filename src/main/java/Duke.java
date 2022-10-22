import java.util.Scanner;

public class Duke {
    private static String line = "---------------------------------------------------------";
    
    private static void init() {        
        System.out.println(line);
        System.out.println("Hello! I'm Duke"+System.lineSeparator()+"What can I do for you?");
        System.out.println(line);
    }
    public static void main(String[] args) {
        init();
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        
        while(!command.equals("bye")) {
            System.out.println(line);
            System.out.println("    > "+command);
            System.out.println(line);

            command = in.nextLine();
        }
        
        System.out.println(line);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(line);

        in.close();
    }
}