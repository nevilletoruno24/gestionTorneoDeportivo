package ni.junaxer.torneo.components;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

@SuppressWarnings("unused")
public class CustomComboBox extends VBox {

    @FXML
    private Label lblTitle;

    @FXML
    private ComboBox<String> cbInput;

    public CustomComboBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom-combobox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    public String getPromptText() {
        return cbInput.getPromptText();
    }

    public void setPromptText(String prompt) {
        cbInput.setPromptText(prompt);
    }

    public String getValue() {
        return cbInput.getValue();
    }

    public void setValue(String value) {
        cbInput.setValue(value);
    }

    public ObservableList<String> getItems() {
        return cbInput.getItems();
    }

    public void clearSelection() {
        cbInput.getSelectionModel().clearSelection();
    }
}
