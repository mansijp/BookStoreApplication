

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
        customerPoints = 0;
        setStatus();
    }
    Customer(String name, String password, int point){
        customerName = name;
        customerPassword = password;
        customerPoints = point;
        setStatus();
    }
    
    public String getCustomerName() {
        return customerName;
    }
    public int getCustomerPoints() {
        return customerPoints;
    }
    public String getCustomerPassword() {
        return customerPassword;
    }
    void setStatus(){
        if(this.customerPoints < 1000){
            customerStatus = "Silver";
        }else{customerStatus = "Gold";}
    }
}
