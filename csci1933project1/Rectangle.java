import java.awt.*;

public class Rectangle {
    private double xPos = 0;
    private double yPos = 0;
    private double width = 0;
    private double height = 0;
    private Color color = Color.BLUE;


    //constructor

    public Rectangle(double x, double y, double w, double h){
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }


    //operator methods

    public double calculatePerimeter() {
        return ((width + height) * 2);
    }

    public double calculateArea() {
        return (width * height);
    }


    //accessor methods

    public void setColor(Color c) {
        color = c;
    }

    public void setPos(double x, double y) {
        xPos = x;
        yPos = y;
    }

    public void setHeight(double h) {
        height = h;
    }

    public void setWidth(double w) {
        width = w;
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

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }
}
