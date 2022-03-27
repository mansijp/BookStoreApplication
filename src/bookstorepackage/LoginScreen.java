/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstorepackage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author mansi
 */


public class LoginScreen extends Application {
    private Main3 main;
    
    public LoginScreen(Main3 m){
        main = m;
    }
    
    @Override
    public void start(Stage primaryStage, CustomerStartScreen store) {
        
        // grid
        GridPane loginPane = new GridPane();
        
        // alignmentand position
        loginPane.setHgap(50);
        loginPane.setVgap(50);
        
        // elements
        Button loginBtn = new Button("Login");
        loginBtn.setDefaultButton(false);
        if(loginBtn.isArmed()){ 
            loginBtn.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e){
                    Stage stage = new Stage();
                    stage.changeScreen(root);
                    stage.show();
                }
            });
            loginBtn.disarm();
        }
        
        // scene
        Scene scene = new Scene(loginPane, 500, 500, Color.rgb(100, 100, 100));
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    // Button ActionEvent
    public void Login(Store store){
        if(store.state instanceof OwnerState){
            
        } else if(store.state instanceof CustomerState){
            
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
