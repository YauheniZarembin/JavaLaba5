package com.zarembin.javalaba5.client;

import com.zarembin.javalaba5.gamebasis.CellType;
import com.zarembin.javalaba5.util.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewGame {

    private static JTable table1;
    private static JTable table2;
    private JLabel textArea;
    private String move;

    public ViewGame(JTextArea infoClient, JButton shotButton) {
        JFrame frame = new JFrame("Battle ship game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object rowData[][] = {{" ", " ", " "},
                {" ", " ", " "},
                {" "," "," "}};
        Object rowCoponentData[][] = {{" ", " ", " "},
                {" ", " ", " "},
                {" "," "," "}};
        Object columnNames[] = {"1", "2", "3"};
        table1 = new JTable(rowCoponentData, columnNames);
        table2 = new JTable(rowData, columnNames);

        textArea = new JLabel("WELCOME TO SHIP BATTLE");

        frame.add(table1, BorderLayout.EAST);
        frame.add(table2, BorderLayout.WEST);
        frame.add(textArea, BorderLayout.SOUTH);
        frame.setSize(800, 300);
        frame.setVisible(true);

        JPanel panelManager = new JPanel();
        panelManager.setLayout(new GridLayout(2, 1));
        JScrollPane infoScroll = new JScrollPane(infoClient);
        panelManager.add(infoScroll);
        panelManager.add(shotButton);
        frame.add(panelManager, BorderLayout.CENTER);

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setCoordinateInTextArea(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public static void printUserField(CellType[][] types){
        for(int i = 0; i< Constant.HEIGHT_FIELD; i++){
            for(int j = 0; j< Constant.WIDTH_FIELD; j++){
                table2.setValueAt(types[i][j].toString(),i,j);
            }
        }
    }
    public static void printEnemyField(CellType[][] types){
        for(int i = 0; i< Constant.HEIGHT_FIELD; i++){
            for(int j = 0; j< Constant.WIDTH_FIELD; j++){
                table1.setValueAt(types[i][j].toString(),i,j);
            }
        }
    }


    public void setCoordinateInTextArea(MouseEvent evt) {
        int rowIndx, colIndx;
        rowIndx = table1.rowAtPoint(evt.getPoint());
        colIndx = table1.columnAtPoint(evt.getPoint());
        move = rowIndx + "," + colIndx;
        textArea.setText(move);
    }

    public String shotCoordinate(){
        return textArea.getText();
    }
}