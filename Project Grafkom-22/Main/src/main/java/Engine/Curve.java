package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Curve extends Object{

    public Curve(List<ShaderProgram.ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList,vertices,color);
        createCurve();
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
//        uniformsMap.createUniform("uni_color");
    }

    public void createCurve(){
        List<Vector3f> result = new ArrayList<>();

        for (double i=0; i<1;i+=0.01){
            float pointx = 0, pointy = 0;
            for (int j=0;j<vertices.size();j++){
                double a = factorial(vertices.size()-1)/(factorial(j)*factorial(vertices.size()-j-1));
                double b = Math.pow((1-i),(vertices.size()-j-1));
                double c = Math.pow(i,j);
                double temp =  a * b * c;
                pointx= (float) (pointx + vertices.get(j).x * temp);
                pointy= (float) (pointy + vertices.get(j).y * temp);
            }
            result.add(new Vector3f(pointx,pointy,0));
        }
        this.vertices = result;
    }

    public int factorial(int n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n-1));
    }
}
