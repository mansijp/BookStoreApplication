
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Husam
 */
public abstract class StoreState {
    protected Store store;
    
    public StoreState(Store store){
        this.store = store;
    }
    
    public Pane logInScreen(){
        Pane root = new StackPane();
        return root;
    }
}
