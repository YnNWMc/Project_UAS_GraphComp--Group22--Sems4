package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Wheezy_Object extends Circle3D{
    float rZ;
    int stackCount;
    int sectorCount;

    public Wheezy_Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, List<Vector3f> point,Vector4f color, double r, ArrayList<Float> centerPoint, float rX, float rY, float rZ, int stackCount, int sectorCount, int option) {
        super(shaderModuleDataList, vertices, color, centerPoint, rX, rY);
        this.rZ = rZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;

       if(option == 1){
           createOvaloid();//Badan
       }
       else if (option == 2){
           createOvaloidv2();//ovaloid modif, jdi perut
       }
       else if (option == 3){
           createEllipticParaboloid();//eleptic paraboloid, jadi tangan
       }
       else if (option == 4){
           createElipsoid();//sphere, jadi mata, kaki, buletan dasi
       }
       else if (option == 5){
            createHalfElipticCone();//half eliptic cone jadi paruh
       }else if (option == 6){
           createTrapesiumFromBox();//Dasi
       }


        setupVAOVBO();
    }


    public ArrayList<Vector3f> generateBezierPoints(Vector3f first,Vector3f second, Vector3f third)
    {
        float firstX = first.x;
        float firstY = first.y;
        float firstZ = first.z;

        float secondX = second.x;
        float secondY = second.y;
        float secondZ = second.z;

        float thirdX = third.x;
        float thirdY = third.y ;
        float thirdZ = third.z;

        ArrayList<Vector3f> result = new ArrayList<>();
        float newX, newY, newZ;
        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (float) ((Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX));
            newY = (float) ((Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY));
            newZ = (float) ((Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ));
            result.add(new Vector3f(newX, newY, newZ));
        }
        return result;
    }

