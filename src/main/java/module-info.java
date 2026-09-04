module ni.junaxer.torneo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.junaxer.torneo to javafx.fxml;
    opens ni.junaxer.torneo.controller to javafx.fxml;
    opens ni.junaxer.torneo.model to javafx.base;

    exports ni.junaxer.torneo;
    exports ni.junaxer.torneo.controller;
    exports ni.junaxer.torneo.model;
    exports ni.junaxer.torneo.utils;
}