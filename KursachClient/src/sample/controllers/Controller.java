package sample.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import sample.clientActions.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.windows.NewWindowOpen;

import static java.lang.System.exit;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signInLogin;

    @FXML
    private PasswordField signInPassword;

    @FXML
    private Button signUpButtonStart;

    @FXML
    private Button signInButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label labelFull;

    @FXML
    private Label labelAuth;

    @FXML
    void initialize() {
        signInButton.setOnAction(event -> {
            confirmLoginAndPassword();
        });

        exitButton.setOnAction(event -> {
            Client.interactionsWithServer.send("выйти");
            exit(1);
        });

        signUpButtonStart.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(signUpButtonStart,"../graphics/Registration.fxml");
        });
    }

    private void confirmLoginAndPassword(){
        labelAuth.setVisible(false);
        labelFull.setVisible(false);
        String login;
        String password;
        login =  signInLogin.getText();
        password = signInPassword.getText();
        if(!login.equals("") && !password.equals("")){
            labelFull.setVisible(false);
            Client.interactionsWithServer.send("авторизация");
            Client.interactionsWithServer.send(login + " " + password);
            Client.interactionsWithServer.checkUserInDatabase(signInButton, labelAuth);
        }else{
            labelFull.setVisible(true);
        }
    }
}
