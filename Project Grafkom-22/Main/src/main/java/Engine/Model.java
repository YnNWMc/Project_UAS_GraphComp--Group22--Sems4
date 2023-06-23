package Engine;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Model {
    //https://docs.safe.com/fme/html/FME_Desktop_Documentation/FME_ReadersWriters/obj/Supported_File_Syntax.htm#:~:text=The%20obj%20file%20format%20supports,line%20and%20(p)%20point.

    /* Pakai Face */
    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Vector2f> textures = new ArrayList<>();
    public List<Vector2f> lineTextures = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();
//  Model face dengan indexVertices, indexNormal, indexTexture (Triangle mesh)  f 6747/7877/176 6749/7880/176 6771/7909/176
//  Model face dengan indexVertices, indexNormal (Quad mesh)                    f 108//108 109//109 107//107 105//105

    public Model(){

    }

}
