package Engine;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Sphere extends Circle3D {
    private float minX, minY, minZ;
    private float maxX, maxY, maxZ;
    float rZ;
    int stackCount;
    int sectorCount;
    List<Vector3f> normal;
    int nbo;
    Model m ;
    BoundingBox boundingBox ;

    int lampuTidur=0;
    int lampuAtas=1;



    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color,
                  double r, ArrayList<Float> centerPoint, float rX, float rY, float rZ, int stackCount, int sectorCount, Model m) {
        super(shaderModuleDataList, vertices, color, centerPoint, rX, rY);
        this.rZ = rZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        this.m = m;
        createImport();
        calculateBoundingBox();
        boundingBox = new BoundingBox(getWidth(),getHeight(),getDepth());
        setupVAOVBO();
    }

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, double r, ArrayList<Float> centerPoint, float rX, float rY, float rZ, int stackCount, int sectorCount, int option) {
        super(shaderModuleDataList, vertices, color, centerPoint, rX, rY);
        this.rZ = rZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;

        if (option == 0) {
            createBox();
        } else if (option == 1) {
            createSphereElipsoid();

        } else if (option == 2) {
            createTabung();

        } else if (option == 3) {
            createSphere();

        } else if (option == 4) {
            createHyperboloid1();

        } else if (option == 5) {
            createHyperboloid2();

        } else if (option == 6) {
            createEllipticCone();

        } else if (option == 7) {
            createEllipticParaboloid();

        } else if (option == 8) {
            createHyperboloidParaboloid();
        } else if (option == 9) {
            createPotato();
        }

        calculateBoundingBox();
        boundingBox = new BoundingBox(getWidth(),getHeight(),getDepth());
        setupVAOVBO();
    }

    @Override
    public void setupVAOVBO() {
        //// VAOVBONBO UNTUK SHADING

        //Setup VAOVBO, panggil fungsi parent
        super.setupVAOVBO();
        //Setup NBO
        nbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        //Mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(normal), GL_STATIC_DRAW);
//        Ver New Directional Light
//        uniformsMap.createUniform("dirLight");
//        uniformsMap.createUniform("viewPos");

//        Untuk Shading Ver Old
//        uniformsMap.createUniform("lightColor");
//        uniformsMap.createUniform("lightPos");


    }

    public void drawSetup(Camera camera, Projection projection,boolean cek,boolean flashLight) {
        //drawSetup untuk Shading
        super.drawSetup(camera,projection);
        //Bind NBO
        //Untuk Simpan VBO/NBO pada index ke-n
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glVertexAttribPointer(1,3, GL_FLOAT, false, 0,0);
//        uniformsMap.setUniform("lightColor", new Vector3f(1.0f,0.5f,.8f));
//        uniformsMap.setUniform("lightPos", new Vector3f(0.0f,3.0f,2.4f));

////         direction Light // bulan
//        uniformsMap.setUniform("dirLight.direction", new Vector3f(25.0f,13.0f,9.0f));
//        uniformsMap.setUniform("dirLight.ambient", new Vector3f(0.2f,0.2f, 0.2f));
//        uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.4f,0.4f, 0.4f));
//        uniformsMap.setUniform("dirLight.specular", new Vector3f(0.3f,0.3f, 0.3f));

//        posisi PointLight
        Vector3f[] _pointLightPositions ={
                new Vector3f(-8.5f,10f,2.5f),//lampu tidur
                new Vector3f(0.0f,20f,4.0f)//lampu atas
        };

        if(!cek) {//kalau mati
            // lampu tidur
            // lampuny full mati
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].position", _pointLightPositions[lampuTidur]);
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].ambient", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].diffuse", new Vector3f(0.f, 0.f, 0.f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].specular", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].constant", (1f));
            //distance 32
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].linear", (0.f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].quadratic", (0.0f));

            //Lampu Atas
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].position", _pointLightPositions[lampuAtas]);
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].ambient", new Vector3f(0.f, 0.f, 0.f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].diffuse", new Vector3f(0.f, 0.f, 0.f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].specular", new Vector3f(-0.f, -0.f, -0.f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].constant", (1f));
            // distance 0
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].linear", (0.00f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].quadratic", (0.00f));

            //msih ad lampu sedikit
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].position", _pointLightPositions[lampuTidur]);
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].ambient", new Vector3f(0.01f, 0.01f, 0.01f));
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].diffuse", new Vector3f(0.6f, 0.6f, 0.6f));
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].specular", new Vector3f(0.0f, 0.0f, 0.0f));
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].constant", (1f));
//            //distance 32
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].linear", (0.14f));
//            uniformsMap.setUniform("pointLights[" + lampuTidur + "].quadratic", (0.07f));
//
//            //Lampu Atas
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].position", _pointLightPositions[lampuAtas]);
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].ambient", new Vector3f(0.1f, 0.1f, 0.1f));
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].diffuse", new Vector3f(0.6f, 0.6f, 0.6f));
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].specular", new Vector3f(-0.1f, -0.1f, -0.1f));
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].constant", (1f));
//            // distance 0
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].linear", (0.00f));
//            uniformsMap.setUniform("pointLights[" + lampuAtas + "].quadratic", (0.00f));

        }
        else{// kalau lampu nyala
            // lampu tidur
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].position", _pointLightPositions[lampuTidur]);
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].ambient", new Vector3f(0.01f, 0.01f, 0.01f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].diffuse", new Vector3f(0.6f, 0.6f, 0.6f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].specular", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].constant", (1f));

            //distance 50
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].linear", (0.09f));
            uniformsMap.setUniform("pointLights[" + lampuTidur + "].quadratic", (0.032f));

            //Lampu Atas
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].position", _pointLightPositions[lampuAtas]);
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].ambient", new Vector3f(0.4f, 0.4f, 0.4f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].diffuse", new Vector3f(0.6f, 0.6f, 0.6f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].specular", new Vector3f(-0.1f, -0.1f, -0.1f));
            uniformsMap.setUniform("pointLights[" + lampuAtas + "].constant", (1f));
            //      distance 200
            uniformsMap.setUniform("pointLights["+ lampuAtas +"].linear", (0.022f));
            uniformsMap.setUniform("pointLights["+ lampuAtas +"].quadratic", (0.0019f));;
        }


