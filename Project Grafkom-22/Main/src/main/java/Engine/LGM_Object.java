package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class LGM_Object extends Circle3D {
    float rZ;
    int stackCount;
    int sectorCount;
    public LGM_Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float rX, Float rY, List<Vector3f>Point) {
        super(shaderModuleDataList, vertices, color, centerPoint, rX, rY);
        LGM_Bezier(Point);
        setupVAOVBO();
    }
    public LGM_Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, ArrayList<Float> centerPoint, float rX, float rY, float rZ, int stackCount, int sectorCount, int option) {
        super(shaderModuleDataList, vertices, color, centerPoint, rX, rY);
        this.rZ = rZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;

        if (option == 0) {
            // Kalo Overlap Bisa Bikin half object/sphere
            LGM_Ellipsoid();
        }
        else if (option == 1) {
            LGM_Tabung();
        }
        else if (option == 2) {
            LGM_HalfEllipticCone();
        }
        else if (option == 3) {
            LGM_Cube();
        }
        else if (option == 4){
            LGM_PurpleNeck();
        }
        else if (option == 5){
            LGM_TorusRing();
        }
        else if (option == 6){
            LGM_Ear();
        }
        else if (option == 7){
            LGM_ELParbol();
        }
        setupVAOVBO();
    }

    public void LGM_Cube(){

        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        // x jika plus di kanan, x jika minus di kiri
        // y di minus di bawah, y jika plus di atas
        // z di minus belakang, z di plus atas

        //titik 1 kiri atas belakang
        temp.x = (float)centerPoint.get(0) - rX/2;
        temp.y = (float)centerPoint.get(1) + rY/2;
        temp.z = (float)centerPoint.get(2) - rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 2 kiri bawah belakang
        temp.x = (float)centerPoint.get(0) - rX/2;
        temp.y = (float)centerPoint.get(1) - rY/2;
        temp.z = (float)centerPoint.get(2) - rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 3 kanan bawah belakang
        temp.x = (float)centerPoint.get(0) + rX/2;
        temp.y = (float)centerPoint.get(1) - rY/2;
        temp.z = (float)centerPoint.get(2) - rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 4 kanan atas belakang
        temp.x = (float)centerPoint.get(0) + rX/2;
        temp.y = (float)centerPoint.get(1) + rY/2;
        temp.z = (float)centerPoint.get(2) - rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 5 kiri atas depan
        temp.x = (float)centerPoint.get(0) - rX/2;
        temp.y = (float)centerPoint.get(1) + rY/2;
        temp.z = (float)centerPoint.get(2) + rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 6 kiri bawah depan
        temp.x = (float)centerPoint.get(0) - rX/2;
        temp.y = (float)centerPoint.get(1) - rY/2;
        temp.z = (float)centerPoint.get(2) + rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 7 kanan bawah depan
        temp.x = (float)centerPoint.get(0) + rX/2;
        temp.y = (float)centerPoint.get(1) - rY/2;
        temp.z = (float)centerPoint.get(2) + rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();

        //titik 8 kanan atas depan
        temp.x = (float)centerPoint.get(0) + rX/2;
        temp.y = (float)centerPoint.get(1) + rY/2;
        temp.z = (float)centerPoint.get(2) + rZ/2;
        tempVertices.add(temp);

        vertices.clear();
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));

        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));

        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));

        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
    }
    public void LGM_Ellipsoid(){
        //Dari Ellipsoid
        vertices.clear();
        float radiusX = rX;
        float radiusY = rY;
        float radiusZ = rZ;
        List<Float> centerPoint = Arrays.asList(0.0f,0.0f,0.0f);

        float pi = (float)Math.PI;
        float sectorStep = 2*(float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        // Sector/Stackstep bisa bagi 2 buat half object/sphere
        float sectorAngle, StackAngle, stackAngle,xy ,x, y, z;
        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z + 0.2f;
                vertices.add(temp_vector);
            }
        }
    }
    public void LGM_Tabung() {
        vertices.clear();
        float rxTemp = rX, ryTemp = rY;
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double i = 0; i <= 360; i += 0.05f) {
            float x = centerPoint.get(0) + rxTemp * (float) Math.cos(Math.toRadians(i));
            float y = centerPoint.get(1) + ryTemp * (float) Math.sin(Math.toRadians(i));

            temp.add(new Vector3f(x, y, rZ));
            temp.add(new Vector3f(x, y, -rZ));
        }
        vertices = temp;
    }
    public void LGM_HalfEllipticCone() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = 0; v <= 2 * Math.PI; v += Math.PI / 60) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = (float) (rX * v * Math.cos(u));
                float y = (float) (rY * v * Math.sin(u));
                float z = (float) (rZ * v);
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices = temp;
    }

    public void LGM_PurpleNeck() {
        //Dari Torus Ring
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for(double v = 0; v<= Math.PI*2; v+=Math.PI/60){
            for(double u = 0; u<= Math.PI*2; u+=Math.PI/60){
                float x = (rX * 2f + rZ * (float)(Math.cos(v))) * (float)Math.cos(u);
                float y = (rY * 2f + rZ * (float)(Math.cos(v))) * (float)Math.sin(u);
                float z = rZ * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }
    public void LGM_TorusRing() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
            for(double v = 0; v<= Math.PI*2; v+=Math.PI/60){
                for(double u = 0; u<= Math.PI*2; u+=Math.PI/60){
                    float x = (rX*3f + rZ * (float)(Math.cos(v))) * (float)Math.cos(u);
                    float y = (rY*3f + rZ * (float)(Math.cos(v))) * (float)Math.sin(u);
                    float z = rZ * (float)(Math.sin(v));
                    temp.add(new Vector3f(x,y,z));
                }
            }
            vertices = temp;
    }


    public void LGM_Ear(){
        {
            Vector3f temp = new Vector3f();
            ArrayList<Vector3f> tempVertices = new ArrayList<>();
            // x jika plus di kanan, x jika minus di kiri
            // y di minus di bawah, y jika plus di atas
            // z di minus belakang, z di plus atas

            //titik 1 kiri atas belakang
            temp.x = (float)centerPoint.get(0) - rX/2;
            temp.y = (float)centerPoint.get(1) + rY/2;
            temp.z = (float)centerPoint.get(2) - rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 2 kiri bawah belakang
            temp.x = (float)centerPoint.get(0) - rX/2;
            temp.y = (float)centerPoint.get(1) - rY/2;
            temp.z = (float)centerPoint.get(2) - rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 3 kanan bawah belakang
            temp.x = (float)centerPoint.get(0) + rX/2;
            temp.y = (float)centerPoint.get(1) - rY/2;
            temp.z = (float)centerPoint.get(2) - rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 4 kanan atas belakang
            temp.x = (float)centerPoint.get(0) + rX/2;
            temp.y = (float)centerPoint.get(1) + rY/2;
            temp.z = (float)centerPoint.get(2) - rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 5 kiri atas depan
            temp.x = (float)centerPoint.get(0) - rX/2;
            temp.y = (float)centerPoint.get(1) + rY/2;
            temp.z = (float)centerPoint.get(2) + rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 6 kiri bawah depan
            temp.x = (float)centerPoint.get(0) - rX/2;
            temp.y = (float)centerPoint.get(1) - rY/2;
            temp.z = (float)centerPoint.get(2) + rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 7 kanan bawah depan
            temp.x = (float)centerPoint.get(0) + rX/2;
            temp.y = (float)centerPoint.get(1) - rY/2;
            temp.z = (float)centerPoint.get(2) + rZ/2;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 8 kanan atas depan
            temp.x = (float)centerPoint.get(0) + rX/2;
            temp.y = (float)centerPoint.get(1) + rY/2;
            temp.z = (float)centerPoint.get(2) + rZ/2;
            tempVertices.add(temp);

            vertices.clear();
            vertices.add(tempVertices.get(0));
            vertices.add(tempVertices.get(1));
            vertices.add(tempVertices.get(3));

            vertices.add(tempVertices.get(4));
            vertices.add(tempVertices.get(5));
            vertices.add(tempVertices.get(7));

            vertices.add(tempVertices.get(0));
            vertices.add(tempVertices.get(4));
            vertices.add(tempVertices.get(7));
            vertices.add(tempVertices.get(3));

            vertices.add(tempVertices.get(0));
            vertices.add(tempVertices.get(1));
            vertices.add(tempVertices.get(5));
            vertices.add(tempVertices.get(4));

        }
    }
    public void LGM_ELParbol(){
        // Elliptic Paraboloid
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for(double v = 0; v<= 10; v+=0.1){
            for(double u = -Math.PI; u<= Math.PI; u+=0.1){
                float x = rX * (float)v * (float)(Math.cos(u));
                float y = rY * (float)v * (float)((Math.sin(u)));
                float z = rZ * (float)Math.pow(v,2);
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void LGM_Bezier(List<Vector3f> point){
            vertices.clear();
            List<Vector3f> titik = new ArrayList<>();

            // looping t nya
            for (float t = 0; t <= 1; t = t + 0.01f){
                float x = 0.0f;
                float y = 0.0f;
                float z = 0.0f;

                int n = point.size()-1;

                for (int i = 0; i <= n; i++) {
                    // rumus kurva
                    float rumus = comb(n, i) * (float) (Math.pow(1-t, n-i) * Math.pow(t, i) );
                    x += rumus * point.get(i).x;
                    y += rumus * point.get(i).y;
                    z += rumus * point.get(i).z;
                }
                // masukin dalam list
                titik.add(new Vector3f(x, y, z));
            }
            vertices = titik;

    }


    public void draw(Camera camera, Projection projection){
        drawSetup(camera,projection);
        // Draw vertices
        glLineWidth(1);
        glPointSize(1);
        glDrawArrays(GL_POLYGON, 0, vertices.size());
        for(Object child : getChildObject()){
            child.draw(camera,projection);
        }
    }

    //faktorial
    public int factorial(int a){
        if (a == 0) {
            return 1;
        }
        int hasil = 1;
        for (int i = 2; i <= a; i++){
            hasil = hasil * i;
        }
        return hasil;
    }

    //Combinatorial
    public int comb(int n, int r){
        int hasil;
        hasil = factorial(n)/(factorial(n - r)* factorial(r));
        return hasil;
    }

    public float getrZ() {
        return rZ;
    }

    public void setrZ(float rZ) {
        this.rZ = rZ;
    }
    public List<Float> getCenterPoint() {
        return centerPoint;
    }


    public void getRotation(){

    }
    public void setCenterPoint(List<Float> centerPoint) {
        this.centerPoint = centerPoint;
    }


}


/*
public void createWing(){
        vertices.clear();
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();          //titik 1 depan sayap (0, 1)
        temp.x = 0;
        temp.y = 0;
        temp.z = (float)centerPoint.get(2) - rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();
        temp.x = 0;
        temp.y = -rY;
        temp.z = (float)centerPoint.get(2) - rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();          //titik 2 belakang sayap (2, 3)
        temp.x = 0;
        temp.y = 0;
        temp.z = (float)centerPoint.get(2) + rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();
        temp.x = 0;
        temp.y = -rY;
        temp.z = (float)centerPoint.get(2)  + rZ/2;
        tempVertices.add(temp);
        temp = new Vector3f();          //titik 3 ujung paling jauh sayap (4, 5)
        temp.x = (float)centerPoint.get(0) + rX * 1.15f;
        temp.y = 0;
        temp.z = (float)centerPoint.get(2)  + rZ/4;
        tempVertices.add(temp);

    }
 */

