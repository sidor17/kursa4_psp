package com.company.server;

import com.company.database.Database;
import com.company.interfaces.IObjectService;
import com.company.interfaces.IUserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class UserDataHandler implements IObjectService, IUserService {
    private int countUser;
    private Socket socket;

    private BufferedReader messageFromClient;
    private BufferedWriter writeMessage;

    private Database database;

    public UserDataHandler(int countUser, Socket socket, Database database, BufferedReader messageFromClient, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.database = database;
        this.messageFromClient = messageFromClient;
        this.writeMessage = writeMessage;
    }

    public void showAllAdmins(){
        LinkedList<String> listAdmins = database.conclusionAllAdmins();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listAdmins.size()));
        for(String admin:listAdmins){
            ServerConnection.usersConnected.get(countUser).sendMessage(admin);
        }
    }

    @Override
    public void showAll() {
        LinkedList<String> listUsers = database.conclusionAllUsers();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listUsers.size()));
        for(String user:listUsers){
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void delete() {
    }

    @Override
    public void addInDataBase() {
        try {
            String[] line = messageFromClient.readLine().split(" ");
            database.appendNewUserInDatabase(line[0], line[1], line[2]);
         } catch(IOException e){
             e.printStackTrace();
             }
}

    @Override
    public void redaction() {
    }

    @Override
    public void authorization() {
        try {
            String[] line = messageFromClient.readLine().split(" ");
            String check= database.verificationUserInDataBase(line[0], line[1]);
            if (!check.equals("false")) {
                ServerConnection.usersConnected.get(countUser).sendMessage(check);
            } else {
                ServerConnection.usersConnected.get(countUser).sendMessage("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
