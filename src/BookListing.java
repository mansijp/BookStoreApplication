


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represents one row in the table of books for the customer start screen
 * @author aaron
 */
public class BookListing{
    public Book book;
    private StringProperty bookName;
    private DoubleProperty bookPrice;
    private BooleanProperty isSelected;
    
    public BookListing(String name, int price){
        bookName = new SimpleStringProperty(name);
        bookPrice = new SimpleDoubleProperty(price);
        isSelected = new SimpleBooleanProperty(false);
    }
    public StringProperty bookNameProperty() {return bookName;}
    public DoubleProperty bookPriceProperty() {return bookPrice;}
    public BooleanProperty isSelectedProperty() {return isSelected;}
}