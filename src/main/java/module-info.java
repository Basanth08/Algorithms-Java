module unit08 {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.media;
    requires java.desktop;

    opens unit08.queens.view to javafx.fxml;
    exports unit08;
    exports unit08.weighted;
    exports unit08.queens.view;
    exports unit08.queens.model;
    exports unit08.backtracker;
    exports unit08.knapsack;
}
