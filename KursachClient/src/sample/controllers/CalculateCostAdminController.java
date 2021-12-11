package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.classes.Company;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class CalculateCostAdminController {


    @FXML
    private Button calculateCost;

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

    @FXML
    private TextField bbText;

    @FXML
    private TextField beText;

    @FXML
    private TextField rText;

    @FXML
    private TextField xText;

    @FXML
    private TextField resultText;

    @FXML
    private Button instructionButton;

    @FXML
    private Label labelFull;

    @FXML
    private Label labelChooseComp;

    private final ObservableList<Company> listCompanies = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initCompanies(Client.interactionsWithServer.allCompanies());
        } catch (IOException e) {
            e.printStackTrace();
        }

        instructionButton.setOnAction(e -> {
            Stage primaryStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../graphics/InfoAboutModel.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if(root!=null) {
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        backToCompanyAdminMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToCompanyAdminMenu,"../graphics/CompanyAdminMenu.fxml");
        });

        calculateCost.setOnAction(e -> {
            calcCost();
        });
    }

    private void calcCost() {
        try{
            labelChooseComp.setVisible(false);
            labelFull.setVisible(false);
            Company company = allCompaniesTable.getSelectionModel().getSelectedItem();
            int x = Integer.parseInt(xText.getText()), be = Integer.parseInt(beText.getText())
                    , bb = Integer.parseInt(bbText.getText());
            double r = Double.parseDouble(rText.getText()), cost;
            if(company != null)
            {
                cost = be + (x - r * bb) / (1 + r);
                resultText.setText(String.valueOf(cost));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                dateFormat.format(date);
                Client.interactionsWithServer.addStatistic(String.valueOf(company.id), company.name
                        , dateFormat.format(date), String.valueOf(cost));
            }
            else
                labelChooseComp.setVisible(true);
        }catch(Exception e){
            labelFull.setVisible(true);
        }
    }

    private void initCompanies(LinkedList<Company> allCompanies) {
        listCompanies.clear();
        listCompanies.addAll(allCompanies);

        idTab.setCellValueFactory(new PropertyValueFactory<>("Id"));
        idOwnerTab.setCellValueFactory(new PropertyValueFactory<>("IdOwner"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
        fieldTab.setCellValueFactory(new PropertyValueFactory<>("Field"));

        allCompaniesTable.setItems(listCompanies);
    }
}
