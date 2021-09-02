package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected static File taskList;
    protected String filePath;

    /**
     * Constructor for storage.
     *
     * @param filePath the file path to the text file that stores the user's tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new File(filePath);
    }

    /**
     * Returns user's list of tasks
     *
     * @return Task List
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            if (!taskList.createNewFile()) {
                Scanner fileReader = new Scanner(taskList);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] splitString = line.split(" \\| ");
                    if (splitString[0].equals("T")) {
                        Todo t = new Todo(line.substring(8));
                        if (line.charAt(4) == '1') {
                            t.markAsDone();
                        }
                        list.add(t);
                    } else if (splitString[0].equals("D")) {
                        Deadline d = new Deadline(splitString[2], splitString[3]);
                        if (splitString[1].equals("1")) {
                            d.markAsDone();
                        }
                        list.add(d);
                    } else {
                        Event e = new Event(splitString[2], splitString[3]);
                        if (splitString[1].equals("1")) {
                            e.markAsDone();
                        }
                        list.add(e);
                    }
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Saves the users tasks to a text file.
     *
     * @param list the user's list of tasks.
     */
    public void save(ArrayList<Task> list) {
        File taskList = new File(this.filePath);
        taskList.delete();
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < list.size(); i++) {
                File tasks = new File(this.filePath);
                Task t = list.get(i);
                String isDone = t.isDone ? "1" : "0";
                if (t instanceof Todo) {
                    String line = "T | " + isDone + " | " + t.description;
                    writer.write(line + "\n");
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    String line = "D | " + isDone + " | " + t.description + " | " + d.by;
                    writer.write(line + "\n");
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    String line = "E | " + isDone + " | " + t.description + " | " + e.at;
                    writer.write(line + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
