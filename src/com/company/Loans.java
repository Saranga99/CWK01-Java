package com.company;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;

import java.awt.*;

import static com.company.Home.df2;
import static com.company.Home.homeWindow;

public class Loans {
    public static Button btnClse;
    public static Button btnBk;
    public static Button btnCalculateLoan;
    public static Button btnHelp;
    public static AnchorPane keyBoard;
    public static Label lblLoan;
    public static Label lblLoanAmnt;
    public static Label lblIntrstRate;
    public static Label lblLoanPeriod;
    public static Label lblMonthlyPayment;
    public static Label lblPaybackAmnt;
    public static TextField txtLoanAmnt;
    public static TextField txtIntrstRate;
    public static TextField txtLoanPeriod;
    public static TextField txtMonthlyPayment;
    public static TextField txtPaybackAmnt;
    public static boolean selectedTxtLoanAmnt, selectedTxtIntrstRate, selectedTxtLoanPeriod, selectedTxtMonthlyPayment, selectedTxtPaybackAmnt;
    private static double mP, T, lA, r;
    private static boolean validatedData = false;

    private static MongoCollection getCollection() {
        MongoClient connectDB = new databaseInitialization().connectingDB();
        MongoDatabase database = connectDB.getDatabase("FinancialCalculator");
        MongoCollection mongoCollection = database.getCollection("Loans");
        return mongoCollection;

    }


