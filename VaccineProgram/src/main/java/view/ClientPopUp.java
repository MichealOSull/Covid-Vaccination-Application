package view;

/**
 * Pop Up Tab for creating a client
 * @author Micheal O' Sullivan
 * 
 */

import controller.ClientController;
import controller.ClientCollectionController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import models.ClientCollection;
import view.popUpLabels.ClientLabels;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientPopUp {
    private static Button exitButton, addButton;
    private static Label message;
    private static GridPane grid;
    private static TextField fName,lName,phone,email, id;
    private static RadioButton yes, no;
    private static final ToggleGroup group = new ToggleGroup();
    private static RadioButton chk;
    private static ComboBox combo;
    private static ClientLabels clientLabels;
    private static VBox layout;
    private static ClientController cCont=new ClientController();
    private static ClientCollectionController  cColCont=new ClientCollectionController();
    private static List<ClientCollection>  listClientColl;

    public static void displayClientPopUp() {

        Stage popupwindow = new Stage();

        popupwindow.setTitle("Add Client");

        grid = new GridPane();
        grid.setPadding(new Insets(6, 6, 6, 6));

         listClientColl= cColCont.getClientCollections();

        combo=new ComboBox(FXCollections.observableList( listClientColl));
        grid.add(combo,1,0);

        id = new TextField();
        grid.add(id, 1, 1);
        
        fName = new TextField();
        grid.add(fName, 1, 2);

        lName = new TextField();
        grid.add(lName, 1, 3);

        phone = new TextField();
        grid.add(phone, 1, 4);

        email = new TextField();
        grid.add(email, 1, 5);

        
        addButton = new Button("Add");
        grid.add(addButton, 0, 9);
        message=new Label();
        
        addButton.setOnAction(e -> {
            String details= cCont.addClient(
            		combo.getValue().toString(),
            		id.getText(),
                    fName.getText(),
                    lName.getText(),
                    phone.getText(),
                    email.getText());
            message.setText(details);
        });


        exitButton = new Button("Close");
        exitButton.setOnAction(e -> popupwindow.close());
        grid.add(exitButton, 1, 7);
        grid=clientLabels.ClientLabels(grid);


        layout = new VBox(10);
        layout.getChildren().addAll(grid, exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 300);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

 

}
