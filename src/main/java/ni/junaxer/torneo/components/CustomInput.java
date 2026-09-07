package ni.junaxer.torneo.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

@SuppressWarnings("unused")
public class CustomInput extends VBox {

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtInput;

    public CustomInput() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom-input.fxml"));
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
        return txtInput.getPromptText();
    }

    public void setPromptText(String prompt) {
        txtInput.setPromptText(prompt);
    }

    public String getText() {
        return txtInput.getText();
    }

    public void setText(String text) {
        txtInput.setText(text);
    }

    public void clear() {
        txtInput.clear();
    }

    public void requestFocus() {
        txtInput.requestFocus();
    }
}
