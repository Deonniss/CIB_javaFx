package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.diffiHelman.DiffiHelman;
import logic.numberHandler.IntegerHandler;
import logic.propertyHandler.PropertyHandler;
import sample.services.DiffiHelmanService;

public class MainController implements Initializable {

    @FXML
    private Button btnDiffiHelman;

    @FXML
    private Button btnPrac3;

    @FXML
    private Button buttonDiffiHelman;

    @FXML
    private Label labelCalcKey1DiffiHelman;

    @FXML
    private Label labelCalcKey2DiffiHelman;

    @FXML
    private Label labelCalcX1DiffiHelman;

    @FXML
    private Label labelCalcX2DiffiHelman;

    @FXML
    private Label labelGetX1DiffiHelman;

    @FXML
    private Label labelGetX2DiffiHelman;

    @FXML
    private Label labelStatusAlgName;

    @FXML
    private Pane paneStatus;

    @FXML
    private Pane paneDiffiHelman;

    @FXML
    private TextField textFieldADiffiHelman;

    @FXML
    private TextField textFieldB1DiffiHelman;

    @FXML
    private TextField textFieldB2DiffiHelman;

    @FXML
    private TextField textFieldCDiffiHelman;

    private List<Pane> listActivity;


    /**
     * Init метод
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listActivity = new ArrayList<>();
        listActivity.add(paneDiffiHelman);
    }

    /**
     * переключение окон
     * @param event
     */
    @FXML
    private void handleClicks(ActionEvent event) {

        if (event.getSource() == btnDiffiHelman) {

            paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(19, 66, 118),
                    CornerRadii.EMPTY, Insets.EMPTY)));
            labelStatusAlgName.setText("Diffie-Hellman algorithm");
            listActivity.forEach(e -> e.setVisible(false));
            paneDiffiHelman.setVisible(true);


        } else if (event.getSource() == btnPrac3) {
            labelStatusAlgName.setText("3 Practies");
            listActivity.forEach(e -> e.setVisible(false));
        }
    }


    /**
     * Исполнение ДХ
     * @param event
     */
    @FXML
    void eventDiffiHelman(ActionEvent event) {

        if (checkValidationDiffiHelman()) {

            int x1 = DiffiHelmanService.modularDivision(textFieldADiffiHelman.getText(),
                    textFieldCDiffiHelman.getText(), textFieldB1DiffiHelman.getText());
            int x2 = DiffiHelmanService.modularDivision(textFieldADiffiHelman.getText(),
                    textFieldCDiffiHelman.getText(), textFieldB2DiffiHelman.getText());
            int key1 = DiffiHelmanService.modularDivision(Integer.toString(x2),
                    textFieldCDiffiHelman.getText(), textFieldB1DiffiHelman.getText());
            int key2 = DiffiHelmanService.modularDivision(Integer.toString(x1),
                    textFieldCDiffiHelman.getText(), textFieldB2DiffiHelman.getText());

            labelCalcX1DiffiHelman.setText(PropertyHandler.getProperty().getProperty("labelCalcX1") + x1);

            labelCalcX2DiffiHelman.setText(PropertyHandler.getProperty().getProperty("labelCalcX2") + x2);

            labelGetX1DiffiHelman.setText(PropertyHandler.getProperty().getProperty("labelGetX1") + x1);

            labelGetX2DiffiHelman.setText(PropertyHandler.getProperty().getProperty("labelGetX2") + x2);

            labelCalcKey2DiffiHelman.setText(PropertyHandler.getProperty().getProperty("labelCalcKey2") + key2);

            labelCalcKey1DiffiHelman.setText(PropertyHandler.getProperty().getProperty("labelCalcKey1") + key1);
        } else {
            new Alert(Alert.AlertType.INFORMATION, PropertyHandler.getProperty().getProperty("informationAlert")).showAndWait();
        }
    }

    /**
     * Проверяет правильность введенных значений ДХ
     * @return
     */
    private boolean checkValidationDiffiHelman() {
        return DiffiHelmanService.checkValidationInteger(textFieldADiffiHelman.getText())
                && DiffiHelmanService.checkValidationInteger(textFieldCDiffiHelman.getText())
                && DiffiHelmanService.checkValidationSimple(textFieldCDiffiHelman.getText())
                && DiffiHelmanService.checkValidationInteger(textFieldB1DiffiHelman.getText())
                && DiffiHelmanService.checkValidationInteger(textFieldB2DiffiHelman.getText());
    }
}