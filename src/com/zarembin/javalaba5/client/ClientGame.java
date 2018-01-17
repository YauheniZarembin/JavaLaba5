package com.zarembin.javalaba5.client;

import com.zarembin.javalaba5.gamebasis.EnemyField;
import com.zarembin.javalaba5.gamebasis.UserField;
import com.zarembin.javalaba5.util.Message;
import com.zarembin.javalaba5.util.Constant;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientGame {
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private JTextArea infoTextArea;
    private JButton shotButton;
    private boolean buttonPressed;
    private ViewGame viewGame;
    private UserField userField;
    private EnemyField enemyField;


    public ClientGame(Socket socket , InputStream in , OutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        infoTextArea = new JTextArea();
        shotButton = new JButton("SHOT");
        buttonPressed = false;
        shotButton.setEnabled(false);
        shotButton.addActionListener(e -> buttonPressed = true);

        enemyField = new EnemyField();
        String rowData[][] = {{"море", "[]", "[]"},
                            {"море", "море", "море"},
                              {"[]", "море", "[]"}};

        System.out.println(rowData);
        userField = new UserField(rowData);
        viewGame = new ViewGame(infoTextArea,shotButton);
        viewGame.printEnemyField(enemyField.getEnemyTypesField());
        viewGame.printUserField(userField.getUserTypesField());

    }

    public void startGame(){
        try {
            new Message(Constant.I_AM_READY).send(socket);
            infoTextArea.append("Waiting for another player\n");
            String message = Message.receive(in);
            infoTextArea.append(message+"\n");

            do {
                message = Message.receive(in);

                if (message.equals(Constant.YOUR_TURN)) {
                    shotButton.setEnabled(true);
                    infoTextArea.append("Choose cell and press the button.\n");
                    do {

                        while (true){
                            System.out.println("fdf");
                            if(buttonPressed){
                                String coordinate = viewGame.shotCoordinate();
                                new Message(coordinate).send(socket);
                                message = Message.receive(in);
                                infoTextArea.append(message+"\n");
                                enemyField.simulateMove(coordinate, message);
                                viewGame.printEnemyField(enemyField.getEnemyTypesField());
                                buttonPressed = false;
                                break;
                            }
                        }
                    } while (message.equals(Constant.HIT) || message.equals(Constant.SUNK));
                    shotButton.setEnabled(false);
                } else {
                    infoTextArea.append("Your opponent's move - wait\n");
                    String shootResult = userField.executeMove(message);
                    viewGame.printUserField(userField.getUserTypesField());
                    new Message(shootResult).send(socket);
                    if (shootResult.equals(Constant.YOU_WIN)) {
                        infoTextArea.append(Constant.YOU_LOSE+"\n");
                        break;
                    }
                    infoTextArea.append("Your opponent bummed at " + message
                            + " - " + shootResult+"\n");
                }
            } while (!message.equals(Constant.YOU_WIN));

        } catch (IOException e) {
            System.out.println("in start client game");
            System.exit(1);
        }

    }
}
