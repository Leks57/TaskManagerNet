package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyTableModelOfTasks extends JTable {
    private JTable table;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
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