    public static void loansWindow() {
        Stage loanStage = new Stage();
        loanStage.initStyle(StageStyle.UNDECORATED);

        Image image1 = new Image("Pics/LoansBG.jpg");
        ImageView loanBG = new ImageView();
        loanBG.setImage(image1);
        loanBG.setFitWidth(600);
        loanBG.setFitHeight(420);


        btnClse = Components.createBtnClose();
        btnBk = Components.createBtnBack();
        btnHelp = Components.createBtnHelp();
        btnCalculateLoan = Components.creatingButton("Calculate", 210., 308., 25., 100.);
        btnCalculateLoan.setStyle("-fx-background-color:transparent; -fx-border-color:#F0F8FF;-fx-text-fill:#f5f7f7;-fx-font-size:1.5em;-fx-border-radius:10");


        lblLoanAmnt = Components.creatingLabel("Loan Amount", 42., 50., 25., 149.);
        lblIntrstRate = Components.creatingLabel("Interest Rate", 42., 97., 25., 149.);
        lblLoanPeriod = Components.creatingLabel("Loan period", 42., 148., 25., 149.);
        lblMonthlyPayment = Components.creatingLabel("Monthly Payment", 42., 200., 25., 149.);
        lblPaybackAmnt = Components.creatingLabel("Payback Amount", 42., 252., 25., 149.);

        txtLoanAmnt = Components.creatingTextField("LKR", 191., 50., 25., 149.);
        txtIntrstRate = Components.creatingTextField("%", 191., 97., 25., 149.);
        txtLoanPeriod = Components.creatingTextField("months", 191., 148., 25., 149.);
        txtMonthlyPayment = Components.creatingTextField("LKR", 191., 200., 25., 149.);
        txtPaybackAmnt = Components.creatingTextField("LKR", 191., 252., 25., 149.);

        Document lastInsert = new MongoClient().getDatabase("FinancialCalculator").getCollection("Loans").find().sort(new BasicDBObject("_id", -1)).first();
        if (lastInsert != null) {

            txtLoanAmnt.setText(String.valueOf(lastInsert.get("Loan Amount (LKR)")));
            txtIntrstRate.setText(String.valueOf(lastInsert.get("Interest")));
            txtLoanPeriod.setText(String.valueOf(lastInsert.get("Time Period (months)")));
            txtMonthlyPayment.setText(String.valueOf(lastInsert.get("Monthly Payment (LKR)")));
            txtPaybackAmnt.setText(String.valueOf(lastInsert.get("Payback Amount (LKR)")));

        }

        lblLoan = Components.creatingLabelForBackground();


        keyBoard = Components.keyBoard(352., 30.);


        Pane loanPane = new Pane();
        loanPane.getChildren().add(loanBG);
        loanPane.getChildren().add(lblLoan);
        loanPane.getChildren().add(lblLoanAmnt);
        loanPane.getChildren().add(lblIntrstRate);
        loanPane.getChildren().add(lblLoanPeriod);
        loanPane.getChildren().add(lblMonthlyPayment);
        loanPane.getChildren().add(lblPaybackAmnt);
        loanPane.getChildren().add(btnClse);
        loanPane.getChildren().add(btnBk);
        loanPane.getChildren().add(btnCalculateLoan);
        loanPane.getChildren().add(btnHelp);
        loanPane.getChildren().add(txtLoanAmnt);
        loanPane.getChildren().add(txtIntrstRate);
        loanPane.getChildren().add(txtLoanPeriod);
        loanPane.getChildren().add(txtMonthlyPayment);
        loanPane.getChildren().add(txtPaybackAmnt);
        loanPane.getChildren().add(keyBoard);


        btnBk.setOnAction(e -> {
            loanStage.close();
            homeWindow();
        });
        btnClse.setOnAction(e -> {
            loanStage.close();
        });
        btnCalculateLoan.setOnAction(e -> {
            calculate();

        });
        btnHelp.setOnAction(e -> {
            Help.helpWindowLoan();

        });

        txtLoanAmnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtLoanAmnt = true;
                selectedTxtIntrstRate = false;
                selectedTxtLoanPeriod = false;
                selectedTxtMonthlyPayment = false;
                selectedTxtPaybackAmnt = false;
            }
        });
        txtIntrstRate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtLoanAmnt = false;
                selectedTxtIntrstRate = true;
                selectedTxtLoanPeriod = false;
                selectedTxtMonthlyPayment = false;
                selectedTxtPaybackAmnt = false;
            }
        });
        txtLoanPeriod.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtLoanAmnt = false;
                selectedTxtIntrstRate = false;
                selectedTxtLoanPeriod = true;
                selectedTxtMonthlyPayment = false;
                selectedTxtPaybackAmnt = false;
            }
        });
        txtMonthlyPayment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtLoanAmnt = false;
                selectedTxtIntrstRate = false;
                selectedTxtLoanPeriod = false;
                selectedTxtMonthlyPayment = true;
                selectedTxtPaybackAmnt = false;
            }
        });
        txtPaybackAmnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtLoanAmnt = false;
                selectedTxtIntrstRate = false;
                selectedTxtLoanPeriod = false;
                selectedTxtMonthlyPayment = false;
                selectedTxtPaybackAmnt = true;
            }
        });


        loanStage.setScene(new Scene(loanPane, 600, 400));
        loanStage.show();
    }


    public static void calculate() {
        try {
            if (!txtLoanAmnt.getText().equals("") && !txtIntrstRate.getText().equals("") && !txtLoanPeriod.getText().equals("") && txtMonthlyPayment.getText().equals("")) {
                lA = Double.parseDouble(txtLoanAmnt.getText());
                r = Double.parseDouble(txtIntrstRate.getText());
                T = Double.parseDouble(txtLoanPeriod.getText());
                if (lA > 0 && r > 0 && T > 0) {
                    double i = r / 1200;
                    mP = ((lA * i * (Math.pow((1 + i), T))) / ((Math.pow((1 + (i)), T)) - 1));
                    txtMonthlyPayment.setText(df2.format(mP));
                    txtPaybackAmnt.setText(df2.format(mP * T));
                    Components.information("Monthly Payment :  " + df2.format(mP) + "\n" + "Payback Amount  :  " + df2.format(mP * T));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }

            } else if (txtLoanAmnt.getText().equals("") && !txtIntrstRate.getText().equals("") && !txtLoanPeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("")) {
                r = Double.parseDouble(txtIntrstRate.getText());
                T = Double.parseDouble(txtLoanPeriod.getText());
                mP = Double.parseDouble(txtMonthlyPayment.getText());
                if (r > 0 && T > 0 && mP > 0) {
                    double i = r / 1200;
                    lA = (mP / i) * (1 - (1 / (Math.pow((1 + i), T))));
                    txtLoanAmnt.setText(df2.format(lA));
                    txtPaybackAmnt.setText(df2.format(mP * T));
                    Components.information("Loan Amount  :  " + df2.format(lA) + "\n" + "Payback Amount  :  " + df2.format(mP * T));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }

            } else if (!txtLoanAmnt.getText().equals("") && !txtIntrstRate.getText().equals("") && txtLoanPeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("")) {
                lA = Double.parseDouble(txtLoanAmnt.getText());
                r = Double.parseDouble(txtIntrstRate.getText());
                mP = Double.parseDouble(txtMonthlyPayment.getText());
                double i = r / 1200;
                if (lA > 0 && r > 0 && mP > 0) {
                    T = ((Math.log((mP / i) / ((mP / i) - lA))) / (Math.log(1 + i)));
                    txtLoanPeriod.setText(df2.format(T));
                    txtPaybackAmnt.setText(df2.format(mP * T));
                    Components.information("Time Period  :  " + df2.format(T) + "\n" + "Payback Amount  :  " + df2.format(mP * T));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }

            } else if (!txtLoanAmnt.getText().equals("") && txtIntrstRate.getText().equals("") && !txtLoanPeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("") || !txtPaybackAmnt.getText().equals("")) {
                Components.error("You are Not allowed to get this output in ths Version");

            } else if (!txtLoanAmnt.getText().equals("") && !txtIntrstRate.getText().equals("") && !txtLoanPeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("") && txtPaybackAmnt.getText().equals("")) {
                lA = Double.parseDouble(txtLoanAmnt.getText());
                r = Double.parseDouble(txtIntrstRate.getText());
                mP = Double.parseDouble(txtMonthlyPayment.getText());
                T = Double.parseDouble(txtLoanPeriod.getText());
                if (lA > 0 && r > 0 && mP > 0 && T > 0) {
                    txtPaybackAmnt.setText(df2.format(mP * T));
                    Components.information("Payback Amount :  " + df2.format(mP * T));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }

            } else {
                Components.error("Please make sure to Empty one text Field which you want to calculate");
            }
        } catch (Exception e) {
            Components.error("Please make sure the entered data is valid");

        }
        if (validatedData == true) {
            Document loanData = new Document();
            loanData.put("Loan Amount (LKR)", lA);
            loanData.put("Interest", r);
            loanData.put("Time Period (months)", T);
            loanData.put("Monthly Payment (LKR)", mP);
            loanData.put("Payback Amount (LKR)", mP * T);
            getCollection().insertOne(loanData);
            validatedData = false;
        }
    }


}
