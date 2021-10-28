module Diffi.Helman {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires poi;

    exports sample.controllers to javafx.fxml;

    opens logic.algorithmFFS.entity to javafx.base;
    opens sample.controllers;
    opens sample;
}