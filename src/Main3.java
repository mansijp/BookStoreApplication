/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mansi
 */
public class Main3 extends Application{

    private Scene scene;
    
    @Override
    public void start(Stage primaryStage) {  
        StackPane loginRoot = new StackPane();

        scene = new Scene(loginRoot, 500, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        NoUserState defaultState = new NoUserState(this);
    }
    
    public void changeScreen(Pane root){
        scene.setRoot(root);       
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    // combine login screen and nouserstate classes
}