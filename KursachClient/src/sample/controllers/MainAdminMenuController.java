package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.windows.NewWindowOpen;

public class MainAdminMenuController {


    @FXML
    private Button workWithCompany;

    @FXML
    private Button workWithEmployees;

    @FXML
    private Button signInButtonStart;

    @FXML
    private Button viewAdmins;

    @FXML
    private Button viewUsers;

    @FXML
    void initialize() {
        workWithCompany.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(workWithCompany,"../graphics/CompanyAdminMenu.fxml");

        });
        workWithEmployees.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(workWithEmployees,"../graphics/EmployeeAdminMenu.fxml");

        });
        signInButtonStart.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(signInButtonStart,"../graphics/sample.fxml");

        });
        viewAdmins.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(viewAdmins,"../graphics/ViewAdmins.fxml");

        });
        viewUsers.setOnAction(event -> {
            NewWindowOpen.newWindowOpen.openWindow(viewUsers,"../graphics/ViewUsers.fxml");

        });
    }
}
