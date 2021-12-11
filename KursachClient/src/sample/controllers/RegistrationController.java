package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpPassword;

    @FXML
    private CheckBox checkBoxAdmin;

    @FXML
    private Button signInButtonStart;

    @FXML
    private Label labelFull;

    @FXML
    void initialize() {
        signUpButton.setOnAction(e -> {
            registerUser();
        });

        signInButtonStart.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(signInButtonStart,"../graphics/sample.fxml");
        });
    }


    private void registerUser() {
        String login;
        String password;
        String isAdmin;
        login = signUpLogin.getText();
        password = signUpPassword.getText();
        if(!login.equals("") && !password.equals("")){
            if(checkBoxAdmin.isSelected())
                isAdmin = "admin";
            else
                isAdmin = "user";
            Client.interactionsWithServer.addNewUserInDataBase(login, password, isAdmin);
            NewWindowOpen.newWindowOpen.openWindow(signInButtonStart,"../graphics/sample.fxml");

        }else{
            labelFull.setVisible(true);
        }

    }
}
