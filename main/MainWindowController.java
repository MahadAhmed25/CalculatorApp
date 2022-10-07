package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MainWindowController{
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label result;

    private double x,y;
    private double num1 = 0.0;
    private String operator = "+";

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMousePressed(mouseEvent -> {
            stage.close();
        });

        btnMinimize.setOnMousePressed(mouseEvent -> {
            stage.setIconified(true);
        });
    }
    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        result.setText(Double.parseDouble(result.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(result.getText())*10+value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equals")){
            double num2 = Double.parseDouble(result.getText());
            switch(operator){
                case "+" -> result.setText((num1+num2)+"");
                case "-" -> result.setText((num1-num2)+"");
                case "*" -> result.setText((num1*num2)+"");
                case "/" -> result.setText((num1/num2)+"");
            }
            operator = ".";
        }
        else if(symbol.equals("Clear")){
            result.setText(String.valueOf(0.0));
            operator = ".";
        }
        else{
            switch (symbol){
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Multiply" -> operator = "*";
                case "Divide" -> operator = "/";

            }
            num1 = Double.parseDouble(result.getText());
            result.setText(String.valueOf(0.0));
        }
    }
    }


