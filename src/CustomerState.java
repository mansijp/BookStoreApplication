


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
    
    // Also pass in the customer
    public CustomerState(Store store){
        super(store);
        
        String name = "name";
        String password = "password";
        customer = new Customer(name, password);
        
        changeScreen(customerCostScreen());
    }
    
    public void changeScreen(Pane root){
        store.changeScreen(root);
    }
    
    public void logout(){
        store.setState(new NoUserState(store));
    }
    
    public void buyBooksWithMoney(){
        
    }
    
    public String getStatus(){
        if(customer.customerPoints < 1000)
            return "Silver";
        return "Gold";
    }
    
    public void buyBooksWithPoints(){
        
    }
    
    public Pane customerCostScreen(){       
        Text totalCostText = new Text();
        totalCostText.setText("Total Cost: ");
        
        Text pointsText = new Text();
        pointsText.setText("Points: " + customer.customerPoints 
                + ", Status:" + customer.customerStatus);
        
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
        books = FXCollections.observableArrayList();
        
        for(int i = 0; i < 20; i++)
            books.add(new BookListing("harry potter " + i, 200));
        
        return books;
    }
    
    public Pane customerStartScreen(){
        Text welcomeText = new Text();
        welcomeText.setText("Welcome " + customer.customerName 
                + ". You have " + customer.customerPoints + " points. "
                + "Your status is " + customer.customerStatus
        );
        
        //table with three columns,
        // book name, book price, select.
        // TableView<S>
        TableView<BookListing> bookTable = new TableView<>();
        ObservableList<BookListing> books = getBooks();
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
            changeScreen(customerCostScreen());
        });
        
        buyWithPointsButton.setOnAction(event -> {
            changeScreen(customerCostScreen());
        });
        
        logoutButton.setOnAction(event -> {
            logout();
        });
       
        return root;
    }
}
