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

public class ViewAdminsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> adminsTable;

    @FXML
    private TableColumn<User, Integer> idTab;

    @FXML
    private TableColumn<User, String> loginTab;

    @FXML
    private TableColumn<User, String> passwordTab;

    @FXML
    private Button backToCompanyAdminMenu;

    private final ObservableList<User> listAdmins = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initAdmins(Client.interactionsWithServer.allAdmins());
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToCompanyAdminMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToCompanyAdminMenu,"../graphics/MainAdminMenu.fxml");
        });
    }

    void initAdmins(LinkedList<User> users)
    {
        listAdmins.clear();
        listAdmins.addAll(users);


        idTab.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
        loginTab.setCellValueFactory(new PropertyValueFactory<User, String>("Login"));
        passwordTab.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));

        adminsTable.setItems(listAdmins);
    }
}