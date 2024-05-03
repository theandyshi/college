import java.awt.*;

public class Ball extends Circle{

    private double ballX, ballY, ballXSpeed, ballYSpeed, ballRadius;

    public Ball (double x, double y, double r, Color col) {
        super(x,y,r);
        ballX = x;
        ballY = y;
        ballRadius = r;
        setColor(col);
    }

    public void setSpeedX (double xspeed){
        ballXSpeed = xspeed;
    }

    public void setSpeedY (double yspeed){
        ballYSpeed = yspeed;
    }

    public double getSpeedX() {
        return ballXSpeed;
    }

    public double getSpeedY() {
        return ballYSpeed;
    }

    public void updatePosition(double timeUnit) {
        double newBallX = getXPos() + timeUnit*getSpeedX();
        double newBallY = getYPos() + timeUnit*getSpeedY();
        setPos(newBallX,newBallY);
    }

    public boolean intersect(Ball otherBall) {
        double anotherBallX = otherBall.getXPos();
        double anotherBallY = otherBall.getYPos();
        double distance = Math.sqrt(((getXPos()-anotherBallX)*(getXPos()-anotherBallX)) + ((getYPos()-anotherBallY)*(getYPos()-anotherBallY)));
        double radiusAdd = getRadius()*2;
        if (radiusAdd+2 >= distance) {
            return true;
        }
        else {
            return false;
        }
    }

    public void collide(Ball otherBall) {
        if (intersect(otherBall)) {
            double otherBallX = otherBall.getXPos();
            double otherBallY = otherBall.getYPos();
            double distance = Math.sqrt((getXPos() - otherBallX) * (getXPos() - otherBallX) + (getYPos() - otherBallY) * (getYPos() - otherBallY));
            double otherDeltaX = (otherBallX - getXPos()) / distance;
            double otherDeltaY = (otherBallY - getYPos()) / distance;
            double DeltaX = ((getXPos() - otherBallX) / distance);
            double DeltaY = ((getYPos() - otherBallY) / distance);
            double otherNewColVelocity = -(getSpeedX() * DeltaX + getSpeedY() * DeltaY);
            double otherNewNormVelocity = -otherBall.getSpeedX() * otherDeltaY + otherBall.getSpeedY() * otherDeltaX;
            double NewColVelocity = -(otherBall.getSpeedX() * otherDeltaX + otherBall.getSpeedY() * otherDeltaY);
            double NewNormVelocity = -getSpeedX() * DeltaY + getSpeedY() * DeltaX;
            otherBall.setSpeedX(otherNewColVelocity * otherDeltaX - otherNewNormVelocity * otherDeltaY);
            otherBall.setSpeedY(otherNewColVelocity * otherDeltaY + otherNewNormVelocity * otherDeltaX);
            setSpeedX(NewColVelocity * DeltaX - NewNormVelocity * DeltaY);
            setSpeedY(NewColVelocity * DeltaY + NewNormVelocity * DeltaX);
        }
    }
}
