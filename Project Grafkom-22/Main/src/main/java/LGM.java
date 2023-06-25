import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class LGM {
    private Window window = new Window(800, 800, "LGM Main");
    ArrayList<LGM_Object> LGMO = new ArrayList<>();
    ArrayList<Object> Background = new ArrayList<>();

    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    List<ShaderProgram.ShaderModuleData> shaderModuleDataList = Arrays.asList(
            new ShaderProgram.ShaderModuleData(
                    "Project Grafkom-22\\Main\\resources\\shaders\\scene.vert", GL_VERTEX_SHADER),
            new ShaderProgram.ShaderModuleData(
                    "Project Grafkom-22\\Main\\resources\\shaders\\scene.frag", GL_FRAGMENT_SHADER)

    );
    public void run() {
        init();
        loop();
        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init() {
        window.init();
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glDepthMask(true);
        glDepthFunc(GL_LEQUAL);
        glDepthRange(0.0f, 1.0f);
        // code dst jangan ditaruh diatas code diatas
        camera.setPosition(-0.12f, -0.33999997f, 8.54f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));

        //Head
        LGMO.add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                1.0f,
                0.5f,
                0.6f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).translateObject(0.0f, 0.0f, -0.2f);


        // Badan Antena
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.1f,
                0.1f,
                0.4f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(0).translateObject(0.0f, 0.0f, 0.9f);
        LGMO.get(0).getChildObject().get(0).rotateObject(4.75f, 1f, 0f, 0f);

        // Fondasi Antena
        LGMO.get(0).getChildObject().get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.04f,
                0.04f,
                0.05f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        LGMO.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(0.0f, 0.7f, 0.0f);

        // Top of Antena
        LGMO.get(0).getChildObject().get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.2f,
                0.2f,
                0.2f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(0).getChildObject().get(1).translateObject(0.00f, 1.4f, -0.15f);

        //White Eye Middle
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(255/255f, 255/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.25f,
                0.15f,
                0.15f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(1).translateObject(0.00f, 0.25f, 0.35f);

        //Black Eye Middle
        LGMO.get(0).getChildObject().get(1).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0/255f, 0/255f, 0/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.05f,
                0.05f,
                0.05f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(0.00f, 0.2f, 0.50f);

        //Kelopak Eye Middle Bawah
        LGMO.get(0).getChildObject().get(1).getChildObject().add(new LGM_Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData(
                                "C:\\File Coding InteliJ JAVA\\Grafika Komputer\\GrafKom-Yan\\Main\\resources\\shaders\\scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData(
                                "C:\\File Coding InteliJ JAVA\\Grafika Komputer\\GrafKom-Yan\\Main\\resources\\shaders\\scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 2.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(-0.225f, 0.225f, .7f),//TITIK1
                        new Vector3f(0.00f, 0.1f, .7f), //TITIK2
                        new Vector3f(0.225f, 0.225f, .7f) //TITIK3
                )
        ));
        LGMO.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(0.00f, -0.14f, -0.1f);

        //White Eye Left
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(255/255f, 255/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.25f,
                0.15f,
                0.15f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(2).rotateObject(0.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(2).translateObject(-0.495f, 0.19f, 0.35f);

        //Black Eye Left
        LGMO.get(0).getChildObject().get(2).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0/255f, 0/255f, 0/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.05f,
                0.05f,
                0.05f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(2).getChildObject().get(0).translateObject(-0.465f, 0.15f, 0.50f);

        //Kelopak Eye Left Bawah
        LGMO.get(0).getChildObject().get(2).getChildObject().add(new LGM_Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData(
                                "C:\\File Coding InteliJ JAVA\\Grafika Komputer\\GrafKom-Yan\\Main\\resources\\shaders\\scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData(
                                "C:\\File Coding InteliJ JAVA\\Grafika Komputer\\GrafKom-Yan\\Main\\resources\\shaders\\scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 2.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(-0.225f, 0.225f, .7f),//TITIK1
                        new Vector3f(0.00f, 0.1f, .7f), //TITIK2
                        new Vector3f(0.225f, 0.225f, .7f) //TITIK3
                )
        ));
        LGMO.get(0).getChildObject().get(2).getChildObject().get(1).rotateObject(0.35f, 0f, 0f, 1f);

        LGMO.get(0).getChildObject().get(2).getChildObject().get(1).translateObject(-0.35f, -0.15f, -0.1f);

        //White Eye Right
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(255/255f, 255/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.25f,
                0.15f,
                0.15f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(3).rotateObject(-0.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(3).translateObject(0.495f, 0.19f, 0.35f);

        //Black Eye Right
        LGMO.get(0).getChildObject().get(3).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0/255f, 0/255f, 0/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.05f,
                0.05f,
                0.05f,
                90, // Stack -->
                45, // Sector --> Titik
                0));
        //.rotateObject(-0.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(0.465f, 0.15f, 0.50f);

        //Kelopak Eye Right Bawah
        LGMO.get(0).getChildObject().get(3).getChildObject().add(new LGM_Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData(
                                "C:\\File Coding InteliJ JAVA\\Grafika Komputer\\GrafKom-Yan\\Main\\resources\\shaders\\scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData(
                                "C:\\File Coding InteliJ JAVA\\Grafika Komputer\\GrafKom-Yan\\Main\\resources\\shaders\\scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 2.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(-0.225f, 0.225f, .7f),//TITIK1
                        new Vector3f(0.00f, 0.1f, .7f), //TITIK2
                        new Vector3f(0.225f, 0.225f, .7f) //TITIK3
                )
        ));
        LGMO.get(0).getChildObject().get(3).getChildObject().get(1).rotateObject(-0.35f, 0f, 0f, 1f);

        LGMO.get(0).getChildObject().get(3).getChildObject().get(1).translateObject(0.35f, -0.15f, -0.1f);
        //Ear Right
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.6f,
                0.6f,
                0.6f,
                15, // Stack -->
                30, // Sector --> Titik
                6));
        LGMO.get(0).getChildObject().get(4).rotateObject(-1.2f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(4).translateObject(0.6f, 0.275f, 0f);
        //Ear Lobe Right
        LGMO.get(0).getChildObject().get(4).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(150/255f, 160/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.3f,
                0.3f,
                0.1f,
                15, // Stack -->
                30, // Sector --> Titik
                6));
        LGMO.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject(-1.2f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(0.65f, 0.35f, 0.35f);
        //Ear Left
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.6f,
                0.6f,
                0.6f,
                15, // Stack -->
                30, // Sector --> Titik
                6));
        LGMO.get(0).getChildObject().get(5).rotateObject(-0.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(5).translateObject(-0.6f, 0.275f, 0f);
        //Ear Lobe Left
        LGMO.get(0).getChildObject().get(5).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(150/255f, 160/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.3f,
                0.3f,
                0.1f,
                15, // Stack -->
                30, // Sector --> Titik
                6));
        LGMO.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject(-0.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(-0.65f, 0.35f, 0.35f);
        // Mulut
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0/255f, 0/255f, 0/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.07f,
                0.01f,
                0.002f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(6).translateObject(0f, -0.25f, 0.52f);

        //Purple Neck
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(116/255f, 71/255f, 158/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.45f,
                0.225f,
                0.10f,
                90, // Stack -->
                45, // Sector --> Titik
                4));
        LGMO.get(0).getChildObject().get(7).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(7).translateObject(0.00f, -0.32f, 0.0f);

        // Badan Atas Alien
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.8f,
                0.5f,
                0.25f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(8).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(8).translateObject(0f, -0.67f, 0f);
        // Sabuk
        LGMO.get(0).getChildObject().get(8).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(51/255f, 56/255f, 120/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.85f,
                0.60f,
                0.15f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(8).getChildObject().get(0).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(8).getChildObject().get(0).translateObject(0.0f, -0.95f, 0.0f);
        // Kepala Sabuk
        LGMO.get(0).getChildObject().get(8).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(5/255f, 33/255f, 90/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.55f,
                0.10f,
                0.3f,
                15, // Stack -->
                30, // Sector --> Titik
                3));
        LGMO.get(0).getChildObject().get(8).getChildObject().get(1).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(8).getChildObject().get(1).translateObject(0.0f, -0.925f, 0.65f);

        // Gambar Planet Kuning
        LGMO.get(0).getChildObject().get(8).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 157/255f, 126/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.15f,
                0.15f,
                0.025f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(8).getChildObject().get(2).translateObject(0.3f, -0.6f, 0.294f);

        // Badan Biru
        LGMO.get(0).getChildObject().get(8).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.137f,
                0.137f,
                0.025f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(8).getChildObject().get(3).translateObject(0.3f, -0.6f, 0.298f);
        // Gambar Ring Planet
        LGMO.get(0).getChildObject().get(8).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 157/255f, 126/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.1f,
                0.025f,
                0.025f,
                15, // Stack -->
                30, // Sector --> Titik
                5));
        LGMO.get(0).getChildObject().get(8).getChildObject().get(4).rotateObject(1.6f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(8).getChildObject().get(4).rotateObject(0.2f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(8).getChildObject().get(4).translateObject(0.3f, -0.6f, 0.45f);


        // Badan Bawah Alien
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.80f,
                0.55f,
                0.125f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(9).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(9).translateObject(0f, -1.135f, 0f);

        // Paha Kiri Besar
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.475f,
                0.4f,
                0.2f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(10).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(10).translateObject(-0.3f, -1.335f, 0f);
        // Kaki Kiri
        LGMO.get(0).getChildObject().get(10).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(9/255f, 42/255f, 103/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.525f,
                0.5f,
                0.125f,
                100, // Stack -->
                50, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(10).getChildObject().get(0).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(10).getChildObject().get(0).translateObject(-0.3f, -1.635f, 0f);
        // Paha Kiri Kecil
        LGMO.get(0).getChildObject().get(10).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.425f,
                0.4f,
                0.125f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(10).getChildObject().get(1).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(10).getChildObject().get(1).translateObject(-0.3f, -1.635f, 0f);

        // Paha Kanan Besar
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.475f,
                0.4f,
                0.2f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(11).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(11).translateObject(0.3f, -1.335f, 0f);
        // Kaki Kanan
        LGMO.get(0).getChildObject().get(11).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(9/255f, 42/255f, 103/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.525f,
                0.5f,
                0.125f,
                100, // Stack -->
                50, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(11).getChildObject().get(0).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(11).getChildObject().get(0).translateObject(0.3f, -1.635f, 0f);
        // Paha Kanan Kecil
        LGMO.get(0).getChildObject().get(11).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.425f,
                0.4f,
                0.125f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(11).getChildObject().get(1).rotateObject(1.55f, 1f, 0f, 0f);
        LGMO.get(0).getChildObject().get(11).getChildObject().get(1).translateObject(0.3f, -1.635f, 0f);

        // Lengan Kiri
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.2f,
                0.2f,
                0.8f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(12).rotateObject(1.6f, 0f, 1f, 0f);
        LGMO.get(0).getChildObject().get(12).rotateObject(1f, 0f, 0f, -1f);
        LGMO.get(0).getChildObject().get(12).rotateObject(0.3f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(12).translateObject(-1.0f, -0.4f, 0f);
        // Telapak Kiri
        LGMO.get(0).getChildObject().get(12).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.3f,
                0.3f,
                0.3f,
                100, // Stack -->
                50, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(12).getChildObject().get(0).rotateObject(1.6f, 0f, 1f, 0f);
        LGMO.get(0).getChildObject().get(12).getChildObject().get(0).translateObject(-1.9f, 0.22f, 0f);

        // Jari Kiri1
        LGMO.get(0).getChildObject().get(12).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.01f,
                0.01f,
                0.005f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(12).getChildObject().get(1).rotateObject(-1.5f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(12).getChildObject().get(1).translateObject(-2.3f, 0.22f, 0f);

        // Jari Kiri2
        LGMO.get(0).getChildObject().get(12).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.01f,
                0.01f,
                0.005f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(12).getChildObject().get(2).rotateObject(-2.1f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(12).getChildObject().get(2).translateObject(-2.3f, 0.675f, 0f);

        // Jari Kiri3
        LGMO.get(0).getChildObject().get(12).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.0175f,
                0.0175f,
                0.00425f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(12).getChildObject().get(3).rotateObject(-3.3f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(12).getChildObject().get(3).translateObject(-1.6f, 0.7f, 0f);

        // Lengan Kanan
        LGMO.get(0).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(23/255f, 114/255f, 255/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.2f,
                0.2f,
                0.8f,
                15, // Stack -->
                30, // Sector --> Titik
                1));
        LGMO.get(0).getChildObject().get(13).rotateObject(1.6f, 0f, 1f, 0f);
        LGMO.get(0).getChildObject().get(13).rotateObject(1f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(13).rotateObject(0.3f, 0f, 0f, -1f);
        LGMO.get(0).getChildObject().get(13).translateObject(1.0f, -0.4f, 0f);

        // Telapak Kanan
        LGMO.get(0).getChildObject().get(13).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.3f,
                0.3f,
                0.3f,
                100, // Stack -->
                50, // Sector --> Titik
                0));
        LGMO.get(0).getChildObject().get(13).getChildObject().get(0).translateObject(1.75f, 0.28f, -0.25f);


        // Jari Kanan1
        LGMO.get(0).getChildObject().get(13).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.01f,
                0.01f,
                0.005f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(13).getChildObject().get(1).rotateObject(1.9f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(13).getChildObject().get(1).translateObject(2.3f, 0.475f, 0f);

        // Jari Kanan2
        LGMO.get(0).getChildObject().get(13).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.01f,
                0.01f,
                0.005f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(13).getChildObject().get(2).rotateObject(2.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(13).getChildObject().get(2).translateObject(2.1f, 0.8f, 0f);


        // Jari Kanan3
        LGMO.get(0).getChildObject().get(13).getChildObject().add(new LGM_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(188/255f, 209/255f, 75/255f, 1.0f),
                new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)),
                0.0175f,
                0.0175f,
                0.00425f,
                100, // Stack -->
                50, // Sector --> Titik
                7));
        LGMO.get(0).getChildObject().get(13).getChildObject().get(3).rotateObject(3.4f, 0f, 0f, 1f);
        LGMO.get(0).getChildObject().get(13).getChildObject().get(3).translateObject(1.55f, 0.75f, 0f);

        //BG
        //Rak background
        Background.add(new Sphere(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.8f, 0.4f, 0.5f, 1.0f),0.0,
                new ArrayList<>(List.of(0f, 0f, 0f)),
                0.1f,
                0.1f,
                1.0f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Background.get(0).scaleObject(8.0f, 1.0f, 8.0f);
        Background.get(0).translateObject(0.0f, -0.33f, 0.0f);
//        //vas bunga
//        Background.get(0).getChildObject().add(new Sphere(
//                shaderModuleDataList,
//                new ArrayList<>(
//                ),
//                new Vector4f(0.4f, 0.2f, 0.0f, 1.0f),0.0,
//                new ArrayList<>(List.of(0f, 0f, 0f)),
//                0.5f,
//                0.5f,
//                0.5f,
//                15, // Stack -->
//                30, // Sector --> Titik
//                0));
//        Background.get(0).getChildObject().get(0).translateObject(0.0f, -0.03f, -3.0f);
//        //Daun
//        Background.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
//                shaderModuleDataList,
//                new ArrayList<>(
//                ),
//                new Vector4f(0.0f, 0.8f, 0.0f, 1.0f),0.0,
//                new ArrayList<>(List.of(0f, 0f, 0f)),
//                0.3f,
//                0.3f,
//                0.3f,
//                15, // Stack -->
//                30, // Sector --> Titik
//                3));
//        Background.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(0.0f, 0.45f, -3.0f);
    }
    int countRotate = 360;
    int countRotate2 = 360;
    int moveCamera = 360;
    boolean Xpress = false;
    boolean limitKaki = true, limitTanganKanan = true, limitTanganKiri = true, limitAntena = true;
    int counterKaki = 0, counterTgnKanan = 0, counterTgnKiri = 0, counterAntenna = 0;
    float besarMata = 1.0f;
    boolean perbesarMata = true;
    float mulutRotate = 0.0f;
    boolean rotateMulut = true;
    float transMulut = 0.0f;
    //5678rtyfghvbn
    public void input() {
        if (window.isKeyPressed(GLFW_KEY_D)) {
            LGMO.get(0).translateObject(0.002f, 0.0f, 0.0f);
            camera.moveRight(0.002f);
            if (limitKaki) {
                counterKaki++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);


                if (counterKaki == 15.0f) {
                    limitKaki = false;
                }
            } else {
                counterKaki--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                if (counterKaki == -15.0f) {
                    limitKaki = true;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            LGMO.get(0).translateObject(-0.002f, 0.0f, 0.0f);
            camera.moveLeft(0.002f);

            if (limitKaki) {
                counterKaki++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);
                if (counterKaki == 15.0f) {
                    limitKaki = false;
                }
            } else {
                counterKaki--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                if (counterKaki == -15.0f) {
                    limitKaki = true;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_W)) {
            LGMO.get(0).translateObject(0.0f, 0.0f, 0.02f);
            camera.moveBackwards(0.02f);

            if (limitKaki) {
                counterKaki++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);
                if (counterKaki == 15.0f) {
                    limitKaki = false;
                }
            } else {
                counterKaki--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                if (counterKaki == -15.0f) {
                    limitKaki = true;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            LGMO.get(0).translateObject(0.0f, 0.0f, -0.02f);
            camera.moveForward(0.02f);

            if (limitKaki) {
                counterKaki++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);
                if (counterKaki == 15.0f) {
                    limitKaki = false;
                }
            } else {
                counterKaki--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(10).updateCenterPoint();
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(10).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = LGMO.get(0).getChildObject().get(11).updateCenterPoint();
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                LGMO.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(1.0f), 1.0f, 1.0f, 0.0f);
                LGMO.get(0).getChildObject().get(11).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                if (counterKaki == -15.0f) {
                    limitKaki = true;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_V)) {
            if (limitTanganKiri) {
                counterTgnKanan++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(12).updateCenterPoint();
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(1f), 1.0f, 0.0f, 0.0f);
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(1f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                if (counterTgnKanan == 10f) {
                    limitTanganKiri = false;
                }
            } else {
                counterTgnKanan--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(12).updateCenterPoint();
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(-1f), 1.0f, 0.0f, 0.0f);
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(-1f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(12).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                if (counterTgnKanan == -10f) {
                    limitTanganKiri = true;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_N)) {
            if (limitTanganKanan) {
                counterTgnKiri++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(13).updateCenterPoint();
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(1f), 1.0f, 0.0f, 0.0f);
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(1f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                if (counterTgnKiri == 10f) {
                    limitTanganKanan = false;
                }
            } else {
                counterTgnKiri--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(13).updateCenterPoint();
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(-1f), 1.0f, 0.0f, 0.0f);
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(-1f), 0.0f, 0.0f, 1.0f);
                LGMO.get(0).getChildObject().get(13).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                if (counterTgnKiri == -10f) {
                    limitTanganKanan = true;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_B)) {
            if (limitAntena) {
                counterAntenna++;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(0).updateCenterPoint();
                LGMO.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 1.0f, 1.0f);
                LGMO.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                if (counterAntenna == 8f) {
                    limitAntena = false;
                }

            }
            else {
                counterAntenna--;
                Vector3f tempCenterPoint = LGMO.get(0).getChildObject().get(0).updateCenterPoint();
                LGMO.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                LGMO.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(1.0f), 1.0f, 1.0f, 1.0f);
                LGMO.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
                if (counterAntenna == -8f) {
                    limitAntena = true;
                }
            }
        }

        //Marah
        if (window.isKeyPressed(GLFW_KEY_SPACE))
        {
            if(perbesarMata)
            {
                besarMata += 0.1f;
                Vector3f mataTengah = LGMO.get(0).getChildObject().get(1).getChildObject().get(0).getModel().transformPosition(new Vector3f(0, 0, 0));
                LGMO.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(mataTengah.x * -1, mataTengah.y * -1, 0.0f);
                LGMO.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(1.15f, 1.1f, 1.00f);
                LGMO.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(mataTengah.x, mataTengah.y, -0.0f);

                Vector3f mataKanan = LGMO.get(0).getChildObject().get(2).getChildObject().get(0).getModel().transformPosition(new Vector3f(0, 0, 0));
                LGMO.get(0).getChildObject().get(2).getChildObject().get(0).translateObject(mataKanan.x * -1, mataKanan.y * -1, 0.0f);
                LGMO.get(0).getChildObject().get(2).getChildObject().get(0).scaleObject(1.15f, 1.1f, 1.00f);
                LGMO.get(0).getChildObject().get(2).getChildObject().get(0).translateObject(mataKanan.x, mataKanan.y, -0.0f);

                Vector3f mataKiri = LGMO.get(0).getChildObject().get(3).getChildObject().get(0).getModel().transformPosition(new Vector3f(0, 0, 0));
                LGMO.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(mataKiri.x * -1, mataKiri.y * -1, 0.0f);
                LGMO.get(0).getChildObject().get(3).getChildObject().get(0).scaleObject(1.15f, 1.1f, 1.00f);
                LGMO.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(mataKiri.x, mataKiri.y, -0.0f);
                if(besarMata > 2.0f)
                    perbesarMata = false;
            }
            if(rotateMulut){
                mulutRotate+=0.1f;
                Vector3f mulut = LGMO.get(0).getChildObject().get(6).updateCenterPoint();
                LGMO.get(0).getChildObject().get(6).translateObject(mulut.x * -1, mulut.y * -1, mulut.z * -1);
                LGMO.get(0).getChildObject().get(6).rotateObject(0.35f, 1f,0f,0f);
                LGMO.get(0).getChildObject().get(6).translateObject(mulut.x * 1, mulut.y * 1, mulut.z * 1);

                if(mulutRotate >= 1.0f)
                    rotateMulut = false;
            }
            else{
//                if(transMulut < 6){
//                    transMulut += 0.2f;
//                    LGMO.get(0).getChildObjectLGM().get(6).translateObject(0f, 0.0085f, 0.0025f);
//                }
                // transMulut buat bantu hitung berapa offsetY (0.255 == 0.0085 * 6/0.2f)
                if(transMulut < 6){
                    transMulut += 6f;
                    LGMO.get(0).getChildObject().get(6).translateObject(0f, 0.255f, 0.075f);
                }
            }

        }

        // RTYFGH ==> Rotate
        if (window.isKeyPressed(GLFW_KEY_R)) {
            for (LGM_Object i : LGMO) {
                i.rotateObject(0.01f, 0f, 0f, 1f);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_Y)) {
            for (LGM_Object i : LGMO) {
                i.rotateObject(-0.01f, 0f, 0f, 1f);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_T)) {
            Vector3f tempCenterPointw = LGMO.get(0).updateCenterPoint();
            LGMO.get(0).translateObject(tempCenterPointw.x * -1, tempCenterPointw.y * -1, tempCenterPointw.z * -1);
            LGMO.get(0).rotateObject(0.05f, 1f, 0f, 0f);
            LGMO.get(0).translateObject(tempCenterPointw.x * 1, tempCenterPointw.y * 1, tempCenterPointw.z * 1);
        }
        if (window.isKeyPressed(GLFW_KEY_G)) {
            Vector3f tempCenterPoints = LGMO.get(0).updateCenterPoint();
            LGMO.get(0).translateObject(tempCenterPoints.x * -1, tempCenterPoints.y * -1, tempCenterPoints.z * -1);
            LGMO.get(0).rotateObject(-0.05f, 1f, 0f, 0f);
            LGMO.get(0).translateObject(tempCenterPoints.x * 1, tempCenterPoints.y * 1, tempCenterPoints.z * 1);
        }
        if (window.isKeyPressed(GLFW_KEY_F)) {
            Vector3f tempCenterPointsa = LGMO.get(0).updateCenterPoint();
            LGMO.get(0).translateObject(tempCenterPointsa.x * -1, tempCenterPointsa.y * -1, tempCenterPointsa.z * -1);
            LGMO.get(0).rotateObject(-0.05f, 0f, 1f, 0f);
            LGMO.get(0).translateObject(tempCenterPointsa.x * 1, tempCenterPointsa.y * 1, tempCenterPointsa.z * 1);
        }
        if (window.isKeyPressed(GLFW_KEY_H)) {
            Vector3f tempCenterPointsd = LGMO.get(0).updateCenterPoint();
            LGMO.get(0).translateObject(tempCenterPointsd.x * -1, tempCenterPointsd.y * -1, tempCenterPointsd.z * -1);
            LGMO.get(0).rotateObject(0.05f, 0f, 1f, 0f);
            LGMO.get(0).translateObject(tempCenterPointsd.x * 1, tempCenterPointsd.y * 1, tempCenterPointsd.z * 1);
        }

        if (window.isKeyPressed(GLFW_KEY_U)) {
            for (LGM_Object i : LGMO) {
                i.translateObject(0f, 0f, 0.001f);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_O)) {
            for (LGM_Object i : LGMO) {
                i.translateObject(0f, 0f, -0.001f);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_I)) {
            for (LGM_Object i : LGMO) {
                i.translateObject(0f, 0.001f, 0f);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_K)) {
            for (LGM_Object i : LGMO) {
                i.translateObject(0f, -0.001f, 0f);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_J)) {
            for (LGM_Object i : LGMO) {
                i.translateObject(-0.001f, 0f, 0f);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_L)) {
            for (LGM_Object i : LGMO) {
                i.translateObject(0.001f, 0f, 0f);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_ALT)) {
            camera.moveForward(0.12f);
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
            camera.moveBackwards(0.12f);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.moveDown(0.12f);
        }

        if (window.isKeyPressed(GLFW_KEY_UP)) {
            camera.moveUp(0.12f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.moveLeft(0.12f);
        }

        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.moveRight(0.12f);
        }

        if (window.getMouseInput().isLeftButtonPressed()){
            Vector2f displayVec = window.getMouseInput().getDisplVec();
            camera.addRotation((float)Math.toRadians(displayVec.x * 0.1f), (float) Math.toRadians(displayVec.y * 0.1f));
        }

        if (window.getMouseInput().getScroll().y != 0){
            projection.setFOV(projection.getFOV()-(window.getMouseInput().getScroll().y*0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }
        if (window.isKeyPressed(GLFW_KEY_X)){
            if(countRotate == 360)
                countRotate = 0;

        }
        if(countRotate<360){
            Vector3f simpan = camera.getPosition();
            camera.setPosition(LGMO.get(0).getCenterPoint().get(0),LGMO.get(0).getCenterPoint().get(1),LGMO.get(0).getCenterPoint().get(2)+5.5f);
            camera.addRotation(0f,-(float)(Math.toRadians(1)));
            camera.setPosition(simpan.get(0),simpan.get(1),simpan.get(2));
            countRotate+=1;
        }

        if(window.isKeyPressed(GLFW_KEY_Z)){
            camera.rotateObjectCamera(0f,(float)(Math.toRadians(1)));
        }


//        if (window.isKeyPressed(GLFW_KEY_Z)){
//            if(countRotate2 == 360)
//            {
//                countRotate2 = 0;
//            }
//            if(moveCamera == 360){
//                moveCamera = 0;
//            }
//
//        }
//        if(countRotate2<360){
//            if(moveCamera<360){
//                Vector3f simpan = camera.getPosition();
////                camera.setPosition(-0.12f, -0.33999997f, 8.54f);
//                camera.moveLeft(0.2f);
////                camera.setPosition(simpan.get(0),simpan.get(1),simpan.get(2));
//                moveCamera+=1;
//            }
//            Vector3f simpan = camera.getPosition();
//            camera.setPosition(-0.12f, -0.33999997f, 8.54f);
//            camera.addRotation(0f,-(float)(Math.toRadians(1)));
//            camera.setPosition(simpan.get(0),simpan.get(1),simpan.get(2));
//            countRotate2+=1;
//        }


//        if (window.isKeyPressed(GLFW_KEY_Z)){
//            Vector3f temp = LGMO.get(0).getModel().transformPosition(new Vector3f(0.0f,0.0f,0.0f));
//            camera.rotate(0,-(float)(Math.toRadians(1)), temp);
//        }
//
//        if (window.isKeyPressed(GLFW_KEY_A)) {
//            LGMO.get(0).translateObject(-0.01f,0f,0f);
//            camera.moveLeft((float)(Math.toRadians(1)));
//        }
//        if (window.isKeyPressed(GLFW_KEY_D)) {
//            LGMO.get(0).translateObject(0.01f,0f,0f);
//            camera.moveRight((float)(Math.toRadians(1)));
//        }
//        if (window.isKeyPressed(GLFW_KEY_W)) {
//            LGMO.get(0).translateObject(0f,0f,0.01f);
//            camera.moveBackwards((float)(Math.toRadians(1)));
//        }
//        if (window.isKeyPressed(GLFW_KEY_S)) {
//            LGMO.get(0).translateObject(0f,0f,-0.01f);
//            camera.moveForward((float)(Math.toRadians(1)));
//        }

        if(window.isKeyPressed(GLFW_KEY_COMMA))
        {
            camera.addRotation((float) Math.toRadians(0f), (float) Math.toRadians(-1f));
        }
        if(window.isKeyPressed(GLFW_KEY_SLASH))
        {
            camera.addRotation((float) Math.toRadians(0f), (float) Math.toRadians(1f));
        }
        if(window.isKeyPressed(GLFW_KEY_CAPS_LOCK))
        {
            camera.addRotation((float) Math.toRadians(1f), (float) Math.toRadians(0f));
        }
        if(window.isKeyPressed(GLFW_KEY_TAB))
        {
            camera.addRotation((float) Math.toRadians(-1f), (float) Math.toRadians(0f));
        }
    }



    public void loop() {

        while (window.isOpen()) {
            window.update();
            glClearColor(255/255f, 255/255f, 255/255f, 1.0f); // RapidTables.com (RGB color code chart)
            GL.createCapabilities();
            glClearDepth(1.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            input();
            LGMO.get(0).updateCenterPoint(true);

//            for (LGM_Object obj3D : LGMO) {
//                obj3D.draw(camera,projection);
//            }
//            for (Object obj3D : Background) {
//                obj3D.draw(camera,projection);
//            }
            System.out.println("X"+camera.getPosition().get(0));
            System.out.println("Y"+camera.getPosition().get(1));
            System.out.println("Z"+camera.getPosition().get(2));



            System.out.println((float)(Math.toRadians(1)));




            //Restore State
            glDisableVertexAttribArray(0);
            // Pull for window events
            // The key callback above will only be
            // invoked during this call
            glfwPollEvents();
        }
    }


    public static void main (String[]args){
        new LGM().run();
    }
}

