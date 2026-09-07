package ni.junaxer.torneo.controller;

import javafx.fxml.FXML;

public class ToolBarController {

    private Runnable onNuevo;
    private Runnable onConfirmar;
    private Runnable onVaciar;

    public void init(Runnable onNuevo, Runnable onConfirmar, Runnable onVaciar) {
        this.onNuevo = onNuevo;
        this.onConfirmar = onConfirmar;
        this.onVaciar = onVaciar;
    }

    @FXML
    private void onNuevoRegistro() {
        if (onNuevo != null) onNuevo.run();
    }

    @FXML
    private void onConfirmarTodos() {
        if (onConfirmar != null) onConfirmar.run();
    }

    @FXML
    private void onVaciarTabla() {
        if (onVaciar != null) onVaciar.run();
    }
}
