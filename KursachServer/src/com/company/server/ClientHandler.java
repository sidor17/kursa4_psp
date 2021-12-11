package com.company.server;

import com.company.database.Database;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private UserDataHandler userHandler;
    private CompanyDataHandler companyHandler;
    private EmployeeDataHandler employeeHandler;
    private Socket socket;
    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    public ClientHandler(Socket socket, Database database, int count) {
        this.socket = socket;
        try {
            messageFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            userHandler = new UserDataHandler(count,socket,database,messageFromServer,writeMessage);
            companyHandler = new CompanyDataHandler(count,socket,database,messageFromServer,writeMessage);
            employeeHandler = new EmployeeDataHandler(count,socket,database,messageFromServer,writeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            userHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void userHandler() throws IOException {
        while (true) {
            switch (messageFromServer.readLine()) {
                case "авторизация":
                    userHandler.authorization();
                    break;

                case "добавить пользователя":
                    userHandler.addInDataBase();
                    break;

                case "вывести всех пользователей":
                    userHandler.showAll();
                    break;

                case "вывести всех админов":
                    userHandler.showAllAdmins();
                    break;

                case "добавить компанию":
                    companyHandler.addInDataBase();
                    break;

                case "вывести все компании":
                    companyHandler.showAll();
                    break;

                case "вывести мои компании":
                    companyHandler.showMyAll();
                    break;

                case "удалить компанию":
                    companyHandler.delete();
                    break;

                case "удалить сотрудника":
                    employeeHandler.delete();
                    break;

                case "редактировать компанию":
                    companyHandler.redaction();
                    break;

                case "добавить статистику":
                    companyHandler.addStatistics();
                    break;

                case "вывести статистику":
                    companyHandler.showStatistics();
                    break;

                case "вывести всех сотрудников":
                    employeeHandler.showAll();
                    break;

                case "вывести мои сотрудники":
                   employeeHandler.showMyAll();
                    break;

                case "добавить сотрудника":
                    employeeHandler.addInDataBase();
                    break;

                case "редактировать сотрудника":
                    employeeHandler.redaction();
                    break;

                case "вывести диаграмму":
                    employeeHandler.showDiagram();
                    break;

                case "вывести id":
                    companyHandler.showId();
                    break;

                case "выйти":
                    System.out.println("Клиент отключился !");
                    return;
            }
        }
    }


    public void sendMessage(String msg) {
        try {
            writeMessage.write(msg + "\n");
            writeMessage.flush();
        } catch (IOException ignored) {}
    }
}
