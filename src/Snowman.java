import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;
public class Snowman {
    private static final int HEIGHT = 200;
    private static final int WIDTH = 500;
    private static final int BORDER = 38;
    private static final int CIRCLESCOUNT = 3;

    private ArrayList<Circle> circleArrayList;
    private ArrayList<Circle> headArrayList;

    private int height;
    private int width;

    //random circles
    public void crate(int count, int min, int max) {
        Random rnd = new Random(System.currentTimeMillis());
        int head = min;

        height = 0;
        width = min;

        if (circleArrayList == null) circleArrayList = new ArrayList<>();
        else circleArrayList.clear();

        if (headArrayList == null) headArrayList = new ArrayList<>();
        else headArrayList.clear();

        // count body circles
        for (int i = 0; i < count; i++) {
            int radius = rnd.nextInt(max - min) + min;

            head = radius;
            height += radius * 2;
            width = Math.max(width, radius);

            Circle circle = new Circle();
            circle.setRadius(radius);
            circle.setStroke(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
            circle.setFill(Color.WHITE);

            circleArrayList.add(circle);
        }

        // snowman head
        for (int i = 0; i < CIRCLESCOUNT; i++) {
            int radius = rnd.nextInt((int) (head * Math.cos(Math.toRadians(45)) / 2));

            Circle circle = new Circle();
            circle.setRadius(radius);
            circle.setStroke(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
            circle.setFill(Color.WHITE);

            headArrayList.add(circle);
        }
    }

    // snowman
    public void draw(Pane root) {
        int centerX = Math.max(WIDTH, width + BORDER) / 2;
        int centerY = HEIGHT + height;

        // body
        for (int i = 0; i < circleArrayList.size(); i++) {
            circleArrayList.get(i).setCenterX(centerX);
            circleArrayList.get(i).setCenterY(centerY - circleArrayList.get(i).getRadius());

            centerY -= circleArrayList.get(i).getRadius() * 2 + 1;

            root.getChildren().addAll(circleArrayList.get(i));
        }

        // head
        int headCenterX = (int) circleArrayList.get(circleArrayList.size() - 1).getCenterX();
        int headCenterY = (int) circleArrayList.get(circleArrayList.size() - 1).getCenterY();
        int headRadius = (int) circleArrayList.get(circleArrayList.size() - 1).getRadius();

        int angle = -90;

        for (int i = 0; i < headArrayList.size(); i++) {
            headArrayList.get(i).setCenterX(headCenterX + headRadius * Math.cos(angle * Math.PI / 180) / 2);
            headArrayList.get(i).setCenterY(headCenterY - headRadius * Math.sin(angle * Math.PI / 180) / 2);

            angle += 360 / headArrayList.size();

            root.getChildren().addAll(headArrayList.get(i));
        }
    }

    // remove snowman
    public void clear(Pane root) {
        if (circleArrayList != null) {
            for (Circle circle : circleArrayList)
                root.getChildren().remove(circle);

            circleArrayList.clear();
        }

        if (headArrayList != null) {
            for (Circle circle : headArrayList)
                root.getChildren().remove(circle);

            headArrayList.clear();
        }
    }

    // change snowman color
    public void changeColor (Color color) {
        if (circleArrayList != null)
            for (int i = 0; i < circleArrayList.size(); i++)
                circleArrayList.get(i).setFill(color);
    }

    // change snowman color
    public void changeColor (Color color, int i) {
        if (circleArrayList != null)
            circleArrayList.get(i).setFill(color);
    }

    // gradient
    public void gradientColor() {
        if (!circleArrayList.isEmpty()) {
            int step = 211 / circleArrayList.size();

            for (int i = 0; i < circleArrayList.size(); i++) {
                changeColor(Color.rgb(0 + i * step, 0 + i * step, 0 + i * step), i);
            }
        }
    }

    public int[] size() {
        return new int[]{Math.max(WIDTH, width + BORDER), HEIGHT + height + BORDER};
    }
}
