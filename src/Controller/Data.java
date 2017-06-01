package Controller;

import Task.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Data {

    public static void transmitTask(Socket socket, Task task) {
        ObjectOutputStream outputObject = null;
        try {
            outputObject = new ObjectOutputStream(socket.getOutputStream());
            outputObject.writeObject(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void transmitTaskList(Socket socket, TaskList taskList) {
        ObjectOutputStream outputObject = null;
        try {
            outputObject = new ObjectOutputStream(socket.getOutputStream());
            outputObject.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Task receiveTask(Socket socket) {
        ObjectInputStream inputObject = null;
        try {
            inputObject = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
            Task newTask = null;
            try {
                try {
                    newTask = (Task) inputObject.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newTask;
    }

    public static TaskList receiveTaskList(Socket socket) {
        ObjectInputStream inputObject = null;
        try {
            inputObject = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskList taskList = null;
        try {
            taskList = (TaskList) inputObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}