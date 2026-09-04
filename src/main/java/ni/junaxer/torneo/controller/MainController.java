package ni.junaxer.torneo.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.junaxer.torneo.model.Participante;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // Controlador del formulario incluido (inyectado por JavaFX vía fx:include fx:id="formulario")
    @FXML
    private FormularioController formularioController;

    @FXML
    private TableView<Participante> tablaParticipantes;

    @FXML
    private TableColumn<Participante, String> colNombre;

    @FXML
    private TableColumn<Participante, Integer> colEdad;

    @FXML
    private TableColumn<Participante, String> colCategoria;

    @FXML
    private TableColumn<Participante, String> colModalidad;

    @FXML
    private TableColumn<Participante, String> colDisciplina;

    @FXML
    private TableColumn<Participante, String> colCaracteristicas;

    @FXML
    private TableColumn<Participante, String> colEstado;

    @FXML
    private Label lblTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar cada columna con su atributo del modelo
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Asignar la lista compartida del formulario a la tabla
        tablaParticipantes.setItems(formularioController.getListaParticipantes());

        // Listener para actualizar el contador cada vez que la lista cambia
        formularioController.getListaParticipantes().addListener((ListChangeListener<Participante>) change -> {
            actualizarContador();
        });

        actualizarContador();
    }

    private void actualizarContador() {
        int total = tablaParticipantes.getItems().size();
        lblTotal.setText("Total de inscritos: " + total);
    }


}
