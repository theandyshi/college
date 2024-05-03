import java.awt.*;
import java.util.Scanner;

public class Fractals {
    public static double areaSum = 0;
    public static double perimeterSum = 0;
//------------------------------------------------------------------------------------------------------------------------
/*    public static int IterativeCircleDraw(Canvas drawing, Color[] c) {
        int sum = 0;
        int multiple = 1;
        int numCirc[] = new int[7];
        int halfNumCirc[] = new int[7];
        for (int i = 0; i < 7; i++) {
            sum += multiple;
            numCirc[i] = multiple;
            halfNumCirc[i] = multiple / 2;
            multiple = multiple * 2;
        }

        Circle circ[] = new Circle[sum];
        for (int l = 0; l <= (sum - 1); l++) {
            circ[l] = new Circle(100, 100, 100);
        }

        double x = 400;
        double xOrigin = 400;
        double permanentY = 400;
        double r = 300;
        int circCounter = 0;
        int negativePositive = 0;
        int blackWhite = 0;

        for (int j = 0; j < sum; j++) {
            numCirc[circCounter] -= 1;
            circ[j].setColor(c[blackWhite]);
            circ[j].setPos(x, permanentY);
            circ[j].setRadius(r);
            drawing.drawShape(circ[j]);
            if (numCirc[circCounter] == 0) {
                r = r / 2;
                x = xOrigin - r;
                circCounter += 1;
                blackWhite += 1;
                negativePositive = 0;
            } else if (numCirc[circCounter] == halfNumCirc[circCounter]) {
                x = xOrigin + r;
                negativePositive = 1;
            } else if (negativePositive == 0) {
                x = x - 2 * r;
            } else if (negativePositive == 1) {
                x = x + 2 * r;
            }
        }
        return 2;
    }*/

//------------------------------------------------------------------------------------------------------------------------
    public static void RecursiveCircleDraw(Canvas drawing, Color[] c, int blackWhite, double x, double y, double r, int counter){
        //first shape put down must be set down unrecursively
        if (counter == 7){
            Circle firstCircle = new Circle(x, y, r);
            firstCircle.setColor(c[blackWhite]);
            drawing.drawShape(firstCircle);
            //begin recursively putting down circles
            RecursiveCircleDraw(drawing, c, blackWhite + 1, x, y, r, counter - 1);
            //add the area and perimeter of the circles to the sums of each
            areaSum += firstCircle.calculateArea();
            perimeterSum += firstCircle.calculatePerimeter();

        }
        else {
            //place 2 circles within the circle
            double x1 = x - r/2;
            Circle circle1 = new Circle(x1, y, r/2); //first circle
            circle1.setColor(c[blackWhite]);
            double x2 = x + r/2;
            Circle circle2 = new Circle(x2, y, r/2); // second circle
            circle2.setColor(c[blackWhite]);
            drawing.drawShape(circle1);
            drawing.drawShape(circle2);
            areaSum += circle1.calculateArea() + circle2.calculateArea(); //add the area of the two created circles to the total amount
            perimeterSum += circle1.calculatePerimeter() + circle2.calculatePerimeter(); //add the perimeter of the two created circles to the total amount
            //until it is done 7 times
            if (counter > 1) {
                counter -= 1;
                //recursively create 2 more circles within the circles
                RecursiveCircleDraw(drawing, c, blackWhite + 1, x1, y, r/2, counter);
                RecursiveCircleDraw(drawing, c, blackWhite + 1, x2, y, r/2, counter);
            }
        }
    }
//------------------------------------------------------------------------------------------------------------------------
/*  public static int IterativeRectangleDraw(Canvas drawing, Color[] c) {
        int sum = 0;
        int multiple = 1;
        int multiple2 = 1;
        int numsqrs[] = new int[7];
        int sqrheights[] = new int[7];
        for (int i = 0; i < 7; i++) {
            sum += multiple;
            numsqrs[i] = multiple;
            multiple = multiple * 4;
            sqrheights[i] = multiple2;
            multiple2 = multiple2 * 2;
        }
        double xOrigin = 300;
        double yOrigin = 300;
        double x = 300;
        double y = 300;
        double length = 200;
        double height = 200;
        int squareCounter = 0;
        int blackWhite = 0;
        int heightCounter = 0;

        Rectangle rect[] = new Rectangle[sum];
        for (int l = 0; l <= (sum - 1); l++) {
            rect[l] = new Rectangle(100, 100, 100, 100);
        }
        for (int j = 0; j < sum; j++) {
            numsqrs[squareCounter] -= 1;
            rect[j].setColor(c[blackWhite]);
            rect[j].setPos(x, y);
            rect[j].setHeight(height);
            rect[j].setWidth(length);
            drawing.drawShape(rect[j]);
            if (numsqrs[squareCounter] == 0) {
                length = length / 2;
                height = height / 2;
                xOrigin -= length;
                yOrigin -= height;
                x = xOrigin;
                y = yOrigin;
                squareCounter += 1;
                blackWhite += 1;
                heightCounter = 0;
            } else if (heightCounter + 1 == sqrheights[squareCounter]) {
                x += 3 * length;
                y = yOrigin;
                heightCounter = 0;
            } else {
                y += 3 * height;
                heightCounter += 1;
            }
        }
        return 2;
    }*/
//------------------------------------------------------------------------------------------------------------------------
    public static void RecursiveRectangleDraw(Canvas drawing, Color[] c, int blackWhite, double x, double y, double w, double h, int counter) {
        //first shape put down must be set down unrecursively
        if (counter == 7){
            Rectangle firstRectangle = new Rectangle(x, y, w, h);
            firstRectangle.setColor(c[blackWhite]);
            drawing.drawShape(firstRectangle);
            //begin recursively putting down rectangles
            RecursiveRectangleDraw(drawing, c, blackWhite + 1, x, y, w, h,counter - 1);
            //add the area and perimeter of the rectangles to the sums of each
            areaSum += firstRectangle.calculateArea();
            perimeterSum += firstRectangle.calculatePerimeter();
        }
        else {
            //place 4 rectangles around the rectangle
            double x1 = x - w/2;
            double y1 = y + h;
            Rectangle rectangle1 = new Rectangle(x1, y1, w/2,h/2); //first rectangle
            rectangle1.setColor(c[blackWhite]);
            double x2 = x - w/2;
            double y2 = y - h/2;
            Rectangle rectangle2 = new Rectangle(x2, y2, w/2,h/2); //second rectangle
            rectangle2.setColor(c[blackWhite]);
            double x3 = x + w;
            double y3 = y + h;
            Rectangle rectangle3 = new Rectangle(x3, y3, w/2,h/2); //third rectangle
            rectangle3.setColor(c[blackWhite]);
            double x4 = x + w;
            double y4 = y - h/2;
            Rectangle rectangle4 = new Rectangle(x4, y4, w/2,h/2); //fourth rectangle
            rectangle4.setColor(c[blackWhite]);
            drawing.drawShape(rectangle1);
            drawing.drawShape(rectangle2);
            drawing.drawShape(rectangle3);
            drawing.drawShape(rectangle4);
            areaSum += rectangle1.calculateArea() + rectangle2.calculateArea() + rectangle3.calculateArea() + rectangle4.calculateArea(); //add the area of the four created rectangles to the total amount
            perimeterSum += rectangle1.calculateArea()+ rectangle2.calculateArea() + rectangle3.calculateArea() + rectangle4.calculateArea(); //add the perimeter of the four created rectangles to the total amount
            //until it is done 7 times
            if (counter > 1) {
                counter -= 1;
                //recursively create 4 more rectangles around the recatangles
                RecursiveRectangleDraw(drawing, c, blackWhite + 1, x1, y1, w/2, h/2,counter);
                RecursiveRectangleDraw(drawing, c, blackWhite + 1, x2, y2, w/2, h/2,counter);
                RecursiveRectangleDraw(drawing, c, blackWhite + 1, x3, y3, w/2, h/2,counter);
                RecursiveRectangleDraw(drawing, c, blackWhite + 1, x4, y4, w/2, h/2,counter);
            }
        }
    }

//------------------------------------------------------------------------------------------------------------------------
    public static void RecursiveTriangleDraw(Canvas drawing, Color[] c, int blackWhite, double x, double y, double w, int counter) {
        //first shape put down must be set down unrecursively
        if (counter == 7){
            Triangle firstTriangle = new Triangle(x, y, w, w/2 * Math.sqrt(2));
            firstTriangle.setColor(c[blackWhite]);
            drawing.drawShape(firstTriangle);
            //begin recursively putting down triangles
            RecursiveTriangleDraw(drawing, c, blackWhite + 1, x, y, w, counter - 1);
            //add the area and perimeter of the triangles to the sums of each
            areaSum += firstTriangle.calculateArea();
            perimeterSum += firstTriangle.calculatePerimeter();
        }
        else {
            //place 3 triangles around the triangle
            double x1 = x - 3 * w / 8;
            double y1 = y + w * Math.sqrt(2)/8;
            Triangle triangle1 = new Triangle(x1, y1, w/2,w/4 * Math.sqrt(2)); //first triangle
            triangle1.setColor(c[blackWhite]);
            double x2 = x + 7 * w / 8;
            double y2 = y + w * Math.sqrt(2)/8;
            Triangle triangle2 = new Triangle(x2, y2, w/2,w/4 * Math.sqrt(2)); //second triangle
            triangle2.setColor(c[blackWhite]);
            double x3 = x + w / 4;
            double y3 = y - w * Math.sqrt(2)/2;
            Triangle triangle3 = new Triangle(x3, y3, w/2,w/4 * Math.sqrt(2)); //third triangle
            triangle3.setColor(c[blackWhite]);
            drawing.drawShape(triangle1);
            drawing.drawShape(triangle2);
            drawing.drawShape(triangle3);
            areaSum += triangle1.calculateArea() + triangle2.calculateArea() + triangle3.calculateArea(); //add the area of the three created triangles to the total amount
            perimeterSum += triangle1.calculatePerimeter() + triangle2.calculatePerimeter() + triangle3.calculatePerimeter(); //add the perimeter of the three created triangles to the total amount
            //until it is done 7 times
            if (counter > 1) {
                counter -= 1;
                //recursively create 3 more triangles within the triangles
                RecursiveTriangleDraw(drawing, c, blackWhite + 1, x1, y1, w / 2, counter);
                RecursiveTriangleDraw(drawing, c, blackWhite + 1, x2, y2, w / 2, counter);
                RecursiveTriangleDraw(drawing, c, blackWhite + 1, x3, y3, w / 2, counter);
            }
        }
    }
//------------------------------------------------------------------------------------------------------------------------
    //main function
    public static void main(String[] argv) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a Shape: "); //gives user prompt for shape
        String shape = reader.next();
        Canvas drawing = new Canvas(800, 800);
        //gives colors for each iteration
        Color c[] = new Color[7];
        c[0] = Color.RED;
        c[1] = Color.ORANGE;
        c[2] = Color.YELLOW;
        c[3] = Color.GREEN;
        c[4] = Color.BLUE;
        c[5] = Color.CYAN;
        c[6] = Color.MAGENTA;

        //creates fractal of inputed shape
        if (shape.equals("Circle") || shape.equals("circle")) {
            RecursiveCircleDraw(drawing, c, 0,400,400,300,7);
            //will show the total area and perimeter of all the circles combined
            System.out.println("Total Area:" + areaSum);
            System.out.println("Total Perimeter:" + perimeterSum);
        }
        else if (shape.equals("Rectangle") || shape.equals("rectangle")) {
            RecursiveRectangleDraw(drawing, c, 0,300,300,200,200,7);
            //will show the total area and perimeter of all the rectangles combined
            System.out.println("Total Area:" + areaSum);
            System.out.println("Total Perimeter:" + perimeterSum);
        }
        else if (shape.equals("Triangle") || shape.equals("triangle")) {
            RecursiveTriangleDraw(drawing, c, 0,250,500,300,7);
            //will show the total area and perimeter of all the triangles combined
            System.out.println("Total Area:" + areaSum);
            System.out.println("Total Perimeter:" + perimeterSum);

        }
    }
}

