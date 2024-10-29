package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;


public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;
        private Button selectedButton;

        public ShapeChooserPanel(View view) {

                this.view = view;
                String[] buttonLabels = { "○", "▭", "□", "〰", "ᕒ", "Oval", "△" };

//                String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" , "Triangle" };

                int row = 0;
                for (String label : buttonLabels) {
                        Button button = new Button(label);
                        button.setMinWidth(100);
                        this.add(button, 0, row);
                        row++;
                        button.setOnAction(this);
                }
        }

        @Override
        public void handle(ActionEvent event) {
                String command = ((Button) event.getSource()).getText();
                Button button = (Button) event.getSource();
                if (selectedButton != null) {
                        selectedButton.setBackground(Background.EMPTY);
                        selectedButton.setBorder(Border.EMPTY);
                }
                button.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), Insets.EMPTY)));
                button.setBorder(new Border(new BorderStroke(
                        Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));
                selectedButton = button;
                view.setMode(command);
                System.out.println(command);
        }
}


