package com.company.server;

import com.company.database.Database;
import com.company.interfaces.IObjectService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class EmployeeDataHandler implements IObjectService {
    private final int countUser;
    private final Socket socket;

    private final BufferedReader messageFromClient;
    private final BufferedWriter writeMessage;

    private final Database database;

    public EmployeeDataHandler(int countUser, Socket socket, Database database, BufferedReader messageFromClient, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromClient = messageFromClient;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void showAll() {
        LinkedList<String> listEmployees = database.showEmployee();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listEmployees.size()));
        for(String employee:listEmployees){
            ServerConnection.usersConnected.get(countUser).sendMessage(employee);
        }
    }

    @Override
    public void delete() {
        try {
            String id = messageFromClient.readLine();
            database.deleteEmployeeInDataBase(Integer.parseInt(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addInDataBase() {
        try {
            String[] line = messageFromClient.readLine().split(" ");
            database.addEmployeeInDatabase(Integer.parseInt(line[0]), line[1], line[2],line[3],Integer.parseInt(line[4]));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void redaction() {
        try {
            String[] employee = messageFromClient.readLine().split(" ");
            //database.redactionEmployeeInDataBase(employee[0],employee[1],employee[2],Integer.parseInt(employee[3]),Integer.parseInt(employee[4]));
            database.redactionEmployeeInDataBase(Integer.parseInt(employee[0]),employee[1],employee[2],employee[3],Integer.parseInt(employee[4]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showMyAll()
    {
        try {
            String id = messageFromClient.readLine();
            LinkedList<String> listEmployees = database.showUserEmployee(Integer.parseInt(id));
            ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listEmployees.size()));
            for(String employee:listEmployees){
                ServerConnection.usersConnected.get(countUser).sendMessage(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDiagram() {
        LinkedList<String> listDataDiagram = database.showDiagram();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listDataDiagram.size()));
        for(String statistics:listDataDiagram){
            ServerConnection.usersConnected.get(countUser).sendMessage(statistics);
        }
    }
}