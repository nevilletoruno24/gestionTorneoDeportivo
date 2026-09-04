module ni.junaxer.torneo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.junaxer.torneo to javafx.fxml;
    exports ni.junaxer.torneo;
}