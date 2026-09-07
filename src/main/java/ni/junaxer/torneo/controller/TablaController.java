package ni.junaxer.torneo.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.junaxer.torneo.model.Participante;
import ni.junaxer.torneo.utils.AlertaUtil;

import java.util.function.Consumer;

public class TablaController {

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

    private Consumer<Participante> onEliminarCallback;

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
    }

    public void init(ObservableList<Participante> items, Consumer<Participante> onSelect, Consumer<Participante> onEliminar) {
        this.onEliminarCallback = onEliminar;
        tablaParticipantes.setItems(items);

        items.addListener((ListChangeListener<Participante>) change -> actualizarContador());

        tablaParticipantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null && onSelect != null) {
                onSelect.accept(newSel);
            }
        });

        actualizarContador();
    }

    public void clearSelection() {
        tablaParticipantes.getSelectionModel().clearSelection();
    }

    public void confirmarTodos() {
        if (tablaParticipantes.getItems().isEmpty()) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "No hay participantes para confirmar.");
            return;
        }
        for (Participante p : tablaParticipantes.getItems()) {
            p.setEstado("Confirmado");
        }
        tablaParticipantes.refresh();
        AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Confirmación", "Todos los participantes han sido confirmados.");
    }

    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEliminar = new Button("Eliminar");

            {
                btnEliminar.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 4 10; -fx-background-radius: 4;");
                btnEliminar.setOnAction(event -> {
                    Participante p = getTableView().getItems().get(getIndex());
                    if (AlertaUtil.confirmar("¿Está seguro de eliminar al participante " + p.getNombre() + "?")) {
                        if (onEliminarCallback != null) {
                            onEliminarCallback.accept(p);
                        }
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

    @FXML
    private void onVerInformacion() {
        Participante p = tablaParticipantes.getSelectionModel().getSelectedItem();
        if (p == null) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "Seleccione un participante en la tabla.");
            return;
        }
        String info = "Nombre: " + p.getNombre() + "\n"
                + "Edad: " + p.getEdad() + " años\n"
                + "Teléfono: " + p.getTelefono() + "\n"
                + "Categoría: " + p.getCategoria() + "\n"
                + "Modalidad: " + p.getModalidad() + "\n"
                + "Disciplina: " + p.getDisciplina() + "\n"
                + "Características: " + p.getCaracteristicas() + "\n"
                + "Estado: " + p.getEstado();
        AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Ficha del Participante", info);
    }
}
