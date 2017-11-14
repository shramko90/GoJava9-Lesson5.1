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
        gc.strokeOval(125, 0, radius*4, radius*4);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final Canvas canvas = new Canvas(350, 350);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        System.out.println("Введите количество кругов");
        Scanner sc = new Scanner(System.in);
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        for (int i =0; i<b; i++){
            if (i>0 & i<b) {
                drawCircle(gc, c);
                c++;
            }
        }
        Pane root = new Pane();
        root.getChildren().addAll(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
