package view.popUpLabels;

/**
 * Class for pop up labels in the client pop up tab
 * @author Micheal O' Sullivan
 * 
 */

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class ClientLabels {
    private static Label cLocation, cID, cFName,cLName,cPhone,cEmail;

    public static GridPane ClientLabels(GridPane grid){

        cLocation= new Label("Vaccine Location:");
        grid.add(cLocation,0,0);
        
        cID = new Label("ID: ");
        grid.add(cID, 0, 1);

        cFName = new Label("First Name: ");
        grid.add(cFName, 0, 2);

        cLName = new Label("Last Name: ");
        grid.add(cLName, 0, 3);

        cPhone = new Label("Phone No: ");
        grid.add(cPhone, 0, 4);

        cEmail = new Label("Email: ");
        grid.add(cEmail, 0, 5);

        return grid;
    }

}
