

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mansi
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import static javafx.scene.text.TextAlignment.CENTER;
     
public class NoUserState extends StoreState {
    
    protected NoUserState(Store store){
        super(store);
        store.changeScreen(logInScreen());
    }
    protected NoUserState(int i, Store store){
        super(store);
        store.changeScreen(logInScreen2());
    }

    @Override
    public Pane logInScreen(){
       
        Label welcome = new Label ();
        welcome.setFont(Font.font("Vardane", 26));
        HBox usernamehbox = new HBox(10);
        welcome.setText("Welcome to the Bookstore App. Please Login!");
        Label namelabel = new Label ("Username");
        TextField namefield = new TextField();
        namefield.setPrefColumnCount(20);
        namefield.setPromptText("Enter your User Name");
        usernamehbox.getChildren().addAll(namelabel, namefield);
        HBox passhbox = new HBox(10);
        Label pwrdlabel = new Label ("Password");
        TextField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(20);
        passwordField.setPromptText("Enter your Password");
        
        passhbox.getChildren().addAll(pwrdlabel,passwordField);
        
        HBox logindata = new HBox(60);
        logindata.setAlignment(Pos.CENTER);
        logindata.getChildren().addAll(usernamehbox,passhbox);
        
        Button loginButton = new Button();
        loginButton.setText("LogIn");
        loginButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));

        VBox loginstart = new VBox(40);
        loginstart.setAlignment(Pos.CENTER);
        loginstart.getChildren().add(welcome);
        loginstart.getChildren().add(logindata);
        loginstart.getChildren().add(loginButton);
        
        loginButton.setOnAction(event -> {
            String username = namefield.getText();
            String password = passwordField.getText();
            
            if (username.equals("admin") && password.equals("admin")) {
                store.setState(new OwnerState(store));
            } else {
                boolean found = false;
                for (Customer customer : store.customers) {
                    if (customer.getName().equals(username) && customer.getPassword().equals(password)) {                        
                        found = true;
                        store.setState(new CustomerState(store, customer));
                    }
                }
                if (found == false){
                    store.setState(new NoUserState(1, store));
                }
            }
        });
       
        return loginstart;
    }
    
    public Pane logInScreen2(){
       
        Label welcome = new Label ();
        welcome.setFont(Font.font("Vardane", 26));
        welcome.setText("Welcome to the Bookstore App. Please Login!");
        
        
        Label namelabel = new Label ("Username");
        TextField namefield = new TextField();
        namefield.setPrefColumnCount(20);
        namefield.setPromptText("Enter your User Name");
        
        HBox usernamehbox = new HBox(10);
        usernamehbox.getChildren().addAll(namelabel, namefield);
        
        Label pwrdlabel = new Label ("Password");
        TextField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(20);
        passwordField.setPromptText("Enter your Password");
        
        HBox passhbox = new HBox(10);
        passhbox.getChildren().addAll(pwrdlabel,passwordField);
        
        HBox logindata = new HBox(60);
        logindata.setAlignment(Pos.CENTER);
        logindata.getChildren().addAll(usernamehbox,passhbox);
        
        Label statuslabel = new Label ("Invalid Username or Password!");
        statuslabel.setPrefHeight(40);
        statuslabel.setFont(Font.font("Vardane",FontPosture.ITALIC, 12));
        statuslabel.setTextFill(Color.web("red"));
        statuslabel.setTextAlignment(CENTER);
        
        VBox middleBox = new VBox(10);
        middleBox.setAlignment(Pos.CENTER);
        middleBox.getChildren().addAll(logindata,statuslabel);
        
        Button loginButton = new Button();
        loginButton.setText("LogIn");
        loginButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));

        VBox loginstart = new VBox(40);
        loginstart.setAlignment(Pos.CENTER);
        loginstart.getChildren().add(welcome);
        loginstart.getChildren().add(middleBox);
        loginstart.getChildren().add(loginButton);
        
        loginButton.setOnAction(event -> {
            String username = namefield.getText();
            String password = passwordField.getText();
            
            if (username.equals("admin") && password.equals("admin")) {
                store.setState(new OwnerState(store));
            } else {
                boolean found = false;
                for (Customer customer : store.customers) {
                    if (customer.getName().equals(username) && customer.getPassword().equals(password)) {                        
                        found = true;
                        store.setState(new CustomerState(store, customer));
                    }
                }
                if (found == false){
                    store.setState(new NoUserState(1, store));
                }
            }
        });
       
        return loginstart;
    }
}
