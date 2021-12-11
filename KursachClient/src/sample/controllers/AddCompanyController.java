package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.classes.User;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class AddCompanyController {

    @FXML
    private TextField nameOfCompany;

    @FXML
    private TextField fieldActivity;

    @FXML
    private Button backToUserCompany;

    @FXML
    private Button addCompany;

    @FXML
    private Label label;

    @FXML
    private Label labelFull;

    @FXML
    void initialize() {
        backToUserCompany.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserCompany,"../graphics/CompanyUserMenu.fxml");
        });

        addCompany.setOnAction(e -> {
            addCompanyUser();
        });
    }

    private void addCompanyUser() {
        labelFull.setVisible(false);
        label.setVisible(false);
        String name = nameOfCompany.getText();
        String field = fieldActivity.getText();
        if(!name.equals("") && !field.equals(""))
        {
            String idOwner = String.valueOf(User.currentUser.id);
            Client.interactionsWithServer.addNewCompanyInDataBase(idOwner, name, field);
            nameOfCompany.clear();
            fieldActivity.clear();
            label.setVisible(true);
        }
        else
            labelFull.setVisible(true);
    }
}
