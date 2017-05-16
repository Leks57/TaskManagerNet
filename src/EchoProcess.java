import java.io.*;
import java.net.Socket;

public class EchoProcess implements Runnable {

    public Socket getSocket() {
        return socket;
    }

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public EchoProcess(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        // Вывод автоматически выталкивается из буфера PrintWriter'ом
        out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())),
                true);
    }

    @Override
    public void run() {
        try {
            System.out.println("Connection accepted: " + socket);

            while (true) {
                try {

                    String str = in.readLine();
                    int userChoice = Integer.parseInt(str);

                    if (userChoice == 0) {
                        break;
                    }

                    TaskList taskList = null;
                    Task task = null;
                    int taskNumber = 0;

                    switch (userChoice) {
                        case 1: // PrintTasks
                            Data.transmitTaskList(socket, TaskList.getInstance());
                            break;

                        case 2: // CreateTask
                            Task newTask = new Task();
                            newTask = Data.receiveTask(socket);
                            TaskList.getInstance().addTask(newTask);
                            Xml.saveXml();
                            break;

                        case 3: // EditTask
                            Data.transmitTaskList(socket, TaskList.getInstance());
                            task = Data.receiveTask(socket);
                            taskNumber = Integer.parseInt(in.readLine());
                            TaskList.getInstance().getTasks().set(taskNumber, task);
                            Xml.saveXml();
                            break;

                        case 4: // DeleteTask
                            Data.transmitTaskList(socket, TaskList.getInstance());
                            TaskList.getInstance().getTasks().remove(Integer.parseInt(in.readLine()));
                            Xml.saveXml();
                            break;

                        default: out.println("Ничего не выбрано"); break;
                    }
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            // Всегда закрываем два сокета...
        }  finally {
            System.out.println("closing...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}