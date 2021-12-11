package sample.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Company;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class ViewCompanyUserController {


    @FXML
    private TableView<Company> allCompaniesTable;

    @FXML
    private TableColumn<Company, Integer> idTab;

    @FXML
    private TableColumn<Company, String> nameTab;

    @FXML
    private TableColumn<Company, String> fieldTab;

    @FXML
    private Button backToCompanyUserMenu;

    private final ObservableList<Company> listCompanies = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initCompanies(Client.interactionsWithServer.allUserCompanies());
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToCompanyUserMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToCompanyUserMenu,"../graphics/CompanyUserMenu.fxml");
        });
    }

    private void initCompanies(LinkedList<Company> allCompanies) {
        listCompanies.clear();
        listCompanies.addAll(allCompanies);

        idTab.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
        fieldTab.setCellValueFactory(new PropertyValueFactory<>("Field"));

        allCompaniesTable.setItems(listCompanies);
    }
}
