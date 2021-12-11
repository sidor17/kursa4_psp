package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.windows.NewWindowOpen;

public class EmployeeUserMenuController {

    @FXML
    private Button addEmployee;

    @FXML
    private Button backToUserMenu;

    @FXML
    private Button viewDiagram;

    @FXML
    private Button deleteEmployee;

    @FXML
    private Button editEmployee;

    @FXML
    private Button viewEmployee;

    @FXML
    void initialize() {
        backToUserMenu.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(backToUserMenu,"../graphics/MainUserMenu.fxml");
        });

        addEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(addEmployee,"../graphics/AddEmployee.fxml");
        });

        viewEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(viewEmployee,"../graphics/ViewEmployeeUser.fxml");
        });

        editEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(editEmployee,"../graphics/EditEmployee.fxml");
        });

        deleteEmployee.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(deleteEmployee,"../graphics/DeleteEmployeeUser.fxml");
        });

        viewDiagram.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(viewDiagram,"../graphics/DiagramEmployee.fxml");
        });


    }
}
