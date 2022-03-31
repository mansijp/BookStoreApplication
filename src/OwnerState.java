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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;



public class OwnerState extends StoreState{
    //Overview: OwnerState is immutable singleton class
    //
    //The abstraction function is:
    //
    // The rep invarian is: 
    // The rep
    private static OwnerState s = null;
    private String username = "admin";
    private String password = "admin";
    
    public OwnerState(Store store){
        super(store);
        store.changeScreen(ownerStartScreen());
    }
    
    //private OwnerState(){
        
    //}
    public static OwnerState getInstance(){
        if (s == null){
            s = new OwnerState(new Store());
        }
        return s;
    }
    
    public void logIn(){
        System.out.println("Already logged In");
    }

    public void logOut(Store store){
        StoreState st = new NoUserState(store);
        store.setState(st);
    }
    public void addBook(Store store, Book book){
        store.books.add(book);
    }
    public void removeBook(Store store, String bookname, double bookprice){
        Book book = new Book(bookname,bookprice);
        store.books.remove(book);
    }
    public void registerNewCustomer(Store store, String name, String password) {
        Customer customer = new Customer(name, password);
        boolean found = false;
        int size = store.customers.size();
        int i = 0;
        while (found && (i < size)) {
            Customer cst = store.customers.get(i);
            if ((cst.customerName.equals(name)) && (cst.customerPassword.equals(password))) {
                found = true;
                System.out.println("Customer already in Store Customers list");
                
            }
            //i = size + 1;
            i ++;

            /*for(Customer cst:store.customers){
                if((cst.customerName.equals(name))&&(cst.customerPassword.equals(password))){
                    found = true;
                    System.out.println("Customer already in Store Customers list");
                }
            } */
        }
        if (found == false) {
            store.customers.add(customer);
        }
    }
    public void deleteCustomer(Store store, String name, String password){
        Customer customer = new Customer(name, password);
        store.customers.remove(customer);
    }
    
    public Pane ownerStartScreen(){
       
        // three buttons: Books, Customers, and Logout
        Button booksButton = new Button();
        booksButton.setText("Books");
        
        Button customersButton = new Button();
        customersButton.setText("Customers");
        
        Button logoutButton = new Button();
        logoutButton.setText("Logout");

        VBox ownerstart = new VBox(20);
        ownerstart.setAlignment(Pos.CENTER);
        ownerstart.getChildren().add(booksButton);
        ownerstart.getChildren().add(customersButton);
        ownerstart.getChildren().add(logoutButton);
        
        //Button functionality
        customersButton.setOnAction(event -> {
            store.changeScreen(ownerCustomerScreen(store));
        });
        
        booksButton.setOnAction(event -> {
            store.changeScreen(ownerBooksScreen(store));
        });
        
        logoutButton.setOnAction(event -> {
            logOut(store);
        });
        
        return ownerstart;
    }
    
    //Owner start screen events
    
    
    public Pane ownerCustomerScreen(Store store){
                
        //table with three columns,
        // book name, book price, select.
        // TableView<S>
        TableView<Customer> customerTable = new TableView<>();
        ObservableList<Customer> customers = FXCollections.observableArrayList(store.customers);
        customerTable.setItems(customers);
        
        TableColumn<Customer,String> customerNameCol = new TableColumn<>("Username");
        customerNameCol.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Customer, String> str){
                return new ReadOnlyObjectWrapper(str.getValue().customerName);
            }
        });
        
        TableColumn<Customer,String> customerPassCol = new TableColumn<>("Password");
        //customerPassCol.setCellValueFactory(new PropertyValueFactory("customerPassword"));
        customerPassCol.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Customer, String> str){
                return new ReadOnlyObjectWrapper(str.getValue().customerPassword);
            }
        });
        
        TableColumn<Customer,Integer> customerPointsCol = new TableColumn<>("Points");
        customerPointsCol.setCellValueFactory(new PropertyValueFactory("customerPoints"));
        customerPointsCol.setCellValueFactory(new Callback<CellDataFeatures<Customer, Integer>, ObservableValue<Integer>>(){
            public ObservableValue<Integer> call(CellDataFeatures<Customer, Integer> str){
                return new ReadOnlyObjectWrapper(str.getValue().customerPoints);
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

        HBox addCustomer = new HBox(20);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.getChildren().addAll(namelabel, namefield,pwrdlabel,passwordfield,addcustomerButton);
        
        addcustomerButton.setOnAction(event -> {
            registerNewCustomer(store, namefield.getText(), passwordfield.getText());
        });
        
        // Deleted Customer part of the table
        Button deleteCustomerButton = new Button("Delete");
        Button backButton = new Button("Back");
        
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
        
        deleteCustomerButton.setOnAction(event -> {
            deleteCustomer(store, namefield.getText(), passwordfield.getText());
        });
        
        return ownerCustomer;
    }
    
    public Pane ownerBooksScreen(Store store){
                
        //table with three columns,
        // book name, book price, select.
        // TableView<S>
        TableView<Book> bookTable = new TableView<>();
        ObservableList<Book> books = FXCollections.observableArrayList(store.books);
        bookTable.setItems(books);
        
        TableColumn<Book,String> customerNameCol = new TableColumn<>("Book Name");
        customerNameCol.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Book, String> str){
                return new ReadOnlyObjectWrapper(str.getValue().name);
            }
        });
        
        TableColumn<Book,Integer> customerPointsCol = new TableColumn<>("Book Price");
        customerPointsCol.setCellValueFactory(new PropertyValueFactory("bookPrice"));
        customerPointsCol.setCellValueFactory(new Callback<CellDataFeatures<Book, Integer>, ObservableValue<Integer>>(){
            public ObservableValue<Integer> call(CellDataFeatures<Book, Integer> str){
                return new ReadOnlyObjectWrapper(str.getValue().price);
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

        HBox addCustomer = new HBox(20);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.getChildren().addAll(namelabel, namefield, pricelabel, pricefield, addbookButton);
        
        /*addbookButton.setOnAction(event -> {
            
        });*/
        
        // Deleted Book part of the table
        Button deleteBookButton = new Button("Delete");
        Button backButton = new Button("Back");
        
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
        
        /*deleteBookButton.setOnAction(event -> {
           
        });*/
        
        return ownerCustomer;
    }

    
    
}
