package Engine;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Model {
    //https://docs.safe.com/fme/html/FME_Desktop_Documentation/FME_ReadersWriters/obj/Supported_File_Syntax.htm#:~:text=The%20obj%20file%20format%20supports,line%20and%20(p)%20point.

    /* Pakai Face */
    private List<Vector3f> vertices = new ArrayList<>();
    private List<Vector3f> normals = new ArrayList<>();
    private List<Vector2f> textures = new ArrayList<>();
    private List<Vector2f> lineTextures = new ArrayList<>();
    private List<Face> faces = new ArrayList<>();
//  Model face dengan indexVertices, indexNormal, indexTexture (Triangle mesh)  f 6747/7877/176 6749/7880/176 6771/7909/176
//  Model face dengan indexVertices, indexNormal (Quad mesh)                    f 108//108 109//109 107//107 105//105

    public Model(){

    }

    public List<Vector3f> getIndices(){
        List<Vector3f> list = null;
        for (int i = 0; i < faces.size(); i++) {
            list.add(faces.get(i).vertex);
        }
        return list;
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vector3f> vertices) {
        this.vertices = vertices;
    }

    public List<Vector3f> getNormals() {
        return normals;
    }

    public void setNormals(List<Vector3f> normals) {
        this.normals = normals;
    }

    public List<Vector2f> getTextures() {
        return textures;
    }

    public void setTextures(List<Vector2f> textures) {
        this.textures = textures;
    }

    public List<Vector2f> getLineTextures() {
        return lineTextures;
    }

    public void setLineTextures(List<Vector2f> lineTextures) {
        this.lineTextures = lineTextures;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }
}
