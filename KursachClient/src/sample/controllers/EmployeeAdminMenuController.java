package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.windows.NewWindowOpen;

public class EmployeeAdminMenuController {
    @FXML
    private Button backToMainAdminMenu;

    @FXML
    private Button viewDiagram;

    @FXML
    private Button deleteEmployees;

    @FXML
    private Button viewEmployees;

    @FXML
    void initialize() {
        viewDiagram.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(viewDiagram,"../graphics/DiagramEmployee.fxml");
        });

        backToMainAdminMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToMainAdminMenu,"../graphics/MainAdminMenu.fxml");
        });

        viewEmployees.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(viewEmployees,"../graphics/ViewEmployeeAdmin.fxml");
        });

        deleteEmployees.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(deleteEmployees,"../graphics/DeleteEmployeeAdmin.fxml");
        });
    }
}
