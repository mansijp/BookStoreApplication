/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstorepackage;
import java.util.ArrayList;

/**
 *
 * @author Mansi
 */

public class Store {
    protected ArrayList <Book> books =  new ArrayList<>();
    protected ArrayList <Customer> customers =  new ArrayList<>();
    protected StoreState state = null;
    
    // Constructor
    private Store(){    }
    
    // Methods
   /* public void setState(StoreState state){
        this.state = state;
        this.loadData();
    }
    public StoreState getState(){
        return state;
    }
    
    public void loadData(){
         
    }
    
    public void saveData(){
         
    }*/
     
}
