
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private IntegerProperty bookPrice;
    private BooleanProperty isSelected;
    
    public BookListing(String name, int price){
        bookName = new SimpleStringProperty(name);
        bookPrice = new SimpleIntegerProperty(price);
        isSelected = new SimpleBooleanProperty(false);
    }
    public StringProperty bookNameProperty() {return bookName;}
    public IntegerProperty bookPriceProperty() {return bookPrice;}
    public BooleanProperty isSelectedProperty() {return isSelected;}
}