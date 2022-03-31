

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author husam
 */
public class Customer {
    int customerPoints;
    String customerName = "";
    String customerPassword = "";
    String customerStatus = "";
    Customer(String name, String password){
        customerName = name;
        customerPassword = password;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getCustomerPassword() {
        return customerPassword;
    }
    
    public double getCustomerPoints(){
        return customerPoints;
    }
}
