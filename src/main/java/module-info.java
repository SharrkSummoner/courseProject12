module com.example.courseproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;


    opens com.example.courseproject to javafx.fxml;
    exports com.example.courseproject;
}