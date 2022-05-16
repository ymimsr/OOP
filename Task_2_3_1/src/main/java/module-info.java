module ru.nsu.fit.oop.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens ru.nsu.fit.oop.task_2_3_1 to javafx.fxml;
    exports ru.nsu.fit.oop.task_2_3_1;
    exports ru.nsu.fit.oop.task_2_3_1.controller;
    exports ru.nsu.fit.oop.task_2_3_1.model.field;
    exports ru.nsu.fit.oop.task_2_3_1.model;
    exports ru.nsu.fit.oop.task_2_3_1.view;
    opens ru.nsu.fit.oop.task_2_3_1.controller to javafx.fxml;
    opens ru.nsu.fit.oop.task_2_3_1.model.field to javafx.fxml;
    opens ru.nsu.fit.oop.task_2_3_1.model to javafx.fxml;
    opens ru.nsu.fit.oop.task_2_3_1.view to javafx.fxml;
}