package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.windows.NewWindowOpen;

public class InfoAboutModelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        back.setOnAction(e -> {
            NewWindowOpen.newWindowOpen.openWindow(back,"../graphics/MainUserMenu.fxml");
        });
    }
}
