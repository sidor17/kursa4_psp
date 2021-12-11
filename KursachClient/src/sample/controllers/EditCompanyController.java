package sample.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.Company;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class EditCompanyController {

    @FXML
    private Button backToUserCompany;

    @FXML
    private TableView<Company> allCompaniesTable;

    @FXML
    private TableColumn<Company, Integer> idTab;

    @FXML
    private TableColumn<Company, Integer> nameTab;

    @FXML
    private TableColumn<Company, Integer> fieldTab;

    @FXML
    private Button editCompany;

    @FXML
    private TextField nameOfCompany;

    @FXML
    private TextField fieldActivity;

    @FXML
    private Label labelChooseComp;

    @FXML
    private Label labelFull;

    private final ObservableList<Company> listCompanies = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initCompanies(Client.interactionsWithServer.allUserCompanies());
        } catch (IOException e) {
            e.printStackTrace();
        }

        editCompany.setOnAction(e -> {
            labelChooseComp.setVisible(false);
            labelFull.setVisible(false);
            Company company = allCompaniesTable.getSelectionModel().getSelectedItem();
            if(company != null && !nameOfCompany.getText().equals("") && !fieldActivity.getText().equals(""))
            {
                Client.interactionsWithServer.redactionCompany(company.getId(), nameOfCompany.getText(), fieldActivity.getText());
                try {
                    initCompanies(Client.interactionsWithServer.allUserCompanies());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }else
                if(company == null)
                    labelChooseComp.setVisible(true);
                else
                    labelFull.setVisible(true);
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

        nameOfCompany.setText(allCompanies.get(0).name);
        fieldActivity.setText(allCompanies.get(0).field);
    }
}
