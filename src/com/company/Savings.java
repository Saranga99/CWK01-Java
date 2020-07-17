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

public class Savings {
    public static Button btnClse;
    public static Button btnBk;
    public static Button btnCalculateSavings;
    public static Button btnHelp;
    public static AnchorPane keyBoard;
    public static Label lblSavingsAmnt;
    public static Label lblSavingsIRate;
    public static Label lalSavingsPeriod;
    public static Label lblSavingsMonthlyPayment;
    public static Label lblSavingsFValue;
    public static Label lblSavings;
    public static TextField txtSavingsAmnt;
    public static TextField txtSavingsIRate;
    public static TextField txtSavingsPeriod;
    public static TextField txtSavingsMonthlyPayment;
    private static double pV, fV, r, t, mP;
    public static TextField txtSavingsFValue;
    public static boolean selectedTxtSavingsAmnt, selectedTxtSavingsIRate, selectedTxtSavingsPeriod, selectedTxtSavingsPayment, selectedTxtSavingsFv;
    private static boolean validatedData = false;

    private static MongoCollection getCollection() {
        MongoClient connectDB = new databaseInitialization().connectingDB();
        MongoDatabase database = connectDB.getDatabase("FinancialCalculator");
        MongoCollection mongoCollection = database.getCollection("Savings");
        return mongoCollection;

    }

