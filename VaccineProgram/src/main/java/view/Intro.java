package view;

/**
 * Intro Tab
 * @author Micheal O' Sullivan
 * 
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Intro extends Tab {
    private VBox vBox;
    private GridPane grid;

    public Intro(){
        setClosable(false);
        setText("HOME");

        FlowPane pane = new FlowPane();

        Label label=new Label("Welcome To The MTU Vaccine Progam." +
                "\n(By Micheal O' Sullivan)\n(R00128516)");
        label.setStyle("-fx-font-size: 30; ");


        vBox = new VBox(5,label);
        vBox.setPadding(new Insets(80, 40, 10, 70));
        vBox.setAlignment(Pos.CENTER);

        pane.getChildren().add(vBox);

        setContent(pane);

    }

}
