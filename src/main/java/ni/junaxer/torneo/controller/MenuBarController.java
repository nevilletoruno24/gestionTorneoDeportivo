package ni.junaxer.torneo.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import ni.junaxer.torneo.utils.AlertaUtil;

public class MenuBarController {

    @FXML
    private void onSalir() {
        Platform.exit();
    }

    @FXML
    private void onAcercaDe() {
        AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Acerca de", "Sistema de Gestión de Torneo Deportivo v1.0");
    }
}
