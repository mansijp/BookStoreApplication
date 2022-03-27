


import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
public class CustomerCostScreen {
    
    private CustomerState state;
    
    public CustomerCostScreen(CustomerState state){
        this.state = state;
    }
    
    public Pane display(Customer customer){       
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
            state.logout();
        });
        
        return root;
    }
}
