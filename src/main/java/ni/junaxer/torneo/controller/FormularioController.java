package ni.junaxer.torneo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ni.junaxer.torneo.components.CustomComboBox;
import ni.junaxer.torneo.components.CustomInput;
import ni.junaxer.torneo.model.Participante;
import ni.junaxer.torneo.utils.AlertaUtil;

import java.util.ArrayList;
import java.util.List;

public class FormularioController {

    @FXML
    private Label lblTitulo;

    @FXML
    private CustomInput txtNombre;

    @FXML
    private CustomInput txtEdad;

    @FXML
    private CustomInput txtTelefono;

    @FXML
    private CustomComboBox cbCategoria;

    @FXML
    private CustomComboBox cbGenero;

    @FXML
    private CustomComboBox cbModalidad;

    @FXML
    private ListView<String> lvDisciplina;

    @FXML
    private CheckBox chkFederado;

    @FXML
    private CheckBox chkExperiencia;

    @FXML
    private CheckBox chkDisponibilidad;

    @FXML
    private CheckBox chkSeguro;

    @FXML
    private Button btnRegistrar;

    private final ObservableList<Participante> listaParticipantes = FXCollections.observableArrayList();
    private Participante participanteEditando = null;
    private Runnable onReset;

    @FXML
    public void initialize() {
        cbCategoria.getItems().addAll("Juvenil", "Intermedia", "Senior");
        cbGenero.getItems().addAll("Masculino", "Femenino", "Otro");
        cbModalidad.getItems().addAll("Individual", "Parejas", "Equipos");
        lvDisciplina.getItems().addAll("Fútbol", "Baloncesto", "Voleibol", "Atletismo", "Natación", "Tenis");
    }

    public ObservableList<Participante> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setOnReset(Runnable onReset) {
        this.onReset = onReset;
    }

    public void cargarParaEditar(Participante participante) {
        if (participante == null) return;
        this.participanteEditando = participante;

        lblTitulo.setText("Modo Edición");
        btnRegistrar.setText("Guardar Cambios");

        txtNombre.setText(participante.getNombre());
        txtEdad.setText(String.valueOf(participante.getEdad()));
        txtTelefono.setText(participante.getTelefono());
        cbCategoria.setValue(participante.getCategoria());
        cbGenero.setValue(participante.getGenero());
        cbModalidad.setValue(participante.getModalidad());
        lvDisciplina.getSelectionModel().select(participante.getDisciplina());

        String carac = participante.getCaracteristicas();
        chkFederado.setSelected(carac != null && carac.contains("Federado"));
        chkExperiencia.setSelected(carac != null && carac.contains("Experiencia previa"));
        chkDisponibilidad.setSelected(carac != null && carac.contains("Disponibilidad fines de semana"));
        chkSeguro.setSelected(carac != null && carac.contains("Seguro deportivo"));
    }

    public void eliminarParticipante(Participante participante) {
        listaParticipantes.remove(participante);
        if (participanteEditando == participante) {
            onLimpiar();
        }
    }

    @FXML
    private void onRegistrar() {
        String nombre = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
        String edadTexto = txtEdad.getText() != null ? txtEdad.getText().trim() : "";
        String telefono = txtTelefono.getText() != null ? txtTelefono.getText().trim() : "";
        String categoria = cbCategoria.getValue();
        String genero = cbGenero.getValue();
        String modalidad = cbModalidad.getValue();
        String disciplina = lvDisciplina.getSelectionModel().getSelectedItem();

        if (nombre.isEmpty()) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "El nombre es obligatorio.");
            return;
        }

        if (nombre.length() < 5) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "El nombre debe tener mínimo 5 caracteres.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "La edad debe ser un número entero.");
            return;
        }

        if (edad < 15 || edad > 60) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "La edad debe estar entre 15 y 60 años.");
            return;
        }

        if (telefono.isEmpty() || !telefono.matches("\\d+")) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "El teléfono solamente debe aceptar números.");
            return;
        }

        if (telefono.length() != 8) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "El teléfono debe tener exactamente 8 dígitos.");
            return;
        }

        if (categoria == null) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe seleccionarse una categoría.");
            return;
        }

        if (modalidad == null) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe seleccionarse una modalidad.");
            return;
        }

        if (disciplina == null) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe seleccionarse una disciplina.");
            return;
        }

        List<String> caracteristicas = new ArrayList<>();
        if (chkFederado.isSelected()) caracteristicas.add("Federado");
        if (chkExperiencia.isSelected()) caracteristicas.add("Experiencia previa");
        if (chkDisponibilidad.isSelected()) caracteristicas.add("Disponibilidad fines de semana");
        if (chkSeguro.isSelected()) caracteristicas.add("Seguro deportivo");

        String caracteristicasTexto = caracteristicas.isEmpty() ? "Ninguna" : String.join(", ", caracteristicas);

        Participante participante = new Participante(
                nombre,
                edad,
                telefono,
                categoria,
                genero != null ? genero : "No especificado",
                modalidad,
                disciplina,
                caracteristicasTexto,
                "Inscrito"
        );

        if (participanteEditando == null) {
            listaParticipantes.add(participante);
            AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Información", "El participante ha sido registrado correctamente.");
        } else {
            int index = listaParticipantes.indexOf(participanteEditando);
            if (index >= 0) {
                listaParticipantes.set(index, participante);
            }
            AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Información", "Los datos del participante han sido actualizados.");
        }

        onLimpiar();
    }

    @FXML
    public void onLimpiar() {
        participanteEditando = null;
        lblTitulo.setText("Registro de Participantes");
        btnRegistrar.setText("Registrar");

        txtNombre.clear();
        txtEdad.clear();
        txtTelefono.clear();
        cbCategoria.clearSelection();
        cbGenero.clearSelection();
        cbModalidad.clearSelection();
        lvDisciplina.getSelectionModel().clearSelection();
        chkFederado.setSelected(false);
        chkExperiencia.setSelected(false);
        chkDisponibilidad.setSelected(false);
        chkSeguro.setSelected(false);

        if (onReset != null) {
            onReset.run();
        }

        txtNombre.requestFocus();
    }
}
