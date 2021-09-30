package sample.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.diffiHelman.DiffiHelman;

public class Controller {

    private int x1, x2, key1, key2;

    private static Properties property = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("resources/beginTitle/title.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label labelCalcX1;

    @FXML
    private Button button;

    @FXML
    private Label labelGetX2;

    @FXML
    private Label labelCalcKey1;

    @FXML
    private Label labelCalcX2;

    @FXML
    private Label labelGetX1;

    @FXML
    private Label labelCalcKey2;

    @FXML
    private TextField textFieldA;

    @FXML
    private TextField textFieldC;

    @FXML
    private TextField textFieldB1;

    @FXML
    private TextField textFieldB2;

    @FXML
    void initialize() {
        clickButton();
    }


    private void clickButton() {

        button.setOnAction(event -> {

            if (checkValidation()) {

                x1 = DiffiHelman.modularDivision(textFieldA.getText(),
                        textFieldC.getText(), textFieldB1.getText());
                x2 = DiffiHelman.modularDivision(textFieldA.getText(),
                        textFieldC.getText(), textFieldB2.getText());
                key1 = DiffiHelman.modularDivision(Integer.toString(x2),
                        textFieldC.getText(), textFieldB1.getText());
                key2 = DiffiHelman.modularDivision(Integer.toString(x1),
                        textFieldC.getText(), textFieldB2.getText());

                labelCalcX1.setText(property.getProperty("labelCalcX1") + x1);

                labelCalcX2.setText(property.getProperty("labelCalcX2") + x2);

                labelGetX1.setText(property.getProperty("labelGetX1") + x1);

                labelGetX2.setText(property.getProperty("labelGetX2") + x2);

                labelCalcKey2.setText(property.getProperty("labelCalcKey2") + key2);

                labelCalcKey1.setText(property.getProperty("labelCalcKey1") + key1);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "This is an error!").showAndWait();
            }
        });
    }

    private boolean checkValidation() {
        return checkInt(textFieldA.getText())
                && checkInt(textFieldC.getText())
                && checkSimple(textFieldC.getText())
                && checkInt(textFieldB1.getText())
                && checkInt(textFieldB2.getText());
    }

    private boolean checkInt(String str) {
        try {
            return Integer.parseInt(str) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkSimple(String str) {
        try {
            BigInteger a = new BigInteger(str);
            return a.isProbablePrime((int) Math.log(Integer.parseInt(str)));
        } catch (NullPointerException e) {
            return false;
        }
    }
}