/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cyrille
 */


public class OwnerState {
    //Overview: OwnerState is immutable singleton class
    //
    //The abstraction function is:
    //
    // The rep invarian is: 
    // The rep
    private static OwnerState s = null;
    private String username = "admin";
    private String password = "admin";
    
    private OwnerState(){
        
    }
    public static OwnerState getInstance(){
        if (s == null){
            s = new OwnerState();
        }
        return s;
    }
    
    public void logout(){
        
    }
    public void addBook(Book book){}
    public void removeBook(Book book){}
    public void registerNewCustomer(String name, String password){}
    public void deleteCustomer(String name, String password){}
    
    
}
