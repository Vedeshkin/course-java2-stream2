package com.batiaev.java2.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends JFrame implements ClientUI {

    private JTextField jtf;
    private JTextArea jta;
    private Controller controller;

    public Client(Controller controller) {

        this.controller = controller;

        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);


        jbSend.addActionListener(e -> sendMsg());
        jtf.addActionListener(e -> sendMsg());


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                controller.closeConnection();
            }
        });
    }

    private void sendMsg() {
        if (!jtf.getText().trim().isEmpty()) {
            controller.sendMessage(jtf.getText());
            jtf.setText("");
            jtf.grabFocus();
        }
    }

    @Override
    public void addMessage(String w) {
        jta.append(w);
        jta.append("\n");
        controller.storeMessage(w);
    }




    @Override
    public void showUI() {
        setVisible(true);
    }
}
