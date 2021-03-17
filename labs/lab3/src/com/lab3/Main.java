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

    int WIDTH = 800;
    int HEIGHT = 600;

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

//        // Big mushroom
//        root.getChildren().add(BigMushroom.getLowerPartOfStipe());
//        root.getChildren().add(BigMushroom.getUpperPartOfStipe());
//        root.getChildren().add(BigMushroom.getCap());
//        root.getChildren().addAll(BigMushroom.getCapStains());
//
//
//        // Small mushroom
//        root.getChildren().add(SmallMushroom.getStipe());
//        root.getChildren().add(SmallMushroom.getCap());
//        root.getChildren().addAll(SmallMushroom.getCapStains());
//
//        // Animation
//        int duration = 3000;
//
//        ScaleTransition scaleFrom = new ScaleTransition(Duration.millis(duration), root);
//        scaleFrom.setToX(1);
//        scaleFrom.setToY(1);
//
//        ScaleTransition scaleTo = new ScaleTransition(Duration.millis(duration), root);
//        scaleTo.setToX(0.1);
//        scaleTo.setToY(0.1);
//
//        RotateTransition rotate = new RotateTransition(Duration.millis(duration), root);
//        rotate.setByAngle(360f);
//        rotate.setCycleCount(Timeline.INDEFINITE);
//
//        TranslateTransition translateTo = new TranslateTransition(Duration.millis(duration * 2), root);
//        translateTo.setFromX(0);
//        translateTo.setToX(300);
//        translateTo.setCycleCount(Timeline.INDEFINITE);
//        translateTo.setAutoReverse(true);
//
//        TranslateTransition translateFrom = new TranslateTransition(Duration.millis(duration * 2), root);
//        translateFrom.setFromX(300);
//        translateFrom.setToX(0);
//        translateFrom.setCycleCount(Timeline.INDEFINITE);
//        translateFrom.setAutoReverse(true);
//
//        SequentialTransition scale = new SequentialTransition();
//        scale.getChildren().addAll(
//                scaleTo,
//                scaleFrom
//        );
//        scale.setCycleCount(Timeline.INDEFINITE);
//
//        SequentialTransition translate = new SequentialTransition();
//        translate.getChildren().addAll(
//                translateTo,
//                translateFrom
//        );
//        translate.setCycleCount(Timeline.INDEFINITE);
//
//        ParallelTransition animations = new ParallelTransition();
//        animations.getChildren().addAll(
//                scale,
//                rotate,
//                translate
//        );
//        animations.play();
//        // End of animation
//
//
        primaryStage.setTitle("Dron drum");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}