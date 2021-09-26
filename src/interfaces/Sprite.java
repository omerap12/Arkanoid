//ID: 209493667
/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package interfaces;
import biuoop.DrawSurface;
import gameobjects.GameLevel;

import java.awt.Color;

/**
 * The interface creates three function that being implemented in different classes.
 */
public interface Sprite {
    /**
     * The function draw the object on the screen.
     * @param d - the DrawSurface object
     */

    /**
     * The function draw the sprite to the screen.
     * @param d - DrawSurface object.
     */
    void drawOn(DrawSurface d);

    /**
     * The function notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * The function add objcet to the game.
     * @param g  - game object.
     */
    void addToGame(GameLevel g);

    /**
     * The function return the Color of the sprite to the game.
     * @return - color.
     */
    Color getColor();
}