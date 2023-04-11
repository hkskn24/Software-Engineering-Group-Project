package main.java.Pages;

import main.java.Entity.Module;
import main.java.Lec_Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Lec_ModulePage extends JFrame {
    private final JTextField aTextField;
    private final JTextField bTextField;
    private final JTextField cTextField;
    private final JTextField dTextField;
    private final JTextField eTextField;
    private final JTextField fTextField;

    public Lec_ModulePage() {
        setTitle("Lec_ModulePage");  //a frame
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);  //bigger or smaller
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<Module> modules = Lec_Data.getInstance().modules;

        String[] columnNames = {"name", "code", "credit", "hours", "semester", "type"};   //列名
        String[][] tableValues = new String[modules.size()][Module.getGradesAttributes().length];
        for (int i = 0; i < modules.size(); i++) {
            tableValues[i][0] = String.valueOf(modules.get(i).getName());
            tableValues[i][1] = String.valueOf(modules.get(i).getCode());
            tableValues[i][2] = String.valueOf(modules.get(i).getCredits());
            tableValues[i][3] = String.valueOf(modules.get(i).getHours());
            tableValues[i][4] = String.valueOf(modules.get(i).getSemester());
            tableValues[i][5] = String.valueOf(modules.get(i).getType());
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);  //table on the frame
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);  //to be scrolled
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);  //to be seen


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                Object oc = tableModel.getValueAt(selectedRow, 2);
                Object od = tableModel.getValueAt(selectedRow, 3);
                Object oe = tableModel.getValueAt(selectedRow, 4);
                Object of = tableModel.getValueAt(selectedRow, 5);

                aTextField.setText(oa.toString());  //给文本框赋值
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());
                dTextField.setText(od.toString());
                eTextField.setText(oe.toString());
                fTextField.setText(of.toString());
            }
        });
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        panel.add(new JLabel("name: "));
        aTextField = new JTextField("name");
        panel.add(aTextField);

        panel.add(new JLabel("code: "));
        bTextField = new JTextField("code");
        panel.add(bTextField);

        panel.add(new JLabel("credit: "));
        cTextField = new JTextField("credit");
        panel.add(cTextField);

        panel.add(new JLabel("hours: "));
        dTextField = new JTextField("hours");
        panel.add(dTextField);

        panel.add(new JLabel("semester: "));
        eTextField = new JTextField("semester");
        panel.add(eTextField);

        panel.add(new JLabel("type: "));
        fTextField = new JTextField("type");
        panel.add(fTextField);


        final JButton addButton = new JButton("添加");   //添加按钮
        //添加事件
        addButton.addActionListener(e -> {
            String[] rowValues = {aTextField.getText(), bTextField.getText(), cTextField.getText(), dTextField.getText(), eTextField.getText(), fTextField.getText()};
            tableModel.addRow(rowValues);  //添加一行
        });
        panel.add(addButton);

        final JButton updateButton = new JButton("修改");   //修改按钮
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();//获得选中行的索引
            if (selectedRow != -1)   //是否存在选中行
            {
                //修改指定的值：
                tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
                tableModel.setValueAt(bTextField.getText(), selectedRow, 1);
                tableModel.setValueAt(cTextField.getText(), selectedRow, 2);
                tableModel.setValueAt(dTextField.getText(), selectedRow, 3);
                tableModel.setValueAt(eTextField.getText(), selectedRow, 4);
                tableModel.setValueAt(fTextField.getText(), selectedRow, 5);
            }
        });
        panel.add(updateButton);

        final JButton delButton = new JButton("删除");
        delButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();//获得选中行的索引
            if (selectedRow != -1)  //存在选中行
            {
                tableModel.removeRow(selectedRow);  //删除行
            }
        });
        panel.add(delButton);
    }

    public static void main(String[] args) {
        new Lec_ModulePage();
    }
}