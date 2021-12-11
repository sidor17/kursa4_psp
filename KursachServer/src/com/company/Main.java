package com.company;

import com.company.server.ServerConnection;

public class Main {

    public static void main(String[] args) {
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.startServer();
        serverConnection.connectNewClientInToServer();
        serverConnection.closeAll();
    }
}
