package ni.junaxer.torneo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ni.junaxer.torneo.model.Participante;
import ni.junaxer.torneo.utils.AlertaUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FormularioController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private ComboBox<String> cbGenero;

    @FXML
    private ComboBox<String> cbModalidad;

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

    private ObservableList<Participante> listaParticipantes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCategoria.getItems().addAll("Juvenil", "Intermedia", "Senior");
        cbGenero.getItems().addAll("Masculino", "Femenino", "Otro");
        cbModalidad.getItems().addAll("Individual", "Parejas", "Equipos");
        lvDisciplina.getItems().addAll("Fútbol", "Baloncesto", "Voleibol", "Atletismo", "Natación", "Tenis");
    }

    public ObservableList<Participante> getListaParticipantes() {
        return listaParticipantes;
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
            AlertaUtil.mostrarError("El nombre es obligatorio.");
            return;
        }

        if (nombre.length() < 5) {
            AlertaUtil.mostrarError("El nombre debe tener mínimo 5 caracteres.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            AlertaUtil.mostrarError("La edad debe ser un número entero.");
            return;
        }

        if (edad < 15 || edad > 60) {
            AlertaUtil.mostrarError("La edad debe estar entre 15 y 60 años.");
            return;
        }

        if (telefono.isEmpty() || !telefono.matches("\\d+")) {
            AlertaUtil.mostrarError("El teléfono solamente debe aceptar números.");
            return;
        }

        if (telefono.length() < 8) {
            AlertaUtil.mostrarError("El teléfono debe tener 8 dígitos. Ingresó menos de 8.");
            return;
        }

        if (telefono.length() > 8) {
            AlertaUtil.mostrarError("El teléfono debe tener 8 dígitos. Ingresó más de 8.");
            return;
        }

        if (categoria == null) {
            AlertaUtil.mostrarError("Debe seleccionarse una categoría.");
            return;
        }

        if (modalidad == null) {
            AlertaUtil.mostrarError("Debe seleccionarse una modalidad.");
            return;
        }

        if (disciplina == null) {
            AlertaUtil.mostrarError("Debe seleccionarse una disciplina.");
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

        listaParticipantes.add(participante);

        AlertaUtil.mostrarInformacion("El participante ha sido registrado correctamente.");
        onLimpiar();
    }

    @FXML
    private void onLimpiar() {
        txtNombre.clear();
        txtEdad.clear();
        txtTelefono.clear();
        cbCategoria.getSelectionModel().clearSelection();
        cbGenero.getSelectionModel().clearSelection();
        cbModalidad.getSelectionModel().clearSelection();
        lvDisciplina.getSelectionModel().clearSelection();
        chkFederado.setSelected(false);
        chkExperiencia.setSelected(false);
        chkDisponibilidad.setSelected(false);
        chkSeguro.setSelected(false);
        txtNombre.requestFocus();
    }
}
