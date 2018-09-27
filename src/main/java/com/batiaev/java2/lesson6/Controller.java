package com.batiaev.java2.lesson6;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUI(ClientUI clientUI);

    void storeMessage(String msg);

    void loadHistory();
}
