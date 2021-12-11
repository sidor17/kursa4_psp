package com.company.database;

import com.company.constants.Constants;

import java.sql.*;
import java.util.LinkedList;

public class Database {
    Connection dbConnection;
    Statement statement;

    public Database() {
        connectionToDB();
    }

    public void connectionToDB()
    {
        String connectionString = Constants.HOST_DATABASE;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, Constants.USER_DATABASE, Constants.PASSWORD_DATABASE);
            statement = dbConnection.createStatement();
        }catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public void appendNewUserInDatabase(String login,String password,String role){

        try {
            String query = " insert into users (login, password, role) values (?, ?, ?)";
            PreparedStatement prSt = dbConnection.prepareStatement(query);
            prSt.setString (1, login);
            prSt.setString (2, password);
            prSt.setString (3, role);

            prSt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public String verificationUserInDataBase(String loginCheck, String passwordCheck){
        String user="";
        try {
            String query = "SELECT id, role FROM users WHERE login = '"
                    + loginCheck + "' AND password = '" + passwordCheck + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user+=rs.getString("id")+" ";
                user+=rs.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.equals("")) {
            System.out.println("Пользователь не найден !");
            return "false";
        }
        else {
            return user;
        }
    }

    public LinkedList<String> conclusionAllUsers() {

        LinkedList<String> users = new LinkedList<>();
        String query = "SELECT id, login, password, role FROM users WHERE role = 'user'";
        ResultSet rs = null;
        String user="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                user+=rs.getString("id")+" ";
                user+=rs.getString("login")+" ";
                user+=rs.getString("password")+" ";
                user+=rs.getString("role");
                users.add(user);
                user="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }

    public LinkedList<String> conclusionAllAdmins() {

        LinkedList<String> admins = new LinkedList<>();
        String query = "SELECT id, login, password, role FROM users WHERE role = 'admin'";
        ResultSet rs = null;
        String admin="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                admin+=rs.getString("id")+" ";
                admin+=rs.getString("login")+" ";
                admin+=rs.getString("password")+" ";
                admin+=rs.getString("role");
                admins.add(admin);
                admin="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return admins;
    }

    public LinkedList<String> showCompany(){
        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT id, idOwner, name, field FROM companies";
        ResultSet rs = null;
        String company="";
        try {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                company+=rs.getString("id") + " ";
                company+=rs.getString("idOwner") + " ";
                company+=rs.getString("name") + " ";
                company+=rs.getString("field");

                list.add(company);

                company="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public LinkedList<String> showEmployee(){
        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT id, idCompany, name, surname, department, salary FROM employee";
        ResultSet rs = null;
        String employee="";
        try {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                employee+=rs.getString("id") + " ";
                employee+=rs.getString("idCompany") + " ";
                employee+=rs.getString("name") + " ";
                employee+=rs.getString("surname") + " ";
                employee+=rs.getString("department") + " ";
                employee+=rs.getString("salary");

                list.add(employee);

                employee="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public LinkedList<String> showUserCompany(int id){

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT id, name, field FROM companies WHERE idOwner = '" + id + "'";
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                contribution+=rs.getString("id") + " ";
                contribution+=rs.getString("name") + " ";
                contribution+=rs.getString("field");

                list.add(contribution);

                contribution="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public LinkedList<String> showUserEmployee(int idCompany){

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT id, name, surname, department, salary FROM employee WHERE idCompany = '" + idCompany + "'";
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                contribution+=rs.getString("id") + " ";
                contribution+=rs.getString("name") + " ";
                contribution+=rs.getString("surname") + " ";
                contribution+=rs.getString("department") + " ";
                contribution+=rs.getString("salary");

                list.add(contribution);

                contribution="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public void addCompanyInDataBase(int idOwner, String name, String field){

        try {
            String query = " insert into companies (idOwner, name, field) values (?, ?, ?)";

            PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
            preparedStmt.setInt (1, idOwner);
            preparedStmt.setString (2, name);
            preparedStmt.setString (3, field);

            preparedStmt.executeUpdate();
            System.out.println("Новый продукт был добавлен !");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteCompanyInDataBase(int id)  {
        System.out.println(id);
        String selectSQL = "DELETE FROM companies WHERE id = ?";
        try {
            dbConnection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public void deleteEmployeeInDataBase(int id)  {
        System.out.println(id);
        String selectSQL = "DELETE FROM employee WHERE id = ?";
        try {
            dbConnection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = dbConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public  void redactionCompanyInDataBase(int id, String name, String field)  {

        String query = "UPDATE companies SET name = ?, field = ? WHERE id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = dbConnection.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString(2, field);
            preparedStmt.setInt(3, id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public  void redactionEmployeeInDataBase(int id, String name, String surname, String department, int salary)  {

        String query = "UPDATE employee SET name = ?, surname = ?, department = ?, salary = ? WHERE id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = dbConnection.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString(2, surname);
            preparedStmt.setString(3, department);
            preparedStmt.setInt(4, salary);
            preparedStmt.setInt(5, id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void addStatInDb(int id, String name, String date, double cost){

        try {
            String query = " insert into statistics (idCompany, name, date, cost) values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
            preparedStmt.setInt (1, id);
            preparedStmt.setString (2, name);
            preparedStmt.setString (3, date);
            preparedStmt.setDouble (4, cost);

            preparedStmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void addEmployeeInDatabase(int idCompany, String name, String surname, String department, int salary){

        try {
            String query = " insert into employee (idCompany, name, surname, department, salary) values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = dbConnection.prepareStatement(query);
            preparedStmt.setInt(1,idCompany);
            preparedStmt.setString (2, name);
            preparedStmt.setString (3, surname);
            preparedStmt.setString (4, department);
            preparedStmt.setInt(5,salary);

            preparedStmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public LinkedList<String> showStatistics(){
        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT id, idCompany, name, date, cost FROM statistics";
        ResultSet rs = null;
        String statistics="";
        try {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                statistics+=rs.getString("id") + " ";
                statistics+=rs.getString("idCompany") + " ";
                statistics+=rs.getString("name") + " ";
                statistics+=rs.getString("date") + " ";
                statistics+=rs.getString("cost");

                list.add(statistics);

                statistics="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public LinkedList<String> showDiagram(){
        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT department, COUNT(*) as count FROM employee GROUP BY department";
        ResultSet rs = null;
        String statistics="";
        try {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                statistics+=rs.getString("department") + " ";
                statistics+=rs.getString("count");

                list.add(statistics);

                statistics="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public LinkedList<String> showIdComp(){
        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT DISTINCT id FROM companies ";
        ResultSet rs = null;
        String statistics="";
        try {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                statistics+=rs.getString("id") ;
                list.add(statistics);
                statistics="";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }
}
