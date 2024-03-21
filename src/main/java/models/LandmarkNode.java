package models;

public class LandmarkNode {

    int x;
    int y;
    String name = "";

    public LandmarkNode(String name,Pixel coords) {
        this.x = coords.getX();
        this.y = coords.getY();
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LandmarkNode{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
