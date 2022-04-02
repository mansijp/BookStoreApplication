import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static javafx.scene.text.TextAlignment.CENTER;

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
    
    private void logout(){
        store.setState(new NoUserState(store));
    }
    
    private void buyBooksWithMoney(){
        totalCost = getTotalCost();
        
        // $1 = +10 points 
        customer.setPoints(customer.getPoints() + (int)totalCost * 10);
    }
    
    private void buyBooksWithPoints(){
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
        ArrayList<BookListing> booksToBuy = new ArrayList<>();
        double cost = 0;
        for(BookListing book : books){
            if(book.isSelectedProperty().get()){
                booksToBuy.add(book);
                cost += book.bookPriceProperty().get();
            }
        }
        
        for(BookListing book : booksToBuy){
            store.books.remove(book.book);
            books.remove(book);
        }
        
        return cost;
    }
    
    private Pane customerCostScreen(){
        Text totalCostText = new Text();
        totalCostText.setText("Total Cost: " + totalCost);
        totalCostText.setStyle("-fx-font-weight: bold; -fx-text-inner-color: #4da8ab; -fx-font-size: 20px");
        
        Text pointsText = new Text();
        pointsText.setText("Points: " + customer.getPoints() 
                + ", Status: " + customer.getStatus() + "\n");
        pointsText.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
        
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setStyle("-fx-text-fill: #4da8ab; -fx-background-radius: 20px");
        logoutButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
        
        // Animations - logout Button
        logoutButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            logoutButton.setStyle("-fx-background-color: #4da8ab; -fx-text-fill: white; -fx-background-radius: 20px");
            logoutButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
          }
        });
        
        logoutButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            logoutButton.setStyle("-fx-text-fill: #4da8ab; -fx-background-radius: 20px");
            logoutButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
          }
        });
        
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(totalCostText);
        root.getChildren().add(pointsText);
        root.getChildren().add(logoutButton);

        logoutButton.setOnAction(event -> {
            logout();
        });
         
        logoutButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   logout();
                }
        });
       
        return root;
    }
    
    private ObservableList<BookListing> getBooks(){
        ObservableList<BookListing> books = FXCollections.observableArrayList();
        
        for(Book book : store.books)
            books.add(new BookListing(book));
        
        return books;
    }
    
    private Pane customerStartScreen(){
        Text welcomeText = new Text();
        welcomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        welcomeText.setText("\nWelcome " + customer.getName() 
                + ". You have " + customer.getPoints() + " points. "
                + "Your status is \n");
        
        Button status = new Button();
        status.setDisable(true);
        status.setText(customer.getStatus());
        status.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        status.setStyle("-fx-opacity: 1; -fx-background-radius: 12px; -fx-text-fill: black");
        if(customer.getStatus().equals("Silver")){
            status.setStyle("-fx-background-color: #cfd0d1; -fx-opacity: 1; -fx-background-radius: 12px; -fx-text-fill: black");
        } else if(customer.getStatus().equals("Gold")){
            status.setStyle("-fx-background-color: gold; -fx-opacity: 1; -fx-background-radius: 12px; -fx-text-fill: black");
        }
        
        HBox message = new HBox();
        message.getChildren().addAll(welcomeText, status);
        message.setAlignment(Pos.CENTER);
        
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
        bookPriceCol.setStyle("-fx-alignment: CENTER");
        
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
        
        // Animations - buyWithPointsButton Button
        buyWithPointsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            buyWithPointsButton.setStyle("-fx-background-color: #4da8ab; -fx-font-weight: 900; -fx-text-fill: white");
          }
        });
        
        buyWithPointsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            buyWithPointsButton.setStyle("-fx-text-fill: black");
          }
        });
        
        // Animations - buyButton Button
        buyButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            buyButton.setStyle("-fx-background-color: #4da8ab; -fx-font-weight: 900; -fx-text-fill: white");
          }
        });
        
        buyButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            buyButton.setStyle("-fx-text-fill: black");
          }
        });

        // Animations - Logout Button
        logoutButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            logoutButton.setStyle("-fx-background-color: #4da8ab; -fx-font-weight: 900; -fx-text-fill: white");
          }
        });
        
        logoutButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            logoutButton.setStyle("-fx-text-fill: black");
          }
        });

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(buyButton);
        buttons.getChildren().add(buyWithPointsButton);
        buttons.getChildren().add(logoutButton);
        
        Label statuslabel = new Label ("Please select a book.");
        statuslabel.setVisible(false);
        statuslabel.setFont(Font.font("Vardane",FontPosture.ITALIC, 12));
        statuslabel.setTextFill(Color.web("red"));
        statuslabel.setTextAlignment(CENTER);
        
        VBox root = new VBox(10);
        root.getChildren().add(message);
        root.getChildren().add(bookTable);
        root.getChildren().add(buttons);
        root.getChildren().add(statuslabel);
        root.setAlignment(Pos.TOP_CENTER);
        
        buyButton.setOnAction(event -> {
            if(!booksSelected()){
                statuslabel.setVisible(true);
                return;
            }
            buyBooksWithMoney();
            store.changeScreen(customerCostScreen());
        });
        buyButton.setOnKeyPressed(event -> {
            if(!booksSelected()){
                statuslabel.setVisible(true);
                return;
            }
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   buyBooksWithMoney();
                   store.changeScreen(customerCostScreen()); 
                }
        });
        
        buyWithPointsButton.setOnAction(event -> {
            if(!booksSelected()){
                statuslabel.setVisible(true);
                return;
            }
            buyBooksWithPoints();
            store.changeScreen(customerCostScreen());
        });
        
        buyWithPointsButton.setOnKeyPressed(event -> {
            if(!booksSelected()){
                statuslabel.setVisible(true);
                return;
            }
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   buyBooksWithPoints();
                   store.changeScreen(customerCostScreen());
                }
        });
        
        logoutButton.setOnAction(event -> {
            logout();
        });
        
       logoutButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   logout();
                }
        });
       
        return root;
    }
    
    private boolean booksSelected(){
        for(BookListing book : books){
            if(book.isSelectedProperty().get())
                return true;
        }
        return false;
    }
}
