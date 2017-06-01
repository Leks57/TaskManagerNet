package Client;

import Task.*;
import View.*;
import Controller.*;

import java.net.*;

import java.io.*;

public class Client {
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {

        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        InetAddress addr = InetAddress.getByName(null);

        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, SERVER_PORT);

        try {
            MyView view = new MyView();
            view.printMainPage();

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            TaskList taskList = null;
            Task task = null;
            int taskNumber=0;

            //Отправка сообщения на сервер
            String textToServer;
            while((textToServer = read.readLine()) != null) {

                out.println(textToServer);  // send to server
                out.flush();

                int userChoice = Integer.parseInt(textToServer);
                if (userChoice == 0) {
                    break;
                }

                switch (userChoice) {
                    case 1: // PrintTasks
                        taskList = Data.receiveTaskList(socket);
                        taskList.printTasks();
                        break;

                    case 2: // CreateTask
                        task = new Task();
                        task.setTaskName();
                        task.setTaskDescription();
                        Data.transmitTask(socket, task);
                        break;

                    case 3:
                        taskList = Data.receiveTaskList(socket);
                        taskNumber = taskList.editTask();
                        Data.transmitTask(socket, taskList.getTasks().get(taskNumber));
                        out.println(taskNumber);
                        break;

                    case 4:
                        taskList = Data.receiveTaskList(socket);
                        taskNumber = taskList.getDeletedTask();
                        out.println(taskNumber);
                        break;
                    default: out.println("Ничего не выбрано"); break;
                }
                MyView.printLine();
                view.printMainPage();
            }

            out.close();
            in.close();
            read.close();
            socket.close();

        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
