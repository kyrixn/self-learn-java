package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.TaskList;
import duke.commands.Task;

public class Storage {
    public Storage() {}

    private static String changeTaskDescription(ArrayList<Task> tasks) {
        String content = "";
        for(int i=0; i <tasks.size(); i++) {
            Task curtask = tasks.get(i);
            content += (Integer.toString(i+1) + " | ");
            content += ((curtask.getTaskStatus().equals("X") ? "1" : "0") + " | "+ curtask.getTaskDiscription());
            if(!curtask.getClass().getName().equals("duke.commands.Todo")) {
                content += ("| "+tasks.get(i).getDue());
            }
            content += System.lineSeparator();
        }
        return content;
    }

    public static void autoSave(ArrayList<Task> tasks) throws IOException{
        File f = new File(".duke/data/duke.txt");
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(changeTaskDescription(tasks));
        fw.close();
    }

}
