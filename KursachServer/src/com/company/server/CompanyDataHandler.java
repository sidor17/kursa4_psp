package com.company.server;

import com.company.database.Database;
import com.company.interfaces.IObjectService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class CompanyDataHandler implements IObjectService {
    private int countUser;
    private Socket socket;

    private BufferedReader messageFromClient;
    private BufferedWriter writeMessage;

    private Database database;

    public CompanyDataHandler(int countUser, Socket socket, Database database, BufferedReader messageFromClient, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromClient = messageFromClient;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void showAll() {
        LinkedList<String> listCompanies = database.showCompany();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listCompanies.size()));
        for(String company:listCompanies){
            ServerConnection.usersConnected.get(countUser).sendMessage(company);
        }
    }

    public void showMyAll()
    {
        try {
            String id = messageFromClient.readLine();
            LinkedList<String> listCompanies = database.showUserCompany(Integer.parseInt(id));
            ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listCompanies.size()));
            for(String company:listCompanies){
                ServerConnection.usersConnected.get(countUser).sendMessage(company);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            String id = messageFromClient.readLine();
            database.deleteCompanyInDataBase(Integer.parseInt(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addInDataBase() {
        try {
            String[] line = messageFromClient.readLine().split(" ");
            database.addCompanyInDataBase(Integer.parseInt(line[0]), line[1], line[2]);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void redaction() {
        try {
            String[] company = messageFromClient.readLine().split(" ");
            database.redactionCompanyInDataBase(Integer.parseInt(company[0]), company[1],company[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStatistics() {
        try {
            String[] line = messageFromClient.readLine().split(" ");
            database.addStatInDb(Integer.parseInt(line[0]), line[1], line[2], Double.parseDouble(line[3]));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showStatistics() {
        LinkedList<String> listStatistics = database.showStatistics();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listStatistics.size()));
        for(String statistics:listStatistics){
            ServerConnection.usersConnected.get(countUser).sendMessage(statistics);
        }
    }

    public void showId() {
        LinkedList<String> listId = database.showIdComp();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listId.size()));
        for(String s:listId){
            ServerConnection.usersConnected.get(countUser).sendMessage(s);
        }
    }
}
