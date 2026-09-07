package ni.junaxer.torneo.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.junaxer.torneo.model.Participante;
import ni.junaxer.torneo.utils.AlertaUtil;

public class MainController {


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
    private TableColumn<Participante, Void> colAcciones;

    @FXML
    private Label lblTotal;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colCaracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        configurarColumnaAcciones();

        tablaParticipantes.setItems(formularioController.getListaParticipantes());

        formularioController.getListaParticipantes().addListener((ListChangeListener<Participante>) change -> actualizarContador());

        formularioController.setOnReset(() -> tablaParticipantes.getSelectionModel().clearSelection());

        tablaParticipantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                formularioController.cargarParaEditar(newSelection);
            }
        });

        actualizarContador();
    }

    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEliminar = new Button("Eliminar");

            {
                btnEliminar.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 4 10; -fx-background-radius: 4;");
                btnEliminar.setOnAction(event -> {
                    Participante participante = getTableView().getItems().get(getIndex());
                    if (AlertaUtil.confirmar("¿Está seguro de eliminar al participante " + participante.getNombre() + "?")) {
                        formularioController.eliminarParticipante(participante);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnEliminar);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }

    private void actualizarContador() {
        int total = tablaParticipantes.getItems().size();
        lblTotal.setText("Total de inscritos: " + total);
    }
}
