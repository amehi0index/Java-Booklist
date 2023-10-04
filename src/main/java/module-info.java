module org.openjfx.Tablefx {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;

    opens org.openjfx.Tablefx to javafx.fxml;
    exports org.openjfx.Tablefx;
}
