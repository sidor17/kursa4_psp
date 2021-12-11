package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Company;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class DeleteCompanyUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToUserCompany;

    @FXML
    private TableView<Company> allCompaniesTable;

    @FXML
    private TableColumn<Company, Integer> idTab;

    @FXML
    private TableColumn<Company, Integer> idOwnerTab;

    @FXML
    private TableColumn<Company, Integer> nameTab;

    @FXML
    private TableColumn<Company, Integer> fieldTab;

    @FXML
    private Button deleteCompany;

    @FXML
    private Label labelChooseComp;

    private final ObservableList<Company> listCompanies = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initCompanies(Client.interactionsWithServer.allUserCompanies());
        } catch (IOException e) {
            e.printStackTrace();
        }

        deleteCompany.setOnAction(e -> {
            labelChooseComp.setVisible(false);
            Company company = allCompaniesTable.getSelectionModel().getSelectedItem();
            if(company != null)
            {
                Client.interactionsWithServer.deleteCompany(company.getId());
                try {
                    initCompanies(Client.interactionsWithServer.allCompanies());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else
                labelChooseComp.setVisible(true);
        });

        backToUserCompany.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserCompany,"../graphics/CompanyUserMenu.fxml");
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
