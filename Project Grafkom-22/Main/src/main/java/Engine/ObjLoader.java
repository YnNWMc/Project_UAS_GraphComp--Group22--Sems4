package Engine;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.*;

public class ObjLoader {
    public static Model loadModelwFace(File f, boolean withTexture) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model m = new Model();
        String line;

        // \\s+ ==> Untuk catch multiple whitespace
        while((line = reader.readLine()) != null)
        {   //Parse

            // Vector Titik Biasa
            if(line.startsWith("v "))
            {
                // String Dibagi dg delimiter whitespace
                float x = Float.parseFloat(line.split("\\s+")[1]);
                float y = Float.parseFloat(line.split("\\s+")[2]);
                float z = Float.parseFloat(line.split("\\s+")[3]);
                m.vertices.add(new Vector3f(x,y,z));
            }
            // Vector Titik Normal (Shading/Lighting)
            else if(line.startsWith("vn "))
            {
                // String Dibagi dg delimiter whitespace
                float x = Float.parseFloat(line.split("\\s+")[1]);
                float y = Float.parseFloat(line.split("\\s+")[2]);
                float z = Float.parseFloat(line.split("\\s+")[3]);
                m.normals.add(new Vector3f(x,y,z));
            }
            else if(line.startsWith("vt "))
            {
                float x = Float.parseFloat(line.split("\\s+")[1]);
                float y = Float.parseFloat(line.split("\\s+")[2]);
                m.textures.add(new Vector2f(x, y));
            }
            else if(line.startsWith("f "))
            {

//                Vector4f vertexIndices = new Vector4f
//                        (
//                                Float.parseFloat(line.split("\\s+")[1].split("/")[0]), // X
//                                Float.parseFloat(line.split("\\s+")[2].split("/")[0]), // Y
//                                Float.parseFloat(line.split("\\s+")[3].split("/")[0]), // Z
//                                Float.parseFloat(line.split("\\s+")[4].split("/")[0])  // ?
//                        );
//                Vector4f normalIndices = new Vector4f
//                        (
//                                Float.parseFloat(line.split("\\s+")[1].split("/")[2]), // X
//                                Float.parseFloat(line.split("\\s+")[2].split("/")[2]), // Y
//                                Float.parseFloat(line.split("\\s+")[3].split("/")[2]),  // Z
//                                Float.parseFloat(line.split("\\s+")[4].split("/")[0])  // ?
//
//                        );

                Vector3f vertexIndices = new Vector3f
                (
                    Float.parseFloat(line.split("\\s+")[1].split("/")[0]), // X
                    Float.parseFloat(line.split("\\s+")[2].split("/")[0]), // Y
                    Float.parseFloat(line.split("\\s+")[3].split("/")[0])  // Z
                );
                Vector3f normalIndices = new Vector3f
                (
                    Float.parseFloat(line.split("\\s+")[1].split("/")[2]), // X
                    Float.parseFloat(line.split("\\s+")[2].split("/")[2]), // Y
                    Float.parseFloat(line.split("\\s+")[3].split("/")[2])  // Z
                );

                if(withTexture)
                {
                    Vector3f textureIndices = new Vector3f
                            (
                                    Float.parseFloat(line.split("\\s+")[1].split("/")[4]), // X
                                    Float.parseFloat(line.split("\\s+")[2].split("/")[4]), // Y
                                    Float.parseFloat(line.split("\\s+")[3].split("/")[4])  // Z
                            );
                    m.faces.add(new Face(vertexIndices, normalIndices, textureIndices));
                }

                else
                    m.faces.add(new Face(vertexIndices, normalIndices));

            }
            else if(line.startsWith("l "))
            {
                float x = Float.parseFloat(line.split("\\s+")[1]);
                float y = Float.parseFloat(line.split("\\s+")[2]);
                m.lineTextures.add(new Vector2f(x, y));
            }
        }
        reader.close();
        return m;
    }
}
