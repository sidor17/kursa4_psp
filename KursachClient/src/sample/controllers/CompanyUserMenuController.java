package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.windows.NewWindowOpen;

public class CompanyUserMenuController {


    @FXML
    private Button calcCost;

    @FXML
    private Button deleteCompany;

    @FXML
    private Button viewCompanies;

    @FXML
    private Button addCompany;

    @FXML
    private Button editCompany;

    @FXML
    private Button backToUserMenu;

    @FXML
    void initialize() {
        backToUserMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserMenu,"../graphics/MainUserMenu.fxml");
        });

        addCompany.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(addCompany,"../graphics/AddCompany.fxml");
        });

        viewCompanies.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(viewCompanies,"../graphics/ViewCompanyUser.fxml");
        });

        deleteCompany.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(deleteCompany,"../graphics/DeleteCompanyUser.fxml");
        });

        calcCost.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(calcCost,"../graphics/CalcCost.fxml");
        });

        editCompany.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(editCompany,"../graphics/EditCompany.fxml");
        });
    }
}
