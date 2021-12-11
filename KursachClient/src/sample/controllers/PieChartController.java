package sample.controllers;

import java.io.IOException;

import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import sample.windows.NewWindowOpen;

import static sample.clientActions.ClientActionsWithServer.all;

public class PieChartController {

    @FXML
    private Button backToUserEmployee;

    @FXML
    private PieChart piechart;

    @FXML
    void initialize() throws IOException {

        backToUserEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserEmployee,"../graphics/EmployeeUserMenu.fxml");
        });
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(initEmployee());
        piechart.getData().addAll(piechartData);
    }

    public ArrayList initEmployee() throws IOException {
        ArrayList piechartData  = new ArrayList();
           for (String element : all()) {
            String[] arr = element.split(" ");
            piechartData.add(new PieChart.Data(arr[0],Integer.parseInt(arr[1])));
         }
        return piechartData;
    }
}
