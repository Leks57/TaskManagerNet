import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller {
    private Model model;
    private View view;
    private ActionListener actionListener;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

    }

    public void connect(){
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    model.connectToServer();// действия при нажатии кнопки Connect
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.getConnectButton().setText("Connected");
                view.getConnectButton().setEnabled(false);

                view.getConnectionFrame().setVisible(false);
                view.getMainFrame().setVisible(true);
            }
        };
        view.getConnectButton().addActionListener(actionListener);
    }


    public static void main(String[] args) {

                Model model = new Model();
                View view = new View("Window");
                Controller controller = new Controller(model, view);
                controller.connect();
    }
}