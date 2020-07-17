package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.StyledEditorKit;
import java.awt.*;


public class Components {
    //all the useful components created in this class using static methods , for avoid code duplication
    //method for creating buttons
    public static Button creatingButton(String txtOnBtn, Double x, Double y, Double prefHeight, Double setPrefWidth) {
        Button btn = new Button(txtOnBtn);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefHeight(prefHeight);
        btn.setPrefWidth(setPrefWidth);
        btn.setStyle("-fx-background-color:transparent; -fx-border-color:#F0F8FF;-fx-text-fill:#f5f7f7;-fx-font-size:2em;-fx-border-radius:20");
        return btn;
    }

    //method for creating labels
    public static Label creatingLabel(String txtOnLbl, Double x, Double y, Double prefHeight, Double PrefWidth) {
        Label lbl = new Label(txtOnLbl);
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        lbl.setPrefHeight(prefHeight);
        lbl.setPrefWidth(PrefWidth);
        lbl.setStyle("-fx-text-fill:#ffffff");
        lbl.setStyle("-fx-background-color:transparent;-fx-text-fill:#f5f7f7;-fx-font-size:1em;-fx-font-weight: Bold;");
        return lbl;

    }

    //creating label for get dark appearence in all windows
    public static Label creatingLabelForBackground() {
        Label lblBG = new Label();
        lblBG.setLayoutX(0);
        lblBG.setLayoutY(0);
        lblBG.setPrefHeight(500);
        lblBG.setPrefWidth(700);
        lblBG.setStyle("-fx-background-color: #000000;");
        lblBG.setOpacity(0.81);
        return lblBG;
    }

    //method for creating text fields
    public static TextField creatingTextField(String promptTxt, Double x, Double y, Double prefHeight, Double PrefWidth) {
        TextField txtField = new TextField();
        txtField.setPromptText(promptTxt);
        txtField.setLayoutX(x);
        txtField.setLayoutY(y);
        txtField.setPrefWidth(PrefWidth);
        txtField.setPrefHeight(prefHeight);
        txtField.setStyle("-fx-background-color:transparent; -fx-border-color:#B0C4DE;-fx-text-fill:#f5f7f7;-fx-font-size:1em;-fx-border-radius:10");
        return txtField;
    }

    //method for create back button, back button appears in most of the windows and this will avoid code duplication
    public static Button createBtnBack() {
        Image imageBack = new Image("Pics/back.png");
        ImageView backPng = new ImageView();
        backPng.setImage(imageBack);
        backPng.setFitWidth(30);
        backPng.setFitHeight(30);

        Button btnBack = new Button();
        btnBack.setLayoutX(0);
        btnBack.setLayoutY(0);
        btnBack.setPrefHeight(20);
        btnBack.setPrefWidth(30);
        btnBack.setGraphic(backPng);
        btnBack.setStyle("-fx-background-color:transparent; -fx-background-radius:100;");
        btnBack.setOpacity(0.81);
        return btnBack;
    }

    //method for create close button, close button appears in all windows and this will avoid code duplication
    public static Button createBtnClose() {
        Image imageClose = new Image("Pics/Close.png");
        ImageView closePng = new ImageView();
        closePng.setImage(imageClose);
        closePng.setFitWidth(30);
        closePng.setFitHeight(29);

        Button btnClose = new Button();
        btnClose.setLayoutX(560);
        btnClose.setLayoutY(0);
        btnClose.setPrefHeight(19);
        btnClose.setPrefWidth(30);
        btnClose.setGraphic(closePng);
        btnClose.setStyle("-fx-background-color:transparent; -fx-background-radius:100;");
        btnClose.setOpacity(0.81);
        return btnClose;
    }

    //method for creating help button
    public static Button createBtnHelp() {
        Image imageHelp = new Image("Pics/help.png");
        ImageView helpPng = new ImageView();
        helpPng.setImage(imageHelp);
        helpPng.setFitWidth(40);
        helpPng.setFitHeight(40);

        Button btnClose = new Button();
        btnClose.setLayoutX(2);
        btnClose.setLayoutY(350);
        btnClose.setPrefHeight(19);
        btnClose.setPrefWidth(30);
        btnClose.setGraphic(helpPng);
        btnClose.setStyle("-fx-background-color:transparent; -fx-background-radius:100;");
        btnClose.setOpacity(0.81);
        return btnClose;
    }

