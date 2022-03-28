/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author mansi
 */


public class LoginScreen {
    
    private NoUserState noUser;
    
    public LoginScreen(NoUserState noUser){
        this.noUser = noUser;   //combined classes for easy access
    }

    public void start(Stage primaryStage, Store store) {
        
        // grid
        StackPane loginPane = new StackPane();
        
        // scene
        Scene scene = new Scene(loginPane, 500, 500, Color.LIGHTGRAY);

        // alignment and position
        loginPane.setAlignment(Pos.CENTER);
        
        // Table
        

        // button
        Button loginBtn = new Button("Login");
        loginBtn.setDefaultButton(false);
        loginBtn.setOnAction((ActionEvent e) -> {
            if(store.state instanceof OwnerState){
                //noUser.changeScreen(loginPane); // owner screen
            } else if(store.state instanceof CustomerState){
                //noUser.changeScreen(loginPane); // customer screen
            }
        });
        
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
    // complete the table and button functionality
    // code can build and run
}
