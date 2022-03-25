
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

/**
 *
 * @author aaron
 */

public class CustomerStartScreen {
    private ObservableList<BookListing> books;
    private CustomerState state;
    
    // may want to pass in CustomerState to access things
    // or just combine this into that class
    public CustomerStartScreen(CustomerState state){
        this.state = state;
    }
    
    private ObservableList<BookListing> getBooks(){
        books = FXCollections.observableArrayList();
        
        for(int i = 0; i < 20; i++)
            books.add(new BookListing("harry potter " + i, 200));
        
        return books;
    }
    
    public Pane display(Customer customer){
        Text welcomeText = new Text();
        welcomeText.setText("Welcome " + customer.name 
                + ". You have " + customer.points + " points. "
                + "Your status is " + customer.status
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
            state.changeScreen(new CustomerCostScreen(state).display(customer));
        });
        
        buyWithPointsButton.setOnAction(event -> {
            state.changeScreen(new CustomerCostScreen(state).display(customer));
        });
        
        logoutButton.setOnAction(event -> {
            state.logout();
        });
       
        return root;
    }
}
