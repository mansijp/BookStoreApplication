package bookstorepackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mansi
 */

import java.util.ArrayList;
        
public class NoUserState extends StoreState {
    
    private NoUserState(){}
    
    public void login(Store store, String username, String password){
                
        if(username.equals("admin") && password.equals("admin")){
            store.state = (OwnerState) store.state;
            
        } else {
            for(Customer customer: store.customers){
                if(customer.customerName.equals(username) && customer.customerPassword.equals(password)){
                    store.state = (CustomerState) store.state;
                }
            }
        }
    }
}
