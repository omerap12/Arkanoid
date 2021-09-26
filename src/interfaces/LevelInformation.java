/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package interfaces;
import basicgeometry.Point;
import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.Velocity;
import java.awt.Color;
import java.util.List;


/**
 * Level information interface.
 */
public interface LevelInformation {
    /**
     * @return number of balls
     */
    int numberOfBalls();
    /**
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * @return paddle width
     */
    int paddleWidth();
    /**
     * @return the level name
     */
    String levelName();
    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();
    /**
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * @param d to draw on the background
     */
    void drawOn(DrawSurface d);

    /**
     * @return ball color
     */
    Color ballColor();

    /**
     * @param d to draw the text
     */
    void drawText(DrawSurface d);

    /**
     * @return if need block remover
     */
    boolean doesNeedBlockRemover();

    /**
     * @return if is success
     */
    boolean isSuccess();

    /**
     * @param value set success
     */
    void setSuccess(boolean value);

    /**
     * @return starting point of balls
     */
    List<Point> startingPointsOfBalls();
}