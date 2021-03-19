package com.lab3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    Paint bgColor = Color.rgb(238, 255, 138);

    int WIDTH = 900;
    int HEIGHT = 700;
    int ANIMATION_TRANSLATION_DURATION = 200;
    int ANIMATION_SCALE_DURATION = 400;
    int ANIMATION_ROTATE_DURATION = 300;

    Group root;
    Scene scene;

    public static void main (String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new Group();
        scene = new Scene (root, WIDTH, HEIGHT);
        scene.setFill(bgColor);
        root.getChildren().addAll(Drum.getDrumBody());
        root.getChildren().addAll(Drum.getBackgroundBodySkeleton());
        root.getChildren().addAll(Drum.getBelt());
        root.getChildren().addAll(Drum.getDrumSurface());
        root.getChildren().addAll(Drum.getRightStick());
        root.getChildren().addAll(Drum.getLeftStick());
        root.getChildren().addAll(Drum.getForegroundBodySkeleton());

        TranslateTransition translateTo = new TranslateTransition(Duration.millis(ANIMATION_TRANSLATION_DURATION), root);
        translateTo.setFromY(-100);
        translateTo.setToY(100);
        translateTo.setAutoReverse(true);

        TranslateTransition translateFrom = new TranslateTransition(Duration.millis(ANIMATION_TRANSLATION_DURATION), root);
        translateFrom.setFromY(100);
        translateFrom.setToY(-100);
        translateFrom.setAutoReverse(true);

        ScaleTransition scaleFrom = new ScaleTransition(Duration.millis(ANIMATION_SCALE_DURATION), root);
        scaleFrom.setToX(1);
        scaleFrom.setToY(1);

        ScaleTransition scaleTo = new ScaleTransition(Duration.millis(ANIMATION_SCALE_DURATION), root);
        scaleTo.setToY(0.6);
        scaleTo.setToX(0.8);

        RotateTransition rotate = new RotateTransition(Duration.millis(ANIMATION_ROTATE_DURATION), root);
        rotate.setByAngle(360f);
        rotate.setCycleCount(1);

        SequentialTransition scale = new SequentialTransition();
        scale.getChildren().addAll(
                scaleTo,
                scaleFrom
        );
        scale.setCycleCount(1);

        SequentialTransition translate = new SequentialTransition();
        translate.getChildren().addAll(
                translateTo,
                translateFrom
        );
        translate.setCycleCount(2);

        SequentialTransition animations = new SequentialTransition();
        animations.getChildren().addAll(
                scale,
                rotate,
                translate
        );
        animations.setCycleCount(Timeline.INDEFINITE);
        animations.play();

        primaryStage.setTitle("Коваль Андрій КП-83 Лаб3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}