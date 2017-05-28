package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyTableModelOfTasks {
    private String[] columnNames;
    private String[][] data;
    private JTable table;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public MyTableModelOfTasks() {

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };

        tableModel.addColumn("Задача");
        tableModel.addColumn("Описание");
        tableModel.addColumn("Дата");

        table = new JTable(tableModel);
        this.tableScrollPane = new JScrollPane(table);
    }


}