    //method for keyboard keys, all keyboard keys wii created in this method
    public static Button btnForKeyBoard(String txtOnBtn, Double x, Double y, Double prefHeight, Double setPrefWidth) {
        Button key = new Button(txtOnBtn);
        key.setLayoutX(x);
        key.setLayoutY(y);
        key.setPrefHeight(prefHeight);
        key.setPrefWidth(setPrefWidth);
        key.setStyle("-fx-background-color:transparent;-fx-border-color:#66CDAA;-fx-text-fill:#f5f7f7;-fx-font-size:2em;-fx-border-radius:20;");
        return key;

    }

    //method for keyboard, this method can be called with (x,y) values
    public static AnchorPane keyBoard(Double x, Double y) {
        //setting anchor pain and creating buttons and add all into that created anchor pain
        AnchorPane keyBoardPane = new AnchorPane();
        keyBoardPane.setStyle("-fx-border-color: green; -fx-border-width: 0px 0px 0px 1px");
        keyBoardPane.setPrefWidth(234);
        keyBoardPane.setPrefHeight(303);
        keyBoardPane.setLayoutX(x);
        keyBoardPane.setLayoutY(y);
        Button key0 = btnForKeyBoard("0", 36., 210., 46., 89.);
        Button key1 = btnForKeyBoard("1", 35., 164., 46., 45.);
        Button key2 = btnForKeyBoard("2", 80., 164., 46., 45.);
        Button key3 = btnForKeyBoard("3", 125., 164., 46., 45.);
        Button key4 = btnForKeyBoard("4", 35., 118., 46., 45.);
        Button key5 = btnForKeyBoard("5", 80., 118., 46., 45.);
        Button key6 = btnForKeyBoard("6", 125., 118., 46., 45.);
        Button key7 = btnForKeyBoard("7", 35., 72., 46., 45.);
        Button key8 = btnForKeyBoard("8", 80., 72., 46., 45.);
        Button key9 = btnForKeyBoard("9", 125., 72., 46., 45.);
        Button keyClearAll = btnForKeyBoard("C", 170., 164., 46., 45.);
        Button keyPoint = btnForKeyBoard(".", 125., 210., 46., 45.);
        Button keyGO = btnForKeyBoard("G\nO", 170., 73., 90., 45.);
        Button keyCE = btnForKeyBoard("CE", 170., 210., 46., 45.);
        TextField txtKeyBoard = creatingTextField("Enter Value here", 30., 25., 25., 191.);
        txtKeyBoard.setStyle("-fx-background-color:transparent;-fx-border-color:#66CDAA;-fx-text-fill:#f5f7f7;-fx-font-size:1em;-fx-border-radius:10");
        keyBoardPane.getChildren().add(key0);
        keyBoardPane.getChildren().add(key1);
        keyBoardPane.getChildren().add(key2);
        keyBoardPane.getChildren().add(key3);
        keyBoardPane.getChildren().add(key4);
        keyBoardPane.getChildren().add(key5);
        keyBoardPane.getChildren().add(key6);
        keyBoardPane.getChildren().add(key7);
        keyBoardPane.getChildren().add(key8);
        keyBoardPane.getChildren().add(key9);
        keyBoardPane.getChildren().add(keyPoint);
        keyBoardPane.getChildren().add(keyClearAll);
        keyBoardPane.getChildren().add(keyGO);
        keyBoardPane.getChildren().add(keyCE);
        keyBoardPane.getChildren().add(txtKeyBoard);
        //giving action events for all buttons
        key0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "0");
            }
        });
        key1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "1");
            }
        });
        key2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "2");
            }
        });
        key3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "3");
            }
        });
        key4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "4");
            }
        });
        key5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "5");
            }
        });
        key6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "6");
            }
        });
        key7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "7");
            }
        });
        key8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "8");
            }
        });
        key9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + "9");
            }
        });
        keyPoint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText(txtKeyBoard.getText() + ".");
            }
        });
        keyCE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String number = txtKeyBoard.getText();
                if (!(number.equals(""))) {
                    txtKeyBoard.setText(number.substring(0, (number.length() - 1)));
                }
            }
        });
        keyClearAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtKeyBoard.setText("");

            }
        });
        //this will help to find the selected text field in each calculator
        keyGO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (FixDeposit.selectedTxtDA == true) {
                    FixDeposit.txtDepositAmnt.setText(txtKeyBoard.getText());
                } else if (FixDeposit.selectedTxRate == true) {
                    FixDeposit.txtIRate.setText(txtKeyBoard.getText());
                } else if (FixDeposit.selectedTxtTime == true) {
                    FixDeposit.txtTime.setText(txtKeyBoard.getText());
                } else if (FixDeposit.selectedTxtFV == true) {
                    FixDeposit.txtFtureValue.setText(txtKeyBoard.getText());
                } else if (Loans.selectedTxtLoanAmnt == true) {
                    Loans.txtLoanAmnt.setText(txtKeyBoard.getText());
                } else if (Loans.selectedTxtIntrstRate == true) {
                    Loans.txtIntrstRate.setText(txtKeyBoard.getText());
                } else if (Loans.selectedTxtLoanPeriod == true) {
                    Loans.txtLoanPeriod.setText(txtKeyBoard.getText());
                } else if (Loans.selectedTxtMonthlyPayment == true) {
                    Loans.txtMonthlyPayment.setText(txtKeyBoard.getText());
                } else if (Loans.selectedTxtPaybackAmnt == true) {
                    Loans.txtPaybackAmnt.setText(txtKeyBoard.getText());
                } else if (Mortgage.selectedTxtMortgageAmnt == true) {
                    Mortgage.txtMortgageAmnt.setText(txtKeyBoard.getText());
                } else if (Mortgage.selectedTxtMortgageIRate == true) {
                    Mortgage.txtMortgageIRate.setText(txtKeyBoard.getText());
                } else if (Mortgage.selectedTxtMortgagePeriod == true) {
                    Mortgage.txtMortgagePeriod.setText(txtKeyBoard.getText());
                } else if (Mortgage.selectedTxtMonthlyPayment == true) {
                    Mortgage.txtMonthlyPayment.setText(txtKeyBoard.getText());
                } else if (Mortgage.selectedTxtDownPayment == true) {
                    Mortgage.txtDownPayment.setText(txtKeyBoard.getText());
                } else if (Savings.selectedTxtSavingsAmnt == true) {
                    Savings.txtSavingsAmnt.setText(txtKeyBoard.getText());
                } else if (Savings.selectedTxtSavingsIRate == true) {
                    Savings.txtSavingsIRate.setText(txtKeyBoard.getText());
                } else if (Savings.selectedTxtSavingsPeriod == true) {
                    Savings.txtSavingsPeriod.setText(txtKeyBoard.getText());
                } else if (Savings.selectedTxtSavingsPayment == true) {
                    Savings.txtSavingsMonthlyPayment.setText(txtKeyBoard.getText());
                } else if (Savings.selectedTxtSavingsFv == true) {
                    Savings.txtSavingsFValue.setText(txtKeyBoard.getText());
                }
            }
        });

        return keyBoardPane;
    }

    //method for information alert
    public static Alert information(String message) {
        Alert infoAlert = new Alert(Alert.AlertType.NONE);
        infoAlert.setAlertType(Alert.AlertType.INFORMATION);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
        return infoAlert;
    }

    //method for error alert and this will pop up with a sound
    public static Alert error(String message) {
        Toolkit.getDefaultToolkit().beep();
        Alert errorAlert = new Alert(Alert.AlertType.NONE);
        errorAlert.setAlertType(Alert.AlertType.ERROR);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
        return errorAlert;
    }

    //if input values are less than 0 this will popup with a sound
    public static Alert errorInput() {
        Toolkit.getDefaultToolkit().beep();
        Alert errorAlert = new Alert(Alert.AlertType.NONE);
        errorAlert.setAlertType(Alert.AlertType.ERROR);
        errorAlert.setContentText("Please make sure not to  enter (-) values as inputs");
        errorAlert.showAndWait();
        return errorAlert;
    }


}
