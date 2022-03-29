
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Mansi
 */

public class Store extends Application{
    private Scene scene;
    protected ArrayList <Book> books =  new ArrayList<>();
    protected ArrayList <Customer> customers =  new ArrayList<>();
    protected StoreState state = null;
    
    // Constructor
    public Store(){ }
    
    // Methods
   /* 
    public void loadData(){
        
    }
    
    public void saveData(){
         
    }
    */
    
    public void start(Stage primaryStage) {  
        StackPane root = new StackPane();

        scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Book Store");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.setState(new NoUserState(this));
        changeScreen(state.logInScreen());
    }
    
    public static void main(String[] args){
        launch(args);
    }

    // set states in this class and don't typecast the state variable
    public void setState(StoreState state){
        this.state = state;
    }
    
    public void changeScreen(Pane root){
        scene.setRoot(root);
    }
}
