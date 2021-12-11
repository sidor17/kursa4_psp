package sample.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Employee;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;


import static sample.clientActions.ClientActionsWithServer.distinctId;

public class DeleteEmployeeUserController {

    @FXML
    private TableView<Employee> allEmployeeTable;

    @FXML
    private Button backToUserEmployee;

    @FXML
    private Button deleteEmployee;

    @FXML
    private TableColumn<Employee, String> departmentTab;

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

    @FXML
    private ComboBox<String> comboBox;

    private final ObservableList<Employee> listEmployees = FXCollections.observableArrayList();

/*  @FXML
    void initialize() {
        try {
            initEmployees(Client.interactionsWithServer.allUserEmployees());
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
                    initEmployees(Client.interactionsWithServer.allUserEmployees());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else
                labelChooseEmployee.setVisible(true);
        });

        backToUserEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserEmployee,"../graphics/EmployeeUserMenu.fxml");
        });
    }*/


    @FXML
    void initialize() throws IOException {

        ObservableList<String> idData = FXCollections.observableArrayList(distinctId());

        comboBox.setItems(idData);
        comboBox.setOnAction(event -> {
            try {
            String s = comboBox.getSelectionModel().getSelectedItem();

            initEmployees(Client.interactionsWithServer.allUserEmployees(s));

               deleteEmployee.setOnAction(e -> {
                   labelChooseEmployee.setVisible(false);
                   Employee employee = allEmployeeTable.getSelectionModel().getSelectedItem();
                   if(employee != null)
                   {
                       Client.interactionsWithServer.deleteEmployee(employee.getId());
                       try {
                           initEmployees(Client.interactionsWithServer.allUserEmployees(s));
                       } catch (IOException exception) {
                           exception.printStackTrace();
                       }
                   }
                   else
                       labelChooseEmployee.setVisible(true);
               });
        }
            catch (IOException e) {   e.printStackTrace();        }

        });

        backToUserEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserEmployee,"../graphics/EmployeeUserMenu.fxml");
        });
    }
    private void initEmployees(LinkedList<Employee> a)
    {
        listEmployees.clear();
        listEmployees.addAll(a);

        idTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameTab.setCellValueFactory(new PropertyValueFactory<>("surname"));
        departmentTab.setCellValueFactory(new PropertyValueFactory<>("department"));
        salaryTab.setCellValueFactory(new PropertyValueFactory<>("salary"));

        allEmployeeTable.setItems(listEmployees);

    }
}