    public static void savingsWindow() {
        Stage savingsStage = new Stage();
        savingsStage.initStyle(StageStyle.UNDECORATED);

        Image image1 = new Image("Pics/SavingsBg.jpg");
        ImageView savingsBg = new ImageView();
        savingsBg.setImage(image1);
        savingsBg.setFitWidth(600);
        savingsBg.setFitHeight(420);

        btnClse = Components.createBtnClose();
        btnBk = Components.createBtnBack();
        btnHelp = Components.createBtnHelp();
        btnCalculateSavings = Components.creatingButton("Calculate", 210., 308., 25., 100.);
        btnCalculateSavings.setStyle("-fx-background-color:transparent; -fx-border-color:#F0F8FF;-fx-text-fill:#f5f7f7;-fx-font-size:1.5em;-fx-border-radius:10;");

        lblSavingsAmnt = Components.creatingLabel("Savings Amount", 42., 50., 25., 149.);
        lblSavingsIRate = Components.creatingLabel("Interest Rate", 42., 97., 25., 149.);
        lalSavingsPeriod = Components.creatingLabel("Savings period", 42., 148., 25., 149.);
        lblSavingsMonthlyPayment = Components.creatingLabel("Monthly Payment", 42., 200., 25., 149.);
        lblSavingsFValue = Components.creatingLabel("Future Value", 42., 252., 25., 149.);

        txtSavingsAmnt = Components.creatingTextField("LKR", 191., 50., 25., 149.);
        txtSavingsIRate = Components.creatingTextField("%", 191., 97., 25., 149.);
        txtSavingsPeriod = Components.creatingTextField("Years", 191., 148., 25., 149.);
        txtSavingsMonthlyPayment = Components.creatingTextField("LKR", 191., 200., 25., 149.);
        txtSavingsFValue = Components.creatingTextField("LKR", 191., 252., 25., 149.);

        Document lastInsert = new MongoClient().getDatabase("FinancialCalculator").getCollection("Savings").find().sort(new BasicDBObject("_id", -1)).first();
        if (lastInsert != null) {

            txtSavingsAmnt.setText(String.valueOf(lastInsert.get("Loan Amount (LKR)")));
            txtSavingsIRate.setText(String.valueOf(lastInsert.get("Interest")));
            txtSavingsPeriod.setText(String.valueOf(lastInsert.get("Time Period (years)")));
            txtSavingsMonthlyPayment.setText(String.valueOf(lastInsert.get("Monthly Payment (LKR)")));
            txtSavingsFValue.setText(String.valueOf(lastInsert.get("Future Value (LKR)")));

        }


        lblSavings = Components.creatingLabelForBackground();
        lblSavings.setOpacity(.5);
        keyBoard = Components.keyBoard(352., 30.);

        Pane savingsPain = new Pane();
        savingsPain.getChildren().add(savingsBg);
        savingsPain.getChildren().add(lblSavings);
        savingsPain.getChildren().add(btnClse);
        savingsPain.getChildren().add(btnBk);
        savingsPain.getChildren().add(btnHelp);
        savingsPain.getChildren().add(btnCalculateSavings);
        savingsPain.getChildren().add(lblSavingsAmnt);
        savingsPain.getChildren().add(lblSavingsIRate);
        savingsPain.getChildren().add(lalSavingsPeriod);
        savingsPain.getChildren().add(lblSavingsMonthlyPayment);
        savingsPain.getChildren().add(lblSavingsFValue);
        savingsPain.getChildren().add(txtSavingsAmnt);
        savingsPain.getChildren().add(txtSavingsIRate);
        savingsPain.getChildren().add(txtSavingsPeriod);
        savingsPain.getChildren().add(txtSavingsMonthlyPayment);
        savingsPain.getChildren().add(txtSavingsFValue);
        savingsPain.getChildren().add(keyBoard);


        btnBk.setOnAction(e -> {
            savingsStage.close();
            homeWindow();
        });
        btnClse.setOnAction(e -> {
            savingsStage.close();

        });
        btnCalculateSavings.setOnAction(e -> {
            Savings.calculate();

        });
        btnHelp.setOnAction(e -> {
            Help.helpWindowSavings();

        });
        txtSavingsAmnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtSavingsAmnt = true;
                selectedTxtSavingsIRate = false;
                selectedTxtSavingsPeriod = false;
                selectedTxtSavingsPayment = false;
                selectedTxtSavingsFv = false;
            }
        });
        txtSavingsIRate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtSavingsAmnt = false;
                selectedTxtSavingsIRate = true;
                selectedTxtSavingsPeriod = false;
                selectedTxtSavingsPayment = false;
                selectedTxtSavingsFv = false;
            }
        });
        txtSavingsPeriod.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtSavingsAmnt = false;
                selectedTxtSavingsIRate = false;
                selectedTxtSavingsPeriod = true;
                selectedTxtSavingsPayment = false;
                selectedTxtSavingsFv = false;
            }
        });
        txtSavingsMonthlyPayment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtSavingsAmnt = false;
                selectedTxtSavingsIRate = false;
                selectedTxtSavingsPeriod = false;
                selectedTxtSavingsPayment = true;
                selectedTxtSavingsFv = false;
            }
        });
        txtSavingsFValue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtSavingsAmnt = false;
                selectedTxtSavingsIRate = false;
                selectedTxtSavingsPeriod = false;
                selectedTxtSavingsPayment = false;
                selectedTxtSavingsFv = true;
            }
        });


        savingsStage.setScene(new Scene(savingsPain, 600, 400));
        savingsStage.show();


    }

    public static void calculate() {
        try {
            if (txtSavingsAmnt.getText().equals("") && !txtSavingsIRate.getText().equals("") && !txtSavingsPeriod.getText().equals("") && !txtSavingsMonthlyPayment.getText().equals("") && !txtSavingsFValue.getText().equals("")) {
                r = Double.parseDouble(txtSavingsIRate.getText());
                t = Double.parseDouble(txtSavingsPeriod.getText());
                fV = Double.parseDouble(txtSavingsFValue.getText());
                mP = Double.parseDouble(txtSavingsMonthlyPayment.getText());
                if (r > 0 && t > 0 && fV > 0 && mP > 0) {
                    double pow = Math.pow((1 + (r / 1200)), (12 * t));
                    pV = (fV - (mP * ((pow - 1) / (r / 1200)))) / (pow);
                    txtSavingsAmnt.setText(df2.format(pV));
                    Components.information("Present value is :  " + df2.format(pV));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }
            } else if (!txtSavingsAmnt.getText().equals("") && !txtSavingsIRate.getText().equals("") && txtSavingsPeriod.getText().equals("") && !txtSavingsMonthlyPayment.getText().equals("") && !txtSavingsFValue.getText().equals("")) {
                r = Double.parseDouble(txtSavingsIRate.getText());
                fV = Double.parseDouble(txtSavingsFValue.getText());
                mP = Double.parseDouble(txtSavingsMonthlyPayment.getText());
                if (r > 0 && fV > 0 && mP > 0) {
                    t = Math.log(((fV * r) / (1200 * mP)) + 1) / (12 * Math.log(1 + (r / 1200)));
                    txtSavingsPeriod.setText(df2.format(t));
                    Components.information("Duration  is :  " + df2.format(t) + " months");
                    validatedData = true;
                } else {
                    Components.errorInput();
                }
            } else if (!txtSavingsAmnt.getText().equals("") && !txtSavingsIRate.getText().equals("") && !txtSavingsPeriod.getText().equals("") && txtSavingsMonthlyPayment.getText().equals("") && !txtSavingsFValue.getText().equals("")) {
                r = Double.parseDouble(txtSavingsIRate.getText());
                t = Double.parseDouble(txtSavingsPeriod.getText());
                fV = Double.parseDouble(txtSavingsFValue.getText());
                pV = Double.parseDouble(txtSavingsAmnt.getText());
                if (r > 0 && t > 0 && fV > 0 && pV > 0) {
                    double pow = Math.pow((1 + (r / 1200)), (12 * t));
                    mP = (fV - pV * (pow)) / ((pow - 1) / (r / 1200));
                    txtSavingsMonthlyPayment.setText(df2.format(mP));
                    Components.information("Monthly Payment is :  " + df2.format(mP));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }
            } else if (!txtSavingsAmnt.getText().equals("") && !txtSavingsIRate.getText().equals("") && !txtSavingsPeriod.getText().equals("") && !txtSavingsMonthlyPayment.getText().equals("") && txtSavingsFValue.getText().equals("")) {
                r = Double.parseDouble(txtSavingsIRate.getText());
                mP = Double.parseDouble(txtSavingsMonthlyPayment.getText());
                t = Double.parseDouble(txtSavingsPeriod.getText());
                pV = Double.parseDouble(txtSavingsAmnt.getText());
                if (r > 0 && mP > 0 && t > 0 && pV > 0) {
                    double pow = Math.pow((1 + (r / 1200)), (12 * t));
                    fV = (mP * ((pow - 1) / (r / 1200))) + pV * (pow);
                    txtSavingsFValue.setText(df2.format(fV));
                    Components.information("Future Value is :  " + df2.format(fV));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }

            } else if (!txtSavingsAmnt.getText().equals("") && txtSavingsIRate.getText().equals("") && !txtSavingsPeriod.getText().equals("") && !txtSavingsMonthlyPayment.getText().equals("") && !txtSavingsFValue.getText().equals("")) {
                Components.error("You are Not allowed to get this output in ths Version");
            } else {
                Components.error("Please make sure to Empty one text Field which you want to calculate");
            }
        } catch (Exception e) {
            Components.error("Please make sure the entered data is valid");
        }
        if (validatedData == true) {
            Document savingsData = new Document();
            savingsData.put("Loan Amount (LKR)", pV);
            savingsData.put("Interest", r);
            savingsData.put("Time Period (years)", t);
            savingsData.put("Monthly Payment (LKR)", mP);
            savingsData.put("Future Value (LKR)", fV);
            getCollection().insertOne(savingsData);
            validatedData = false;
        }
    }


}
