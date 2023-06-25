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

public class BabiCeleng {
    private Window window =
            new Window(600, 600, "IAN BABI Main");
    ArrayList<Object> Babi = new ArrayList<>();
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



        camera.setPosition(0.0f, 0.0f, 0.5f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(30.0f));
        // code dst jangan ditaruh diatas code diatas

        // Badan Babi
        Babi.add(new SphereBabi(

                shaderModuleDataList
                ,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.2f,
                0.3f,
                0.2f,
                15, // Stack -->
                30, // Sector --> Titik
                3));
        Babi.get(0).scaleObject(1.3f, 1.3f, 1.3f);
        Babi.get(0).rotateObject((float)Math.toRadians(90.0f), 1.0f, 0.0f, 0.0f);

        //Kepala Babi
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.2f,
                0.2f,
                0.2f,
                15, // Stack -->
                30, // Sector --> Titik
                3));
        Babi.get(0).getChildObject().get(0).scaleObject(1.0f, 1.0f, 1.0f);
        Babi.get(0).getChildObject().get(0).translateObject(0.0f, 0.075f, 0.43f);

        // Hidung babi
        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.115f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(0.0f, 0.1f, 0.68f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(8.0f), 1.0f, 0.0f, 0.0f);

        // lubang hidung
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.4f, 0.69f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.2f,
                0.2f,
                0.5f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).scaleObject(0.075f, 0.1f, 0.03f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).translateObject(0.05f, 0.12f, 0.695f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(8.0f), 1.0f, 0.0f, 0.0f);

        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.4f, 0.69f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.2f,
                0.2f,
                0.5f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(1).scaleObject(0.075f, 0.1f, 0.03f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(1).translateObject(-0.05f, 0.12f, 0.695f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(8.0f), 1.0f, 0.0f, 0.0f);

        //Mata
        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.2f,
                0.2f,
                0.2f,
                50, // Stack -->
                100, // Sector --> Titik
                3));
        Babi.get(0).getChildObject().get(0).getChildObject().get(1).scaleObject(0.1f, 0.1f, 0.1f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(1).translateObject(0.09f, 0.15f, 0.605f);

        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.2f,
                0.2f,
                0.2f,
                15, // Stack -->
                30, // Sector --> Titik
                3));
        Babi.get(0).getChildObject().get(0).getChildObject().get(2).scaleObject(0.1f, 0.1f, 0.1f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(2).translateObject(-0.09f, 0.15f, 0.605f);

        //udel
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.08f,
                0.08f,
                0.03f,
                15, // Stack -->
                30, // Sector --> Titik
                2));

        Babi.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(90.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(1).translateObject(0.0f, -0.275f, 0.0f);

        //EKOR
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                15, // Stack -->
                30, // Sector --> Titik
                6));

        Babi.get(0).getChildObject().get(2).scaleObject(0.1f, 0.1f, 0.1f);
        Babi.get(0).getChildObject().get(2).translateObject(0.0f, 0.0f, -0.43f);

        // LUBANG CELENGAN
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.15f,
                0.1f,
                0.1f,
                15, // Stack -->
                30, // Sector --> Titik
                31));

        Babi.get(0).getChildObject().get(3).translateObject(0.0f, 0.17f, 0.0f);
        Babi.get(0).getChildObject().get(3).rotateObject((float)Math.toRadians(90.0f),0.0f, 1.0f,  0.0f);

        //KAKI KIRI DEPAN
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.115f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(4).scaleObject(0.5f, 0.5f, 3.0f);
        Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(4).translateObject(0.1f, -0.01f, 0.25f);
        Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);
        //KAKI KIRI BELAKANG
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.115f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(5).scaleObject(0.5f, 0.5f, 3.0f);
        Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(5).translateObject(0.1f, -0.01f, -0.25f);
        Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);
        // kaki kanan depan
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.115f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(6).scaleObject(0.5f, 0.5f, 3.0f);
        Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(6).translateObject(-0.1f, -0.01f, 0.25f);
        Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);
        //kaki kanan belakang
        Babi.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.115f,
                15, // Stack -->
                30, // Sector --> Titik
                2));
        Babi.get(0).getChildObject().get(7).scaleObject(0.5f, 0.5f, 3.0f);
        Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(7).translateObject(-0.1f, -0.01f, -0.25f);
        Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        // KUKU Kaki kanan belakang
        Babi.get(0).getChildObject().get(7).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(7).getChildObject().get(0).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(0).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(0).rotateObject((float) Math.toRadians(-50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(0).translateObject(-0.17f, -0.38f, -0.227f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(0).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(7).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(7).getChildObject().get(1).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(1).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(1).rotateObject((float) Math.toRadians(-50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(1).translateObject(-0.17f, -0.38f, -0.25f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(1).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(7).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(7).getChildObject().get(2).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(2).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(2).rotateObject((float) Math.toRadians(-50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(2).translateObject(-0.17f, -0.38f, -0.273f);
        Babi.get(0).getChildObject().get(7).getChildObject().get(2).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        // KUKU Kaki kanan depan
        Babi.get(0).getChildObject().get(6).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(6).getChildObject().get(0).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject((float) Math.toRadians(-50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(0).translateObject(-0.17f, -0.38f, 0.227f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(6).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(6).getChildObject().get(1).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(1).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(1).rotateObject((float) Math.toRadians(-50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(1).translateObject(-0.17f, -0.38f, 0.25f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(1).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(6).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(6).getChildObject().get(2).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(2).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(2).rotateObject((float) Math.toRadians(-50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(2).translateObject(-0.17f, -0.38f, 0.273f);
        Babi.get(0).getChildObject().get(6).getChildObject().get(2).rotateObject((float) Math.toRadians(-30.0f), 0.0f, 0.0f, 1.0f);

        // KUKU Kaki kiri belakang
        Babi.get(0).getChildObject().get(5).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(5).getChildObject().get(0).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject((float) Math.toRadians(50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(0.17f, -0.38f, -0.227f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(5).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(5).getChildObject().get(1).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(1).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(1).rotateObject((float) Math.toRadians(50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(1).translateObject(0.17f, -0.38f, -0.25f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(1).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(5).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(5).getChildObject().get(2).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(2).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(2).rotateObject((float) Math.toRadians(50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(2).translateObject(0.17f, -0.38f, -0.273f);
        Babi.get(0).getChildObject().get(5).getChildObject().get(2).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);

        // KUKU Kaki kiri depan
        Babi.get(0).getChildObject().get(4).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(4).getChildObject().get(0).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(0.17f, -0.38f, 0.227f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(4).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(4).getChildObject().get(1).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(1).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(1).rotateObject((float) Math.toRadians(50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(1).translateObject(0.17f, -0.38f, 0.25f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(1).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);

        Babi.get(0).getChildObject().get(4).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f,
                30, // Stack -->
                0, // Sector --> Titik
                6));
        Babi.get(0).getChildObject().get(4).getChildObject().get(2).scaleObject(0.04f, 0.04f, 0.1f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(2).rotateObject((float) Math.toRadians(270.0f), 1.0f, 0.0f, 0.0f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(2).rotateObject((float) Math.toRadians(50.0f), 0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(2).translateObject(0.17f, -0.38f, 0.273f);
        Babi.get(0).getChildObject().get(4).getChildObject().get(2).rotateObject((float) Math.toRadians(30.0f), 0.0f, 0.0f, 1.0f);

        //kuping
        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.27f,
                0.27f,
                0.27f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Babi.get(0).getChildObject().get(0).getChildObject().get(3).scaleObject(0.5f, 0.5f, 0.1f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(3).translateObject(-0.105f, 0.195f, 0.39f);

        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.6f, 0.89f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.27f,
                0.27f,
                0.27f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Babi.get(0).getChildObject().get(0).getChildObject().get(4).scaleObject(0.5f, 0.5f, 0.1f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float)Math.toRadians(-90.0f),0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(4).translateObject(0.105f, 0.195f, 0.39f);

        //dalam kuping
        Babi.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.4f, 0.69f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.24f,
                0.24f,
                0.24f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Babi.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(0).scaleObject(0.5f, 0.5f, 0.05f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(3).getChildObject().get(0).translateObject(-0.105f, 0.19f, 0.395f);

        Babi.get(0).getChildObject().get(0).getChildObject().get(4).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.4f, 0.69f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.24f,
                0.24f,
                0.24f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Babi.get(0).getChildObject().get(0).getChildObject().get(4).getChildObject().get(0).scaleObject(0.5f, 0.5f, 0.05f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float)Math.toRadians(-90.0f),0.0f, 0.0f, 1.0f);
        Babi.get(0).getChildObject().get(0).getChildObject().get(4).getChildObject().get(0).translateObject(0.105f, 0.19f, 0.395f);

        //kurva alis
        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(-0.14f,0.19f,0.60f),//TITIK1
                        new Vector3f(-0.09f,0.22f,0.60f), //TITIK2
                        new Vector3f(-0.04f,0.19f,0.60f) //TITIK3
                       )//kanan

        ));
        Babi.get(0).getChildObject().get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(0.14f,0.19f,0.60f),//TITIK1
                        new Vector3f(0.09f,0.22f,0.60f), //TITIK2
                        new Vector3f(0.04f,0.19f,0.60f) //TITIK3
                )

        ));

        //BG
        //Rak background
        Babi.add(new Sphere(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.8f, 0.4f, 0.0f, 1.0f),0.0,
                new ArrayList<>(List.of(0f, 0f, 0f)),
                0.1f,
                0.1f,
                1.0f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Babi.get(1).scaleObject(8.0f, 1.0f, 8.0f);
        Babi.get(1).translateObject(0.0f, -0.33f, 0.0f);
        //vas bunga
        Babi.get(1).getChildObject().add(new Sphere(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.4f, 0.2f, 0.0f, 1.0f),0.0,
                new ArrayList<>(List.of(0f, 0f, 0f)),
                0.5f,
                0.5f,
                0.5f,
                15, // Stack -->
                30, // Sector --> Titik
                0));
        Babi.get(1).getChildObject().get(0).translateObject(0.0f, -0.03f, -3.0f);
        //Daun
        Babi.get(1).getChildObject().get(0).getChildObject().add(new Sphere(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(0.0f, 0.8f, 0.0f, 1.0f),0.0,
                new ArrayList<>(List.of(0f, 0f, 0f)),
                0.3f,
                0.3f,
                0.3f,
                15, // Stack -->
                30, // Sector --> Titik
                3));
        Babi.get(1).getChildObject().get(0).getChildObject().get(0).translateObject(0.0f, 0.45f, -3.0f);
    }
    //parameter gerak
    private float countjalan=0;
    private float countnoleh=0;
    private float countngangguk =0;
    private boolean kaki = true;


    public void input() {
        //animasi jalan
        // maju
        if (window.isKeyPressed(GLFW_KEY_W)) {
            Babi.get(0).translateObject(0.0f, 0.0f, 0.005f);
            if (kaki) {
                countjalan++;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);


                if (countjalan == 15.0f) {
                    kaki = false;
                }
            } else {
                countjalan--;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);

                if (countjalan == -15.0f) {
                    kaki = true;
                }
            }
        }
        // mundur
        if (window.isKeyPressed(GLFW_KEY_A)) {
            Babi.get(0).translateObject(0.0f, 0.0f, -0.005f);
            if (kaki) {
                countjalan++;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);


                if (countjalan == 15.0f) {
                    kaki = false;
                }
            } else {
                countjalan--;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);

                if (countjalan == -15.0f) {
                    kaki = true;
                }
            }
        }
        //JALAN KANAN
        if (window.isKeyPressed(GLFW_KEY_S)) {
            Babi.get(0).translateObject(0.005f, 0.0f, 0.0f);
            if (kaki) {
                countjalan++;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);


                if (countjalan == 15.0f) {
                    kaki = false;
                }
            } else {
                countjalan--;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);

                if (countjalan == -15.0f) {
                    kaki = true;
                }
            }
        }
        //JALAN KIRI
        if (window.isKeyPressed(GLFW_KEY_D)) {
            Babi.get(0).translateObject(-0.005f, 0.0f, 0.0f);
            if (kaki) {
                countjalan++;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);


                if (countjalan == 15.0f) {
                    kaki = false;
                }
            } else {
                countjalan--;
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(7).updateCenterPoint();
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                Babi.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(7).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);

                Vector3f tempCenterPoint2 = Babi.get(0).getChildObject().get(6).updateCenterPoint();
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * -1, tempCenterPoint2.y * -1, tempCenterPoint2.z * -1);
                Babi.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(6).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y * 1, tempCenterPoint2.z * 1);

                Vector3f tempCenterPoint3 = Babi.get(0).getChildObject().get(5).updateCenterPoint();
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * -1, tempCenterPoint3.y * -1, tempCenterPoint3.z * -1);
                Babi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(5).translateObject(tempCenterPoint3.x * 1, tempCenterPoint3.y * 1, tempCenterPoint3.z * 1);

                Vector3f tempCenterPoint4 = Babi.get(0).getChildObject().get(4).updateCenterPoint();
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * -1, tempCenterPoint4.y * -1, tempCenterPoint4.z * -1);
                Babi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(4).translateObject(tempCenterPoint4.x * 1, tempCenterPoint4.y * 1, tempCenterPoint4.z * 1);

                if (countjalan == -15.0f) {
                    kaki = true;
                }
            }
        }

        // menoleh kanan
        if (window.isKeyPressed(GLFW_KEY_E)) {

            if (countnoleh < 30.0f) {
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(0).updateCenterPoint();
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                countnoleh++;
                Babi.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }
        }
        // menoleh kiri
        if (window.isKeyPressed(GLFW_KEY_Q)) {
            if (countnoleh > -30.0f) {
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(0).updateCenterPoint();
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                countnoleh--;
                Babi.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }
        }
        // hadap atas
        if (window.isKeyPressed(GLFW_KEY_C)) {

            if (countngangguk < 20.0f) {
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(0).updateCenterPoint();
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                countngangguk++;
                Babi.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }
        }
        // hadap bawah
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            if (countngangguk > -20.0f) {
                Vector3f tempCenterPoint = Babi.get(0).getChildObject().get(0).updateCenterPoint();
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                countngangguk--;
                Babi.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                Babi.get(0).getChildObject().get(0).translateObject(tempCenterPoint.x * 1, tempCenterPoint.y * 1, tempCenterPoint.z * 1);
            }
        }


        //wasd
        // rotate ke atas dan kebawah bikin erorr
        if (window.isKeyPressed(GLFW_KEY_1)) {
            Vector3f tempCenterPointw = Babi.get(0).updateCenterPoint();
            Babi.get(0).translateObject(tempCenterPointw.x * -1, tempCenterPointw.y * -1, tempCenterPointw.z * -1);

            Babi.get(0).rotateObject(0.05f, 1f, 0f, 0f);
            Babi.get(0).translateObject(tempCenterPointw.x * 1, tempCenterPointw.y * 1, tempCenterPointw.z * 1);

//            Babi.get(1).rotateObject(0.05f, 1f, 0f, 0f);

        }

        if (window.isKeyPressed(GLFW_KEY_2)) {
            Vector3f tempCenterPoints = Babi.get(0).updateCenterPoint();
            Babi.get(0).translateObject(tempCenterPoints.x * -1, tempCenterPoints.y * -1, tempCenterPoints.z * -1);
            Babi.get(0).rotateObject(-0.05f, 1f, 0f, 0f);
            Babi.get(0).translateObject(tempCenterPoints.x * 1, tempCenterPoints.y * 1, tempCenterPoints.z * 1);

//            Babi.get(1).rotateObject(-0.05f, 1f, 0f, 0f);
        }

        if (window.isKeyPressed(GLFW_KEY_3)) {
            Vector3f tempCenterPointsa = Babi.get(0).updateCenterPoint();
            Babi.get(0).translateObject(tempCenterPointsa.x * -1, tempCenterPointsa.y * -1, tempCenterPointsa.z * -1);
            Babi.get(0).rotateObject(-0.05f, 0f, 1f, 0f);
            Babi.get(0).translateObject(tempCenterPointsa.x * 1, tempCenterPointsa.y * 1, tempCenterPointsa.z * 1);


//            Babi.get(1).rotateObject(-0.05f, 0f, 1f, 0f);
        }

        if (window.isKeyPressed(GLFW_KEY_4)) {
            Vector3f tempCenterPointsd = Babi.get(0).updateCenterPoint();
            Babi.get(0).translateObject(tempCenterPointsd.x * -1, tempCenterPointsd.y * -1, tempCenterPointsd.z * -1);
            Babi.get(0).rotateObject(0.05f, 0f, 1f, 0f);
            Babi.get(0).translateObject(tempCenterPointsd.x * 1, tempCenterPointsd.y * 1, tempCenterPointsd.z * 1);


//            Babi.get(1).rotateObject(0.05f, 0f, 1f, 0f);
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            camera.moveForward(0.02f);
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
            camera.moveBackwards(0.05f);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.moveDown(0.05f);
        }

        if (window.isKeyPressed(GLFW_KEY_UP)) {
            camera.moveUp(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.moveLeft(0.02f);
        }

        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.moveRight(0.02f);
        }

        if (window.getMouseInput().isLeftButtonPressed()) {
            Vector2f displayVec = window.getMouseInput().getDisplVec();
            camera.addRotation((float) Math.toRadians(displayVec.x * 0.1f), (float) Math.toRadians(displayVec.y * 0.1f));
        }

        if (window.getMouseInput().getScroll().y != 0) {
            projection.setFOV(projection.getFOV() - (window.getMouseInput().getScroll().y * 0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }

        if (window.isKeyPressed(GLFW_KEY_F)) {
            camera.addRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(2f));
        }
        if (window.isKeyPressed(GLFW_KEY_G)) {
            camera.addRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(-2f));
        }
    }


    public void loop() {

        while (window.isOpen()) {
            window.update();
            glClearColor(0.5f, 0.3f, 1.0f, 0.0f); // RapidTables.com (RGB color code chart)
            GL.createCapabilities();
            input();

//            Babi.get(0).getChildObject().get(0).getChildObject().get(5).drawLine(camera,projection);
            for (Object obj3D : Babi) {
                obj3D.draw(camera, projection);
            }


            //Restore State
            glDisableVertexAttribArray(0);
            // Pull for window events
            // The key callback above will only be
            // invoked during this call
            glfwPollEvents();

        }
    }


    public static void main (String[]args){
        new BabiCeleng().run();
    }
}


