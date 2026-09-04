package ni.junaxer.torneo.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertaUtil {

    public static void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void mostrarError(String mensaje) {
        mostrar(Alert.AlertType.ERROR, "Error", mensaje);
    }

    public static void mostrarInformacion(String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, "Información", mensaje);
    }

}
