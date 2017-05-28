import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {
    private Model model;
    private View view;
    private ActionListener actionListener;
    private ActionListener action;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

    }

    public void connectToServer() throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        model.setSocket(new Socket(addr, Model.getServerPort()));
    }

    public void connect(){
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    connectToServer();// действия при нажатии кнопки Connect
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.getConnectButton().setText("Connected");
                view.getConnectButton().setEnabled(false);

                view.getConnectionFrame().setVisible(false);
                view.getMainFrame().setVisible(true);
                model.setTaskList(Data.receiveTaskList(model.getSocket()));

                //Вывод полученных с сервера задач в таблицу
                for(Task t: model.getTaskList().getTasks()){
                    view.getMyTable().getTableModel().addRow(t.taskToString());
                }
            }
        };
        view.getConnectButton().addActionListener(actionListener);

        action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        view.getSaveButton().addActionListener(action);
    }


    public static void main(String[] args) {

                Model model = new Model();
                View view = new View("Window");
                Controller controller = new Controller(model, view);
                controller.connect();
    }
}