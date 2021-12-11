package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.windows.NewWindowOpen;

public class CompanyAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button calculateCost;

    @FXML
    private Button backToMainAdminMenu;

    @FXML
    private Button viewCompanies;

    @FXML
    private Button deleteCompanies;

    @FXML
    void initialize() {
        calculateCost.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(calculateCost,"../graphics/CalculateCostAdmin.fxml");
        });

        backToMainAdminMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToMainAdminMenu,"../graphics/MainAdminMenu.fxml");
        });

        viewCompanies.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(viewCompanies,"../graphics/ViewCompanyAdmin.fxml");
        });

        deleteCompanies.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(deleteCompanies,"../graphics/DeleteCompanyAdmin.fxml");
        });
    }
}
