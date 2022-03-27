module GameOfLife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.gameoflife.gui to javafx.fxml;
    exports com.example.gameoflife.gui;
    opens com.example.gameoflife.core to javafx.fxml;
    exports com.example.gameoflife.core;
}