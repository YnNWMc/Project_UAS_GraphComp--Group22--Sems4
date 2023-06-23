package Engine;

import org.joml.Vector3f;

public class Face {
    public Vector3f vertex = new Vector3f(); // three Indices
    public Vector3f normal = new Vector3f();
    public Vector3f texture = new Vector3f();
    public Face(Vector3f vertex, Vector3f normal){
        this.vertex = vertex;
        this.normal = normal;
    }
    public Face(Vector3f vertex, Vector3f normal,  Vector3f texture){
        this.vertex = vertex;
        this.normal = normal;
        this.texture = texture;
    }



//    public Vector4f vertex = new Vector4f(); // Four Indices
//    public Vector4f normal = new Vector4f();
//    public Face(Vector4f vertex, Vector4f normal){
//        this.vertex = vertex;
//        this.normal = normal;
//
//    }


}
