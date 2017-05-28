package View;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class View {

    private JFrame connectionFrame;
    private JLabel label;
    private JButton connectButton;

    public JFrame getConnectionFrame() {
        return connectionFrame;
    }
    public JButton getConnectButton(){
        return connectButton;
    }
    public JButton getSaveButton() {
        return saveButton;
    }
    public DefaultListModel getListModel() {
        return listModel;
    }
    public JScrollPane getScroll() {
        return scroll;
    }

    private JFrame mainFrame;
    private JButton saveButton;
    private JButton editButton;
    private JButton deleteButton;
    private JList listOfTasks;
    private DefaultListModel listModel = new DefaultListModel();
    private JScrollPane scroll = new JScrollPane();
    String[] elements = {"1", "2", "3"};
    private MyTableModelOfTasks myTable;

    public MyTableModelOfTasks getMyTable() {
        return myTable;
    }

    String[][] data = new String[][]{};

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public View(String text){
        connectionFrame = new JFrame("View");
        connectionFrame.getContentPane().setLayout(new BorderLayout());
        connectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectionFrame.setSize(200,200);


        label = new JLabel(text);
        connectionFrame.getContentPane().add(label, BorderLayout.CENTER);

        connectButton = new JButton("Connect");
        connectionFrame.getContentPane().add(connectButton, BorderLayout.PAGE_END);
        connectionFrame.setVisible(true);


        mainFrame = new JFrame("Main Window");
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400,400);

        Container container = mainFrame.getContentPane();
        container.setLayout(new BorderLayout());

        saveButton = new JButton("Save");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        JPanel panel = new JPanel();
        panel.add(saveButton);
        panel.add(editButton);
        panel.add(deleteButton);
        container.add(panel, BorderLayout.PAGE_START);

        myTable = new MyTableModelOfTasks();
        container.add(myTable.getTableScrollPane(), BorderLayout.CENTER);

    }

}