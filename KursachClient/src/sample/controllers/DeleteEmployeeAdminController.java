package sample.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Employee;

import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class DeleteEmployeeAdminController {

    @FXML
    private TableView<Employee> allEmployeeTable;

    @FXML
    private Button backToEmployeeAdminMenu;

    @FXML
    private Button deleteEmployee;

    @FXML
    private TableColumn<Employee, String> departmentTab;

    @FXML
    private TableColumn<Employee, String> idCompanyTab;

    @FXML
    private TableColumn<Employee, Integer> idTab;

    @FXML
    private Label labelChooseEmployee;

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

        deleteEmployee.setOnAction(e -> {
            labelChooseEmployee.setVisible(false);
            Employee employee = allEmployeeTable.getSelectionModel().getSelectedItem();
            if(employee != null)
            {
                Client.interactionsWithServer.deleteEmployee(employee.getId());
                try {
                    initEmployees(Client.interactionsWithServer.allEmployees());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else
                labelChooseEmployee.setVisible(true);
        });

        backToEmployeeAdminMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToEmployeeAdminMenu,"../graphics/EmployeeAdminMenu.fxml");
        });
    }

    private void initEmployees(LinkedList<Employee> allEmployees) {
        listEmployees.clear();
        listEmployees.addAll(allEmployees);

        idTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCompanyTab.setCellValueFactory(new PropertyValueFactory<>("idCompany"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameTab.setCellValueFactory(new PropertyValueFactory<>("surname"));
        departmentTab.setCellValueFactory(new PropertyValueFactory<>("department"));
        salaryTab.setCellValueFactory(new PropertyValueFactory<>("salary"));

        allEmployeeTable.setItems(listEmployees);
    }
}
