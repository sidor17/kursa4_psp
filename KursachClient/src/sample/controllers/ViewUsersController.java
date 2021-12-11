package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.classes.User;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class ViewUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> idTab;

    @FXML
    private TableColumn<User, String> loginTab;

    @FXML
    private TableColumn<User, String> passwordTab;

    @FXML
    private Button backToCompanyAdminMenu;

    private final ObservableList<User> listUsers = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initUsers(Client.interactionsWithServer.allUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToCompanyAdminMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToCompanyAdminMenu,"../graphics/MainAdminMenu.fxml");
        });
    }

    void initUsers(LinkedList<User> users)
    {
        listUsers.clear();
        listUsers.addAll(users);

        idTab.setCellValueFactory(new PropertyValueFactory<>("id"));
        loginTab.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordTab.setCellValueFactory(new PropertyValueFactory<>("password"));

        usersTable.setItems(listUsers);
    }
}