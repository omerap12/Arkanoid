/**
 * @author Omer Aplatony <omerap12@gmail.com>
 * @version 1.0
 * @since 13.04.2021
 */
package gameobjects;
import basicgeometry.Point;

/**
 * velocity class.
 */
public class Velocity {
    /**
     * @param args - ball movement, move the ball by changing the x an y values of the ball center
     */
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx x value change rate
     * @param dy y value change rate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * function that change the x,y values of the center point of the ball.
     * @param p - center point of the ball
     * @return new point with the updated x,y values
     */
    public Point applyToPoint(Point p) {
        //add to x, y value dx and dy
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * function that return the x change rate value.
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * function that return the y change rate value.
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * function that convert angle and speed to dx and dy.
     * @param angle - convert to dx by using the formula sin(angle)*speed
     * @param speed - convert to dy by using the formula cos(angle)*speed
     * @return new velocity with the dx and dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -1 * Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * The function set dx.
     * @param newDx - dx value.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    /**
     * The function set dx.
     * @param newDy - dx value.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
}
