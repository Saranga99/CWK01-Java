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

import static com.company.Home.df2;
import static com.company.Home.homeWindow;

public class Mortgage {
    public static Button btnClse;
    public static Button btnBk;
    public static Button btnCalculateMortgage;
    public static Button btnHelp;
    public static Label lblMortgage;
    public static Label lblMortgageAmnt;
    public static Label lblMortgageIRate;
    public static Label lalMortgagePeriod;
    public static Label lblMonthlyPayment;
    public static Label lblDownpayment;
    public static TextField txtMortgageAmnt;
    public static TextField txtMortgageIRate;
    public static TextField txtMortgagePeriod;
    public static TextField txtMonthlyPayment;
    public static TextField txtDownPayment;
    public static AnchorPane keyBoard;
    public static boolean selectedTxtMortgageAmnt, selectedTxtMortgageIRate, selectedTxtMortgagePeriod, selectedTxtMonthlyPayment, selectedTxtDownPayment;
    private static double mP, T, mA, r, dP = 0;
    private static boolean validatedData = false;

    private static MongoCollection getCollection() {
        MongoClient connectDB = new databaseInitialization().connectingDB();
        MongoDatabase database = connectDB.getDatabase("FinancialCalculator");
        MongoCollection mongoCollection = database.getCollection("Mortgage");
        return mongoCollection;

    }


