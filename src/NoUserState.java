

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
        
public class NoUserState extends StoreState {
    
    protected NoUserState(Store store){
        super(store);
        //LoginScreen screen = new LoginScreen(this);
    }
    
    public void login(Store store, String username, String password, Pane root){
                
        if(username.equals("admin") && password.equals("admin")){
            store.state = (OwnerState) store.state;
            store.changeScreen(root);
            
        } else {
            
            for(Customer customer: store.customers){
                if(customer.customerName.equals(username) && customer.customerPassword.equals(password)){
                    store.state = (CustomerState) store.state;
                    store.changeScreen(root);
                }
            }
            
        }
    }
    public Pane logInScreen(){
       
        Label namelabel = new Label ("UserName");
        TextField namefield = new TextField("Enter your Username");
        Label pwrdlabel = new Label ("Password");
        TextField passwordField = new PasswordField();
        passwordField.setText("password");

        HBox logindata = new HBox(20);
        logindata.setAlignment(Pos.CENTER);
        logindata.getChildren().addAll(namelabel, namefield,pwrdlabel,passwordField);
        
        Button loginButton = new Button();
        loginButton.setText("LogIn");

        VBox loginstart = new VBox(20);
        loginstart.setAlignment(Pos.CENTER);
        loginstart.getChildren().add(logindata);
        loginstart.getChildren().add(loginButton);
       
        return loginstart;
    }
    
//    public void changeScreen(Pane root){
//        store.changeScreen(root);
//    }
}
