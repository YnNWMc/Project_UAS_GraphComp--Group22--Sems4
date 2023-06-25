package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Player extends Sphere{
    private float moveSpeed = 1;
    private float rotateSpeed = 0.1f;
    private int characterDir = 180;
    private float currSpeed = 0.05f;
    private float currRotateSpeed = 0;
    public Player(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, double r, ArrayList<Float> centerPoint, float rX, float rY, float rZ, int stackCount, int sectorCount, Model m) {
        super(shaderModuleDataList, vertices, color, r, centerPoint, rX, rY, rZ, stackCount, sectorCount, m);
    }

    public void checkInputs(String direction, Player p){
        if(direction == "f")
        {
            currSpeed = -moveSpeed;
        }
        else if(direction == "b")
        {
            currSpeed = moveSpeed;
        }
        else
        {
            currSpeed = 0;
        }
        if(direction == "r")
        {
            currRotateSpeed = rotateSpeed;
            currSpeed = -moveSpeed;

        }
        else if(direction == "l")
        {
            currSpeed = moveSpeed;

            currRotateSpeed = -rotateSpeed;
        }
        else
        {

            currRotateSpeed = 0;
        }
    }
    public void move(String direction, Player p)
    {
        //checkInputs(direction,p);
        System.out.println(characterDir);
        if(direction == "f")
        {
            while(characterDir != 0){
                p.rotateObjectOnPoint(1,0f,1f,0f, p.getCenterPoint().get(0),p.getCenterPoint().get(1),p.getCenterPoint().get(2));
                characterDir-=1;
            }
            p.translateObject(0f,0f, -currSpeed);

        }
        else if(direction == "b")
        {
            while(characterDir != 180){
                p.rotateObjectOnPoint(1,0f,1f,0f, p.getCenterPoint().get(0),p.getCenterPoint().get(1),p.getCenterPoint().get(2));

                if(characterDir > 180)
                    characterDir-=1;
                else
                    characterDir+=1;
            }
            p.translateObject(0f,0f, currSpeed);
        }
        if(direction == "r")
        {
            while(characterDir != 90){
                p.rotateObjectOnPoint(1,0f,1f,0f, p.getCenterPoint().get(0),p.getCenterPoint().get(1),p.getCenterPoint().get(2));
                if(characterDir > 90)
                    characterDir-=1;
                else
                    characterDir+=1;
            }
            p.translateObject(currSpeed/2,0f,0f );
        }
        else if(direction == "l")
        {
            while(characterDir != 270){
                p.rotateObjectOnPoint(1,0f,1f,0f, p.getCenterPoint().get(0),p.getCenterPoint().get(1),p.getCenterPoint().get(2));
                if(characterDir > 270)
                    characterDir-=1;
                else
                    characterDir+=1;
            }
            p.translateObject(-currSpeed/2,0f,0f);
        }
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public float getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(float rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    public int getCharacterDir() {
        return characterDir;
    }

    public void setCharacterDir(int characterDir) {
        this.characterDir = characterDir;
    }

    public float getCurrSpeed() {
        return currSpeed;
    }

    public void setCurrSpeed(float currSpeed) {
        this.currSpeed = currSpeed;
    }

    public float getCurrRotateSpeed() {
        return currRotateSpeed;
    }

    public void setCurrRotateSpeed(float currRotateSpeed) {
        this.currRotateSpeed = currRotateSpeed;
    }
}


//    public void jump(Player p){
//        int limit = 50;
//        for(int i = 0 ; i < limit ; i++)
//        {
//            p.translateObject(0f,0.005f,0f);
//        }
//        backDown(p);
//    }
//    public void backDown(Player p){
//        int limit = 50;
//        for(int i = 0 ; i < limit ; i++)
//        {
//            p.translateObject(0f,-0.5f,0f);
//        }
//    }


/*
public void jump(Player p) {
//        float jumpHeight = 0.3f;  // Adjust this value to control the height of the jump
//        float gravity = 0.01f;  // Adjust this value to control the speed of descent
//
//        float initialVelocity = 0.2f;  // Adjust this value to control the initial jump velocity
//        float velocity = initialVelocity;
//
//        float yPosition = p.getCenterPoint().get(2);  // Assuming 'y' is the vertical position of the player
//
//        while (yPosition > 0) {
//            yPosition += velocity;
//            velocity -= gravity;
//
//            p.setY(yPosition);
//
//            // Assuming p.translateObject(dx, dy, dz) moves the player by (dx, dy, dz) in the 3D space
//            p.translateObject(0f, velocity, 0f);
//        }
    }
 */
