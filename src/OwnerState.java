/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cyrille
 */

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class OwnerState extends StoreState{
    //Overview: OwnerState is immutable singleton class
    //
    //The abstraction function is:
    //
    // The rep invarian is: 
    // The rep
    
    public OwnerState(Store store){
        super(store);
        store.changeScreen(ownerStartScreen());
    }

    private void logOut(Store store){
        StoreState st = new NoUserState(store);
        store.setState(st);
    }
    
    private void addBook(Store store, Book book){
        store.books.add(book);
        store.changeScreen(ownerBooksScreen(store));
    }
    
    private void removeBook(Store store, String bookname, double bookprice){
        Book bookToDelete = null;
        for (Book book:store.books){
            if ((book.getName().equals(bookname)) && (book.getPrice() == bookprice))
                bookToDelete = book;
        }
        
        if(bookToDelete != null)
            store.books.remove(bookToDelete);
    }
    
    private void registerNewCustomer(Store store, String name, String password) {
        Customer customer = new Customer(name, password);
        boolean found = false;
        int size = store.customers.size();
        int i = 0;
        while (found==false && (i < size)) {
            Customer cst = store.customers.get(i);
            if ((cst.getName().equals(name)) && (cst.getPassword().equals(password))) {
                found = true;
                System.out.println("Customer already in Store Customers list");
            }
            i ++;
        }
        if (found == false) {
            store.customers.add(customer);
            store.changeScreen(ownerCustomerScreen(store));
        }
    }
    
    private void deleteCustomer(Store store, String name, String password){
        Customer customerToDelete = null;
        for (Customer customer:store.customers){
            if ((customer.getName().equals(name)) && (customer.getPassword().equals(password)))
                customerToDelete = customer;
        }
        
        if(customerToDelete != null)
            store.customers.remove(customerToDelete);
    }
    
    private Pane ownerStartScreen(){
        // three buttons: Books, Customers, and Logout
        Button booksButton = new Button();
        booksButton.setText("Books");
        booksButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
        booksButton.setMinSize(250, 30);
        
        Button customersButton = new Button();
        customersButton.setText("Customers");
        customersButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
        customersButton.setMinSize(250, 30);
        
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
        logoutButton.setMinSize(250, 30);
        logoutButton.setFocusTraversable(false);

        VBox ownerstart = new VBox(20);
        ownerstart.setAlignment(Pos.CENTER);
        ownerstart.setSpacing(35);
        ownerstart.getChildren().add(booksButton);
        ownerstart.getChildren().add(customersButton);
        ownerstart.getChildren().add(logoutButton);
        
        //Button functionality
        customersButton.setOnAction(event -> {
            store.changeScreen(ownerCustomerScreen(store));
        });
        
        customersButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                    store.changeScreen(ownerCustomerScreen(store));
                }
        });
        
        booksButton.setOnAction(event -> {
            store.changeScreen(ownerBooksScreen(store));
        });
        
        booksButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                    store.changeScreen(ownerBooksScreen(store));
                }
        });
                
        logoutButton.setOnAction(event -> {
            logOut(store);
        });
        
        logoutButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                    event.consume();
                    logOut(store);
                }
        });
        
        // Animations - Books Button
        booksButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            booksButton.setMinSize(300, 50);
            booksButton.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
            booksButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold; -fx-font-size: 25px;"
            + "-fx-border-width: 3px; -fx-border-color: #4da8ab; -fx-border-radius: 15px; -fx-background-radius: 15px");
          }
        });
        
        booksButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            booksButton.setMinSize(250, 30);
            booksButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            booksButton.setStyle("-fx-text-fill: #000000");
          }
        });
        
        // Animations - Customers Button
        customersButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            customersButton.setMinSize(300, 50);
            customersButton.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
            customersButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold; -fx-font-size: 25px;"+
            "-fx-border-width: 3px; -fx-border-color: #4da8ab; -fx-border-radius: 15px; -fx-background-radius: 15px");
          }
        });
        
        customersButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            customersButton.setMinSize(250, 30);
            customersButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            customersButton.setStyle("-fx-text-fill: #000000");
          }
        });
        
        // Animations - Logout Button
        logoutButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            logoutButton.setMinSize(300, 50);
            logoutButton.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
            logoutButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold; -fx-font-size: 25px;" +
                    "-fx-border-width: 3px; -fx-border-color: #4da8ab; -fx-border-radius: 15px; -fx-background-radius: 15px");
          }
        });
        
        logoutButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            logoutButton.setMinSize(250, 30);
            logoutButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            logoutButton.setStyle("-fx-text-fill: #000000");
          }
        });
        
        return ownerstart;
    }
    
    //Owner start screen events
    private Pane ownerCustomerScreen(Store store){
                
        //table with three columns,
        // book name, book price, select.
        // TableView<S>
        TableView<Customer> customerTable = new TableView<>();
        ObservableList<Customer> customers = FXCollections.observableArrayList(store.customers);
        customerTable.setItems(customers);
        
        TableColumn<Customer,String> customerNameCol = new TableColumn<>("Username");
        customerNameCol.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Customer, String> str){
                return new ReadOnlyObjectWrapper(str.getValue().getName());
            }
        });
        
        TableColumn<Customer,String> customerPassCol = new TableColumn<>("Password");
        //customerPassCol.setCellValueFactory(new PropertyValueFactory("customerPassword"));
        customerPassCol.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Customer, String> str){
                return new ReadOnlyObjectWrapper(str.getValue().getPassword());
            }
        });
        
        TableColumn<Customer,Integer> customerPointsCol = new TableColumn<>("Points");
        customerPointsCol.setCellValueFactory(new PropertyValueFactory("customerPoints"));
        customerPointsCol.setCellValueFactory(new Callback<CellDataFeatures<Customer, Integer>, ObservableValue<Integer>>(){
            public ObservableValue<Integer> call(CellDataFeatures<Customer, Integer> str){
                return new ReadOnlyObjectWrapper(str.getValue().getPoints());
            }
        });

        customerTable.getColumns().setAll(customerNameCol, customerPassCol, customerPointsCol);
        customerTable.setEditable(true);
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Add customer part of windows
        Label namelabel = new Label ("Username");
        TextField namefield = new TextField();
        namefield.setPromptText("New Customer's Username");
        namefield.setMinWidth(170);
        Label pwrdlabel = new Label ("Password");
        TextField passwordfield = new TextField();
        passwordfield.setPromptText("New Customer's Password");
        passwordfield.setMinWidth(170);
        
        Button addcustomerButton = new Button();
        addcustomerButton.setText("Add");
        
        // Animations - Back Button
        addcustomerButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            addcustomerButton.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
            addcustomerButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold");
          }
        });
        
        addcustomerButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            addcustomerButton.setMaxSize(250, 200);
            addcustomerButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            addcustomerButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
          }
        });

        HBox addCustomer = new HBox(20);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.getChildren().addAll(namelabel, namefield,pwrdlabel,passwordfield,addcustomerButton);
        
        // Deleted Customer part of the table
        Button deleteCustomerButton = new Button("Delete");
        deleteCustomerButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
        
        // Animations - Back Button
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            backButton.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
            backButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold");
          }
        });
        
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            backButton.setMaxSize(250, 200);
            backButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            backButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
          }
        });
        
        // Animations - Delete Button
        deleteCustomerButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            deleteCustomerButton.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
            deleteCustomerButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold");
          }
        });
        
        deleteCustomerButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            deleteCustomerButton.setMaxSize(250, 200);
            deleteCustomerButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            deleteCustomerButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
          }
        });
        
        VBox ownerCustomer = new VBox(10);
        ownerCustomer.getChildren().add(customerTable);
        ownerCustomer.getChildren().add(addCustomer);
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(deleteCustomerButton, backButton);
        buttons.setAlignment(Pos.CENTER);
        
        ownerCustomer.getChildren().add(buttons);
        ownerCustomer.setAlignment(Pos.TOP_CENTER);
        
        backButton.setOnAction(event -> {
            store.changeScreen(ownerStartScreen());
        });
        
        backButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   store.changeScreen(ownerStartScreen()); 
                }
        });
        
        addcustomerButton.setOnAction(event -> {
            registerNewCustomer(store, namefield.getText(), passwordfield.getText());
        });
        
        addcustomerButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   registerNewCustomer(store, namefield.getText(), passwordfield.getText()); 
                }
        });
        
        deleteCustomerButton.setOnAction(event -> {
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            customers.remove(customer);
            deleteCustomer(store, customer.getName(), customer.getPassword());
            System.out.println("Deleted "+customer.getName() + " " + customer.getPassword());
        });
        