//      SpotLight atau senter
        if(flashLight) {
            uniformsMap.setUniform("spotLight.position", camera.getPosition());
            uniformsMap.setUniform("spotLight.direction", camera.getDirection());
            uniformsMap.setUniform("spotLight.ambient", new Vector3f(1f, 1f, 1f));
            uniformsMap.setUniform("spotLight.diffuse", new Vector3f(1.0f, 1.0f, 1.0f));
            uniformsMap.setUniform("spotLight.specular", new Vector3f(1.0f, 1.0f, 1.0f));
            uniformsMap.setUniform("spotLight.constant", (1f));
            uniformsMap.setUniform("spotLight.linear", (0.07f));
            uniformsMap.setUniform("spotLight.quadratic", (0.017f));
            uniformsMap.setUniform("spotLight.cutOff", (float) (Math.cos(Math.toRadians(70))));
            uniformsMap.setUniform("spotLight.outerCutOff", (float) (Math.cos(Math.toRadians(50))));

        }
        else{
            uniformsMap.setUniform("spotLight.position", camera.getPosition());
            uniformsMap.setUniform("spotLight.direction", camera.getDirection());
            uniformsMap.setUniform("spotLight.ambient", new Vector3f(0f, 0f, 0f));
            uniformsMap.setUniform("spotLight.diffuse", new Vector3f(0f, 0f, 0f));
            uniformsMap.setUniform("spotLight.specular", new Vector3f(0f, 0f, 0f));
            uniformsMap.setUniform("spotLight.constant", (1f));
            uniformsMap.setUniform("spotLight.linear", (0.0f));
            uniformsMap.setUniform("spotLight.quadratic", (0.0f));
            uniformsMap.setUniform("spotLight.cutOff", (float) (Math.cos(Math.toRadians(0))));
            uniformsMap.setUniform("spotLight.outerCutOff", (float) (Math.cos(Math.toRadians(0))));
        }
        uniformsMap.setUniform("viewPos", camera.getPosition());
    }


    public void draw(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // Draw vertices
        glLineWidth(1);
        glPointSize(1);
        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
        for (Object child : getChildObject()) {
            child.draw(camera, projection);
        }
    }


    public void draw(Camera camera, Projection projection,boolean cek,boolean flashLight){
        drawSetup(camera,projection,cek,flashLight);
        // Draw vertices
        glLineWidth(1);
        glPointSize(1);
        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
        for(Object child : getChildObject()){
            child.draw(camera,projection,cek,flashLight);
        }
    }


    public void createImport() {
        normal = new ArrayList<>();
        for (Face face : m.faces) {
            Vector3f n1 = m.normals.get((int) face.normal.x - 1);
            normal.add(n1);
            Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
            vertices.add(v1);

            Vector3f n2 = m.normals.get((int) face.normal.y - 1);
            normal.add(n2);
            Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
            vertices.add(v2);

            Vector3f n3 = m.normals.get((int) face.normal.z - 1);
            normal.add(n3);
            Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
            vertices.add(v3);
        }
    }

    public void createImport2() {
        //Perlu Fix
        normal = new ArrayList<>();
        for (Face face : m.faces) {
            Vector3f n1 = m.normals.get((int) face.normal.x - 1);
            normal.add(n1);
            Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
            vertices.add(v1);

            Vector3f n2 = m.normals.get((int) face.normal.y - 1);
            normal.add(n2);
            Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
            vertices.add(v2);

            Vector3f n3 = m.normals.get((int) face.normal.z - 1);
            normal.add(n3);
            Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
            vertices.add(v3);
        }
    }

    public void createBox() {
        vertices.clear();
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //Titik 1 kiri atas belakang
        temp.x = centerPoint.get(0) - rX / 2;
        temp.y = centerPoint.get(1) + rY / 2;
        temp.z = centerPoint.get(2) - rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 2 kiri bawah belakang
        temp.x = centerPoint.get(0) - rX / 2;
        temp.y = centerPoint.get(1) - rY / 2;
        temp.z = centerPoint.get(2) - rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 3 kanan bawah belakang
        temp.x = centerPoint.get(0) + rX / 2;
        temp.y = centerPoint.get(1) - rY / 2;
        temp.z = centerPoint.get(2) - rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 4 kanan atas belakang
        temp.x = centerPoint.get(0) + rX / 2;
        temp.y = centerPoint.get(1) + rY / 2;
        temp.z = centerPoint.get(2) - rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 5 kiri atas depan
        temp.x = centerPoint.get(0) - rX / 2;
        temp.y = centerPoint.get(1) + rY / 2;
        temp.z = centerPoint.get(2) + rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 6 kiri bawah depan
        temp.x = centerPoint.get(0) - rX / 2;
        temp.y = centerPoint.get(1) - rY / 2;
        temp.z = centerPoint.get(2) + rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 7 kanan bawah depan
        temp.x = centerPoint.get(0) + rX / 2;
        temp.y = centerPoint.get(1) - rY / 2;
        temp.z = centerPoint.get(2) + rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 8 kanan atas depan
        temp.x = centerPoint.get(0) + rX / 2;
        temp.y = centerPoint.get(1) + rY / 2;
        temp.z = centerPoint.get(2) + rZ / 2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //kotak belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(0));
        //kotak depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(4));
        //kotak samping kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(4));

        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak samping kanan
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));
        //kotak bawah
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(1));
        //kotak atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(3));

        normal = new ArrayList<>(Arrays.asList(
                //belakang
                new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 0.0f, -1.0f),
                new Vector3f(0.0f, 0.0f, -1.0f),
                //depan
                new Vector3f(0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 1.0f),
                //kiri
                new Vector3f(-1.0f, 0.0f, 0.0f),
                new Vector3f(-1.0f, 0.0f, 0.0f),
                new Vector3f(-1.0f, 0.0f, 0.0f),
                new Vector3f(-1.0f, 0.0f, 0.0f),
                new Vector3f(-1.0f, 0.0f, 0.0f),
                new Vector3f(-1.0f, 0.0f, 0.0f),
                //kanan
                new Vector3f(1.0f, 0.0f, 0.0f),
                new Vector3f(1.0f, 0.0f, 0.0f),
                new Vector3f(1.0f, 0.0f, 0.0f),
                new Vector3f(1.0f, 0.0f, 0.0f),
                new Vector3f(1.0f, 0.0f, 0.0f),
                new Vector3f(1.0f, 0.0f, 0.0f),
                //bawah
                new Vector3f(0.0f, -1.0f, 0.0f),
                new Vector3f(0.0f, -1.0f, 0.0f),
                new Vector3f(0.0f, -1.0f, 0.0f),
                new Vector3f(0.0f, -1.0f, 0.0f),
                new Vector3f(0.0f, -1.0f, 0.0f),
                new Vector3f(0.0f, -1.0f, 0.0f),
                //atas
                new Vector3f(0.0f, 1.0f, 0.0f),
                new Vector3f(0.0f, 1.0f, 0.0f),
                new Vector3f(0.0f, 1.0f, 0.0f),
                new Vector3f(0.0f, 1.0f, 0.0f),
                new Vector3f(0.0f, 1.0f, 0.0f),
                new Vector3f(0.0f, 1.0f, 0.0f)
        ));
    }

    public void createSphereElipsoid() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 180) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 180) {
                float x = rX * (float) (Math.cos(v) * Math.cos(u));
                float y = rY * (float) (Math.cos(v) * Math.sin(u));
                float z = rZ * (float) (Math.sin(v));
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices = temp;
    }

    public void createTabung() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double i = 0; i <= 360; i += 0.1f) {
            float x = centerPoint.get(0) + rX * (float) Math.cos(Math.toRadians(i));
            float y = centerPoint.get(1) + rY * (float) Math.sin(Math.toRadians(i));

            temp.add(new Vector3f(x, y, 0f));
            temp.add(new Vector3f(x, y, -rZ));
        }
        vertices = temp;
    }

    public void createPotato() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (int i = 0; i < 360; i++) {
            float rad = (float) (i * Math.PI / 180);
            float x = (float) (rX * 10 * Math.cos(rad) * (1.0 + 0.2 * Math.sin(3 * rad)));
            float y = (float) (rY * 10 * Math.sin(rad) * (1.0 + 0.1 * Math.sin(3 * rad)));
            float z = (float) (rZ * 10 * Math.sin(3 * rad) * 0.2);

            temp.add(new Vector3f(x, y, rZ));
            temp.add(new Vector3f(x, y, z));
        }
        vertices = temp;
    }

    public void createSphere() {
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 9) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 9) {
                float x = this.rX * (float) (Math.cos(v) * Math.cos(u));
                float y = this.rY * (float) (Math.cos(v) * Math.sin(u));
                float z = this.rZ * (float) (Math.sin(v));
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices = temp;
    }

    public void createSphere2() {
        vertices.clear();

        for (float u = -180; u <= 180; u += 180 / 180) {
            for (float v = (-90); v <= 90; v += 180 / 180) {
                Vector3f temp_vector = new Vector3f();
                float uRad = (float) Math.toRadians(u);
                float vRad = (float) Math.toRadians(v);
                temp_vector.x = (float) (this.rX * Math.cos(vRad) * Math.cos(uRad));
                temp_vector.z = (float) (this.rZ * Math.cos(vRad) * Math.sin(uRad));
                temp_vector.y = (float) (this.rY * Math.sin(vRad));
                vertices.add(temp_vector);
            }
        }
        for (float u = -180; u <= 180; u += 180 / 180) {
            for (float v = (-90); v <= 90; v += 180 / 180) {
                Vector3f temp_vector = new Vector3f();
                float uRad = (float) Math.toRadians(u);
                float vRad = (float) Math.toRadians(v);
                temp_vector.y = (float) (this.rY * Math.cos(vRad) * Math.cos(uRad));
                temp_vector.z = (float) (this.rZ * Math.cos(vRad) * Math.sin(uRad));
                temp_vector.x = (float) (this.rX * Math.sin(vRad));
                vertices.add(temp_vector);
            }
        }
    }

    public void createHyperboloid1() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = rX * (float) ((1 / Math.cos(v)) * Math.cos(u));
                float y = rY * (float) ((1 / Math.cos(v)) * Math.sin(u));
                float z = rZ * (float) (Math.tan(v));
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices = temp;
    }

    public void createHyperboloid2() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = -Math.PI / 2; u <= Math.PI / 2; u += Math.PI / 60) {
                float x = 0.5f * (float) ((1 / Math.cos(v)) * Math.cos(u));
                float y = 0.5f * (float) ((1 / Math.cos(v)) * Math.sin(u));
                float z = 0.5f * (float) (Math.tan(v));
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices = temp;

    }

    public void createEllipticCone() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        /*for(double v = 0; v<= 100; v+=0.05){
            for(double u = -Math.PI; u<= Math.PI; u+=1){
                float x = 0.5f * (float)v * (float)(Math.cos(u));
                float y = 0.5f * (float)v * (float)((Math.sin(u)));
                float z = 0.5f * (float)v;
                temp.add(new Vector3f(x,z,y));
            }
        }*/
        for (double v = 0; v <= 100; v += 0.05) {
            for (double u = -Math.PI; u <= Math.PI; u += 1) {
                float x = 0.5f * (float) u * (float) (Math.cos(v));
                float y = 0.5f * (float) u * (float) ((Math.sin(v)));
                float z = 0.5f * (float) u;
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices = temp;
    }

    public void createEllipticConev2() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (float u = -180; u <= 180; u += 180 / 36) {
            for (float v = (-90); v <= 90; v += 180 / 36) {
                Vector3f temp_vector = new Vector3f();
                float uRad = (float) Math.toRadians(u);
                float vRad = (float) Math.toRadians(v);
                temp_vector.x = (float) (1 * vRad * Math.cos(uRad));
                temp_vector.z = (float) (1 * vRad * Math.sin(uRad));
                temp_vector.y = (float) (1 * vRad);
                vertices.add(temp_vector);
            }
        }
    }

    public void createEllipticConev3() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = 0; v <= 100; v += 0.05) {
            for (double u = -Math.PI; u <= Math.PI; u += 0.01) {
                float x = 0.5f * (float) v * (float) (Math.tan(u));
                float y = 0.5f * (float) v * (float) ((1 / Math.cos(u)));
                float z = 0.5f * (float) Math.pow(v, 2);
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices = temp;
    }

    public void createEllipticParaboloid() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = 0; v <= 100; v += 0.05) {
            for (double u = -Math.PI; u <= Math.PI; u += 0.01) {
                float x = 0.5f * (float) v * (float) (Math.cos(u));
                float y = 0.5f * (float) v * (float) ((Math.sin(u)));
                float z = 0.5f * (float) Math.pow(v, 2);
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices = temp;
    }

    public void createHyperboloidParaboloid() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = 0; v <= 100; v += 0.01) {
            for (double u = -Math.PI; u <= Math.PI; u += 0.666) {
                float x = 0.125f * (float) v * (float) (Math.tan(u));
                float y = 0.25f * (float) v * (float) ((1 / Math.cos(u)));
                float z = 0.5f * (float) Math.pow(v, 2);
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices = temp;
    }

    /*
    public void createHyperboloidParaboloid() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for(double v = 0; v<= 100; v+=0.01){
            for(double u = -Math.PI; u<= Math.PI; u+=0.666){
                float x = 0.125f * (float)v * (float)(Math.tan(u));
                float y = 0.25f * (float)v * (float)((1/Math.cos(u)));
                float z = 0.5f * (float)Math.pow(v,2);
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }*/
    public void rotateObjectOnPoint(float degree, float x, float y, float z, float tempx, float tempy, float tempz) {
        translateObject(-tempx, -tempy, -tempz);

        Matrix4f rotationMatrix = new Matrix4f().rotate((float) Math.toRadians(degree), x, y, z);
        model.mul(rotationMatrix);

        // Update the center point
        float newcpx = getCenterPoint().get(0) * rotationMatrix.m00() + getCenterPoint().get(1) * rotationMatrix.m01() + getCenterPoint().get(2) * rotationMatrix.m02();
        float newcpy = getCenterPoint().get(0) * rotationMatrix.m10() + getCenterPoint().get(1) * rotationMatrix.m11() + getCenterPoint().get(2) * rotationMatrix.m12();
        float newcpz = getCenterPoint().get(0) * rotationMatrix.m20() + getCenterPoint().get(1) * rotationMatrix.m21() + getCenterPoint().get(2) * rotationMatrix.m22();

        ArrayList<Float> xxx = new ArrayList<Float>(Arrays.asList(newcpx, newcpy, newcpz));
        setCenterPoint(xxx);

        for (Object child : childObject) {
            ((Sphere) child).rotateObjectOnPoint(degree, x, y, z, tempx, tempy, tempz);
        }

        translateObject(tempx, tempy, tempz);
    }



    public float getrZ() {
        return rZ;
    }

    public void setrZ(float rZ) {
        this.rZ = rZ;
    }

    public boolean CheckCollide(Sphere obj) {
        // Calculate the half widths, half heights, and half lengths of the bounding boxes
        float halfWidth1 = boundingBox.getWidth() / 2;
        float halfHeight1 = boundingBox.getHeight() / 2;
        float halfDepth1 = boundingBox.getDepth() / 2;

        float halfWidth2 = obj.boundingBox.getWidth() / 2;
        float halfHeight2 = obj.boundingBox.getHeight() / 2;
        float halfDepth2 = obj.boundingBox.getDepth() / 2;

        // Calculate the centers of the bounding boxes
        float center1X = getMinX() + halfWidth1;
        float center1Y = getMinY() + halfHeight1;
        float center1Z = getMinZ() + halfDepth1;

        float center2X = obj.getMinX() + halfWidth2;
        float center2Y = obj.getMinY() + halfHeight2;
        float center2Z = obj.getMinZ() + halfDepth2;

        // Calculate the distances between the centers of the bounding boxes
        float distanceX = Math.abs(center1X - center2X);
        float distanceY = Math.abs(center1Y - center2Y);
        float distanceZ = Math.abs(center1Z - center2Z);
        // Check for overlap in each dimension
        if (distanceX <= halfWidth1 + halfWidth2 && distanceY <= halfHeight1 + halfHeight2 && distanceZ <= halfDepth1 + halfDepth2) {
            // Collision detected
            return true;
        }

        // No collision detected
        return false;
    }
    public void calculateBoundingBox() {
        if(this.m != null){
            // Initialize min and max coordinates with the first vertex
            Vector3f firstVertex = m.vertices.get(0);
            minX = maxX = firstVertex.x;
            minY = maxY = firstVertex.y;
            minZ = maxZ = firstVertex.z;

            // Find the min and max values for each coordinate
            for (Vector3f vertex : m.vertices) {
                if (vertex.x < minX) minX = vertex.x;
                if (vertex.x > maxX) maxX = vertex.x;

                if (vertex.y < minY) minY = vertex.y;
                if (vertex.y > maxY) maxY = vertex.y;

                if (vertex.z < minZ) minZ = vertex.z;
                if (vertex.z > maxZ) maxZ = vertex.z;
            }
        }
        else{
            // Initialize min and max coordinates with the first vertex
            Vector3f firstVertex = vertices.get(0);
            minX = maxX = firstVertex.x;
            minY = maxY = firstVertex.y;
            minZ = maxZ = firstVertex.z;

            // Find the min and max values for each coordinate
            for (Vector3f vertex : vertices) {
                if (vertex.x < minX) minX = vertex.x;
                if (vertex.x > maxX) maxX = vertex.x;

                if (vertex.y < minY) minY = vertex.y;
                if (vertex.y > maxY) maxY = vertex.y;

                if (vertex.z < minZ) minZ = vertex.z;
                if (vertex.z > maxZ) maxZ = vertex.z;
            }
        }

    }

    public double sdfTriangle(Vector3D p, Vector3D a, Vector3D b, Vector3D c) {
        Vector3D ba = b.subtract(a);
        Vector3D pa = p.subtract(a);
        Vector3D cb = c.subtract(b);
        Vector3D pb = p.subtract(b);
        Vector3D ac = a.subtract(c);
        Vector3D pc = p.subtract(c);
        Vector3D nor = ba.crossProduct(ac);


        return Math.sqrt((Math.signum(dot3(cross3(ba, nor), pa)) +
                Math.signum(dot3(cross3(cb, nor), pb)) +
                Math.signum(dot3(cross3(ac, nor), pc)) < 2.0) ?
                Math.min(Math.min(
                                dot2(subtract2(multiply2(ba, constrain(dot3(ba, pa) / dot2(ba), 0.0, 1.0)), pa)),
                                dot2(subtract2(multiply2(cb, constrain(dot3(cb, pb) / dot2(cb), 0.0, 1.0)), pb)) ),
                        dot2(subtract2(multiply2(ac, constrain(dot3(ac, pc) / dot2(ac), 0.0, 1.0)), pc)) ) :
                dot3(nor, pa) * dot3(nor, pa) / dot2(nor));
    }

    public double dot2(Vector3D v) {
        return dot3(v, v);
    }

    public double dot3(Vector3D v1, Vector3D v2) {
        return v1.dotProduct(v2);
    }

    public Vector3D cross3(Vector3D v1, Vector3D v2) {
        return v1.crossProduct(v2);
    }

    public Vector3D subtract2(Vector3D v1, Vector3D v2) {
        return v1.subtract(v2);
    }

    public Vector3D multiply2(Vector3D v, double scalar) {
        return v.scalarMultiply(scalar);
    }

    public double constrain(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public boolean detectCollision(Vector3D p, RawModel o, double thresh) {
        List<Vertex> vertices = o.getVertices();
        List<Integer> indicies = o.getIndices();

        for (int i = 0; i < indicies.size()/3; i++) {
            Vector3D a = new Vector3D(
                    vertices.get(indicies.get(i)).getPosition().x + position.x,
                    vertices.get(indicies.get(i)).getPosition().y + position.y,
                    vertices.get(indicies.get(i)).getPosition().z + position.z
            );
            Vector3D b = new Vector3D(
                    vertices.get(indicies.get(i+1)).getPosition().x + position.x,
                    vertices.get(indicies.get(i+1)).getPosition().y + position.y,
                    vertices.get(indicies.get(i+1)).getPosition().z + position.z
            );
            Vector3D c = new Vector3D(
                    vertices.get(indicies.get(i+2)).getPosition().x + position.x,
                    vertices.get(indicies.get(i+2)).getPosition().y + position.y,
                    vertices.get(indicies.get(i+2)).getPosition().z + position.z
            );



            if (sdfTriangle(p, a, b, c) < thresh) {
                return true;
            }
        }
        return false;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public float getWidth() {
        return maxX - minX;
    }

    public float getHeight() {
        return maxY - minY;
    }

    public float getDepth() {
        return maxZ - minZ;
    }

    public float getMinX() {
        return minX;
    }

    public void setMinX(float minX) {
        this.minX = minX;
    }

    public float getMinY() {
        return minY;
    }

    public void setMinY(float minY) {
        this.minY = minY;
    }

    public float getMinZ() {
        return minZ;
    }

    public void setMinZ(float minZ) {
        this.minZ = minZ;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    public float getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(float maxZ) {
        this.maxZ = maxZ;
    }
}