package sample.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.classes.User;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

import java.io.IOException;

import static sample.clientActions.ClientActionsWithServer.distinctId;

public class AddEmployeeController {

    @FXML
    private Button addEmployee;

    @FXML
    private Button backToUserEmployee;

    @FXML
    private TextField fieldDepartment;

    @FXML
    private TextField fieldIdCompany;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldSalary;

    @FXML
    private TextField fieldSurname;

    @FXML
    private Label label;

    @FXML
    private Label labelError;

    @FXML
    private Label labelFull;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    void initialize() throws IOException {

        ObservableList<String> idData = FXCollections.observableArrayList(distinctId());
        comboBox.setItems(idData);

        backToUserEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserEmployee,"../graphics/EmployeeUserMenu.fxml");
        });

        addEmployee.setOnAction(e -> {
            try {
                addEmployeeUser();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addEmployeeUser() throws IOException {
        try{

        labelFull.setVisible(false);
        label.setVisible(false);
        String idComp = comboBox.getSelectionModel().getSelectedItem();
        String name = fieldName.getText();
        String surname = fieldSurname.getText();
        String department = fieldDepartment.getText();
        int salary = Integer.parseInt(fieldSalary.getText());
        if(!name.equals("") && !surname.equals("") && !department.equals(""))
        {
            Client.interactionsWithServer.addNewEmployeeInDataBase(idComp, name, surname, department, salary);
            fieldName.clear();
            fieldSurname.clear();
            fieldDepartment.clear();
            fieldSalary.clear();
            label.setVisible(true);
        }
        else
            labelFull.setVisible(true);
    }catch (NumberFormatException e){
            labelError.setVisible(true);
        }
    }
}