//        deleteCustomerButton.setOnKeyPressed(event -> {
//            KeyCode key = event.getCode();
//                if(key.equals(KeyCode.ENTER)){
//                   Customer customer = customerTable.getSelectionModel().getSelectedItem();
//                    customers.remove(customer);
//                    deleteCustomer(store, customer.getName(), customer.getPassword());
//                    System.out.println("Deleted "+customer.getName() + " " + customer.getPassword());
//                }
//        });
        
        return ownerCustomer;
    }
    
    private Pane ownerBooksScreen(Store store){
                
        //table with three columns,
        // book name, book price, select.
        // TableView<S>
        TableView<Book> bookTable = new TableView<>();
        ObservableList<Book> books = FXCollections.observableArrayList(store.books);
        bookTable.setItems(books);
        
        TableColumn<Book,String> customerNameCol = new TableColumn<>("Book Name");
        customerNameCol.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Book, String> str){
                return new ReadOnlyObjectWrapper(str.getValue().getName());
            }
        });
        
        TableColumn<Book,Integer> customerPointsCol = new TableColumn<>("Book Price");
        customerPointsCol.setCellValueFactory(new PropertyValueFactory("bookPrice"));
        customerPointsCol.setCellValueFactory(new Callback<CellDataFeatures<Book, Integer>, ObservableValue<Integer>>(){
            public ObservableValue<Integer> call(CellDataFeatures<Book, Integer> str){
                return new ReadOnlyObjectWrapper(str.getValue().getPrice());
            }
        });

        bookTable.getColumns().setAll(customerNameCol,customerPointsCol);
        bookTable.setEditable(true);
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Add book part of windows
        Label namelabel = new Label ("Book Name");
        TextField namefield = new TextField();
        namefield.setPromptText("New Book's Name");
        namefield.setMinWidth(270);
        Label pricelabel = new Label ("Book Price");
        TextField pricefield = new TextField();
        pricefield.setPromptText("New Book's Price");
        pricefield.setMinWidth(100);

        Button addbookButton = new Button();
        addbookButton.setText("Add");
        
        // Animations - Add Button
        addbookButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            addbookButton.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
            addbookButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold");
          }
        });
        
        addbookButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            addbookButton.setMaxSize(250, 200);
            addbookButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            addbookButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
          }
        });

        HBox addCustomer = new HBox(20);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.getChildren().addAll(namelabel, namefield, pricelabel, pricefield, addbookButton);
        
        // Deleted Book part of the table
        Button deleteBookButton = new Button("Delete");
        Button backButton = new Button("Back");
        
        // Animations - Back Button
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            backButton.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
            backButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold");
          }
        });
        
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            backButton.setMaxSize(250, 200);
            backButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            backButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
          }
        });
        
        // Animations - Delete Button
        deleteBookButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            deleteBookButton.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
            deleteBookButton.setStyle("-fx-text-fill: #4da8ab; -fx-font-weight: bold");
          }
        });
        
        deleteBookButton.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            deleteBookButton.setMaxSize(250, 200);
            deleteBookButton.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            deleteBookButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 12px");
          }
        });
        
        VBox ownerCustomer = new VBox(10);
        ownerCustomer.getChildren().add(bookTable);
        ownerCustomer.getChildren().add(addCustomer);
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(deleteBookButton, backButton);
        buttons.setAlignment(Pos.CENTER);
        
        ownerCustomer.getChildren().add(buttons);
        ownerCustomer.setAlignment(Pos.TOP_CENTER);
        
        backButton.setOnAction(event -> {
            store.changeScreen(ownerStartScreen());
        });
        
        backButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   store.changeScreen(ownerStartScreen()); 
                }
        });
        
        addbookButton.setOnAction(event -> {
            try{
                Book book = new Book (namefield.getText(), Double.parseDouble(pricefield.getText()));    
                addBook(store, book);
                System.out.println("Book "+namefield.getText()+" Price "+pricefield.getText()+" added");
            }catch(NumberFormatException e){
                namefield.setText("");
                pricefield.setText("");
            }
        });
        
        addbookButton.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
                if(key.equals(KeyCode.ENTER)){
                   Book book = new Book (namefield.getText(), Double.parseDouble(pricefield.getText()));    
                   addBook(store, book);
                   System.out.println("Book "+namefield.getText()+" Price "+pricefield.getText()+" added");
                }
        });
        
        deleteBookButton.setOnAction(event -> {
            Book book = bookTable.getSelectionModel().getSelectedItem();
            books.remove(book);
            removeBook(store, book.getName(), book.getPrice());
            System.out.println("Deleted Book: " + book.getName() + " " + book.getPrice());
        });
        
        return ownerCustomer;
    }
}
