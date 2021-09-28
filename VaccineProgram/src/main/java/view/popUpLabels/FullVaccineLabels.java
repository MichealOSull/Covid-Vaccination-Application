package view.popUpLabels;

/**
 * Class for pop up labels in the vaccine pop up tab
 * @author Micheal O' Sullivan
 * 
 */

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class FullVaccineLabels {
    private static Label vName,vEff,vDate,vDate2,vDose2,vLocation;


    public static GridPane FullVaccineLabels(GridPane grid){

        vName= new Label("Vaccine Name:");
        grid.add(vName,0,0);

        vEff = new Label("Efficany(50%-100%): ");
        grid.add(vEff, 0, 1);

        vDate = new Label("Date Of 1st Jab: ");
        grid.add(vDate, 0, 2);

        vDate2 = new Label("Date Of 2nd Jab(If Needed): ");
        grid.add(vDate2, 0, 4);

        vLocation = new Label("Vaccine Location: ");
        grid.add(vLocation, 0, 5);

        return grid;
    }

}

