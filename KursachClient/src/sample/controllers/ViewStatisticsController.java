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
import sample.classes.Company;
import sample.classes.Statistic;
import sample.classes.User;
import sample.clientActions.Client;
import sample.windows.NewWindowOpen;

public class ViewStatisticsController {


    @FXML
    private TableView<Statistic> statisticsTable;

    @FXML
    private TableColumn<Statistic, Integer> idCompanyTab;

    @FXML
    private TableColumn<Statistic, String> nameTab;

    @FXML
    private TableColumn<Statistic, String> dateTab;

    @FXML
    private TableColumn<Statistic, Double> costTab;

    @FXML
    private Button backToMenu;

    private final ObservableList<Statistic> listStatistics = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        try {
            initStatistics(Client.interactionsWithServer.allStatistics());
        } catch (IOException e) {
            e.printStackTrace();
        }

        backToMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToMenu,"../graphics/MainUserMenu.fxml");
        });

    }

    private void initStatistics(LinkedList<Statistic> statistics) {
        listStatistics.clear();
        listStatistics.addAll(statistics);

        idCompanyTab.setCellValueFactory(new PropertyValueFactory<>("IdCompany"));
        nameTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
        dateTab.setCellValueFactory(new PropertyValueFactory<>("Date"));
        costTab.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        statisticsTable.setItems(listStatistics);
    }
}
