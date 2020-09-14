package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ExpertStaffCell extends Cell {
	
	public ExpertStaffCell(String id,String txt) {
        super(id);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5,5 , 5, 5));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(50, 25);
        buttonCurrent.setText(txt);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(50, 25);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        setView(hbox);

    }


}
