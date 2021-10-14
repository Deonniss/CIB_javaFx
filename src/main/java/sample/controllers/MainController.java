package sample.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.algorithmDiffiHelman.propertyHandler.PropertyHandler;
import sample.services.DiffiHelmanService;
import sample.services.SKeyService;

public class MainController implements Initializable {

    @FXML
    private Button btnDiffiHelman;

    @FXML
    private Button btnNextStep;

    @FXML
    private Button btnSKEY;

    @FXML
    private Button btnStartSKEY;

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
    private Label labelxn1;

    @FXML
    private Pane paneStatus;

    @FXML
    private Pane paneDiffiHelman;

    @FXML
    private Pane paneSKEY;

    @FXML
    private TextField textFieldNumberInputs;

    @FXML
    private TextField textFieldLastHash;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldADiffiHelman;

    @FXML
    private TextField textFieldB1DiffiHelman;

    @FXML
    private TextField textFieldB2DiffiHelman;

    @FXML
    private TextField textFieldCDiffiHelman;

    @FXML
    private ListView<String> listMD5Client;

    @FXML
    private Label labelStatusAuth;

    @FXML
    private Label labelStatusPassword;

    private List<Pane> listActivity;


    /**
     * Init метод
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listActivity = new ArrayList<>();
        listActivity.add(paneDiffiHelman);
        listActivity.add(paneSKEY);
    }

    @FXML
    public void eventStartSKey(ActionEvent event) {

        listMD5Client.getItems().clear();

        if (checkValidationStartSKey()) {
            String[] hashes = SKeyService.calculateHashes(textFieldPassword.getText(),
                    Integer.parseInt(textFieldNumberInputs.getText()));

            listMD5Client.getItems().addAll(hashes);

            labelStatusAuth.setVisible(false);
            labelStatusPassword.setVisible(false);
            labelxn1.setText(PropertyHandler.getProperty().getProperty("labelxn1")
                    + SKeyService.getActualValue());
        } else {
            labelStatusPassword.setVisible(true);
            labelStatusPassword.setTextFill(Color.rgb(255, 107, 104));
            new Alert(Alert.AlertType.INFORMATION,
                    PropertyHandler.getProperty().getProperty("informationAlertStartSKey")).showAndWait();
        }
    }

    @FXML
    public void eventNextStepSKey(ActionEvent event) {

        labelStatusAuth.setVisible(true);
        if (checkValidationNextStepSKey()) {
            labelStatusAuth.setText("authorization is successful!");
            labelStatusAuth.setTextFill(Color.rgb(88, 150, 78));

            List<String> currentList = new ArrayList<>(SKeyService.getAllElements());
            String actualValue = currentList.get(currentList.size() - 1);

            listMD5Client.getItems().clear();
            currentList.remove(currentList.size() - 1);

            if (currentList.size() == 0) {
                labelStatusPassword.setVisible(true);
            }

            listMD5Client.getItems().addAll(currentList);
            SKeyService.replaceActualValue(actualValue);
            SKeyService.setAllElements(currentList);
            textFieldLastHash.setText("");

            labelxn1.setText(PropertyHandler.getProperty().getProperty("labelxn1")
                    + SKeyService.getActualValue());

        } else {
            labelStatusAuth.setText("invalid password!");
            labelStatusAuth.setTextFill(Color.rgb(255, 107, 104));
        }
    }

    /**
     * Проверяет правильность введенных значений ДХ
     *
     * @return
     */
    private boolean checkValidationStartSKey() {
        return SKeyService.checkValidationInteger(textFieldNumberInputs.getText());
    }

    /**
     * Проверяет правильность введенных значений ДХ
     *
     * @return
     */
    private boolean checkValidationNextStepSKey() {
        return SKeyService.checkValidationEquals(textFieldLastHash.getText());
    }


    /**
     * переключение окон
     *
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


        } else if (event.getSource() == btnSKEY) {
            labelStatusAlgName.setText("S/KEY");
            listActivity.forEach(e -> e.setVisible(false));
            paneSKEY.setVisible(true);
        }
    }

    /**
     * Закрытие приложения
     */
    public void close() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Исполнение ДХ
     *
     * @param event
     */
    @FXML
    public void eventDiffiHelman(ActionEvent event) {

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
            new Alert(Alert.AlertType.INFORMATION,
                    PropertyHandler.getProperty().getProperty("informationAlertDiffiHelman")).showAndWait();
        }
    }

    /**
     * Проверяет правильность введенных значений ДХ
     *
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