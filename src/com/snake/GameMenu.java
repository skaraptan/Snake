package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yoga2pro on 14.06.2016.
 */
public class GameMenu extends JFrame {
    private JButton startServer;
    private JButton startGame;
    private JPanel panel;
    private JPanel panel2;
    private JTextField portNumber;
    private JTextField address;
    private JTextArea description;
    public GameMenu(){
        super("Snake game");
        startGame = new JButton("Start game");
        startServer = new JButton("Start server");
        startGame.setFont(new Font("TimesRoman", Font.BOLD, 20));
        startServer.setFont(new Font("TimesRoman", Font.BOLD, 20));
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AppStarter(Integer.parseInt(portNumber.getText()), address.getText());
            }
        });

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameServer(Integer.parseInt(portNumber.getText())).start();
            }
        });

        portNumber = new JTextField("1234");
        portNumber.setFont(new Font("TimesRoman", Font.BOLD, 20));
        address = new JTextField("localhost");
        address.setFont(new Font("TimesRoman", Font.BOLD, 20));
        description = new JTextArea(" Hello everybody, \n you are playing multiplayer snake game. \n In order to play this game, please start server \n " +
                "on set port. Avoid using of systems ports. \n When the server is started, you, and other player \n can simply start new game providing port and server address " +
                "\n Have fun :)");
        description.setFont(new Font("TimesRoman", Font.BOLD, 20));
        description.setEditable(false);
        panel = new JPanel();
        panel2 = new JPanel();
        setResizable(false);
        setSize(640, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.add(description);

        panel2.add(address);
        panel2.add(portNumber);
        panel2.add(startServer);
        panel2.add(startGame);
        panel.setBackground(Color.GREEN);
        add(panel, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args){
        new GameMenu();
    }
}
