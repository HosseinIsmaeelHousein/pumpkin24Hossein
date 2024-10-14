module org.example.pumpkin {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pumpkin to javafx.fxml;
    exports org.example.pumpkin;
}