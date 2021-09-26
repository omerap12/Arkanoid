/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package interfaces;
import basicgeometry.Line;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import biuoop.DrawSurface;
import gameobjects.Ball;
import gameobjects.Velocity;

/**
 * The interface creates four function that being implemented in different classes.
 */
public interface Collidable {
    /**
     * function that return the rectangle object of the collision point.
     * @return rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * The function return new velocity.
     * @param collisionPoint - the point value of the collision.
     * @param currentVelocity - the current velocity.
     * @param hitter ball that hit
     * @return new velocity based on the force of the object.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * the fucntion return list of collision points between line and object.
     * @param line -  the line to check with.
     * @return list of points
     */
    java.util.List<Point> intersectionPoints(Line line);
    /**
     * The function draw the object on the surface.
     * @param surface - the surface object
     */
    void drawOn(DrawSurface surface);
}