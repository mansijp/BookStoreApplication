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
    static ArrayList <Book> books =  new ArrayList<>();
    static ArrayList <Customer> customers =  new ArrayList<>();
    StoreState state= null;
    public Store(){
        
    }
    public void setState(StoreState state){
        this.state = state;
        this.loadData();
    }
    public StoreState getState(){
        return state;
    }
    
    public void loadData(){
         
    }
    public void saveData(){
         
    }
     
}
