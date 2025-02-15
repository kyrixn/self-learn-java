package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

import duke.commands.*;

public class Storage {
    public Storage() {}

    private static String[] changeLoadedDescription(String dscrption) {
        String[] taskinfo = dscrption.split(" \\| ");
        return taskinfo;
    }

    private static Datetime convertToDT(String datetime, DateTimeFormatter formatter) {
        String[] splittedDT = datetime.split(" ");
        if(splittedDT.length == 1) {
            return new Datetime(LocalDate.parse(splittedDT[0], formatter));
        } else {
            return new Datetime(LocalDate.parse(splittedDT[0], formatter),LocalTime.parse(splittedDT[1]));
        }
    }

    public static ArrayList<Task> loadFile(String path) {
        ArrayList<Task> task = new ArrayList<>();
        DateTimeFormatter loadingformatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");

        try {
            Scanner scanner = new Scanner(new File(path));
            String curtask = "";

            while(scanner.hasNextLine()) {
                curtask = scanner.nextLine();
                String[] taskinfo = changeLoadedDescription(curtask);
                if(taskinfo[0].equals("T")) {
                    task.add(new Todo(taskinfo[2]));
                }
                else if(taskinfo[0].equals("E")) task.add(new Event(taskinfo[2], convertToDT(taskinfo[3], loadingformatter)));
                else task.add(new Deadline(taskinfo[2],convertToDT(taskinfo[3], loadingformatter)));

                if(taskinfo[1].equals("1")) task.get(task.size()-1).markAsDone();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    private static String changeDescriptionForSaving(ArrayList<Task> tasks) {
        String content = "";
        for(int i=0; i <tasks.size(); i++) {
            Task curtask = tasks.get(i);
            if(!curtask.getClass().getName().equals("duke.commands.Todo")) {
                if(curtask.getClass().getName().equals("duke.commands.Event")) content += "E | ";
                else content += "D | ";
            }
            else content += "T | ";

            content += ((curtask.getTaskStatus().equals("X") ? "1" : "0") + " | "+ curtask.getTaskDiscription());
            if(!curtask.getClass().getName().equals("duke.commands.Todo")) content += (" | "+tasks.get(i).getDue());
            content += System.lineSeparator();
        }
        return content;
    }

    public static void autoSave(ArrayList<Task> tasks, String path) throws IOException{
        File f = new File(path);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileWriter fw = new FileWriter(path);
        fw.write(changeDescriptionForSaving(tasks));
        fw.close();
    }

}
