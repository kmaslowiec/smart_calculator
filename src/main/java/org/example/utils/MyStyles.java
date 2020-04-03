package org.example.utils;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MyStyles {

    public void transformToCircle(Circle circle, Image img, double radius) {
        circle.setRadius(radius);
        circle.setFill(new ImagePattern(img));
    }
}