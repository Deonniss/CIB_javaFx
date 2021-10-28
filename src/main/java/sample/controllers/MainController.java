package sample.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.algorithmDiffiHelman.excelExp.ExcelExport;
import logic.algorithmDiffiHelman.numberHandler.IntegerHandler;
import logic.algorithmDiffiHelman.propertyHandler.PropertyHandler;
import logic.algorithmFFS.CalculatorFFS;
import logic.algorithmFFS.entity.VectorLink;
import sample.services.DiffiHelmanService;
import sample.services.SKeyService;

import static javafx.scene.control.cell.TextFieldTableCell.*;

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
    private Button btnCopy;

    @FXML
    private Button btnFFS;

    @FXML
    private Button btnGenProb;

    @FXML
    private Button btnCalcFFS;

    @FXML
    private Button btnCalcFFSTable;

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
    private Pane paneFFS;

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
    private TextField textFieldP;

    @FXML
    private TextField textFieldQ;

    @FXML
    private TextField textFieldI;

    @FXML
    private TextField textFieldSizePQ;

    @FXML
    private TextField textFieldSizeSR;

    @FXML
    private ListView<String> listMD5Client;

    @FXML
    private Label labelStatusAuth;

    @FXML
    private Label labelStatusAuthFFS;

    @FXML
    private Label labelStatusPassword;

    @FXML
    private Label labelNFFS;

    @FXML
    private CheckBox checkHaveKey;

    @FXML
    private TableView<VectorLink> tableSR;

    @FXML
    private TableColumn<VectorLink, String> columnR;

    @FXML
    private TableColumn<VectorLink, String> columnS;

    @FXML
    private TableColumn<VectorLink, String> columnE;

    @FXML
    private TableColumn<VectorLink, String> columnV;

    @FXML
    private TableColumn<VectorLink, String> columnX;

    @FXML
    private TableColumn<VectorLink, String> columnXres;

    @FXML
    private TableColumn<VectorLink, String> columnYY;

    @FXML
    private TableColumn<VectorLink, String> columnY;

    private List<Pane> listActivity;

    private ObservableList<VectorLink> links;

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
        listActivity.add(paneFFS);
    }

    @FXML
    public void editColumnS(TableColumn.CellEditEvent event) {

        VectorLink vectorLink = tableSR.getSelectionModel().getSelectedItem();
        vectorLink.setS((String) event.getNewValue());
    }

    @FXML
    public void editColumnR(TableColumn.CellEditEvent event) {

        VectorLink vectorLink = tableSR.getSelectionModel().getSelectedItem();
        vectorLink.setR((String) event.getNewValue());
    }

    private ObservableList<VectorLink> getEmptyCollect(int n) {
        ObservableList<VectorLink> links = FXCollections.observableArrayList();

        for (int i = 0; i < n; i++) {

            if (IntegerHandler.checkInt(textFieldSizeSR.getText())) {
                links.add(new VectorLink(
                        IntegerHandler.generateProbablyPrime(Integer.parseInt(textFieldSizeSR.getText())),
                        IntegerHandler.generateProbablyPrime(Integer.parseInt(textFieldSizeSR.getText()))));
            }
        }

        return links;
    }

    @FXML
    public void eventCalcFFSTable(ActionEvent event) {

        Random random = new Random();

        for (int i = 0; i < links.size(); i++) {
            VectorLink v = new VectorLink();
            VectorLink current = links.get(i);

            v.setS(current.getS());
            v.setR(current.getR());

            v.setV(CalculatorFFS.calcV(v.getS()));
            v.setX(CalculatorFFS.calcX(v.getR()));
            v.setE(random.nextDouble() >= 0.5 ? "1" : "0");

            if (checkHaveKey.isSelected()) {
                v.setY(CalculatorFFS.calcY(v.getR(), v.getS(), v.getE()));
            } else {
                v.setY(CalculatorFFS.calcY(v.getR(), v.getS(), "0"));
            }

            v.setRes(CalculatorFFS.calcXRes(v.getX(), v.getV(), v.getE()));
            v.setYy(CalculatorFFS.calcYy(v.getY()));

            links.set(i, v);
        }
        boolean perm = true;
        for (VectorLink vectorLink : links) {
            if (!vectorLink.getYy().equals(vectorLink.getRes())) {
                perm = false;
                break;
            }
        }

        if (perm) {
            labelStatusAuthFFS.setText("Аутентификация пройдена!");
            labelStatusAuthFFS.setTextFill(Color.rgb(88, 150, 78));
        } else {
            labelStatusAuthFFS.setText("Аутентификация провалена!");
            labelStatusAuthFFS.setTextFill(Color.rgb(255, 107, 104));
        }

        tableSR.setItems(links);
    }

    @FXML
    public void eventGenProbablyPrime(ActionEvent event) {

        if (IntegerHandler.checkInt(textFieldSizePQ.getText())) {
            textFieldP.setText(IntegerHandler.generateProbablyPrime(Integer.parseInt(textFieldSizePQ.getText())));
            textFieldQ.setText(IntegerHandler.generateProbablyPrime(Integer.parseInt(textFieldSizePQ.getText())));
        }
    }

    @FXML
    public void eventExportExcel(ActionEvent event) {

        ExcelExport<VectorLink> excelExport = new ExcelExport<>();
        excelExport.export(tableSR);
    }


    @FXML
    public void eventCalcFFS(ActionEvent event) {

        if (IntegerHandler.checkBigInt(textFieldP.getText())
                && IntegerHandler.checkBigInt(textFieldQ.getText())
                && IntegerHandler.checkInt(textFieldI.getText())
                && IntegerHandler.checkSimpleBigInt((textFieldP.getText()))
                && IntegerHandler.checkSimpleBigInt((textFieldQ.getText()))) {

            BigInteger bi = new BigInteger(textFieldP.getText()).multiply(new BigInteger(textFieldQ.getText()));
            labelNFFS.setText(bi.toString());
            CalculatorFFS.setN(bi.toString());

            links = getEmptyCollect(Integer.parseInt(textFieldI.getText()));

            columnR.setCellValueFactory(new PropertyValueFactory<>("r"));
            columnS.setCellValueFactory(new PropertyValueFactory<>("s"));

            columnV.setCellValueFactory(new PropertyValueFactory<>("v"));
            columnX.setCellValueFactory(new PropertyValueFactory<>("x"));
            columnY.setCellValueFactory(new PropertyValueFactory<>("y"));
            columnE.setCellValueFactory(new PropertyValueFactory<>("e"));
            columnXres.setCellValueFactory(new PropertyValueFactory<>("res"));
            columnYY.setCellValueFactory(new PropertyValueFactory<>("yy"));


            tableSR.setItems(links);

            tableSR.setEditable(true);
            columnR.setEditable(true);
            columnS.setEditable(true);

            columnE.setEditable(true);
            columnV.setEditable(true);
            columnX.setEditable(true);
            columnXres.setEditable(true);
            columnY.setEditable(true);
            columnYY.setEditable(true);

            columnR.setCellFactory(TextFieldTableCell.forTableColumn());
            columnS.setCellFactory(TextFieldTableCell.forTableColumn());
        } else {
            System.out.println("error!");
        }
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

    @FXML
    public void eventCopy(ActionEvent event) {

        Clipboard clipboard = Clipboard.getSystemClipboard();

        ClipboardContent content = new ClipboardContent();

        List<String> hashes = SKeyService.getAllElements();
        if (hashes != null && hashes.size() != 0) {
            content.putString(hashes.get(hashes.size() - 1));
            clipboard.setContent(content);
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

        } else if (event.getSource() == btnFFS) {
            labelStatusAlgName.setText("Feig-Fiat-Shamir Protocol");
            listActivity.forEach(e -> e.setVisible(false));
            paneFFS.setVisible(true);

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