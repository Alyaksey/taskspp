package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.EmptyStackException;

public class MainController {
    public TextField input;
    public TextField output;
    public TextField currentSystemText;
    public int previousSystem;
    public int currentSystem;
    public Button aButton;
    public Button bButton;
    public Button cButton;
    public Button dButton;
    public Button eButton;
    public Button fButton;
    public Button binaryButton;
    public Button octalButton;
    public Button decButton;
    public Button hexButton;
    public Button sinButton;
    public Button cosButton;
    public Button tanButton;
    public Button cotButton;
    public Button powButton;
    public Button sqrtButton;
    public Button dotButton;
    public Button separatorButton;
    public RadioButton commonRB;


    @FXML
    private void handleInput(javafx.event.ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText();
        String oldText = input.getText();
        StringBuilder newText = new StringBuilder(oldText);
        newText.append(buttonText);
        input.setText(newText.toString());
    }

    @FXML
    public void handleCalculate(ActionEvent actionEvent) {
        ExpressionParser exprParser = new ExpressionParser();
        StringBuilder expression = new StringBuilder(input.getText());
        if (commonRB.isSelected()) {
            try {
                output.setText(exprParser.calcExpression(expression));
            } catch (ArithmeticException e) {
                output.setText("Деление на ноль");
            } catch (EmptyStackException e) {
                output.setText("Выражение введено неправильно");
            }
        } else {
            try {
                output.setText(exprParser.calcExpression(expression, currentSystem));
            } catch (NumberFormatException e) {
                output.setText("Число введено в неправильной системе счисления");
            }
        }
    }

    @FXML
    public void handleBackSpace(ActionEvent actionEvent) {
        StringBuilder expression = new StringBuilder(input.getText());
        try {
            expression.deleteCharAt(expression.length() - 1);
        } catch (StringIndexOutOfBoundsException e) {
            input.clear();
        }
        input.setText(expression.toString());
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        input.clear();
        output.clear();
    }

    @FXML
    public void changedToCommon(ActionEvent actionEvent) {
        currentSystemText.clear();
        aButton.setDisable(true);
        bButton.setDisable(true);
        cButton.setDisable(true);
        dButton.setDisable(true);
        eButton.setDisable(true);
        fButton.setDisable(true);
        binaryButton.setDisable(true);
        octalButton.setDisable(true);
        decButton.setDisable(true);
        hexButton.setDisable(true);
        sinButton.setDisable(false);
        cosButton.setDisable(false);
        tanButton.setDisable(false);
        cotButton.setDisable(false);
        powButton.setDisable(false);
        sqrtButton.setDisable(false);
        dotButton.setDisable(false);
        separatorButton.setDisable(false);
    }

    @FXML
    public void changedToNumeral(ActionEvent actionEvent) {
        input.clear();
        output.clear();
        currentSystem = 10;
        currentSystemText.setText(Integer.toString(currentSystem));
        aButton.setDisable(false);
        bButton.setDisable(false);
        cButton.setDisable(false);
        dButton.setDisable(false);
        eButton.setDisable(false);
        fButton.setDisable(false);
        binaryButton.setDisable(false);
        octalButton.setDisable(false);
        decButton.setDisable(false);
        hexButton.setDisable(false);
        sinButton.setDisable(true);
        cosButton.setDisable(true);
        tanButton.setDisable(true);
        cotButton.setDisable(true);
        powButton.setDisable(true);
        sqrtButton.setDisable(true);
        dotButton.setDisable(true);
        separatorButton.setDisable(true);
    }

    @FXML
    public void changeSystem(ActionEvent event) {
        ExpressionParser exParcer = new ExpressionParser();
        previousSystem = currentSystem;
        currentSystem = Integer.parseInt(((Button) event.getSource()).getText());
        currentSystemText.setText(Integer.toString(currentSystem));
        String oldText = input.getText();
        input.setText(exParcer.convertExpression(oldText, previousSystem, currentSystem));
        oldText = output.getText();
        output.setText(exParcer.convertExpression(oldText, previousSystem, currentSystem));
    }
}
