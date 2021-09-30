module Diffi.Helman {

    requires javafx.fxml;
    requires javafx.controls;

    exports sample.controllers to javafx.fxml;

    opens sample.controllers;
    opens sample;
}