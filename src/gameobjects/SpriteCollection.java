/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;

/**
 * The class contains ArrayList of Sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteList;
    /**
     * Constructor.
     * @param spriteList - new ArrayList of Sprites
     */
    public SpriteCollection(ArrayList<Sprite> spriteList) {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * The function add new Sprite to the list.
     * @param s - new Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * The function call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        //iterating through the list
        for (int i = 0; i < spriteList.size(); i++) {
            //calling timePassed function
            spriteList.get(i).timePassed();
        }
    }

    /**
     * remove sprite from game.
     * @param s - the4 sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * The function call drawOn(d) on all sprites.
     * @param d - DrawSurface object
     */
    public void drawAllOn(DrawSurface d) {
        //iterating through the list
        for (int i = 0; i < spriteList.size(); i++) {
            //calling timePassed drawOn function
            spriteList.get(i).drawOn(d);
        }
    }

}
