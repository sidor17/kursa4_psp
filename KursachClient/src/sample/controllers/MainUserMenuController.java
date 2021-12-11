package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.windows.NewWindowOpen;

public class MainUserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button infoAboutModel;

    @FXML
    private Button signInButtonStart;

    @FXML
    private Button statistics;

    @FXML
    private Button workWithCompany;

    @FXML
    private Button workWithEmployee;

    @FXML
    void initialize() {
        signInButtonStart.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(signInButtonStart,"../graphics/sample.fxml");
        });

        statistics.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(statistics,"../graphics/ViewStatistics.fxml");
        });

        infoAboutModel.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(infoAboutModel,"../graphics/InfoAboutModel.fxml");
        });

        workWithCompany.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(workWithCompany,"../graphics/CompanyUserMenu.fxml");
        });

        workWithEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(workWithEmployee,"../graphics/EmployeeUserMenu.fxml");
        });
    }
}
