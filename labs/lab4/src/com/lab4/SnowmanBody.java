package com.lab4;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;

public class SnowmanBody {

    private static float bodyOffset = -0.1f;

    private static float topBallY = 0.65f + bodyOffset;
    private static float middleBallY = 0.45f + bodyOffset;
    private static float bottomBallY = 0.1f + bodyOffset;


    private static String assetsDir = System.getProperty("user.dir") + "\\assets\\";
    private static int primFlags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

    public static TransformGroup getBody() {
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.addChild(getSnowball(0.1f, topBallY));
        transformGroup.addChild(getSnowball(0.15f, middleBallY));
        transformGroup.addChild(getSnowball(0.25f, bottomBallY));
        transformGroup.addChild(getNose());
        transformGroup.addChild(getEye(new Vector3f(0.05f, topBallY + 0.05f, 0.05f)));
        transformGroup.addChild(getEye(new Vector3f(0.07f, topBallY + 0.05f, -0.02f)));
        return transformGroup;
    }

    public static TransformGroup getSnowball(float r, float y) {
        TransformGroup tg = new TransformGroup();
        Transform3D transformTop = new Transform3D();
        Sphere sphere = new Sphere(r, primFlags, getSnowballAppearance());
        Vector3f vectorTop = new Vector3f(.0f, y, .0f);
        transformTop.setTranslation(vectorTop);
        tg.setTransform(transformTop);
        tg.addChild(sphere);
        return tg;
    }

    public static TransformGroup getNose() {
        TransformGroup tg = new TransformGroup();
        Transform3D transformTop = new Transform3D();
        transformTop.setRotation(new Matrix3d(new double[] {
            0.5,  0.866,  0,
            0,  0,  1,
            -0.8661,  0.5, 0,
        }));
        Cone carrot = new Cone(0.03f, 0.3f, primFlags, getCarrotAppearance());
        Vector3f vectorTop = new Vector3f(0.2f, topBallY, 0.1f);
        transformTop.setTranslation(vectorTop);
        tg.setTransform(transformTop);
        tg.addChild(carrot);
        return tg;
    }

    public static TransformGroup getEye(Vector3f translation) {
        TransformGroup tg = new TransformGroup();
        Transform3D transformEye = new Transform3D();
        Sphere eye = new Sphere(0.03f, primFlags, getEyesAppearance());
//        transformEye.setRotation(new Matrix3d(new double[] {
//                0.5,  0.866,  0,
//                0,  0,  1,
//                -0.8661,  0.5, 0,
//        }));
        transformEye.setTranslation(translation);
        tg.setTransform(transformEye);
        tg.addChild(eye);
        return tg;
    }

    private static Appearance getEyesAppearance() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(100,38, 38));
        Color3f diffuse = new Color3f(new Color(178,38, 38));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getSnowballAppearance() {
        TextureLoader loader = new TextureLoader(assetsDir + "snow.jpg", new Container());
        Texture texture = loader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        Appearance appearance = new Appearance();
        appearance.setTexture(texture);
        appearance.setTextureAttributes(texAttr);
        return appearance;
    }

    private static Appearance getCarrotAppearance() {
        TextureLoader loader = new TextureLoader(assetsDir + "carrot.jpg", new Container());
        Texture texture = loader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        Appearance appearance = new Appearance();
        appearance.setTexture(texture);
        appearance.setTextureAttributes(texAttr);
        return appearance;
    }
}