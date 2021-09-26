/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;
import basicgeometry.Line;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is ap Paddle that moved by the user and hit the ball, contain rectangle and keyboardSensor.
 */
public class Paddle implements Sprite, Collidable {
    static final int THIRTY = 30;
    static final int TEN = 10;
    static final int MAXWIDTH = 800;

    private Rectangle rectangle;
    private Color color;
    private DrawSurface d;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private int paddleSpeed;


    /**
     * Constructor.
     * @param rectangle - the rectangle of the paddle
     * @param keyboardSensor - keyboardSensor for paddle movement
     */
    public Paddle(Rectangle rectangle, KeyboardSensor keyboardSensor) {
        this.rectangle = rectangle;
        this.rectangle.setPaddle();
        this.color = rectangle.getColor();
        this.keyboardSensor = keyboardSensor;
    }


    /**
     * function that set paddle speed.
     * @param speed - the paddle speed.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }
    /**
     * The function set Gui and DrawSurface object.
     * @param guiObject - the gui object
     */
    public void setGuiAndDrawSurface(GUI guiObject) {
        this.gui = guiObject;
        this.d = guiObject.getDrawSurface();
    }

    /**
     * The function move the paddle to the left.
     */
    public void moveLeft() {
        //if paddle it at the far-most left position don't move it to the left
        if (this.rectangle.getUpperLeft().getX() -  THIRTY <= 0) {
            return;
        }
        //set the upper-left x point value of the paddle minus ten.
        Point updatetUpperLeft = new Point(this.rectangle.getUpperLeft().getX() - paddleSpeed,
                this.rectangle.getUpperLeft().getY());
        //create the new rectangle with the new upper-left value
        Rectangle rect = new Rectangle(updatetUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
        //set the new rectangle
        this.rectangle = rect;
        //set the rectangle to the paddle
        rect.setPaddle();
        //draw paddle to the screen
        drawOn(this.d);
    }
    /**
     * The function move the paddle to the right.
     */
    public void moveRight() {
        //if paddle it at the far-most right position don't move it to the left
        if (this.rectangle.getUpperRight().getX() + THIRTY >= MAXWIDTH) {
            return;
        }
        //set the upper-left x point value of the paddle plus ten.
        Point updatetUpperLeft = new Point(this.rectangle.getUpperLeft().getX() + paddleSpeed,
                this.rectangle.getUpperLeft().getY());
        //create the new rectangle with the new upper-left value
        Rectangle rect = new Rectangle(updatetUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
        //set the new rectangle
        this.rectangle = rect;
        //set the rectangle to the paddle
        rect.setPaddle();
        //draw paddle to the screen
        drawOn(this.d);
    }

    /**
     * The function return the collision rectangle.
     * @return
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * The function move the ball according to the ball hit degree with the paddle.
     * @param collisionPoint - the point value of the collision.
     * @param currentVelocity - the current velocity.
     * @return = new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        hitter.setClean(true);
        double collisionPointX = collisionPoint.getX();
        Velocity updatedVelocity = currentVelocity;
        Line lineUpper = this.rectangle.lineUpper();
        //calculating the absolute speed of the ball
        double absoluteSpeed = Math.sqrt(Math.pow(updatedVelocity.getDx(), 2) + Math.pow(updatedVelocity.getDy(), 2));
        //calculating ration value
        double ration = this.rectangle.lineUpper().length() / 5;

        //if the ball hit the first part of the paddle
        if ((collisionPointX >= lineUpper.start().getX()) && (collisionPointX <= lineUpper.start().getX() + ration)) {
            //set angle of the ball to 300
            updatedVelocity = Velocity.fromAngleAndSpeed(300, absoluteSpeed);
            //if the ball hit the second part of the paddle
        } else if ((collisionPointX >= lineUpper.start().getX() + ration)
                && (collisionPointX <= lineUpper.start().getX() + (2 * ration))) {
            //set angle of the ball to 330
            updatedVelocity = Velocity.fromAngleAndSpeed(330, absoluteSpeed);
            //if the ball hit the third part of the paddle
        } else if ((collisionPointX >= lineUpper.start().getX() + (2 * ration))
                && (collisionPointX <= lineUpper.start().getX() + (3 * ration))) {
            //set angle of the ball to 360
            updatedVelocity = Velocity.fromAngleAndSpeed(360, absoluteSpeed);
            //if the ball hit the forth part of the paddle
        } else if ((collisionPointX >= lineUpper.start().getX() + (3 * ration))
                && (collisionPointX <= lineUpper.start().getX() + (4 * ration))) {
            //set angle of the ball to 390
            updatedVelocity = Velocity.fromAngleAndSpeed(390, absoluteSpeed);
            //if the ball hit the edges
        } else {
            updatedVelocity.setDy(currentVelocity.getDy() * -1);
            updatedVelocity.setDx(currentVelocity.getDx() * -1);
        }
        //return the new velocity
        return updatedVelocity;
    }

    /**
     *The function return a list of collision points between the line and the paddle.
     * @param line -  the line to check with.
     * @return list of intersectionPoints
     */
    @Override
    public List<Point> intersectionPoints(Line line) {
        //creating new list
        List<Point> pointArrayList = new ArrayList<>();
        //if the line intersecting with the left side of the paddle
        if (line.isIntersecting(this.rectangle.lineLeft())) {
            //add points to the list using intersectionWith function
            pointArrayList.add(line.intersectionWith(this.rectangle.lineLeft()));
        }
        //if the line intersecting with the lower side of the paddle
        if (line.isIntersecting(this.rectangle.lineLower())) {
            //add points to the list using intersectionWith function
            pointArrayList.add(line.intersectionWith(this.rectangle.lineLower()));
        }
        //if the line intersecting with the right side of the paddle
        if (line.isIntersecting(this.rectangle.lineRight())) {
            //add points to the list using intersectionWith function
            pointArrayList.add(line.intersectionWith(this.rectangle.lineRight()));
        }
        //if the line intersecting with the upper side of the paddle
        if (line.isIntersecting(this.rectangle.lineUpper())) {
            //add points to the list using intersectionWith function
            pointArrayList.add(line.intersectionWith(this.rectangle.lineUpper()));
        }
        return pointArrayList;
    }

    /**
     * The function draw the paddle.
     * @param drawSurface - the DrawSurface object
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        //set color
        drawSurface.setColor(color);
        //draw the ball
        drawSurface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * The function check if the lest key or right key is pressed.
     */
    @Override
    public void timePassed() {
        //if left key pressed, call moveLeft function
        if (keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        //if left key pressed, call moveRight function
        if (keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * The function add the paddle to the game.
     * @param g - Game object
     */
    @Override
    public void addToGame(GameLevel g) {
        //add paddle to sprites list
        g.addSprite(this);
        //add paddle to collidables list
        g.addCollidable(this);
        //draw paddle
        drawOn(g.getEnvironment().getSurface());
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
