package models;

public class LandmarkNode {

    int x;
    int y;
    String name = "";
    int culturalValue = 0;

    public LandmarkNode(String name,Pixel coords,int culturalVal) {
        setName(name);
        setY(coords.getY());
        setX(coords.getX());
        setCulturalValue(culturalVal);
    }

    public int getCulturalValue() {
        return culturalValue;
    }

    public void setCulturalValue(int culturalValue) {
        this.culturalValue = culturalValue;
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
