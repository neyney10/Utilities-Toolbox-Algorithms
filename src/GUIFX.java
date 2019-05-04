

import java.util.Iterator;
import java.util.Random;

import algorithms.linearprogramming.GaussEliminiation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utilities.Print;


/**
 * CURRENTLY DOES NOT WORK, W.I.P
 * THIS IS THE GRAPHICAL INTERFACE FOR ALL ALGORITHMS.
 * @author neyne
 *
 */
public class GUIFX extends Application {

    @Override
    public void start(Stage primaryStage) {
    	int SIZE = 3;
        int length = SIZE;
        int width = SIZE+1;

        GridPane root = new GridPane();    

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                Random rand = new Random();
                int rand1 = rand.nextInt(10);

                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(true);
                tf.setText(Integer.toString(rand1));

                // Iterate the Index using the loops
                root.setRowIndex(tf,y);
                root.setColumnIndex(tf,x);    
                root.getChildren().add(tf);
            }
        }
        
        Button btn_solve = new Button("Solve using Gauss Elimination"); 
        root.setRowIndex(btn_solve,4);
        root.setColumnIndex(btn_solve,0);
        root.getChildren().add(btn_solve);
        btn_solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	double matrix[][] = new double[length][width];
            	int i=0;
            	for(Node n : root.getChildren()) {
            		if (n instanceof TextField) {
            			matrix[i/width][i%width] = Integer.parseInt(((TextField) n).getText());
            			i++;
            		}
            	}
            	Print.printMatrix(matrix);
                double xsolutions[] = GaussEliminiation.compute(matrix);
                Print.printArray(xsolutions);
            }
        });

        Scene scene = new Scene(root, 500, 500);    
        primaryStage.setTitle("Random Binary Matrix (JavaFX)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}