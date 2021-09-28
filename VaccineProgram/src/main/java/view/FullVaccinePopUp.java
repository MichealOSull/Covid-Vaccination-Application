package view;

/**
 * Pop Up Tab for adding a vaccine
 * @author Micheal O' Sullivan
 * 
 */

import controller.FullVaccineController;
import controller.ClientCollectionController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import view.popUpLabels.FullVaccineLabels;
import models.FullVaccine;
import models.Client;
import models.ClientCollection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class FullVaccinePopUp {

    private static Label message;
    private static Button exitButton;
    private static GridPane grid;
    private static VBox layout;
    public static RadioButton yes, no, check;
    private static ComboBox combo, combo2;
    private static TextField vName,vEff,vLocation;
    private static DatePicker vDate, vDate2;
    private static FullVaccineLabels fullVaccineLabels;
    private static Button addButton;
    private static final ToggleGroup group = new ToggleGroup();
    private static FullVaccineController fullVaccineController=new FullVaccineController();
    private static ClientCollectionController cColCont=new ClientCollectionController();
    private static List<ClientCollection> listClientCol;

    public static void displayFullVaccinePopUp() {
        Stage popupwindow = new Stage();

        popupwindow.setTitle("Add Vaccine");

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        listClientCol=cColCont.getClientCollections();
        
        
        vName = new TextField();
        grid.add(vName, 1, 0);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "50%", "55%", "60%", "65%", "70%", "75%", "80%","85%","90%","95%", "100%"
                );
        combo2 = new ComboBox(options);
        grid.add(combo2, 1, 1);
        
        

        vDate = new DatePicker();
        grid.add(vDate, 1, 2);
        
        yes = new RadioButton("2nd Dose Needed");
        yes.setUserData(true);
        yes.setToggleGroup(group);
        no = new RadioButton("2nd Dose Not Needed");
        no.setToggleGroup(group);
        grid.add(yes, 0, 3);
        grid.add(no, 1, 3);
        

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                check = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                System.out.println("Selected Radio Button - " + check.getText());

            }
        });
        
       

        vDate2 = new DatePicker();
        grid.add(vDate2, 1, 4);
        
        
        combo=new ComboBox(FXCollections.observableList(listClientCol));
        grid.add(combo,1,5);
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(combo.getValue() + " selected");
                    }
                };

        combo.setOnAction(event);
        message=new Label();



        addButton = new Button("Add");
        grid.add(addButton, 0, 8);

        grid.add(message,0,9);
        


        
        addButton.setOnAction(e -> {
            String details=fullVaccineController.addFullVaccine(
            		combo.getValue().toString(),
                    vName.getText(),
                    combo2.getValue().toString(),
                    vDate.getValue().toString(),
                    check.getText(),
                    vDate2.getValue().toString());       
            message.setText(details);

        });
        
      
        exitButton = new Button("Close");
        exitButton.setOnAction(e -> popupwindow.close());
        
        grid=fullVaccineLabels.FullVaccineLabels(grid);
        
        layout = new VBox(10);
        layout.getChildren().addAll(grid,exitButton);
        layout.setAlignment(Pos.CENTER);


        Scene scene1 = new Scene(layout, 350, 350);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    
    }
}
