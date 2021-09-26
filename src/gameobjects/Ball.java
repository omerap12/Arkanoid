/**
 * @author Omer Aplatony <omerap12@gmail.com>
 * @version 1.0
 * @since 13.04.2021
 */
package gameobjects;
import basicgeometry.Line;
import basicgeometry.Point;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * Ball class, presented by center point and radios.
 */
public class Ball implements Sprite {
    private Point center;
    private double x;
    private double y;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private boolean isPosition;
    private GameEnvironment gameEnvironment;
    private boolean isClean;

    /**
     * @param center the center point of the ball
     * @param r the radios length of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.x = center.getX();
        this.y = center.getY();
        this.color = color;
        this.v = new Velocity(r - 5, r - 6);
        this.maxX = 200;
        this.maxY = 200;
        this.minX = 0;
        this.minY = 0;
        this.r = r;
        this.isPosition = false;
        this.isClean = true;
    }

    /**
     *
     * @param x the x value of the center point
     * @param y the y vakue of the center point
     * @param r the length of the radios
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.center = new Point(x, y);
        this.v = new Velocity(r - 5, r - 6);
        this.maxX = 200;
        this.maxY = 200;
        this.minX = 0;
        this.minY = 0;
        this.isPosition = false;
        this.isClean = true;
    }

    /**
     * function that set the maximum width in ball.
     * @param number tha value.
     */
    public void setMaxX(int number) {
        this.maxX = number;
    }
    /**
     * function that set the maximum height in ball.
     * @param number tha value.
     */
    public void setMaxY(int number) {
        this.maxY = number;
    }
    /**
     * function that set the minimum width in ball.
     * @param number tha value.
     */
    public void setMinX(int number) {
        this.minX = number;
    }
    /**
     * function that set the minimum height in ball.
     * @param number tha value.
     */
    public void setMinY(int number) {
        this.minY = number;
    }

    /**
     * The function set the game environment object.
     * @param environment - the game object.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * function set the center point of the ball.
     * @param newX - the new x value
     * @param newY - the new y value
     */
    public void setCenter(double newX, double newY) {
        //set the center point with the new point
        this.center = new Point(newX, newY);
        this.x = newX;
        this.y = newY;
    }

    /**
     * @param value to clean
     */
    public void setClean(boolean value) {
        this.isClean = value;
    }

    /**
     * @return clean
     */
    public boolean getClean() {
        return this.isClean;
    }

    /**
     * function that checks and fixed the ball radios, x and y values.
     */
    private void checkBeforeMove() {
        //boolean variable to whether the center needs change
        boolean isCenterNeedsChange = false;
        //if radios length is too small or too big
        if (this.r <= 0 || this.r > maxY / 3 || this.r > maxX / 3) {
            //set radion length to the max size divided by five
            this.r = (int) maxX / 5;
            //update the velocity values
            this.setVelocity(1 / this.r + 1, 1 / this.r + 1);
        }

        //if x value is negative
        if (this.x < 0) {
            //convert to positive
            this.x = -this.x;
            //set boolean variable to true
            isCenterNeedsChange = true;
        }
        //if y value is negative
        if (this.y < 0) {
            //convert to positive
            this.y = -this.y;
            //set boolean variable to true
            isCenterNeedsChange = true;
        }
        //if boolean variable is true update the center point
        if (isCenterNeedsChange) {
            this.center = new Point(this.x, this.y);
        }
    }

    /**
     * function that return the x center value.
     * @return the x center value.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     *function that return the y center value.
     * @return the y center value
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * function that return the radios length value.
     * @return the radios length value
     */
    public int getSize() {
        return r;
    }

    /**
     *function that return the center point.
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     *function that draw the ball to the screen.
     * @param surface - draw the ball on the surface
     */
    public void drawOn(DrawSurface surface) {
        //set color
        surface.setColor(color);
        //draw the ball
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * function that set velocity.
     * @param newV - set the velocity
     */
    public void setVelocity(Velocity newV) {
        this.v = newV;
    }

    /**
     * function that set velocity, by using dx and dy value.
     * @param dx set x value rate of the velocity
     * @param dy set y value rate of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     *  function that return velocity.
     * @return the velocity
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * function the initialize ball location and moves the ball across the screen.
     */
    public void moveOneStep() {
        Velocity currentV = this.v;
        //creating the line of the ball movement
        Line trajectory = new Line(this.center, new Point(this.center.getX() + this.v.getDx(),
                this.center.getY() + this.v.getDy()));

        //getting collision info from the game environment class
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        //if collision occur
        if (collisionInfo != null) {
            //getting the point from class
            Point collisionPoint = collisionInfo.collisionPoint();
            //calling set center function to adjust the center of the ball to right coordinates
            setCenter(collisionPoint.getX() - currentV.getDx(), collisionPoint.getY() - currentV.getDy());
            //adjust velocity using hit function
            currentV = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), currentV);
            //set new velocity
            setVelocity(currentV);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * function that add the ball to the sprites list and game environment.
     * @param g - game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        drawOn(g.getEnvironment().getSurface());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * @param g the game to remove from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
