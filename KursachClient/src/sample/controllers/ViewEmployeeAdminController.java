package sample.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Company;
import sample.classes.Employee;

import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class ViewEmployeeAdminController {

    @FXML
    private TableView<Employee> allEmployeesTable;

    @FXML
    private Button backToEmployeeAdminMenu;

    @FXML
    private TableColumn<Employee, String> departmentTab;

    @FXML
    private TableColumn<Employee, Integer> idCompanyTab;

    @FXML
    private TableColumn<Employee, Integer> idTab;

    @FXML
    private TableColumn<Employee, String> nameTab;

    @FXML
    private TableColumn<Employee, Integer> salaryTab;

    @FXML
    private TableColumn<Employee, String> surnameTab;

    private final ObservableList<Employee> listEmployees = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initEmployees(Client.interactionsWithServer.allEmployees());
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToEmployeeAdminMenu.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(backToEmployeeAdminMenu,"../graphics/EmployeeAdminMenu.fxml");

        });

    }

    private void initEmployees(LinkedList<Employee> companies){

        listEmployees.clear();
        listEmployees.addAll(companies);

        idTab.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        idCompanyTab.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("idCompany"));
        nameTab.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        surnameTab.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
        departmentTab.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));
        salaryTab.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));

        allEmployeesTable.setItems(listEmployees);
    }
}
