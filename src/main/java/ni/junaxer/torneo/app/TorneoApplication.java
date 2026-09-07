package ni.junaxer.torneo.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TorneoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TorneoApplication.class.getResource("/ni/junaxer/torneo/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 680);
        stage.setTitle("Sistema de Gestión de Torneo Deportivo");
        stage.setScene(scene);
        stage.show();
    }
}
