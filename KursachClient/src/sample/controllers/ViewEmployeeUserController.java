package sample.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Employee;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

import static sample.clientActions.ClientActionsWithServer.distinctId;

public class ViewEmployeeUserController {

    @FXML
    private TableView<Employee> allEmployeesTable;

    @FXML
    private Button backToEmployeeUserMenu;

    @FXML
    private TableColumn<Employee, String> departmentTab;

    //@FXML
    //private TableColumn<Employee, Integer> idCompanyTab;

    @FXML
    private TableColumn<Employee, Integer> idTab;

    @FXML
    private TableColumn<Employee, String> nameTab;

    @FXML
    private TableColumn<Employee, Integer> salaryTab;

    @FXML
    private TableColumn<Employee, String> surnameTab;

    @FXML
    private ComboBox<String> comboBox;

    private final ObservableList<Employee> listEmployees = FXCollections.observableArrayList();

    @FXML
    void initialize() throws IOException {
/*        try {
            initEmployees(Client.interactionsWithServer.allEmployees());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
     ObservableList<String> idData = FXCollections.observableArrayList(distinctId());

        comboBox.setItems(idData);
        comboBox.setOnAction(event -> {
            try
            {
            String s = comboBox.getSelectionModel().getSelectedItem();

            initEmployees(Client.interactionsWithServer.allUserEmployees(s));
            }
         catch (IOException e) {
            e.printStackTrace();
        }});

        backToEmployeeUserMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToEmployeeUserMenu,"../graphics/EmployeeUserMenu.fxml");
        });
    }


    private void initEmployees(LinkedList<Employee> allEmployees) {
        listEmployees.clear();
        listEmployees.addAll(allEmployees);

        idTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        //idCompanyTab.setCellValueFactory(new PropertyValueFactory<> ("idCompany"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameTab.setCellValueFactory(new PropertyValueFactory<>("surname"));
        departmentTab.setCellValueFactory(new PropertyValueFactory<>("department"));
        salaryTab.setCellValueFactory(new PropertyValueFactory<>("salary"));

        allEmployeesTable.setItems(listEmployees);
    }
}


