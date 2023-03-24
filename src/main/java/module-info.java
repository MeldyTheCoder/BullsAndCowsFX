module ru.bullsandcows.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.bullsandcows.demo to javafx.fxml;
    exports ru.bullsandcows.demo;
}