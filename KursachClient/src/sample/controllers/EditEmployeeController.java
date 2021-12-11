package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import static sample.clientActions.ClientActionsWithServer.distinctId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Employee;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

import static sample.clientActions.ClientActionsWithServer.all;


public class EditEmployeeController {

    @FXML
    private TableView<Employee> allEmployeesTable;

    @FXML
    private Button backToUserEmployee;

    @FXML
    private TableColumn<Employee,String> departmentTab;

    @FXML
    private Button editEmployee;

    @FXML
    private TextField fieldDepartment;

    @FXML
    private TextField fieldId;

    @FXML
    private TextField fieldIdCompany;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSalary;

    @FXML
    private TextField filedSurname;

    @FXML
    private TableColumn<Employee, Integer> idTab;

    @FXML
    private Label labelChooseEmployee;

    @FXML
    private Label labelFull;

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
        ObservableList<String> piechartData = FXCollections.observableArrayList(distinctId());

        comboBox.setItems(piechartData);
        comboBox.setOnAction(event -> {
            try {
                String s = comboBox.getSelectionModel().getSelectedItem();

                //initEmployee(Client.interactionsWithServer.allUserEmployees());
                initEmployee(Client.interactionsWithServer.allUserEmployees(s));

                editEmployee.setOnAction(e -> {
                            labelChooseEmployee.setVisible(false);
                            labelFull.setVisible(false);
                            Employee employee = allEmployeesTable.getSelectionModel().getSelectedItem();
                            if (employee != null && !nameTab.getText().equals("") && !surnameTab.getText().equals("") && !departmentTab.getText().equals("")) {
                                Client.interactionsWithServer.redactionEmployee(employee.getId(), fieldName.getText(), filedSurname.getText(), fieldDepartment.getText(), Integer.parseInt(fieldSalary.getText()));
                                try {
                                    initEmployee(Client.interactionsWithServer.allUserEmployees(s));
                                } catch (IOException exception) {
                                    exception.printStackTrace();
                                }
                            } else if (employee == null)
                                labelChooseEmployee.setVisible(true);
                            else
                                labelFull.setVisible(true);
                        }
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        backToUserEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserEmployee,"../graphics/EmployeeUserMenu.fxml");
        });
    }

    private void initEmployee(LinkedList<Employee> allEmployee) {
        listEmployees.clear();
        listEmployees.addAll(allEmployee);

        idTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surnameTab.setCellValueFactory(new PropertyValueFactory<>("surname"));
        departmentTab.setCellValueFactory(new PropertyValueFactory<>("department"));
        salaryTab.setCellValueFactory(new PropertyValueFactory<>("salary"));


        allEmployeesTable.setItems(listEmployees);

        fieldName.setText(allEmployee.get(0).name);
        filedSurname.setText(allEmployee.get(0).surname);
        fieldDepartment.setText(allEmployee.get(0).department);
        fieldSalary.setText(String.valueOf(allEmployee.get(0).salary));
    }
}
