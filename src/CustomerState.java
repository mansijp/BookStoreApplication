


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
        
        String name = "name";
        String password = "password";
        customer = new Customer(name, password);
                
        store.changeScreen(customerStartScreen());
    }
    
    public void logout(){
        store.setState(new NoUserState(store));
    }
    
    public void buyBooksWithMoney(){
        totalCost = getTotalCost();
        
        // $1 = +10 points 
        customer.customerPoints += totalCost * 10;
    }
    
    public void buyBooksWithPoints(){
        totalCost = getTotalCost();
        
        // -100 points = $1
        while(customer.customerPoints >= 100 && totalCost > 0){
            customer.customerPoints -= 100;
            totalCost -= 1;
        }
        
        // $1 = +10 points 
        customer.customerPoints += totalCost * 10;
    }
    
    public String getStatus(){
        if(customer.customerPoints < 1000)
            return "Silver";
        return "Gold";
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
        pointsText.setText("Points: " + customer.customerPoints 
                + ", Status: " + getStatus());
        
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
        
        books.add(new BookListing("book1a", 200));
        books.add(new BookListing("book1b", 500));
        books.add(new BookListing("book2", 50));
        books.add(new BookListing("book3", 100));
        
        //for(int i = 0; i < 20; i++)
        //    books.add(new BookListing("harry potter " + i, 200));
        
        return books;
    }
    
    public Pane customerStartScreen(){
        Text welcomeText = new Text();
        welcomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        welcomeText.setText("Welcome " + customer.customerName 
                + ". You have " + customer.customerPoints + " points. "
                + "Your status is " + customer.customerStatus
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
