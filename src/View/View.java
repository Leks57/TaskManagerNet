package View;

import javax.swing.*;
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
    public JButton getEditButton() {
        return editButton;
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

    private JFrame editFrame;
    public JFrame getEditFrame() {
        return editFrame;
    }
    private JTextField editTaskName;
    public JTextField getEditTaskName() {
        return editTaskName;
    }
    private JTextField editTaskDescription;
    public JTextField getEditTaskDescription() {
        return editTaskDescription;
    }
    private JTextField editTaskDate;
    public JTextField getEditTaskDate() {
        return editTaskDate;
    }

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

        editFrame = new JFrame("Edit Frame");
        editFrame.getContentPane().setLayout(new BorderLayout());
        editFrame.setSize(400,200);
        Container editContainer = editFrame.getContentPane();
        editContainer.setLayout(new BorderLayout());
        Dimension textField = new Dimension(100, 30);
        editTaskName = new JTextField("Task.Task Name");
        editTaskName.setPreferredSize(textField);
        editTaskDescription = new JTextField("Task.Task Description");
        editTaskDescription.setPreferredSize(textField);
        editTaskDate = new JTextField("Task.Task Date");
        editTaskDate.setPreferredSize(textField);
        JTextField taskContacts = new JTextField("Task.Task Contacts");
        taskContacts.setPreferredSize(textField);
        JPanel editPanel = new JPanel(new GridLayout(4, 1));
        editPanel.add(editTaskName);
        editPanel.add(editTaskDescription);
        editPanel.add(editTaskDate);
        editPanel.add(taskContacts);
        editContainer.add(editPanel, BorderLayout.CENTER);

        JPanel editButtonPanel = new JPanel(new FlowLayout());
        JButton saveChangeButton = new JButton("Save Task.Task");
        JButton cancelChangeButton = new JButton("Cancel");
        editButtonPanel.add(saveChangeButton);
        editButtonPanel.add(cancelChangeButton);
        editContainer.add(editButtonPanel, BorderLayout.PAGE_END);


    }

}