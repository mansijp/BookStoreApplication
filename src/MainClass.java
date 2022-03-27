


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author aaron
 */
public class MainClass extends Application {
    private Scene scene;
    
    public void start(Stage primaryStage) {  
        StackPane root = new StackPane();

        scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        CustomerState state = new CustomerState(this);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // use this to switch screens
    
    //stage is the actual window
    //Switching root node keeps eventhandlers intact
    //switching scenes unlinks eventhandlers -> may be better if we run into issues
    
    public void changeScreen(Pane root){
        scene.setRoot(root);       
    }
}
