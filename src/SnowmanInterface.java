import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SnowmanInterface {
    private Snowman snowman;

    public void snowmanInterfaceInit (Pane root, Stage primaryStage) {
        TextField textCircleCount = new TextField();
        textCircleCount.setPromptText("Кол-во кругов");
        textCircleCount.setPrefColumnCount(10);
        textCircleCount.setTranslateX(10);
        textCircleCount.setTranslateY(40);

        TextField textMinRadius = new TextField();
        textMinRadius.setPromptText("Минимальный радиус");
        textMinRadius.setPrefColumnCount(12);
        textMinRadius.setTranslateX(150);
        textMinRadius.setTranslateY(40);

        TextField textMaxRadius = new TextField();
        textMaxRadius.setPromptText("Максимальный радиус");
        textMaxRadius.setPrefColumnCount(12);
        textMaxRadius.setTranslateX(310);
        textMaxRadius.setTranslateY(40);

        Label labelStatus = new Label("Заполните данные:");
        labelStatus.setTranslateX(10);
        labelStatus.setTranslateY(10);

        // draw snowman
        Button showButton = new Button("Нарисовать снеговика");
        showButton.setTranslateX(10);
        showButton.setTranslateY(80);

        // redraw in red
        Button drawInRedButton = new Button("Покрасить в красный");
        drawInRedButton.setTranslateX(10);
        drawInRedButton.setTranslateY(110);

        // draw gradient
        Button gradientButton = new Button("Градиент");
        gradientButton.setTranslateX(10);
        gradientButton.setTranslateY(140);

        // clear snowman
        Button clearButton = new Button("Удалить снеговика");
        clearButton.setTranslateX(10);
        clearButton.setTranslateY(170);

        root.getChildren().addAll(textCircleCount, textMinRadius, textMaxRadius, labelStatus,
                showButton, drawInRedButton, gradientButton, clearButton);

        showButton.setOnAction((ActionEvent event) -> {
            if (textCircleCount.getText() == null || textCircleCount.getText().isEmpty()) {
                labelStatus.setText("Введите кол-во кругов");
                return;
            }

            if (textMinRadius.getText() == null || textMinRadius.getText().isEmpty()) {
                labelStatus.setText("Введите минимальный радиус");
                return;
            }

            if (textMaxRadius.getText() == null || textMaxRadius.getText().isEmpty()) {
                labelStatus.setText("Введите максимальный радиус");
                return;
            }

            int circleCount = Integer.parseInt(textCircleCount.getText());
            int minRadius = Integer.parseInt(textMinRadius.getText());
            int maxRadius = Integer.parseInt(textMaxRadius.getText());

            if (snowman == null) snowman = new Snowman();

            snowman.clear(root);
            snowman.crate(circleCount, minRadius, maxRadius);
            resize(primaryStage);
            snowman.draw(root);

            labelStatus.setText("Снеговик нарисован");
        });

        clearButton.setOnAction((ActionEvent event) -> {
            if (snowman != null) snowman.clear(root);

            labelStatus.setText("Снеговик удалён");
        });

        drawInRedButton.setOnAction((ActionEvent event) -> {
            if (snowman != null) snowman.changeColor(Color.RED);

            labelStatus.setText("Снеговик в красном");
        });

        gradientButton.setOnAction((ActionEvent event) -> {
            if (snowman != null) snowman.gradientColor();

            labelStatus.setText("Градиент");
        });
    }

    private void resize(Stage primaryStage) {
        if (snowman != null) {
            int[] size = snowman.size();

            primaryStage.setWidth(size[0]);
            primaryStage.setHeight(size[1]);
        }
    }
}
