package com.lab4;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;

import javax.swing.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnowMan extends Applet implements ActionListener {

    private static String assetsDir = System.getProperty("user.dir") + "\\assets\\";

    private final TransformGroup animationGroup = new TransformGroup();
    private final Transform3D animationTransform = new Transform3D();

    private double dAngleX = 0.01;
    private double dAngleY = 0.02;

    private double angleX = 0;
    private double angleY = 0;
    private final Timer timer = new Timer(10, this);

    public static void main(String[] args) {
        new SnowMan();
    }

    public SnowMan()
    {
        SimpleUniverse universe = new SimpleUniverse();
        BranchGroup group = new BranchGroup();
        universe.getViewingPlatform().setNominalViewingTransform();
        animationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        addLight(group);
        addBackground(group);

        animationGroup.addChild(SnowmanBody.getBody());
        group.addChild(animationGroup);

        universe.addBranchGraph(group);

        timer.start();
    }

    public void addLight(BranchGroup group) {
        Color3f light1Color = new Color3f(0.8f, 1.1f, 0.1f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);

        group.addChild(light1);
    }

    public void addBackground(BranchGroup group) {
        ImageComponent2D texture = new TextureLoader(assetsDir + "background.jpg", new Container()).getImage();

        Background background = new Background(texture);
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        background.setCapability(Background.ALLOW_IMAGE_WRITE);
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        group.addChild(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        animationTransform.rotX(angleX);
//         doesn't work ??
//        animationTransform.rotY(angleY);
        animationGroup.setTransform(animationTransform);
        animationGroup.setTransform(animationTransform);
        angleX += dAngleX;
        angleY += dAngleY;
        angleX = circleNumber(angleX, 0, Math.PI * 2);
        angleY = circleNumber(angleY, 0, Math.PI * 2);
    }

    private static double circleNumber(double num, double left, double right) {
        if (num < left) {
            return right;
        }
        if (num > right) {
            return left;
        }
        return num;
    }
}