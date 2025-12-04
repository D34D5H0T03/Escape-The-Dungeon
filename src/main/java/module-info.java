module com.myproject.escapethedungeon {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.myproject.escapethedungeon to javafx.fxml;
    exports com.myproject.escapethedungeon;
}