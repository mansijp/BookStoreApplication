


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
    private final StringProperty bookName;
    private final DoubleProperty bookPrice;
    private final BooleanProperty isSelected;
    
    public BookListing(Book book){
        bookName = new SimpleStringProperty(book.getName());
        bookPrice = new SimpleDoubleProperty(book.getPrice());
        isSelected = new SimpleBooleanProperty(false);
    }
    public StringProperty bookNameProperty() {return bookName;}
    public DoubleProperty bookPriceProperty() {return bookPrice;}
    public BooleanProperty isSelectedProperty() {return isSelected;}
}