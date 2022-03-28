

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mansi
 */

import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

        
public class NoUserState extends StoreState {
    
    //private Main3 main;
    
    protected NoUserState(){
        
    }
    
    public void login(Store store, String username, String password, Pane root){
                
        if(username.equals("admin") && password.equals("admin")){
            store.state = (OwnerState) store.state;
            //changeScreen(root);
            
        } else {
            
            for(Customer customer: store.customers){
                if(customer.customerName.equals(username) && customer.customerPassword.equals(password)){
                    store.state = (CustomerState) store.state;
                    //changeScreen(root);
                }
            }
            
        }
    }
    
    
    public Pane loginScreen(Stage primaryStage, Store store) {
        
        
        
        // Table
        

        // button
        Button loginBtn = new Button("Login");
        loginBtn.setDefaultButton(false);
        loginBtn.setOnAction((ActionEvent e) -> {
            if(store.state instanceof OwnerState){
                //main.changeScreen(loginPane); // owner screen
            } else if(store.state instanceof CustomerState){
                //main.changeScreen(loginPane); // customer screen
            }
        });
        
        VBox vbox = new VBox(50);
        vbox.getChildren().add(loginBtn);
        
        return vbox;
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
