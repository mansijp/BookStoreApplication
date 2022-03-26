/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstorepackage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author cyril
 */
public class Main2 extends Application {

    
    private Scene scene;
    
    public void start(Stage primaryStage) {  
        StackPane ownercust = new StackPane();
        OwnerState state;
        Store store = new Store();
        scene = new Scene(ownercust, 700, 700);
        Customer c = new Customer ("jean","jb");
        store.customers.add(c);
        Customer c1 = new Customer ("jeasn","jfb");
        store.customers.add(c1);
        Customer c2 = new Customer ("jeafsn","jbf");
        store.customers.add(c2);
        primaryStage.setTitle("Owner_Customer_Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        state = OwnerState.getInstance();
        changeScreen(state.ownerCustomerScreen(store));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // use this to switch screens
    
    //stage is the actual window
    //Switching root node keeps eventhandlers intact
    //switching scenes unlinks eventhandlers -> may be better if we run into issues
    
    public void changeScreen(Pane ownerstart){
        scene.setRoot(ownerstart);       
    }
}