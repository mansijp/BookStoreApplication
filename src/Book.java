

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mansi
 */
public class Book {
    private String name;
    private double price;
    
    public Book (String bookname, double bookprice){
        name = bookname;
        price = bookprice;
    }
    
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
