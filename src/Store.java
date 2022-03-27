/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    protected Store(){    }
    
    // Methods
   /* 
    public void loadData(){
        
    }
    
    public void saveData(){
         
    }
    */
    public void setState(StoreState state){
        this.state = state;
    }
    public void main(String[] args){
        
        Store bookstoreApplication = new Store();
        //bookstoreApplication.launch();
        
    }

     
}
