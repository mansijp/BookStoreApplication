

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
    private int points;
    private String name = "";
    private String password = "";
    private String status = "";
    
    Customer(String name, String password){
        this.name = name;
        this.password = password;
        points = 0;
        setStatus();
    }
    
    Customer(String name, int point, String password){
        this.name = name;
        this.password = password;
        points = point;
        setStatus();
    }
    
    public String getName() {
        return name;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getStatus(){
        return status;
    }
    
    void setStatus(){
        if(this.points < 1000){
            status = "Silver";
        }else{status = "Gold";}
    }
}
