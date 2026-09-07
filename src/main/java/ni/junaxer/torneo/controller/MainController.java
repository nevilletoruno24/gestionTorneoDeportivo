package ni.junaxer.torneo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import ni.junaxer.torneo.utils.AlertaUtil;

public class MainController {

    @FXML
    private ToolBar toolBar;

    @FXML
    private ToolBarController toolBarController;

    @FXML
    private VBox formulario;

    @FXML
    private FormularioController formularioController;

    @FXML
    private VBox tabla;

    @FXML
    private TablaController tablaController;

    @FXML
    public void initialize() {
        tablaController.init(
                formularioController.getListaParticipantes(),
                participante -> formularioController.cargarParaEditar(participante),
                participante -> formularioController.eliminarParticipante(participante)
        );

        formularioController.setOnReset(() -> tablaController.clearSelection());

        toolBarController.init(
                () -> formularioController.onLimpiar(),
                () -> tablaController.confirmarTodos(),
                () -> {
                    if (AlertaUtil.confirmar("¿Desea eliminar todos los participantes del torneo?")) {
                        formularioController.getListaParticipantes().clear();
                        formularioController.onLimpiar();
                    }
                }
        );
    }
}
