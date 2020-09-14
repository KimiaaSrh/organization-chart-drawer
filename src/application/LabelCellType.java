package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import application.Cell;

public class LabelCellType extends Cell {
	public static Button buttonCurrentlabel;

    public LabelCellType(String id,String txt) {
        super(id);

        /*Label view = new Label(id);
        view.prefWidth(view.getText().length() * 12);
        */
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5,5 , 5, 5));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        buttonCurrentlabel = new Button("Current");
        buttonCurrentlabel.setPrefSize(50, 40);
        buttonCurrentlabel.setText(txt);
        
        hbox.getChildren().addAll(buttonCurrentlabel);

        setView(hbox);

    }

}