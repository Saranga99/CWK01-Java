package com.company;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;

public class Help {
    public static Label lblFeedbackBG;
    public static TextField txtFeeback;
    public static Button btnClse;
    public static Button btnBk;
    public static Button btnSubmit;

    private static MongoCollection getCollection() {
        MongoClient connectDB = new databaseInitialization().connectingDB();
        MongoDatabase database = connectDB.getDatabase("FinancialCalculator");
        MongoCollection mongoCollection = database.getCollection("Feedback");
        return mongoCollection;
    }

    //method to get feedback from users , , creating window and adding components and input feedback details into database
    public static void feedbackWindow() {
        Stage fdbkStage = new Stage();
        fdbkStage.initStyle(StageStyle.UNDECORATED);
        Image image1 = new Image("Pics/feedback.jpg");
        ImageView bgfd = new ImageView();
        bgfd.setImage(image1);
        bgfd.setFitWidth(609);
        bgfd.setFitHeight(500);
        lblFeedbackBG = Components.creatingLabelForBackground();
        txtFeeback = Components.creatingTextField("your text here", 50., 50., 200., 450.);
        btnSubmit = Components.creatingButton("Submit", 228., 320., 25., 100.);
        btnClse = Components.createBtnClose();
        btnBk = Components.createBtnBack();
        Pane feedbackPane = new Pane();
        feedbackPane.getChildren().add(bgfd);
        feedbackPane.getChildren().add(lblFeedbackBG);
        feedbackPane.getChildren().add(txtFeeback);
        feedbackPane.getChildren().add(btnSubmit);
        feedbackPane.getChildren().add(btnClse);
        feedbackPane.getChildren().add(btnBk);
        btnBk.setOnAction(e -> {
            fdbkStage.close();
            Home.homeWindow();
        });
        btnClse.setOnAction(e -> {
            fdbkStage.close();
        });
        btnSubmit.setOnAction(e -> {
            submitting();
        });
        fdbkStage.setScene(new Scene(feedbackPane, 600, 400));
        fdbkStage.show();
    }

    //method for submit button
    public static void submitting() {
        String fv = txtFeeback.getText();
        Help.getCollection();
        Document feedbkData = new Document();
        feedbkData.put("Present Value (LKR)", fv);
        getCollection().insertOne(feedbkData);
        Components.information("Feedback Submitted");
        txtFeeback.clear();
    }

    //methods for all help windows in each calculators
    public static void helpWindow() {
        Stage helpStage = new Stage();
        helpStage.initStyle(StageStyle.UNDECORATED);
        Image image1 = new Image("Pics/help.jpg");
        ImageView bghelp = new ImageView();
        bghelp.setImage(image1);
        bghelp.setFitWidth(609);
        bghelp.setFitHeight(500);
        btnClse = Components.createBtnClose();
        btnBk = Components.createBtnBack();
        Pane helpPane = new Pane();
        helpPane.getChildren().add(bghelp);
        helpPane.getChildren().add(btnClse);
        helpPane.getChildren().add(btnBk);
        btnBk.setOnAction(e -> {
            helpStage.close();
            Home.homeWindow();
        });
        btnClse.setOnAction(e -> {
            helpStage.close();
        });
        helpStage.setScene(new Scene(helpPane, 600, 500));
        helpStage.show();
    }

    public static void helpWindowFix() {
        Stage helpStage = new Stage();
        helpStage.initStyle(StageStyle.UNDECORATED);
        Image image1 = new Image("Pics/helpFixed.jpg");
        ImageView bghelp = new ImageView();
        bghelp.setImage(image1);
        bghelp.setFitWidth(609);
        bghelp.setFitHeight(500);
        btnClse = Components.createBtnClose();
        Pane helpPane = new Pane();
        helpPane.getChildren().add(bghelp);
        helpPane.getChildren().add(btnClse);
        btnClse.setOnAction(e -> {
            helpStage.close();
        });
        helpStage.setScene(new Scene(helpPane, 600, 500));
        helpStage.show();
    }

    public static void helpWindowLoan() {
        Stage helpStage = new Stage();
        helpStage.initStyle(StageStyle.UNDECORATED);
        Image image1 = new Image("Pics/helpLoans.jpg");
        ImageView bghelp = new ImageView();
        bghelp.setImage(image1);
        bghelp.setFitWidth(609);
        bghelp.setFitHeight(500);
        btnClse = Components.createBtnClose();
        Pane helpPane = new Pane();
        helpPane.getChildren().add(bghelp);
        helpPane.getChildren().add(btnClse);
        btnClse.setOnAction(e -> {
            helpStage.close();
        });
        helpStage.setScene(new Scene(helpPane, 600, 500));
        helpStage.show();
    }

    public static void helpWindowSavings() {
        Stage helpStage = new Stage();
        helpStage.initStyle(StageStyle.UNDECORATED);
        Image image1 = new Image("Pics/helpSavings.jpg");
        ImageView bghelp = new ImageView();
        bghelp.setImage(image1);
        bghelp.setFitWidth(609);
        bghelp.setFitHeight(500);
        btnClse = Components.createBtnClose();
        Pane helpPane = new Pane();
        helpPane.getChildren().add(bghelp);
        helpPane.getChildren().add(btnClse);
        btnClse.setOnAction(e -> {
            helpStage.close();
        });
        helpStage.setScene(new Scene(helpPane, 600, 500));
        helpStage.show();
    }

    public static void helpWindowMortgage() {
        Stage helpStage = new Stage();
        helpStage.initStyle(StageStyle.UNDECORATED);
        Image image1 = new Image("Pics/helpMortgage.jpg");
        ImageView bghelp = new ImageView();
        bghelp.setImage(image1);
        bghelp.setFitWidth(609);
        bghelp.setFitHeight(500);
        btnClse = Components.createBtnClose();
        Pane helpPane = new Pane();
        helpPane.getChildren().add(bghelp);
        helpPane.getChildren().add(btnClse);
        btnClse.setOnAction(e -> {
            helpStage.close();
        });
        helpStage.setScene(new Scene(helpPane, 600, 500));
        helpStage.show();
    }

}
