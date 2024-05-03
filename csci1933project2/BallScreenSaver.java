import java.awt.*;
import java.awt.event.*;

public class BallScreenSaver extends AnimationFrame {
    private int numBalls = 15, w = 800, h =800;
    private String name = "BallBros";
    private double ballX, ballY, ballXSpeed, ballYSpeed, ballRadius = 6, BORDER = 30;
    private Color color = Color.green;
    private Ball[] listBalls = new Ball[10];

    public BallScreenSaver () {
        super();
        listBalls = new Ball[numBalls];
        setFPS(30);
        for (int i = 0; i <= numBalls-1; i++) {
            ballX = Math.random() * (getWidth() - (BORDER * 2)-2) + BORDER+1;
            ballY = Math.random() * (getHeight() - (BORDER * 2)-2) + BORDER+1;
            ballXSpeed=(Math.random()*200)-100;
            ballYSpeed=(Math.random()*200)-100;
            listBalls[i] = new Ball(ballX, ballY, ballRadius, color);
            listBalls[i].setSpeedX(ballXSpeed);
            listBalls[i].setSpeedY(ballYSpeed);
        }
        listBalls[0].setColor(Color.red);

    }

    public BallScreenSaver (int w, int h, String name, int balls) {
        super(w,h,name);
        numBalls = balls;
        listBalls = new Ball[numBalls];
        setFPS(60);
        for (int i = 0; i <= numBalls-1; i++){
            ballX = Math.random()*(getWidth() -(BORDER*2))+BORDER;
            ballY = Math.random()*(getHeight() -(BORDER*2))+BORDER;
            ballXSpeed=(Math.random()*200)-100;
            ballYSpeed=(Math.random()*200)-100;
            listBalls[i] = new Ball(ballX,ballY,ballRadius,color);
            listBalls[i].setSpeedX(ballXSpeed);
            listBalls[i].setSpeedY(ballYSpeed);
        }
        listBalls[0].setColor(Color.red);
    }

    public void action(){
        for (int j = 0; j <= numBalls-1; j++) {
            for (int k = j+1; k <= numBalls-1; k++) {
                if (listBalls[j].intersect(listBalls[k])){
                    listBalls[j].collide(listBalls[k]);
                    listBalls[j].updatePosition(1/getFPS());
                    listBalls[k].updatePosition(1/getFPS());
                    if (listBalls[j].getColor() == Color.red || listBalls[k].getColor() == Color.red) {
                        listBalls[j].setColor(Color.red);
                        listBalls[k].setColor(Color.red);
                    }
                }
            }
            if ( listBalls[j].getXPos()<BORDER || (listBalls[j].getXPos()+ballRadius*2)>(getHeight()-BORDER) ){
                listBalls[j].setSpeedX(listBalls[j].getSpeedX()* -1);
                if ( listBalls[j].getXPos()<BORDER) {
                    listBalls[j].setPos(listBalls[j].getXPos()+5,listBalls[j].getYPos());
                }
                else if ((listBalls[j].getXPos()+ballRadius*2)>(getHeight()-BORDER) ) {
                    listBalls[j].setPos(listBalls[j].getXPos()-5,listBalls[j].getYPos());
                }
            }
            if ( listBalls[j].getYPos()<BORDER || (listBalls[j].getYPos()+ballRadius*2)>(getWidth()-BORDER) ){
                listBalls[j].setSpeedY(listBalls[j].getSpeedY()* -1);
                if ( listBalls[j].getYPos()<BORDER) {
                    listBalls[j].setPos(listBalls[j].getXPos(),listBalls[j].getYPos()+5);
                }
                else if ((listBalls[j].getYPos()+ballRadius*2)>(getHeight()-BORDER) ) {
                    listBalls[j].setPos(listBalls[j].getXPos(),listBalls[j].getYPos()-5);
                }
            }
            double newBallX = listBalls[j].getXPos() + listBalls[j].getSpeedX()/getFPS();
            double newBallY = listBalls[j].getYPos() + listBalls[j].getSpeedY()/getFPS();
            listBalls[j].setPos(newBallX,newBallY);
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.white);
        int adjustWidth = (int) (getWidth()-BORDER*2);
        int adjustHeight = (int) (getHeight()-BORDER*2);
        g.drawRect((int) BORDER,(int) BORDER,adjustWidth,adjustHeight);
        int ballWidth = (int)ballRadius * 2;
        for (int i = 0; i <= numBalls-1; i++){
            double x = listBalls[i].getXPos();
            double y = listBalls[i].getYPos();
            g.setColor(listBalls[i].getColor());
            g.fillOval((int)x, (int)y, ballWidth, ballWidth);
        }
    }

    public void processKeyEvent(KeyEvent click){
        int keyPressed = click.getKeyCode();
        if (click.getID() == KeyEvent.KEY_PRESSED && keyPressed == KeyEvent.VK_LEFT) {
            for (int i = 0; i <= numBalls-1; i++) {
                listBalls[i].setSpeedX(listBalls[i].getSpeedX() * .9);
                listBalls[i].setSpeedY(listBalls[i].getSpeedY() * .9);
            }
        }
        if (click.getID() == KeyEvent.KEY_PRESSED && keyPressed == KeyEvent.VK_RIGHT) {
            for (int i = 0; i <= numBalls-1; i++) {
                listBalls[i].setSpeedX(listBalls[i].getSpeedX() * 1.1);
                listBalls[i].setSpeedY(listBalls[i].getSpeedY() * 1.1);
            }
        }
    }
    public static void main(String[] args){
        BallScreenSaver BallBros = new BallScreenSaver(600,600,"Ball Bros",40);
        BallBros.start();
    }
}
