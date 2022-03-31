


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aaron
 */
public class CustomerState extends StoreState{
    private Customer customer;
    private ObservableList<BookListing> books;
    private double totalCost;
    
    // Also pass in the customer
    public CustomerState(Store store, Customer customer){
        super(store);
        this.customer = customer;
                
        store.changeScreen(customerStartScreen());
    }
    
    public void logout(){
        store.setState(new NoUserState(store));
    }
    
    public void buyBooksWithMoney(){
        totalCost = getTotalCost();
        
        // $1 = +10 points 
        customer.setPoints(customer.getPoints() + (int)totalCost * 10);
    }
    
    public void buyBooksWithPoints(){
        totalCost = getTotalCost();
        
        // -100 points = $1
        while(customer.getPoints() >= 100 && totalCost > 0){
            customer.setPoints(customer.getPoints() - 100);
            totalCost -= 1;
        }
        
        // $1 = +10 points 
        customer.setPoints(customer.getPoints() + (int)totalCost * 10);
    }
    
    private double getTotalCost(){
        double cost = 0;
        for(BookListing book : books){
            if(book.isSelectedProperty().get())
                cost += book.bookPriceProperty().get();
        }
        return cost;
    }
    
    public Pane customerCostScreen(){
        Text totalCostText = new Text();
        totalCostText.setText("Total Cost: " + totalCost);
        
        Text pointsText = new Text();
        pointsText.setText("Points: " + customer.getPoints() 
                + ", Status: " + customer.getStatus());
        
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(totalCostText);
        root.getChildren().add(pointsText);
        root.getChildren().add(logoutButton);

        logoutButton.setOnAction(event -> {
            logout();
        });
        
        return root;
    }
    
    private ObservableList<BookListing> getBooks(){
        ObservableList<BookListing> books = FXCollections.observableArrayList();
        
        for(Book book : store.books)
            books.add(new BookListing(book));
        
        return books;
    }
    
    public Pane customerStartScreen(){
        Text welcomeText = new Text();
        welcomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        welcomeText.setText("Welcome " + customer.getName() 
                + ". You have " + customer.getPoints() + " points. "
                + "Your status is " + customer.getStatus()
        );
        
        //table with three columns,
        // book name, book price, select.
        // TableView<S>
        TableView<BookListing> bookTable = new TableView<>();
        books = getBooks();
        bookTable.setItems(books);
        
        TableColumn<BookListing,String> bookNameCol = new TableColumn<>("Book Name");
        bookNameCol.setCellValueFactory(new PropertyValueFactory("bookName"));
        
        TableColumn<BookListing,Integer> bookPriceCol = new TableColumn<>("Book Price");
        bookPriceCol.setCellValueFactory(new PropertyValueFactory("bookPrice"));
        
        TableColumn<BookListing,Boolean> isSelectedCol = new TableColumn<>("Select");
        isSelectedCol.setCellValueFactory(new PropertyValueFactory("isSelected"));
        isSelectedCol.setCellFactory(column -> new CheckBoxTableCell());

        bookTable.getColumns().setAll(bookNameCol, bookPriceCol, isSelectedCol);
        bookTable.setEditable(true);
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        // three buttons: buy, reedem points and buy, and logout
        Button buyButton = new Button();
        buyButton.setText("Buy");
        
        Button buyWithPointsButton = new Button();
        buyWithPointsButton.setText("Redeem points and Buy");
        
        Button logoutButton = new Button();
        logoutButton.setText("Logout");

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(buyButton);
        buttons.getChildren().add(buyWithPointsButton);
        buttons.getChildren().add(logoutButton);
        
        VBox root = new VBox(10);
        root.getChildren().add(welcomeText);
        root.getChildren().add(bookTable);
        root.getChildren().add(buttons);
        root.setAlignment(Pos.TOP_CENTER);
        
        buyButton.setOnAction(event -> {
            buyBooksWithMoney();
            store.changeScreen(customerCostScreen());
        });
        
        buyWithPointsButton.setOnAction(event -> {
            buyBooksWithPoints();
            store.changeScreen(customerCostScreen());
        });
        
        logoutButton.setOnAction(event -> {
            logout();
        });
       
        return root;
    }
}
