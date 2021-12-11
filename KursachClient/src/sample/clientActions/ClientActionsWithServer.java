package sample.clientActions;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.classes.Company;
import sample.classes.Statistic;
import sample.classes.User;
import sample.classes.Employee;
import sample.windows.NewWindowOpen;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientActionsWithServer {
    private static BufferedReader acceptMessage;
    private static BufferedWriter sendMessage;

    public ClientActionsWithServer(Socket clientSocket) {
        try {
            acceptMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMessage = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void send(String message) {
        try {
            sendMessage.write(message + "\n");
            sendMessage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkUserInDatabase(Button button, Label label){
        try {
            String answer = acceptMessage.readLine();
            if(!answer.equals("false")){
                String[] string = answer.split(" ");
                    User.currentUser = new User(Integer.parseInt(string[0]),string[1]);
                if(User.currentUser.getRole()) {
                    NewWindowOpen.newWindowOpen.openWindow(button, "../graphics/MainAdminMenu.fxml");
                }else{
                    NewWindowOpen.newWindowOpen.openWindow(button, "../graphics/MainUserMenu.fxml");
                }
            }else{
                label.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Company> allUserCompanies() throws IOException {
        send("вывести мои компании");
        send(String.valueOf(User.currentUser.getId()));

        LinkedList<Company> companies = new LinkedList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addCompanyInList(acceptMessage.readLine(),companies);
        }
        return companies;
    }

    public LinkedList<Company> allCompanies() throws IOException {
        send("вывести все компании");
        LinkedList<Company> companies = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addGeneralCompanyInList(acceptMessage.readLine(),companies);
        }
        return companies;
    }

    public LinkedList<Employee> allUserEmployees() throws IOException {
        send("вывести мои сотрудники");
        send(String.valueOf(User.currentUser.getId()));

        LinkedList<Employee> employees = new LinkedList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addEmployeeInList(acceptMessage.readLine(), employees);
        }
        return employees;
    }

    public LinkedList<Employee> allUserEmployees(String idCompany) throws IOException {
        send("вывести мои сотрудники");
        send(String.valueOf(idCompany));

        LinkedList<Employee> employees = new LinkedList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addEmployeeInList(acceptMessage.readLine(), employees);
        }
        return employees;
    }

    public LinkedList<Employee> allEmployees() throws IOException {
        send("вывести всех сотрудников");
        LinkedList<Employee> employees = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addGeneralEmployeesInList(acceptMessage.readLine(),employees);
        }
        return employees;
    }

    private void addCompanyInList(String string, LinkedList<Company> companies){
        String[] company;
        company = string.split(" ");
        companies.add(new Company(Integer.parseInt(company[0]), company[1], company[2]));
    }

    private void addEmployeeInList(String string, LinkedList<Employee> employees){
        String[] employee;
        employee = string.split(" ");
        employees.add(new Employee(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Integer.parseInt(employee[4])));
    }

    private void addGeneralCompanyInList(String string, LinkedList<Company> companies){
        String[] company;
        company = string.split(" ");
        companies.add(new Company(Integer.parseInt(company[0]), Integer.parseInt(company[1]), company[2], company[3]));
    }

    private void addGeneralEmployeesInList(String string, LinkedList<Employee> employees){
        String[] employee;
        employee = string.split(" ");
        employees.add(new Employee(Integer.parseInt(employee[0]), Integer.parseInt(employee[1]), employee[2], employee[3], employee[4], Integer.parseInt(employee[5])));
    }

    public LinkedList<User> allAdmins() throws IOException {
        send("вывести всех админов");
        LinkedList<User> admins = new LinkedList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++)
            addUserInList(acceptMessage.readLine(),admins);
        return admins;
    }

    public LinkedList<User> allUsers() throws IOException {
        send("вывести всех пользователей");
        LinkedList<User> users = new LinkedList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++)
            addUserInList(acceptMessage.readLine(),users);
        return users;
    }

    private void addUserInList(String string, LinkedList<User> users){
        String[] user;
        user = string.split(" ");
        users.add(new User(Integer.parseInt(user[0]),user[1],user[2], user[3]));
    }


    public void redactionCompany(int id, String name, String field) {
        send("редактировать компанию");
        send(id+" "+name+" "+field);
    }

    public void redactionEmployee(int id, String name, String surname, String department, Integer salary) {
        send("редактировать сотрудника");
        send(id+" "+name+" "+surname+ " "+department+" "+salary);
    }

    public void addNewUserInDataBase(String login, String password ,String isAdmin) {
        send("добавить пользователя");
        send(login + " " + password + " " + isAdmin);
    }

    public void deleteCompany(int id) {
        send("удалить компанию");
        send(String.valueOf(id));
    }

    public void deleteEmployee(int id) {
        send("удалить сотрудника");
        send(String.valueOf(id));
    }

    public void addNewCompanyInDataBase(String idOwner, String name,String field) {
        send("добавить компанию");
        send(idOwner + " " + name + " " + field);
    }

    public void addStatistic(String id, String name, String date, String cost) {
        send("добавить статистику");
        send(id + " " + name + " " + date + " " + cost);
    }

    public LinkedList<Statistic> allStatistics() throws IOException {
        send("вывести статистику");
        LinkedList<Statistic> statistics = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            addStatisticInList(acceptMessage.readLine(),statistics);
        }
        return statistics;
    }

    private void addStatisticInList(String string, LinkedList<Statistic> statistics){
        String[] statistic;
        statistic = string.split(" ");
        statistics.add(new Statistic(Integer.parseInt(statistic[0]), Integer.parseInt(statistic[1])
                , statistic[2], statistic[3], Double.parseDouble(statistic[4])));
    }

    public void addNewEmployeeInDataBase(String idCompany, String name,String surname, String department, int salary) {
        send("добавить сотрудника");
        send(idCompany + " " + name + " " + surname + " " +department+ " "+ salary);
    }

    public static ArrayList<String> all() throws IOException {
        send("вывести диаграмму");
        ArrayList<String> statistics = new ArrayList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            statistics.add(acceptMessage.readLine());
        }
        return statistics;
    }

    public static ArrayList<String> distinctId() throws IOException {
        send("вывести id");
        ArrayList<String> id = new ArrayList<>();
        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i=0;i<sizeList;i++){
            id.add(acceptMessage.readLine());
        }
        return id;
    }
}
