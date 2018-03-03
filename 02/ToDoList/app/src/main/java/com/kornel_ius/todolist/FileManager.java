package com.kornel_ius.todolist;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Kornel_ius.
 */

public class FileManager {

    private final static String FILE_PATH = Environment.getExternalStorageDirectory() + "/TODOLIST/todos.txt";

    public FileManager() {
        checkIfFileExist();
    }

    private void checkIfFileExist() {
        File directory = new File(Environment.getExternalStorageDirectory() + "/TODOLIST");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTasks(ArrayList<Task> data) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            checkIfFileExist();

        }

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            String line;

            for (int i = 0; i < data.size(); ++i) {
                line = data.get(i).getTask() + ";" + data.get(i).getPriority();
                stream.write(line.getBytes());
                stream.write("\n\r".getBytes());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(String data) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            checkIfFileExist();
        }

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file, true);
            stream.write(data.getBytes());
            stream.write("\n\r".getBytes());

        } catch (Exception e) {

        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Task> getTasks() {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        final File file = new File(FILE_PATH);

        if (!file.exists()) {
            checkIfFileExist();
        }
        try {
            fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            String[] data;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                data = line.split(";");
                taskArrayList.add(new Task(data[0], data[1]));

            }
        } catch (Exception e) {
        }


        return taskArrayList;
    }
}
