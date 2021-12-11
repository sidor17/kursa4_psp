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
import sample.classes.Company;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class ViewCompanyAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Company> allCompaniesTable;

    @FXML
    private TableColumn<Company, Integer> idTab;

    @FXML
    private TableColumn<Company, Integer> idOwnerTab;

    @FXML
    private TableColumn<Company, String> nameTab;

    @FXML
    private TableColumn<Company, String> fieldTab;

    @FXML
    private Button backToCompanyAdminMenu;

    private final ObservableList<Company> listCompanies = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initCompanies(Client.interactionsWithServer.allCompanies());
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToCompanyAdminMenu.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(backToCompanyAdminMenu,"../graphics/CompanyAdminMenu.fxml");

        });

    }

    private void initCompanies(LinkedList<Company> companies){

        listCompanies.clear();
        listCompanies.addAll(companies);

        idTab.setCellValueFactory(new PropertyValueFactory<Company, Integer>("id"));
        idOwnerTab.setCellValueFactory(new PropertyValueFactory<Company, Integer>("idOwner"));
        nameTab.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        fieldTab.setCellValueFactory(new PropertyValueFactory<Company, String>("field"));

        allCompaniesTable.setItems(listCompanies);
    }
}
