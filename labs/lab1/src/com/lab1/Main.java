package com.lab1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class Main extends Application {

    private final double WIDTH = 900;
    private final double HEIGHT = 500;

    private final double HALF_HEIGHT = HEIGHT / 2;

    private final double PADDING = 40;
    private final double HOLE_RADIUS = PADDING * 2;

    private final int LINES_COUNT = 9;
    private final double LINES_BLOCK_PADDING_FROM_CIRCLE = PADDING / 5;
    private final double LINE_PADDING = (HOLE_RADIUS - LINES_BLOCK_PADDING_FROM_CIRCLE  * 2) / (LINES_COUNT - 1);

    private final Color BACKGROUND_COLOR = Color.YELLOW;
    private final Color GUITAR_COLOR = Color.BLUE;
    private final Color GUITAR_HOLE_COLOR = Color.RED;
    private final Color STRING_COLOR = Color.BLACK;

    public static void main (String args[]) {
        launch(args); // main method
    }
    @Override
    public void start(Stage primaryStage) // start - is the main entry point fo
    {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setFill(BACKGROUND_COLOR);
        attachLeftGuitarBackground(root);
        attachRightGuitarBackground(root);
        attachGuitarCircle(root);
        attachLines(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab1, Andrii Koval KP-83");
        primaryStage.show();

    }

    private void attachLeftGuitarBackground(Group group) {
        Polygon leftPart = new Polygon();
        leftPart.getPoints().addAll(new Double[]{
                PADDING, PADDING,
                WIDTH / 2 - PADDING, HALF_HEIGHT,
                PADDING, HEIGHT - PADDING,
                PADDING * 3, HALF_HEIGHT});
        leftPart.setFill(GUITAR_COLOR);
        group.getChildren().add(leftPart);
    }

    private void attachRightGuitarBackground(Group group) {
        Polygon rightPart = new Polygon();
        rightPart.getPoints().addAll(new Double[]{
                WIDTH - PADDING, HALF_HEIGHT - 3 * PADDING,
                WIDTH - 2.8 * PADDING, HALF_HEIGHT,
                WIDTH - PADDING, HALF_HEIGHT + 3 * PADDING,
                WIDTH - 5 * PADDING, HALF_HEIGHT});
        rightPart.setFill(GUITAR_COLOR);
        group.getChildren().add(rightPart);
    }

    private void attachGuitarCircle(Group group) {
        Circle circle = new Circle(
                PADDING * 5.6, HALF_HEIGHT, HOLE_RADIUS
        );
        circle.setFill(GUITAR_HOLE_COLOR);
        group.getChildren().add(circle);
    }

    private void attachLines(Group group) {
        Line[] lines = new Line[LINES_COUNT];
        double linesBlockHeight = (LINES_COUNT - 1) * LINE_PADDING;
        double firstLineYPos = HALF_HEIGHT - linesBlockHeight / 2;
        double firstLineXPos = WIDTH / 4 - PADDING * 1.5;
        double lineLength = WIDTH / 1.5;
        for (int i = 0; i < LINES_COUNT; i++) {
            lines[i] = new Line(
                    firstLineXPos,
                    firstLineYPos + LINE_PADDING * i,
                    firstLineXPos + lineLength,
                    firstLineYPos + LINE_PADDING * i);
            lines[i].setFill(STRING_COLOR);
        }

        group.getChildren().addAll(lines);
    }
}
