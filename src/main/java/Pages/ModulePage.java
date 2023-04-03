package main.java.Pages;

import main.java.Data;
import main.java.Entity.Module;
import main.java.Controller.*;

import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.table.TableRowSorter;



public class ModulePage extends JFrame {
    public static void main(String[] args) {
        new ModulePage();
    }

    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private JTextField dTextField;
    private JTextField eTextField;
    private JTextField fTextField;
    private JTextField gTextField;
//    private JTextField hTextField;

    public ModulePage(){
        setTitle("Module");  //a frame
        setBackground(new Color(250, 250, 250));
        setBounds(450, 250, 1500, 900);
        setResizable(true);  //bigger or smaller
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Module> modules = Data.getInstance().modules;

        String[] columnNames = {"name","code","credit","hours","semester","type","grades"};   //列名
        String[][] tableValues = new String[modules.size()][Module.getAllAttributes().length];
        for (int i = 0; i < modules.size(); i++) {
            tableValues[i][0] = String.valueOf(modules.get(i).getName());
            tableValues[i][1] = String.valueOf(modules.get(i).getCode());
            tableValues[i][2] = String.valueOf(modules.get(i).getCredits());
            tableValues[i][3] = String.valueOf(modules.get(i).getHours());
            tableValues[i][4] = String.valueOf(modules.get(i).getSemester());
            tableValues[i][5] = String.valueOf(modules.get(i).getType());
            tableValues[i][6] = String.valueOf(modules.get(i).getGrades());
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableValues,columnNames);  //table on the frame
        JTable table = new JTable(tableModel);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);  //to be scrolled
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        setVisible(true);  //to be seen


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter(){    //鼠标事件
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = tableModel.getValueAt(selectedRow, 0);
                Object ob = tableModel.getValueAt(selectedRow, 1);
                Object oc = tableModel.getValueAt(selectedRow, 2);
                Object od = tableModel.getValueAt(selectedRow, 3);
                Object oe = tableModel.getValueAt(selectedRow, 4);
                Object of = tableModel.getValueAt(selectedRow, 5);
                Object og = tableModel.getValueAt(selectedRow, 6);
//                Object oh = tableModel.getValueAt(selectedRow, 7);
                aTextField.setText(oa.toString());  //给文本框赋值
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());
                dTextField.setText(od.toString());
                eTextField.setText(oe.toString());
                fTextField.setText(of.toString());
                gTextField.setText(og.toString());
//                hTextField.setText(oh.toString());
            }
        });
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
        getContentPane().add(panel,BorderLayout.SOUTH);

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

        panel.add(new JLabel("grade: "));
        gTextField = new JTextField("grade");
        panel.add(gTextField);


        final JButton addButton = new JButton("添加");   //添加按钮
        addButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                String []rowValues = {aTextField.getText(),bTextField.getText(),cTextField.getText(),dTextField.getText(),eTextField.getText(),fTextField.getText(),gTextField.getText()};
                tableModel.addRow(rowValues);  //添加一行
//                int rowCount = table.getRowCount() +1;   //行数加上1
//                aTextField.setText("A"+rowCount);
//                bTextField.setText("B"+rowCount);
            }
        });
        panel.add(addButton);

        final JButton updateButton = new JButton("修改");   //修改按钮
        updateButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if(selectedRow!= -1)   //是否存在选中行
                {
                    //修改指定的值：
                    tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
                    tableModel.setValueAt(bTextField.getText(), selectedRow, 1);
                    tableModel.setValueAt(cTextField.getText(), selectedRow, 2);
                    tableModel.setValueAt(dTextField.getText(), selectedRow, 3);
                    tableModel.setValueAt(eTextField.getText(), selectedRow, 4);
                    tableModel.setValueAt(fTextField.getText(), selectedRow, 5);
                    tableModel.setValueAt(gTextField.getText(), selectedRow, 6);
                }
            }
        });
        panel.add(updateButton);

        final JButton delButton = new JButton("删除");
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                    tableModel.removeRow(selectedRow);  //删除行
                }
            }
        });
        panel.add(delButton);

        // select by semester
        JPanel filterPanel = new JPanel();

        String[] semesterOptions = {"All", "1", "2", "3", "4", "5", "6", "7", "8"};
        JComboBox<String> semesterComboBox = new JComboBox<>(semesterOptions);
        filterPanel.add(new JLabel("Filter by semester: "));
        filterPanel.add(semesterComboBox);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(ModuleController.createFilterActionListener(table, sorter, semesterComboBox));
        filterPanel.add(filterButton);

        table.setRowSorter(sorter);

        //set border
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //layout
        JPanel combinedPanel = new JPanel(new GridBagLayout());
        getContentPane().add(combinedPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        combinedPanel.add(filterPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;

        combinedPanel.add(panel, gbc);

        combinedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
