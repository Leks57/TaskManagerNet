import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Model {
    private static final int SERVER_PORT = 9090;
    private TaskList taskList;
    private Socket socket;

    public static int getServerPort() {
        return SERVER_PORT;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    public TaskList getTaskList() {
        return taskList;
    }

}
