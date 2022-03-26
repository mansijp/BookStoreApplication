package bookstorepackage;


import javafx.scene.layout.Pane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aaron
 */
public class CustomerState{
    private Customer customer;
    private MainClass main;
    
    // Also pass in the customer
    public CustomerState(MainClass main){
        this.main = main;
        CustomerStartScreen screen = new CustomerStartScreen(this);
        
        String name = "name";
        String password = "password";
        customer = new Customer(name, password);
        changeScreen(screen.display(customer));
    }
    
    public void changeScreen(Pane root){
        main.changeScreen(root);
    }
    
    public void logout(){
        // StoreState should have a changeState() method
        // that i can use here
        // change to noUserState which should display the login page
    }
    
    public void buyBooksWithMoney(){
        
    }
    
    public String getStatus(){
        if(customer.customerPoints < 1000)
            return "Silver";
        return "Gold";
    }
    
    public void buyBooksWithPoints(){
        
    }
}