//    public void createPotatoBody(){
//        vertices.clear();
//        ArrayList<Vector3f> temp = new ArrayList<>();
//
//        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/180){
//            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/180){
//                float x = rX* (float)(Math.cos(v) * Math.cos(u));
//                float y = rY * (float)(Math.cos(v) * Math.sin(u));
//                float z = rZ * (float)(Math.sin(v));
//                temp.add(new Vector3f(x,y,z));
//            }
//        }
//        vertices = temp;
//    }

    public void createHalfElipticCone(){// paruh
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double v = 0; v <= 2 * Math.PI; v += Math.PI / 180) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 180) {
                float x = (float) (rX * v * Math.cos(u));
                float y = (float) (rY * v * Math.sin(u));
                float z = (float) (rZ * v);
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices = temp;
    }

    public void createEllipticParaboloid() {// EllipticParaboloid jadi tangan
        vertices.clear();
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float StackAngle, x, y, z;

        // u stackup angle
        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = rX * StackAngle *(float)Math.cos(StackAngle);
            y = rY * StackAngle * (float)Math.sin(StackAngle);
            z = rZ * StackAngle * StackAngle;

            for (int j = 0; j <= sectorCount; ++j)
            {
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x;
                temp_vector.y = centerPoint.get(1) + y ;
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }

    public void createTrapesiumFromBox() {// trapesium
            Vector3f temp = new Vector3f();
            float cpx = centerPoint.get(0);
            float cpy = centerPoint.get(1);
            float cpz = centerPoint.get(2);

            ArrayList<Vector3f> tempVertices = new ArrayList<>();

            //titik 0 kiri atas belakang
            temp.x = cpx - rZ / 4f;
            temp.y = cpy + rY / 4f;
            temp.z = cpz - rZ / 4f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 1 kiri bawah belakang
            temp.x = cpx - rZ / 2f;
            temp.y = cpy - rY / 2f;
            temp.z = cpz - rZ / 2f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 2 kanan bawah belakang
            temp.x =  cpx + rZ / 2f;
            temp.y =  cpy - rY / 2f;
            temp.z =  cpz - rZ / 2f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 3 kanan atas belakang
            temp.x =  cpx + rZ / 4f;
            temp.y =  cpy + rY / 4f;
            temp.z =  cpz - rZ / 4f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 4 kiri atas depan
            temp.x =  cpx - rZ / 4f;
            temp.y =  cpy + rY / 4f;
            temp.z =  cpz + rZ / 4f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 5 kiri bawah depan
            temp.x =  cpx - rZ / 2f;
            temp.y =  cpy - rY / 2f;
            temp.z =  cpz + rZ / 2f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 6 kanan bawah depan
            temp.x =  cpx + rZ / 2f;
            temp.y =  cpy - rY / 2f;
            temp.z =  cpz + rZ / 2f;
            tempVertices.add(temp);
            temp = new Vector3f();

            //titik 7 kanan atas depan
            temp.x =  cpx + rZ / 4f;
            temp.y =  cpy + rY / 4f;
            temp.z =  cpz + rZ / 4f;
            tempVertices.add(temp);

            vertices.clear();
            {
                // bagian atas trapesium
                vertices.add(tempVertices.get(0));
                vertices.add(tempVertices.get(3));
                vertices.add(tempVertices.get(7));
                vertices.add(tempVertices.get(4));
                vertices.add(tempVertices.get(0));

                //bagian kiri trapesium
                vertices.add(tempVertices.get(1));
                vertices.add(tempVertices.get(0));
                vertices.add(tempVertices.get(4));
                vertices.add(tempVertices.get(5));
                vertices.add(tempVertices.get(1));

                // bagian belakang trapesium
                vertices.add(tempVertices.get(0));
                vertices.add(tempVertices.get(3));
                vertices.add(tempVertices.get(2));
                vertices.add(tempVertices.get(1));
                vertices.add(tempVertices.get(0));

                //bagian kanan trapesium
                vertices.add(tempVertices.get(3));
                vertices.add(tempVertices.get(7));
                vertices.add(tempVertices.get(6));
                vertices.add(tempVertices.get(2));

                //bagian depan trapesium
                vertices.add(tempVertices.get(6));
                vertices.add(tempVertices.get(5));
            }
        }

    public void createElipsoid(){// buat buletan untuk dasi, mata, kaki, dan pantat

        vertices.clear();
        float radiusX = rX;
        float radiusY = rY;
        float radiusZ = rZ;
        List<Float> centerPoint = Arrays.asList(0.0f,0.0f,0.0f);

        float pi = (float)Math.PI;
        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
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

    public void createOvaloidv2(){// jadi bagian yg putih
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (int i = 0; i < 360; i+= 1f) {
            float rad = (float) (i * Math.PI / 180);
            float x = (float) (rX * Math.cos(rad) * (1.6 + 0.2 * Math.sin(3 * rad)));
            float y = (float) -(rY * Math.sin(rad) * (1.35 + 0.1 * Math.sin(3 * rad))); // di reverse pake -

            temp.add(new Vector3f(x,y,0f));
            temp.add(new Vector3f(x,y,rZ));
        }
        vertices = temp;
    }

    public void createOvaloid() {// jadi badan
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (float i = 0; i < 360; i += 0.6f) {
            float rad = (float) (i * Math.PI / 180);
            float x = (float) (rX * Math.cos(rad) * (1.0 + 0.2 * Math.sin(3 * rad)));
            float y = (float) -(rY * Math.sin(rad) * (1.2 + 0.1 * Math.sin(3 * rad))); // di reverse

            temp.add(new Vector3f(x, y, 0f));
            temp.add(new Vector3f(x, y, rZ));
        }
        vertices = temp;
    }

    public void createCurve (List<Vector3f> point){
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

    //polinomial
    public int comb(int n, int r){
        int hasil;
        hasil = factorial(n)/(factorial(n - r)* factorial(r));
        return hasil;
    }
//    public void draw(Camera camera, Projection projection){
//        drawSetup(camera,projection);
//        // Draw vertices
//        glLineWidth(1);
//        glPointSize(1);
//        glDrawArrays(GL_POLYGON, 0, vertices.size());
//        for(Object child : getChildObject()){
//            child.draw(camera,projection);
//        }
//    }

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
//    public void draw(Camera camera, Projection projection){
//        drawSetup(camera,projection);
//        // Draw vertices
//        glLineWidth(1);
//        glPointSize(1);
//        glDrawArrays(GL_POLYGON, 0, vertices.size());
//        for(Object child : getChildObject()){
//            child.draw(camera,projection);
//        }
//    }



    //    public void draw(Camera camera, Projection projection){
//        drawSetup(camera,projection);
//        // Draw vertices
//        glLineWidth(1);
//        glPointSize(1);
//        glDrawArrays(GL_POLYGON, 0, vertices.size());
//        for(Object child : getChildObject()){
//            child.draw(camera,projection);
//        }
//    }

    public float getrZ() {

        return rZ;
    }

    public void setrZ(float rZ) {
        this.rZ = rZ;
    }
}
