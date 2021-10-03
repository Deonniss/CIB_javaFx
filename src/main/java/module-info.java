module Diffi.Helman {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    exports sample.controllers to javafx.fxml;

    opens sample.controllers;
    opens sample;
}