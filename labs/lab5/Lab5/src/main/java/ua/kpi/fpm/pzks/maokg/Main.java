package ua.kpi.fpm.pzks.maokg;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends JFrame implements ActionListener, KeyListener {
    private final static String crabLocation = "crab.obj";
    private final static String backgroundLocation = "background.png";
    private final BranchGroup root = new BranchGroup();
    private final Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    private final TransformGroup mainTransformGroup = new TransformGroup();
    private final Transform3D transform3D = new Transform3D();
    private final Transform3D rotateTransformX = new Transform3D();
    private final Transform3D rotateTransformY = new Transform3D();
    private final Transform3D rotateTransformZ = new Transform3D();
    private final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
    private SimpleUniverse universe;
    private Scene scene;
    private Background background;

    private float currentX = 3;
    private float currentScale = 1;

    public static void main(String[] args) {
        try {
            var window = new Main();
            window.addKeyListener(window);
            window.setVisible(true);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Main() throws IOException {
        initialize();
        addTexture();
        addImageBackground();
        addLight();
        setInitialViewAngle();
        setInitialLocation();
        root.compile();
        universe.addBranchGraph(root);
    }

    private void setInitialLocation() {
        transform3D.setTranslation(new Vector3f(currentX, 0, 0));
        transform3D.setScale(currentScale);
        mainTransformGroup.setTransform(transform3D);
    }

    private void initialize() throws IOException {
        // window settings
        setTitle("Lab #5");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);

        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();

        canvas.addKeyListener(this);
        scene = getSceneFromFile();
    }

    private void addLight() {
        var dirLight = new DirectionalLight(
                new Color3f(Color.WHITE),
                new Vector3f(4.0f, -7.0f, -12.0f)
        );

        dirLight.setInfluencingBounds(new BoundingSphere(new Point3d(), 1000));
        root.addChild(dirLight);

        var ambientLight = new AmbientLight(new Color3f(Color.WHITE));
        var directionalLight = new DirectionalLight(
                new Color3f(Color.BLACK),
                new Vector3f(-1F, -1F, -1F)
        );
        var influenceRegion = new BoundingSphere(new Point3d(), 1000);
        ambientLight.setInfluencingBounds(influenceRegion);
        directionalLight.setInfluencingBounds(influenceRegion);
        root.addChild(ambientLight);
        root.addChild(directionalLight);
    }

    private TextureLoader getTextureLoader(String path) throws IOException {
        var textureResource = currentClassLoader.getResource(path);
        if (textureResource == null) {
            throw new IOException("Couldn't find texture: " + path);
        }
        return new TextureLoader(textureResource.getPath(), canvas);
    }

    private Material getMaterial() {
        var material = new Material();
        material.setAmbientColor(new Color3f(new Color(243, 242, 221)));
        material.setDiffuseColor(new Color3f(new Color(255, 233, 207)));
        material.setSpecularColor(new Color3f(new Color(255, 203, 195)));
        material.setLightingEnable(true);
        return material;
    }

    private void addTexture() throws IOException {
//        nameMap = scene.getNamedObjects();
        mainTransformGroup.addChild(scene.getSceneGroup());
        mainTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(mainTransformGroup);

    }

    private void addImageBackground() throws IOException {
        background = new Background(getTextureLoader(backgroundLocation).getImage());
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        background.setApplicationBounds(new BoundingSphere(new Point3d(),1000));
        background.setCapability(Background.ALLOW_IMAGE_WRITE);
        root.addChild(background);
    }

    private void setInitialViewAngle() {
        var vp = universe.getViewingPlatform();
        var transform = new Transform3D();
        transform.lookAt(
                new Point3d(-3.985f, -0.07f, 0),
                new Point3d(-0.7, 0, 0),
                new Vector3d(0, 1, 0)
        );
        transform.invert();
        vp.getViewPlatformTransform().setTransform(transform);
    }

    private Scene getSceneFromFile() throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        var inputStream = currentClassLoader.getResourceAsStream(crabLocation);
        if (inputStream == null) {
            throw new IOException("Resource " + crabLocation + " not found");
        }
        return file.load(new BufferedReader(new InputStreamReader(inputStream)));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        float diff = 0.05f;

        switch (keyCode) {
            case KeyEvent.VK_UP: {
                transform3D.setTranslation(new Vector3f(currentX, 0, 0));
                transform3D.setScale(currentScale);
                mainTransformGroup.setTransform(transform3D);
                currentX += 0.05;
                currentScale -= 0.005;
            } break;
            case KeyEvent.VK_X: {
                rotateTransformX.rotX(diff);
                transform3D.mul(rotateTransformX);
                mainTransformGroup.setTransform(transform3D);
            } break;
            case KeyEvent.VK_Y: {
                rotateTransformY.rotY(diff);
                transform3D.mul(rotateTransformY);
                mainTransformGroup.setTransform(transform3D);
            } break;
            case KeyEvent.VK_Z: {
                rotateTransformZ.rotZ(diff);
                transform3D.mul(rotateTransformZ);
                mainTransformGroup.setTransform(transform3D);
            } break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
