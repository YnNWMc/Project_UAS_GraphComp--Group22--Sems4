import Engine.*;
import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Wheezy {
    private Window window =
            new Window(800, 800, "Wheezy");
    ArrayList<Wheezy_Object> bodyPart = new ArrayList<>();
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
        // code dst jangan ditaruh diatas code diatas
        camera.setPosition(-3.0f, 0.0f, 4.7f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(30.0f));

        //Arron Kurniawan C14210060

        // badan utama
        bodyPart.add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.32f,// titik x
                0.48f,// titik y
                0.40f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                1));// option
        bodyPart.get(0).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).translateObject(-0.3f, 0f, 0f); // POSISI

        //bagian perut yg putih
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.89f, 0.87f, 0.74f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.16f,// titik x
                0.22f,// titik y
                0.05f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                2));// option
        bodyPart.get(0).getChildObject().get(0).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(0).translateObject(-0.41f, -0.18f, 0.33f); // POSISI

        //tangan kanan
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.16f,// titik x
                0.26f,// titik y
                0.2f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                3));// option
        bodyPart.get(0).getChildObject().get(1).rotateObject(2.2f, 1.3f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(1).rotateObject(1.2f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(1).translateObject(-0.138f, 0.16f, 0.28f); // POSISI

        //tangan Kiri
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.16f,// titik x
                0.26f,// titik y
                0.2f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                3));// option

        bodyPart.get(0).getChildObject().get(2).rotateObject(2.2f, 1.3f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(2).rotateObject(4.4f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(2).translateObject(-0.59f, 0.16f, 0.08f); // POSISI


        //kelopak mata kanan
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.95f, 1f, 1f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.05f,// titik x
                0.038f,// titik y
                0.02f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(3).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(3).translateObject(-0.3f, 0.46f, 0.2f); // POSISI

        //kelopak mata kiri
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.95f, 1f, 1f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.05f,// titik x
                0.038f,// titik y
                0.02f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(4).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(4).translateObject(-0.42f, 0.465f, 0.12f); // POSISI

        //mata kanan
        bodyPart.get(0).getChildObject().get(3).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.03f,// titik x
                0.02f,// titik y
                0.01f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(-0.31f, 0.45f, 0.21f); // POSISI

        //mata kiri
        bodyPart.get(0).getChildObject().get(4).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.03f,// titik x
                0.02f,// titik y
                0.01f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(-0.43f, 0.455f, 0.13f);// POSISI

        // paruh atas
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.95f, 0.75f, 0.09f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.01f,// titik x
                0.017f,// titik y
                0.023f,// titik z
                15, // Stack -->
                30, // Sector --> TitikW
                5));// option

        bodyPart.get(0).getChildObject().get(5).rotateObject(3.13f, 1f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(5).translateObject(-0.44f, 0.3f, 0.51f); // POSISI

        // paruh bwh
        bodyPart.get(0).getChildObject().get(5).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.95f, 0.75f, 0.09f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.008f,// titik x
                0.007f,// titik y
                0.02f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                5));// option

        bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject(3.13f, 1f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(-0.44f, 0.26f, 0.5f); // POSISI

        //buletan dasi
        bodyPart.get(0).getChildObject().get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(1f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.03f,// titik x
                0.02f,// titik y
                0.06f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(-0.355f, -0.009f, 0.19f);// POSISI


        //dasi kanan
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(1f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.07f,// titik x
                0.068f,// titik y
                0.09f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                6));// option

        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).rotateObject(29.84f, 1f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).rotateObject(-30.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).translateObject(-0.426f, -0.009f, 0.41f);// POSISI

        //dasi kiri
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(1f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.07f,// titik x
                0.068f,// titik y
                0.09f,// titik z
                15, // Stack -->
                30, // Sector --> Titik
                6));// option

        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(1).rotateObject(29.81f, 1f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(1).rotateObject(29.3f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(1).translateObject(-0.477f, -0.007f, 0.39f);// POSISI

        //kaki kiri
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.89f, 0.87f, 0.74f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.2f,// titik x
                0.3f,// titik y
                0.11f,// titik z
                150, // Stack -->
                150, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(6).rotateObject(1.57f, 1f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(6).rotateObject(90f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(6).translateObject(-0.5f, -0.41f, 0.1f);

        //kaki kanan
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0.89f, 0.87f, 0.74f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.2f,// titik x
                0.3f,// titik y
                0.11f,// titik z
                150, // Stack -->
                150, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(7).rotateObject(1.57f, 1f, 0f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(7).rotateObject(-60f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(7).translateObject(-0.2f, -0.41f, 0.3f);

        //pantat penguin
        bodyPart.get(0).getChildObject().add(new Wheezy_Object(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new ArrayList<>(
                ),
                new Vector4f(0f, 0f, 0f, 1.0f),// color
                0.00,// radius
                new ArrayList<>(List.of(0f, 0f, 0f)),// center point
                0.2f,// titik x
                0.3f,// titik y
                0.15f,// titik z
                150, // Stack -->
                150, // Sector --> Titik
                4));// option

        bodyPart.get(0).getChildObject().get(8).rotateObject(-0.40f, 0f, 1f, 0f);// putar biar bgs kliatannya
        bodyPart.get(0).getChildObject().get(8).translateObject(-0.23f, -0.21f, -0.14f);

        //alis penguin kanan
        bodyPart.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(-0.095f,0.19f,0.60f),//TITIK1
                        new Vector3f(-0.05f,0.22f,0.60f), //TITIK2
                        new Vector3f(-0.01f,0.19f,0.60f) //TITIK3
                )
        ));
        bodyPart.get(0).getChildObject().get(9).rotateObject(0.6f,1f, 0.0f, 0.0f);
        bodyPart.get(0).getChildObject().get(9).rotateObject(-0.28f,0f, 1.0f, 0.0f);
        bodyPart.get(0).getChildObject().get(9).translateObject(-0.16f, 0.7f, -0.18f);


        //alis penguin Kiri
        bodyPart.get(0).getChildObject().add(new SphereBabi(
                shaderModuleDataList,
                new ArrayList<>(
                ),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                List.of(0.0f, 0.0f, 0.0f),
                0.24f,
                0.24f,
                Arrays.asList(
                        new Vector3f(-0.095f,0.19f,0.60f),//TITIK1
                        new Vector3f(-0.05f,0.22f,0.60f), //TITIK2
                        new Vector3f(-0.01f,0.19f,0.60f) //TITIK3
                )
        ));
        bodyPart.get(0).getChildObject().get(10).rotateObject(0.6f,1f, 0.0f, -0.0f);
        bodyPart.get(0).getChildObject().get(10).rotateObject(-0.28f,0f, 1.0f, -0.0f);
        bodyPart.get(0).getChildObject().get(10).translateObject(-0.285f, 0.7f, -0.25f);


    }

    // sebagai variable control
    float counterParuh = 0f;
    boolean cekParuh = true;

    float counterMata = 0f;
    boolean cekMata = true;

    float counterJalan = 0f;
    boolean cekJalan = true;

    float counterJalanDepan = 0f;
    boolean cekJalanDepan = true;


    public void input() {
        // gerakkin Paruh
        if (window.isKeyPressed(GLFW_KEY_1)) {
            if (cekParuh == true) {
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(tempCenterPoint.x * -1,tempCenterPoint.y * -1,tempCenterPoint.z *-1);
                counterParuh ++;
                //bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).rotateObjectOnPoint((float) Math.toRadians(-0.1/1.5f), 1.0f, 0.0f, 0.0f,-0.44f, 0.3f, 0.51f);
                bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(tempCenterPoint.x * 1,tempCenterPoint.y * 1,tempCenterPoint.z * 1);
            }
            else {
                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(tempCenterPoint2.x * -1,tempCenterPoint2.y * -1,tempCenterPoint2.z * -1);
                counterParuh --;
                //bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).rotateObjectOnPoint((float) Math.toRadians(0.1/1.5f), 1.0f, 0.0f, 0.0f,-0.44f, 0.3f, 0.51f);
                bodyPart.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(tempCenterPoint2.x * 1, tempCenterPoint2.y* 1,tempCenterPoint2.z* 1);
            }
            if (counterParuh == 40.0f){
                cekParuh = false;
            }
            if(counterParuh == 0.0f){
                cekParuh = true;
            }
        }

        //gerakkin mata
        if (window.isKeyPressed(GLFW_KEY_2)) {
            if (cekMata == true) {
                counterMata++;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(-1.0f/3),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(-1.0f/3),1.0f, -.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);
            }
            else {
                counterMata--;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(1.0f/3),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(1.0f/3),1.0f, -.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);
            }
            if (counterMata== 12.0f){
                cekMata = false;
            }
            if(counterMata == 0){
                cekMata = true;
            }
        }

        // jalan samping kiri
        if (window.isKeyPressed(GLFW_KEY_3)) {
            bodyPart.get(0).translateObject(-0.002f,0.0f,0.0f);
            if(cekJalanDepan == true) {
                counterJalanDepan++;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);

            } else{
                counterJalanDepan--;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);
            }

            if (counterJalanDepan == 130.0f){
                cekJalanDepan = false;
            }
            if(counterJalanDepan == -100.0f){
                cekJalanDepan = true;
            }

        }

        // jalan samping kanan
        if (window.isKeyPressed(GLFW_KEY_4)) {
            bodyPart.get(0).translateObject(0.002f,0.0f,0.0f);
            if(cekJalanDepan == true) {
                counterJalanDepan++;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);

            } else{
                counterJalanDepan--;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);
            }

            if (counterJalanDepan == 130.0f){
                cekJalanDepan = false;
            }
            if(counterJalanDepan == -100.0f){
                cekJalanDepan = true;
            }
        }

        // jalan depan
        if (window.isKeyPressed(GLFW_KEY_5)) {
            bodyPart.get(0).translateObject(0.0f,0.0f,0.002f);
            if(cekJalanDepan == true) {
                counterJalanDepan++;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);

            } else{
                counterJalanDepan--;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);
            }

            if (counterJalanDepan == 130.0f){
                cekJalanDepan = false;
            }
            if(counterJalanDepan == -100.0f){
                cekJalanDepan = true;
            }

        }

        if (window.isKeyPressed(GLFW_KEY_6)) {
            bodyPart.get(0).translateObject(0.0f,0.0f,-0.002f);
            if(cekJalanDepan == true) {
                counterJalanDepan++;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);

            } else{
                counterJalanDepan--;
                Vector3f tempCenterPoint = bodyPart.get(0).getChildObject().get(6).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
                bodyPart.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(6).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);

                Vector3f tempCenterPoint2 = bodyPart.get(0).getChildObject().get(7).updateCenterPoint();
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*-1,tempCenterPoint2.y*-1,tempCenterPoint2.z*-1);
                bodyPart.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(1.0f),1.0f, 0.0f, 0.0f);
                bodyPart.get(0).getChildObject().get(7).translateObject(tempCenterPoint2.x*1,tempCenterPoint2.y*1,tempCenterPoint2.z*1);
            }

            if (counterJalanDepan == 130.0f){
                cekJalanDepan = false;
            }
            if(counterJalanDepan == -100.0f){
                cekJalanDepan = true;
            }
        }

        if (window.isKeyPressed(GLFW_KEY_W)) {// muter ke atas
            Vector3f tempCenterPoint = bodyPart.get(0).updateCenterPoint();
            bodyPart.get(0).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
            bodyPart.get(0).rotateObject(0.01f, 1f, 0f, 0f);
            bodyPart.get(0).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);
        }

        if (window.isKeyPressed(GLFW_KEY_S)) {// muter ke bawah
            Vector3f tempCenterPoint = bodyPart.get(0).updateCenterPoint();
            bodyPart.get(0).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
            bodyPart.get(0).rotateObject(-0.01f, 1f, 0f, 0f);
            bodyPart.get(0).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {// muter ke kiri
            Vector3f tempCenterPoint = bodyPart.get(0).updateCenterPoint();
            bodyPart.get(0).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
            bodyPart.get(0).rotateObject(-0.01f, 0f, 1f, 0f);
            bodyPart.get(0).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);
        }

        if (window.isKeyPressed(GLFW_KEY_D)) {// muter ke kanan
            Vector3f tempCenterPoint = bodyPart.get(0).updateCenterPoint();
            bodyPart.get(0).translateObject(tempCenterPoint.x*-1,tempCenterPoint.y*-1,tempCenterPoint.z*-1);
            bodyPart.get(0).rotateObject(0.01f, 0f, 1f, 0f);
            bodyPart.get(0).translateObject(tempCenterPoint.x*1,tempCenterPoint.y*1,tempCenterPoint.z*1);
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {// zoom in
            camera.moveForward(0.02f);
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {// zoom out
            camera.moveBackwards(0.01f);
        }
    }

    public void loop() {

        while (window.isOpen()) {
            window.update();
            glClearColor(1f, 1f, 1f, 0.0f); // RapidTables.com (RGB color code chart)
            GL.createCapabilities();
            input();
//            for (Wheezy_Object obj3D : bodyPart) {
//                obj3D.draw(camera,projection);
//            }
            //Restore State
            glDisableVertexAttribArray(0);
            // Pull for window events
            // The key callback above will only be
            // invoked during this call
            glfwPollEvents();
        }
    }


    public static void main (String[]args){
        new Wheezy().run();
    }
}


