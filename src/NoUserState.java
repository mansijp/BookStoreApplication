

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mansi
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
     
public class NoUserState extends StoreState {
    
    protected NoUserState(Store store){
        super(store);
    }
    
    public void login(Store store, String username, String password, Pane root){
                
        /*if(username.equals("admin") && password.equals("admin")){
            store.state = (OwnerState) store.state;
            //changeScreen(root);
            store.changeScreen(root);
            
        } else {
            
            for(Customer customer: store.customers){
                if(customer.customerName.equals(username) && customer.customerPassword.equals(password)){
                    store.state = (CustomerState) store.state;
                    store.changeScreen(root);
                }
            }
            
        }*/
    }
    
    
    public Pane loginScreen(Store store) {
        // Table
        

        // button
        Button loginBtn = new Button("Login");
        /*loginBtn.setDefaultButton(false);
        loginBtn.setOnAction((ActionEvent e) -> {
            if(store.state instanceof OwnerState){
                //main.changeScreen(loginPane); // owner screen
            } else if(store.state instanceof CustomerState){
                //main.changeScreen(loginPane); // customer screen
            }
        });*/
        
        VBox vbox = new VBox(20);
        vbox.getChildren().add(loginBtn);
        
        return vbox;
    }
        

    @Override
    public Pane logInScreen(){
       
        Label welcome = new Label ();
        welcome.setFont(Font.font("Vardane", 26));
        HBox usernamehbox = new HBox(10);
        welcome.setText("Welcome. Please Login");
        Label namelabel = new Label ("UserName");
        TextField namefield = new TextField();
        namefield.setPrefColumnCount(20);
        namefield.setPromptText("Enter your User Name");
        usernamehbox.getChildren().addAll(namelabel, namefield);
        HBox passhbox = new HBox(10);
        Label pwrdlabel = new Label ("Password");
        TextField passwordField = new PasswordField();
        //passwordField.setHeight(10);
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
       
        return loginstart;
    }
}
