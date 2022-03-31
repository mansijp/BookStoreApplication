
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Mansi
 */

public class Store extends Application{
    private Scene scene;
    protected ArrayList <Book> books =  new ArrayList<>();
    protected ArrayList <Customer> customers =  new ArrayList<>();
    protected StoreState state = null;
    
    // Constructor
    public Store(){ 
    }
    
    @Override
    public void start(Stage primaryStage) {  
        StackPane root = new StackPane();

        scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Book Store");
        primaryStage.setScene(getScene());
        primaryStage.show();
        
        //Store bookstoreApplication = new Store();
        this.setState(new NoUserState(this));
        
        loadData();
    }
    
    @Override
    public void stop(){
        saveData();
    }
    
    public static void main(String[] args){
        launch(args);
    }

    public void setState(StoreState state){
        this.state = state;
    }
    
    public void changeScreen(Pane root){
        getScene().setRoot(root);
    }
    
    public void loadData(){
        books = new ArrayList<>();
        customers = new ArrayList<>();

        try{
            File file = new File("src/books.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String[] bookData = scanner.nextLine().split(";");
                books.add(new Book(bookData[0], Double.parseDouble(bookData[1])));
                System.out.println(bookData[0] + " " + Double.parseDouble(bookData[1]));
            }
            
            file = new File("src/customers.txt");
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String[] data = scanner.nextLine().split(";");
                //name points password
                customers.add(new Customer(data[0], Integer.parseInt(data[1]), data[2]));
            }
            
            scanner.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void saveData(){
         try{
            PrintWriter writer = new PrintWriter("src/books.txt");
            for(Book book : books)
                writer.println(book.getName() + ";" + book.getPrice());
            writer.close();
            
            writer = new PrintWriter("src/customers.txt");
            for(Customer customer : customers)
                writer.println(customer.getName() + ";" 
                        + customer.getPoints() + ";"
                        + customer.getPassword());
            writer.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }
}
