import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    void drawCircle(GraphicsContext gc, float radius){
        gc.setStroke(Color.BLUE);
        gc.strokeOval(100, 100, radius*2, radius*2);
        gc.strokeOval(100, 100, radius*4, radius*4);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final Canvas canvas = new Canvas(250, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawCircle(gc, 30);
        drawCircle(gc, 60);
        drawCircle(gc, 100);
        drawCircle(gc, 400);

        Pane root = new Pane();
        root.getChildren().addAll(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
