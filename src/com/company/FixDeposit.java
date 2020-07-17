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

public class FixDeposit {
    //variables for calculations , validations and components
    public static Button btnClse;
    public static Button btnBk;
    public static Button btnCalculateFix;
    public static Button btnHelp;
    public static Label lblDpsitAmnt;
    public static Label lblIntrstRate;
    public static Label lblTime;
    public static Label lblFtureValue;
    public static TextField txtDepositAmnt;
    public static TextField txtIRate;
    public static TextField txtTime;
    public static TextField txtFtureValue;
    public static AnchorPane keyBoard;
    public static Label lblFD;
    private static double pV, fV, r, t;
    public static boolean selectedTxtDA, selectedTxRate, selectedTxtTime, selectedTxtFV;
    private static boolean validatedData = false;

    //connection with database and creating collection for add data
    private static MongoCollection getCollection() {
        MongoClient connectDB = new databaseInitialization().connectingDB();
        MongoDatabase database = connectDB.getDatabase("FinancialCalculator");
        MongoCollection mongoCollection = database.getCollection("FixDeposit");
        return mongoCollection;

    }

    //creating Fd window
    public static void fixDepositWindow() {
        Stage fDStage = new Stage();
        fDStage.initStyle(StageStyle.UNDECORATED);

        //adding image to background
        Image image1 = new Image("Pics/FDBG.jpg");
        ImageView FDBg = new ImageView();
        FDBg.setImage(image1);
        FDBg.setFitWidth(600);
        FDBg.setFitHeight(420);
        //creating labels, buttons
        btnClse = Components.createBtnClose();
        btnBk = Components.createBtnBack();
        btnHelp = Components.createBtnHelp();
        btnCalculateFix = Components.creatingButton("Calculate", 210., 308., 25., 100.);
        btnCalculateFix.setStyle("-fx-background-color:transparent; -fx-border-color:#F0F8FF;-fx-text-fill:#f5f7f7;-fx-font-size:1.5em;-fx-border-radius:10;");


        lblDpsitAmnt = Components.creatingLabel("Deposit Amount (P)", 42., 97., 25., 149.);
        lblIntrstRate = Components.creatingLabel("Interest Rate (r)", 42., 148., 25., 149.);
        lblTime = Components.creatingLabel("Time (t)", 42., 200., 25., 149.);
        lblFtureValue = Components.creatingLabel("Future Value (A)", 42., 252., 25., 149.);

        txtDepositAmnt = Components.creatingTextField("LKR", 191., 97., 25., 149.);
        txtIRate = Components.creatingTextField("% (Monthly)", 191., 148., 25., 149.);
        txtTime = Components.creatingTextField("Years", 191., 200., 25., 149.);
        txtFtureValue = Components.creatingTextField("LKR", 191., 252., 25., 149.);

        //reading fiddeposit collection to load previous data into text fields
        Document lastInsert = new MongoClient().getDatabase("FinancialCalculator").getCollection("FixDeposit").find().sort(new BasicDBObject("_id", -1)).first();
        if (lastInsert != null) {

            txtDepositAmnt.setText(String.valueOf(lastInsert.get("Present Value (LKR)")));
            txtIRate.setText(String.valueOf(lastInsert.get("Interest")));
            txtTime.setText(String.valueOf(lastInsert.get("Time Period (years)")));
            txtFtureValue.setText(String.valueOf(lastInsert.get("Future Value (LKR)")));

        }

        lblFD = Components.creatingLabelForBackground();


        keyBoard = Components.keyBoard(352., 72.);
        //adding all components into anchorpain
        Pane fDPane = new Pane();
        fDPane.getChildren().add(FDBg);
        fDPane.getChildren().add(lblFD);
        fDPane.getChildren().add(lblDpsitAmnt);
        fDPane.getChildren().add(lblIntrstRate);
        fDPane.getChildren().add(lblTime);
        fDPane.getChildren().add(lblFtureValue);
        fDPane.getChildren().add(btnClse);
        fDPane.getChildren().add(btnBk);
        fDPane.getChildren().add(btnCalculateFix);
        fDPane.getChildren().add(btnHelp);
        fDPane.getChildren().add(txtDepositAmnt);
        fDPane.getChildren().add(txtIRate);
        fDPane.getChildren().add(txtTime);
        fDPane.getChildren().add(txtFtureValue);
        fDPane.getChildren().add(keyBoard);

        //setting action methods to all buttons
        btnBk.setOnAction(e -> {
            fDStage.close();
            homeWindow();
        });
        btnClse.setOnAction(e -> {
            fDStage.close();

        });
        btnCalculateFix.setOnAction(e -> {
            calculate();

        });
        btnHelp.setOnAction(e -> {
            Help.helpWindowFix();

        });
        //boolean condition to select the selected textfield in this fd calculator
        txtDepositAmnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtDA = true;
                selectedTxRate = false;
                selectedTxtTime = false;
                selectedTxtFV = false;
            }
        });
        txtIRate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtDA = false;
                selectedTxRate = true;
                selectedTxtTime = false;
                selectedTxtFV = false;
            }
        });
        txtTime.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtDA = false;
                selectedTxRate = false;
                selectedTxtTime = true;
                selectedTxtFV = false;
            }
        });
        txtFtureValue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedTxtDA = false;
                selectedTxRate = false;
                selectedTxtTime = false;
                selectedTxtFV = true;
            }
        });
        fDStage.setScene(new Scene(fDPane, 600, 400));
        fDStage.show();


    }

    //method for calculate button
    public static void calculate() {
        try { //compound interest is calculated by monthly
            //checking condition to get empty textfield
            if (txtDepositAmnt.getText().equals("") && !txtIRate.getText().equals("") && !txtTime.getText().equals("") && !txtFtureValue.getText().equals("")) {
                //getting values and convert them into doubble
                r = Double.parseDouble(txtIRate.getText());
                t = Double.parseDouble(txtTime.getText());
                fV = Double.parseDouble(txtFtureValue.getText());
                //condition to check (-) values, if user enters (-) values calculation will not be execute
                if (r > 0 && t > 0 && fV > 0) {
                    pV = fV / (Math.pow((1 + r / 1200), 12 * t));
                    //set output into relevent textfield and display information alert
                    txtDepositAmnt.setText(df2.format(pV));
                    Components.information("Present value is :  " + df2.format(pV));
                    //validated input for save into database
                    validatedData = true;
                    //error message
                } else {
                    Components.errorInput();
                }
            } else if (!txtDepositAmnt.getText().equals("") && txtIRate.getText().equals("") && !txtTime.getText().equals("") && !txtFtureValue.getText().equals("")) {
                pV = Double.parseDouble(txtDepositAmnt.getText());
                t = Double.parseDouble(txtTime.getText());
                fV = Double.parseDouble(txtFtureValue.getText());
                if (pV > 0 && t > 0 && fV > 0) {
                    r = 100 * (12 * (Math.pow((fV / pV), 1 / (12 * t)) - 1));
                    txtIRate.setText(df2.format(r));
                    Components.information("Interest Rate is :  " + df2.format(r));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }
            } else if (!txtDepositAmnt.getText().equals("") && !txtIRate.getText().equals("") && txtTime.getText().equals("") && !txtFtureValue.getText().equals("")) {
                pV = Double.parseDouble(txtDepositAmnt.getText());
                r = Double.parseDouble(txtIRate.getText());
                fV = Double.parseDouble(txtFtureValue.getText());
                if (pV > 0 && r > 0 && fV > 0) {
                    t = (Math.log(fV / pV)) / (12 * Math.log(1 + (r / 1200)));
                    txtTime.setText(df2.format(t));
                    Components.information("Duration  is :  " + df2.format(t) + " years");
                    validatedData = true;
                } else {
                    Components.errorInput();
                }
            } else if (!txtDepositAmnt.getText().equals("") && !txtIRate.getText().equals("") && !txtTime.getText().equals("") && txtFtureValue.getText().equals("")) {
                pV = Double.parseDouble(txtDepositAmnt.getText());
                r = Double.parseDouble(txtIRate.getText());
                t = Double.parseDouble(txtTime.getText());
                if (pV > 0 && r > 0 && t > 0) {
                    fV = pV * (Math.pow((1 + r / 1200), 12 * t));
                    txtFtureValue.setText(df2.format(fV));
                    Components.information("Future Value is :  " + df2.format(fV));
                    validatedData = true;
                } else {
                    Components.errorInput();
                }
            } else {
                //if user filled all textfields this error message wil popup
                Components.error("Please make sure to Empty one text Field which you want to calculate");
            }
        } catch (Exception e) {
            //if user enter string as an input this message will popup
            Components.error("Please make sure the entered data is valid");
        }
        //if there be a validated input it will save into database in relevant
        if (validatedData == true) {
            //creating document and adding validated data into it
            Document fdData = new Document();
            fdData.put("Present Value (LKR)", pV);
            fdData.put("Interest", r);
            fdData.put("Time Period (years)", t);
            fdData.put("Future Value (LKR)", fV);
            getCollection().insertOne(fdData);
            validatedData = false;
        }


    }
}
