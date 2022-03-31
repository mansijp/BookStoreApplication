

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
    
    Customer(String name, String password){
        this.name = name;
        this.password = password;
        points = 0;
    }
    
    Customer(String name, int point, String password){
        this.name = name;
        this.password = password;
        points = point;
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
        if(this.points < 1000)
            return "Silver";
        else
            return "Gold";
    }
}
