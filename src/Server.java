import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Timer;

public class Server {

    // Выбираем порт вне пределов 1-1024:
    public static final int PORT = 8080;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String pathFile = "C:\\test.xml";

    public static String getPathFile() {
        return pathFile;
    }

    static ScheduledTask myTask = new ScheduledTask();
    static Timer myTimer = new Timer();
    static Thread timerThread = new Thread(myTask);

    public static void main(String[] args) throws IOException {
        Xml.defineXml();

        // Обновление даты первого срабатывания таймера и запуск таймера
        myTask.updateTimer();

        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: " + s);

        try {
            while(true) {
                Socket socket = s.accept();
                new Thread(new EchoProcess(socket)).start();
            }
        } finally {
            s.close();
        }
    }

}