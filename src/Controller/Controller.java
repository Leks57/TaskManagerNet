package Controller;

import Model.Model;
import Server.Server;
import Task.Task;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;

public class Controller {
    private Model model;
    private View view;
    private ActionListener actionListener;
    private ActionListener saveButtonAction;
    private int selectedTask;

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

        saveButtonAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        view.getSaveButton().addActionListener(saveButtonAction);

        view.getEditButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedTask = view.getMyTable().getTable().getSelectedRow();
                model.getTaskList().getTasks().get(selectedTask).printTask();
                view.getEditFrame().setVisible(true);
                view.getEditTaskName().setText(model.getTaskList().getTasks().get(selectedTask).getName());
                view.getEditTaskDescription().setText(model.getTaskList().getTasks().get(selectedTask).getDescription());
                view.getEditTaskDate().setText(Server.DATE_FORMAT.format(model.getTaskList().getTasks().get(selectedTask).getDate()));
            }
        });

        view.getSaveChangeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getTaskList().getTasks().get(selectedTask).setName(view.getEditTaskName().getText());
                model.getTaskList().getTasks().get(selectedTask).setDescription(view.getEditTaskDescription().getText());
                try {
                    model.getTaskList().getTasks().get(selectedTask).setDate((Server.DATE_FORMAT.parse(view.getEditTaskDate().getText())));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                view.getEditFrame().setVisible(false);
            }
        });

        view.getCancelChangeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.getEditFrame().setVisible(false);
            }
        });
    }


    public static void main(String[] args) {

                Model model = new Model();
                View view = new View("Window");
                Controller controller = new Controller(model, view);
                controller.connect();
    }
}