/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cyrille
 */

package bookstorepackage;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    
    private OwnerState(){
        
    }
    public static OwnerState getInstance(){
        if (s == null){
            s = new OwnerState();
        }
        return s;
    }
    
    public void logIn(){
        System.out.println("Already logged In");
    }
    Main3 m = new Main3();
    public void logOut(Store store){
        StoreState st = new NoUserState(m);
        store.setState(st);
    }
    public void addBook(Store store, Book book){
        store.books.add(book);
    }
    public void removeBook(Store store, String bookname, double bookprice){
        Book book = new Book(bookname,bookprice);
        store.books.remove(book);
    }
    public void registerNewCustomer(Store store,String name, String password){
        Customer customer = new Customer(name, password);
        boolean found = false;
        while (found==false){
            for(Customer cst:store.customers){
                if((cst.customerName.equals(name))&&(cst.customerPassword.equals(password))){
                    found = true;
                    System.out.println("Customer already in Store Customers list");
                }
            }
        }
        if(found==false){
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
        Label namelabel = new Label ("UserName");
        TextField namefield = new TextField("Enter new customer's Username");
        Label pwrdlabel = new Label ("Password");
        TextField passwordfield = new TextField("Enter new customer's Password");
        
        Button addcustomerButton = new Button();
        addcustomerButton.setText("Add");

        HBox addCustomer = new HBox(20);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.getChildren().addAll(namelabel, namefield,pwrdlabel,passwordfield,addcustomerButton);
        
        // Deleted Customer part of the table
        Button deleteCustomerButton = new Button("Delete");
        VBox ownerCustomer = new VBox(10);
        ownerCustomer.getChildren().add(customerTable);
        ownerCustomer.getChildren().add(addCustomer);
        ownerCustomer.getChildren().add(deleteCustomerButton);
        ownerCustomer.setAlignment(Pos.TOP_CENTER);
        
        return ownerCustomer;
    }

    
    
}
