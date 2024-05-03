import java.awt.*;

public class Circle {

    private double xPos = 0;
    private double yPos = 0;
    private double radius = 0;
    private Color color = Color.BLUE;


    //constructor

    public Circle(double x, double y, double r){
        xPos = x;
        yPos = y;
        radius = r;
    }


    //operator methods

    public double calculatePerimeter() {
        return (2* radius * Math.PI);
    }

    public double calculateArea() {
        return (radius * radius * Math.PI);
    }


    //accessor methods

    public void setColor(Color c) {
        color = c;
    }

    public void setPos(double x, double y) {
        xPos = x;
        yPos = y;
    }

    public void setRadius(double r) {
        radius = r;
    }

    public Color getColor() {
        return color;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getRadius(){
        return radius;
    }
}