    public static void mortgageWindow() {
        Stage mortgageStage = new Stage();
        mortgageStage.initStyle(StageStyle.UNDECORATED);

        Image image1 = new Image("Pics/MortgageBG.jpg");
        ImageView mortBG = new ImageView();
        mortBG.setImage(image1);
        mortBG.setFitWidth(600);
        mortBG.setFitHeight(420);

        btnClse = Components.createBtnClose();
        btnBk = Components.createBtnBack();
        btnHelp = Components.createBtnHelp();
        btnCalculateMortgage = Components.creatingButton("Calculate", 210., 308., 25., 100.);
        btnCalculateMortgage.setStyle("-fx-background-color:transparent; -fx-border-color:#F0F8FF;-fx-text-fill:#f5f7f7;-fx-font-size:1.5em;-fx-border-radius:10");

        lblMortgageAmnt = Components.creatingLabel("Mortgage Amount", 42., 50., 25., 149.);
        lblMortgageIRate = Components.creatingLabel("Interest Rate", 42., 97., 25., 149.);
        lalMortgagePeriod = Components.creatingLabel("Mortgage period", 42., 148., 25., 149.);
        lblMonthlyPayment = Components.creatingLabel("Monthly Payment", 42., 200., 25., 149.);
        lblDownpayment = Components.creatingLabel("DownPayment", 42., 252., 25., 149.);

        txtMortgageAmnt = Components.creatingTextField("LKR", 191., 50., 25., 149.);
        txtMortgageIRate = Components.creatingTextField("%", 191., 97., 25., 149.);
        txtMortgagePeriod = Components.creatingTextField("months", 191., 148., 25., 149.);
        txtMonthlyPayment = Components.creatingTextField("LKR", 191., 200., 25., 149.);
        txtDownPayment = Components.creatingTextField("LKR", 191., 252., 25., 149.);

        Document lastInsert = new MongoClient().getDatabase("FinancialCalculator").getCollection("Mortgage").find().sort(new BasicDBObject("_id", -1)).first();
        if (lastInsert != null) {

            txtMortgageAmnt.setText(String.valueOf(lastInsert.get("Mortgage Amount (LKR)")));
            txtMortgageIRate.setText(String.valueOf(lastInsert.get("Interest")));
            txtMortgagePeriod.setText(String.valueOf(lastInsert.get("Time Period (months)")));
            txtMonthlyPayment.setText(String.valueOf(lastInsert.get("Monthly Payment (LKR)")));
            txtDownPayment.setText(String.valueOf(lastInsert.get("Downpayment (LKR)")));

        }

        lblMortgage = Components.creatingLabelForBackground();


        keyBoard = Components.keyBoard(352., 30.);

        Pane mortgagePane = new Pane();
        mortgagePane.getChildren().add(mortBG);
        mortgagePane.getChildren().add(lblMortgage);
        mortgagePane.getChildren().add(btnClse);
        mortgagePane.getChildren().add(btnBk);
        mortgagePane.getChildren().add(btnHelp);
        mortgagePane.getChildren().add(btnCalculateMortgage);
        mortgagePane.getChildren().add(lblMortgageAmnt);
        mortgagePane.getChildren().add(lblMortgageIRate);
        mortgagePane.getChildren().add(lalMortgagePeriod);
        mortgagePane.getChildren().add(lblMonthlyPayment);
        mortgagePane.getChildren().add(lblDownpayment);
        mortgagePane.getChildren().add(txtMortgageAmnt);
        mortgagePane.getChildren().add(txtMortgageIRate);
        mortgagePane.getChildren().add(txtMortgagePeriod);
        mortgagePane.getChildren().add(txtMonthlyPayment);
        mortgagePane.getChildren().add(txtDownPayment);
        mortgagePane.getChildren().add(keyBoard);


        btnBk.setOnAction(e -> {
            mortgageStage.close();
            homeWindow();
        });
        btnClse.setOnAction(e -> {
            mortgageStage.close();
        });
        btnCalculateMortgage.setOnAction(e -> {
            calculate();
        });
        btnHelp.setOnAction(e -> {
            Help.helpWindowMortgage();
        });
        txtMortgageAmnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtMortgageAmnt = true;
                selectedTxtMortgageIRate = false;
                selectedTxtMortgagePeriod = false;
                selectedTxtMonthlyPayment = false;
                selectedTxtDownPayment = false;
            }
        });
        txtMortgageIRate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtMortgageAmnt = false;
                selectedTxtMortgageIRate = true;
                selectedTxtMortgagePeriod = false;
                selectedTxtMonthlyPayment = false;
                selectedTxtDownPayment = false;
            }
        });
        txtMortgagePeriod.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtMortgageAmnt = false;
                selectedTxtMortgageIRate = false;
                selectedTxtMortgagePeriod = true;
                selectedTxtMonthlyPayment = false;
                selectedTxtDownPayment = false;
            }
        });
        txtMonthlyPayment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtMortgageAmnt = false;
                selectedTxtMortgageIRate = false;
                selectedTxtMortgagePeriod = false;
                selectedTxtMonthlyPayment = true;
                selectedTxtDownPayment = false;
            }
        });
        txtDownPayment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtMortgageAmnt = false;
                selectedTxtMortgageIRate = false;
                selectedTxtMortgagePeriod = false;
                selectedTxtMonthlyPayment = false;
                selectedTxtDownPayment = true;
            }
        });


        mortgageStage.setScene(new Scene(mortgagePane, 600, 400));
        mortgageStage.show();


    }

    public static void calculate() {

        try {
            if (!txtMortgageAmnt.getText().equals("") && !txtMortgageIRate.getText().equals("") && !txtMortgagePeriod.getText().equals("") && txtMonthlyPayment.getText().equals("")) {
                if (txtDownPayment.getText().equals("")) {
                    mA = Double.parseDouble(txtMortgageAmnt.getText());
                    r = Double.parseDouble(txtMortgageIRate.getText());
                    T = Double.parseDouble(txtMortgagePeriod.getText());
                    double i = r / 1200;
                    if (mA > 0 && r > 0 && T > 0) {
                        mP = ((mA * i * (Math.pow((1 + i), T))) / ((Math.pow((1 + (i)), T)) - 1));
                        txtMonthlyPayment.setText(df2.format(mP));
                        Components.information("Monthly Payment :  " + df2.format(mP) + "\n" + "Payback Amount :  " + df2.format(mP * T));
                        validatedData = true;
                    } else {
                        Components.errorInput();
                    }
                } else {
                    mA = Double.parseDouble(txtMortgageAmnt.getText());
                    r = Double.parseDouble(txtMortgageIRate.getText());
                    T = Double.parseDouble(txtMortgagePeriod.getText());
                    dP = Double.parseDouble(txtDownPayment.getText());
                    double i = r / 1200;
                    if (mA > 0 && r > 0 && T > 0 && dP > 0) {
                        mP = (((mA - dP) * i * (Math.pow((1 + i), T))) / ((Math.pow((1 + (i)), T)) - 1));
                        txtMonthlyPayment.setText(df2.format(mP));
                        Components.information("Monthly Payment :  " + df2.format(mP) + "\n" + "Payback Amount with downpayment :  " + df2.format((mP * T) + dP));
                        validatedData = true;
                    } else {
                        Components.errorInput();
                    }
                }
            } else if (txtMortgageAmnt.getText().equals("") && !txtMortgageIRate.getText().equals("") && !txtMortgagePeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("")) {
                if (txtDownPayment.getText().equals("")) {
                    r = Double.parseDouble(txtMortgageIRate.getText());
                    T = Double.parseDouble(txtMortgagePeriod.getText());
                    mP = Double.parseDouble(txtMonthlyPayment.getText());
                    double i = r / 1200;
                    if (r > 0 && T > 0 && mP > 0) {
                        mA = ((mP / i) * (1 - (1 / (Math.pow((1 + i), T)))));
                        txtMortgageAmnt.setText(df2.format(mA));
                        Components.information("Mortgage Amount :  " + df2.format(mA) + "\n" + "Payback Amount :  " + df2.format(mP * T));
                        validatedData = true;
                    } else {
                        Components.errorInput();
                    }
                } else {
                    r = Double.parseDouble(txtMortgageIRate.getText());
                    T = Double.parseDouble(txtMortgagePeriod.getText());
                    dP = Double.parseDouble(txtDownPayment.getText());
                    mP = Double.parseDouble(txtMonthlyPayment.getText());
                    double i = r / 1200;
                    if (r > 0 && T > 0 && dP > 0 && mP > 0) {
                        mA = (((mP / i) * (1 - (1 / (Math.pow((1 + i), T))))) + dP);
                        txtMortgageAmnt.setText(df2.format(mA));
                        Components.information("Mortgage Amount :  " + df2.format(mA) + "\n" + "Payback Amount with downpayment :  " + df2.format((mP * T) + dP));
                        validatedData = true;
                    } else {
                        Components.errorInput();
                    }

                }
            } else if (!txtMortgageAmnt.getText().equals("") && !txtMortgageIRate.getText().equals("") && txtMortgagePeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("")) {
                if (txtDownPayment.getText().equals("")) {
                    r = Double.parseDouble(txtMortgageIRate.getText());
                    mA = Double.parseDouble(txtMortgageAmnt.getText());
                    mP = Double.parseDouble(txtMonthlyPayment.getText());
                    double i = r / 1200;
                    if (r > 0 && mA > 0 && mP > 00) {
                        T = ((Math.log((mP / i) / ((mP / i) - mA)) / (Math.log(1 + i))));
                        txtMortgagePeriod.setText(df2.format(T));
                        Components.information("Mortgage Period :  " + df2.format(T) + "\n" + "Payback Amount :  " + df2.format(mP * T));
                        validatedData = true;
                    } else {
                        Components.errorInput();
                    }

                } else {
                    r = Double.parseDouble(txtMortgageIRate.getText());
                    mA = Double.parseDouble(txtMortgageAmnt.getText());
                    mP = Double.parseDouble(txtMonthlyPayment.getText());
                    dP = Double.parseDouble(txtDownPayment.getText());
                    double i = r / 1200;
                    if (r > 0 && mA > 0 && mP > 0 && dP > 0) {
                        T = ((Math.log((mP / i) / ((mP / i) - (mA - dP)))) / (Math.log(1 + i)));
                        txtMortgagePeriod.setText(df2.format(T));
                        Components.information("Mortgage Period :  " + df2.format(T) + "\n" + "Payback Amount with downpayment :  " + df2.format((mP * T) + dP));
                        validatedData = true;
                    } else {
                        Components.errorInput();
                    }

                }

            } else if (!txtMortgageAmnt.getText().equals("") && !txtMortgageIRate.getText().equals("") && !txtMortgagePeriod.getText().equals("") && !txtMonthlyPayment.getText().equals("") && txtDownPayment.getText().equals("")) {
                r = Double.parseDouble(txtMortgageIRate.getText());
                mA = Double.parseDouble(txtMortgageAmnt.getText());
                mP = Double.parseDouble(txtMonthlyPayment.getText());
                T = Double.parseDouble(txtMortgagePeriod.getText());
                double i = r / 1200;
                if (r > 0 && mA > 0 && mP > 0 && T > 0) {
                    dP = mA - ((mP / i) * (1 - (1 / (Math.pow((1 + i), T)))));
                    txtDownPayment.setText(df2.format(dP));
                    Components.information("Mortgage downpayment :  " + df2.format(dP) + "\n" + "Payback Amount with downpayment :  " + df2.format((mP * T) + dP));
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
            Document mortgageData = new Document();
            mortgageData.put("Mortgage Amount (LKR)", mA);
            mortgageData.put("Interest", r);
            mortgageData.put("Time Period (months)", T);
            mortgageData.put("Monthly Payment (LKR)", mP);
            mortgageData.put("Downpayment (LKR)", dP);
            getCollection().insertOne(mortgageData);
            validatedData = false;
        }
    }
}
