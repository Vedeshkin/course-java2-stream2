package com.batiaev.java2.lesson6;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ClientController implements Controller {
    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;
    private final static String HISTORY_LOG = "history.log";
    private ClientUI ui;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;
    private int index = new Random().nextInt(3) + 1;

    public ClientController() {
        initConnection();
    }

    public void showUI(ClientUI ui) {
        this.ui = ui;
        ui.showUI();
        loadHistory();
        sendMessage("/auth login" + index + " pass" + index);
    }

    @Override
    public void storeMessage(String msg) {
        File f = new File(HISTORY_LOG);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {

            }
        }
        try( PrintWriter pw = new PrintWriter(new FileWriter(f,true))){
            pw.println(msg);

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadHistory()
    {
        File f = new File(HISTORY_LOG);
        if (!f.exists()){
            System.out.println("History not found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int readLines = 0;
        int lines = 100;
        ArrayList<String> history = new ArrayList<>();
        try ( RandomAccessFile randomAccessFile = new RandomAccessFile(f,"r"))
        {
            long fileLength = f.length() - 1;
            randomAccessFile.seek(fileLength);
            for(long pointer = fileLength; pointer >= 0; pointer--){
                randomAccessFile.seek(pointer);
                char c;
                c = (char)randomAccessFile.read();
                if(c == '\n'){
                    readLines++;
                    if(readLines == lines)
                        break;
                }
                sb.append(c);
            }
            history.add(sb.reverse().toString());



        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = history.size() - 1;0<= i; i--)
        {
            ui.addMessage(history.get(i));
        }

    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while (true) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        if (w.startsWith("end session")) break;
                        ui.addMessage(w);
                    }
                }
            } catch (Exception e) {
            }
        }).start();
    }

    @Override
    public void sendMessage(String msg) {
        out.println(msg);
        out.flush();
    }

    @Override
    public void closeConnection() {
        try {
            sendMessage("/exit");

            sock.close();
            out.close();
            in.close();
        } catch (IOException exc) {
        }
    }
}
