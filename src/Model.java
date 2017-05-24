import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Model {
    private static final int SERVER_PORT = 9090;

    public void connectToServer() throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        Socket socket = new Socket(addr, SERVER_PORT);
    }



}
