package com.batiaev.java2.lesson6;

import com.batiaev.java2.lesson7.AuthService;
import com.batiaev.java2.lesson7.BaseAuthService;
import com.batiaev.java2.lesson7.SQLAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private  static final Logger logger = LoggerFactory.getLogger(Server.class);
    private ServerSocket serverSocket;
    private AuthService authService;
    private List<ClientHandler> clients = new ArrayList<>();

    public Server(AuthService authService) {
        this.authService = authService;
        try {
            logger.info("Attempt to start up server on port 8189");
            serverSocket = new ServerSocket(8189);
            logger.info("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            logger.error("Ошибка инициализации сервера");
            close();
        }
    }

    public void close() {
        try {
            logger.info("Shutting down the server");
            serverSocket.close();
        } catch (IOException e) {
            logger.error("The following error has occurred during the server shutdown:",e);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        AuthService authService = new BaseAuthService();
        Server server = new Server(authService);
        server.start();
    }

    private void start() {
        while (true) {
            try {
                logger.info("Awaiting for new connections");
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            } catch (IOException e) {
                logger.error("The following IO error has occurred: ",e);
            }
        }
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients) {
            logger.info("Broadcasting message: "+ msg);
            client.sendMessage(msg);
        }
    }


    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick()))
                return true;
        }
        return false;
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        logger.info(String.format("Client %s connected \n",clientHandler.getNick()));
        sendBroadcastMessage(msg);
        logger.info(msg);
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        logger.info(String.format("Client %s disconnected \n",clientHandler.getNick()));
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler);
    }

    public void sendPrivateMessage(String toUser, String msg) {

        for (ClientHandler client : clients)
        {
            if(client.getNick().equals(toUser)){
                logger.info(String.format("Sending private message to %s",client.getNick()));
                client.sendMessage(msg);
                break;
            }
        }
    }
}