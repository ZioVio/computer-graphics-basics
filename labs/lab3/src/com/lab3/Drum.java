package com.lab3;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.List;

public class Drum {

    private static Color stickColor = Color.rgb(191, 162, 89);
    private static Color surfaceColor = Color.rgb(237, 208, 66);
    private static Color bodyColor = Color.RED;
    private static Color skeletonStickColor = Color.rgb(200, 200, 200);
    private static Color beltColor = Color.rgb(45, 124, 181);

    public static Node[] getRightStick() {
        Polygon stick = new Polygon(
                355, 255,
                496, 122,
                525, 108,
                532, 122,
                515, 145,
                368, 259
        );
        stick.setFill(stickColor);
        stick.setStroke(Color.BLACK);
        stick.setStrokeType(StrokeType.OUTSIDE);

        Circle circle = new Circle(350, 270, 30);
        circle.setFill(stickColor);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.OUTSIDE);

        Circle shiny = new Circle(360, 265, 10);
        shiny.setFill(Color.WHEAT);
        return new Node[] { stick, circle, shiny };
    }

    public static Node[] getLeftStick() {
        Polygon stick = new Polygon(
                387, 144,
                179, 117,
                165, 121,
                160, 140,
                170, 145,
                384, 151
        );
        stick.setFill(stickColor);
        stick.setStroke(Color.BLACK);
        stick.setStrokeType(StrokeType.OUTSIDE);

        Circle circle = new Circle(404, 156, 30);
        circle.setFill(stickColor);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.OUTSIDE);

        Circle shiny = new Circle(414, 161, 10);
        shiny.setFill(Color.WHEAT);
        return new Node[] { stick, circle, shiny };
    }

    public static Node[] getDrumSurface() {
        Ellipse ellipse = new Ellipse(356, 272, 200, 50);
        ellipse.setFill(surfaceColor);
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeType(StrokeType.OUTSIDE);
        return new Node[] { ellipse };
    }

    public static Node[] getDrumBody() {
        Polygon body = new Polygon(
                161, 271,
                555, 271,
                555, 274,
                555, 440,
                481, 460,
                424, 465,
                389, 467,
                343, 468,
                312, 467,
                247, 459,
                197, 450,
                160, 440
        );
        body.setFill(bodyColor);
        body.setStroke(Color.BLACK);
        body.setStrokeType(StrokeType.OUTSIDE);
        return new Node[] { body };
    }

    public static Node[] getForegroundBodySkeleton() {
        List<Node> nodes = new ArrayList<Node>();
        Polygon[] skeletonSticks = new Polygon[] {
                new Polygon(
                        552, 277,
                    548, 439,
                    553, 446,
                    556, 445
                ),
                new Polygon(
                    458, 310,
                    449, 462,
                    460, 468,
                    465, 461
                ),
                new Polygon(
                    290, 318,
                    281, 465,
                    285, 471,
                    295, 465
                ),
                new Polygon(
                    158, 270,
                    155, 433,
                    166, 450,
                    170, 445
                )
        };
        for (Polygon skeletonStick : skeletonSticks) {
            skeletonStick.setFill(skeletonStickColor);
            skeletonStick.setStroke(Color.BLACK);
            skeletonStick.setStrokeType(StrokeType.OUTSIDE);
            nodes.add(skeletonStick);
        }

        Circle[] circles = new Circle[] {
                new Circle(553, 266, 10),
                new Circle(456, 305, 10),
                new Circle(288, 308, 10),
                new Circle(160, 266, 10)
        };
        for (Circle circle : circles) {
            circle.setFill(skeletonStickColor);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(StrokeType.OUTSIDE);
            nodes.add(circle);
        }
        Node[] nodesArr = new Node[nodes.size()];
        return nodes.toArray(nodesArr);
    }


    public static Node[] getBackgroundBodySkeleton() {
        Circle[] circles = new Circle[] {
                new Circle(456, 220, 10),
                new Circle(288, 215, 10),
        };
        for (Circle circle : circles) {
            circle.setFill(skeletonStickColor);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(StrokeType.OUTSIDE);
        }

        return circles;
    }

    public static Node[] getBelt() {
        Polygon belt = new Polygon(
                551, 272,
                621, 303,
                640, 344,
                636, 392,
                622, 465,
                592, 525,
                494, 560,
                449, 526,
                416, 500,
                372, 495,
                325, 499,
                281, 510,
                245, 537,
                174, 530,
                98, 516,
                87, 477,
                98, 436,
                70, 397,
                60, 356,
                76, 302,
                111, 274,
                154, 270,
                // back
                154, 256,
                87, 254,
                58, 278,
                30, 326,
                31, 396,
                64, 439,
                63, 514,
                87, 555,
                160, 565,
                235, 566,
                287, 559,
                309, 533,
                375, 521,
                423, 539,
                460, 581,
                511, 587,
                579, 576,
                624, 541,
                654, 514,
                675, 455,
                679, 389,
                669, 309,
                622, 278,
                551, 261
        );
        belt.setFill(beltColor);
        belt.setStroke(Color.BLACK);
        belt.setStrokeType(StrokeType.OUTSIDE);
        return new Node[] { belt };
    }

}
