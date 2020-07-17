package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.text.DecimalFormat;

public class Home extends Application {
    //variables for buttons and components
    public static Button btnFD;
    public static Button btnSavings;
    public static Button btnMortgage;
    public static Button btnLoan;
    public static Label lblHome;
    public static Button btnFDName;
    public static Button btnSavingsName;
    public static Button btnMortgageName;
    public static Button btnLoanName;
    public static Button btnClse;
    public static Button btnHelp;
    public static Button btnFeedback;
    //format decimal 2 numbers
    public static DecimalFormat df2 = new DecimalFormat("0.00");

    //home window calling
    @Override
    public void start(Stage primaryStage) {
        //calling method
        homeWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void homeWindow() {
        //creating stage and disable title bar
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //adding images and give sizes and (x,y)
        Image image1 = new Image("Pics/HomeBG.jpg");
        ImageView homeBG = new ImageView();
        homeBG.setImage(image1);
        homeBG.setFitWidth(609);
        homeBG.setFitHeight(416);

        Image imageGif = new Image("Pics/hmm.gif");
        ImageView homeBGGif = new ImageView();
        homeBGGif.setImage(imageGif);
        homeBGGif.setFitWidth(609);
        homeBGGif.setFitHeight(416);
        homeBGGif.setOpacity(.8);

        Image image2 = new Image("Pics/Dixed Deposit.png");
        ImageView btnFDPng = new ImageView();
        btnFDPng.setImage(image2);
        btnFDPng.setFitWidth(82);
        btnFDPng.setFitHeight(84);

        Image image3 = new Image("Pics/savings.png");
        ImageView btnSavingsPng = new ImageView();
        btnSavingsPng.setImage(image3);
        btnSavingsPng.setFitWidth(82);
        btnSavingsPng.setFitHeight(90);

        Image image4 = new Image("Pics/Mortgage.png");
        ImageView btnMortgagePng = new ImageView();
        btnMortgagePng.setImage(image4);
        btnMortgagePng.setFitWidth(82);
        btnMortgagePng.setFitHeight(84);

        Image image5 = new Image("Pics/loan.png");
        ImageView btnLoanPng = new ImageView();
        btnLoanPng.setImage(image5);
        btnLoanPng.setFitWidth(82);
        btnLoanPng.setFitHeight(84);

        Image image6 = new Image("Pics/Feedback.png");
        ImageView btnFedbk = new ImageView();
        btnFedbk.setImage(image6);
        btnFedbk.setFitWidth(40);
        btnFedbk.setFitHeight(40);

        Image image7 = new Image("Pics/Financial cal.png");
        ImageView nameImg = new ImageView();
        nameImg.setImage(image7);
        nameImg.setFitWidth(200);
        nameImg.setFitHeight(100);
        nameImg.setLayoutX(190);
        nameImg.setLayoutY(-15);
        nameImg.setOpacity(0.5);

        //Creating buttons from components class and giving parameter values
        btnFD = Components.creatingButton("", 125., 63., 90., 82.);
        btnFD.setGraphic(btnFDPng);
        btnSavings = Components.creatingButton("", 357., 63., 90., 82.);
        btnSavings.setGraphic(btnSavingsPng);
        btnMortgage = Components.creatingButton("", 125., 222., 90., 82.);
        btnMortgage.setGraphic(btnMortgagePng);
        btnLoan = Components.creatingButton("", 357., 222., 90., 82.);
        btnLoan.setGraphic(btnLoanPng);
        btnClse = Components.createBtnClose();
        btnHelp = Components.createBtnHelp();
        btnFeedback = Components.createBtnHelp();
        btnFeedback.setGraphic(btnFedbk);
        btnFeedback.setLayoutY(350);
        btnFeedback.setLayoutX(548);

        //label for dark apperaence for home window
        lblHome = Components.creatingLabelForBackground();
        lblHome.setOpacity(0.65);
        //creating labels for each buttons and set style for them
        btnFDName = Components.creatingButton("Fixed Deposit", 74., 155., 50., 200.);
        btnFDName.setStyle("-fx-background-color:transparent; -fx-text-fill:#f5f7f7;-fx-font-size:1.5em;");
        btnSavingsName = Components.creatingButton("Savings", 306., 155., 50., 200.);
        btnSavingsName.setStyle("-fx-background-color:transparent; -fx-text-fill:#f5f7f7;-fx-font-size:1.5em;");
        btnMortgageName = Components.creatingButton("Mortgage", 74., 312., 50., 200.);
        btnMortgageName.setStyle("-fx-background-color:transparent; -fx-text-fill:#f5f7f7;-fx-font-size:1.5em;");
        btnLoanName = Components.creatingButton("Loan", 306., 312., 50., 200.);
        btnLoanName.setStyle("-fx-background-color:transparent; -fx-text-fill:#f5f7f7;-fx-font-size:1.5em;");
        //adding all elements in to homepane in relevant oder
        Pane homePane = new Pane();
        homePane.getChildren().add(homeBG);
        homePane.getChildren().add(homeBGGif);
        homePane.getChildren().add(lblHome);
        homePane.getChildren().add(btnFeedback);
        homePane.getChildren().add(nameImg);
        homePane.getChildren().add(btnFDName);
        homePane.getChildren().add(btnSavingsName);
        homePane.getChildren().add(btnMortgageName);
        homePane.getChildren().add(btnLoanName);
        homePane.getChildren().add(btnFD);
        homePane.getChildren().add(btnSavings);
        homePane.getChildren().add(btnMortgage);
        homePane.getChildren().add(btnLoan);
        homePane.getChildren().add(btnClse);
        homePane.getChildren().add(btnHelp);

        //giving action methods for each buttons in homepage
        btnFD.setOnAction(e -> {
            primaryStage.close();
            FixDeposit.fixDepositWindow();
        });
        btnSavings.setOnAction(e -> {
            primaryStage.close();
            Savings.savingsWindow();
        });
        btnMortgage.setOnAction(e -> {
            primaryStage.close();
            Mortgage.mortgageWindow();

        });
        btnLoan.setOnAction(e -> {
            primaryStage.close();
            Loans.loansWindow();

        });
        btnClse.setOnAction(e -> {
            primaryStage.close();
        });
        btnFeedback.setOnAction(e -> {
            primaryStage.close();
            Help.feedbackWindow();
        });
        btnFeedback.setOnAction(e -> {
            primaryStage.close();
            Help.feedbackWindow();
        });
        btnHelp.setOnAction(e -> {
            primaryStage.close();
            Help.helpWindow();
        });
        primaryStage.setScene(new Scene(homePane, 600, 400));
        primaryStage.show();
    }
}
