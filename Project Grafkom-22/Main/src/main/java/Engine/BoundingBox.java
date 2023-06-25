package Engine;

public class BoundingBox {

    private float width, height, length;

    public BoundingBox(float width, float height, float length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }
}
