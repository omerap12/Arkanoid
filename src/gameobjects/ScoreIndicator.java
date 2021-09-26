/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * score indicator to the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounters;
    private String levelName;
    private int lives;

    /**
     * constructor.
     * @param scoreCounters - counters.
     * @param levelName - name.
     * @param lives - balls.
     */
    public ScoreIndicator(Counter scoreCounters, String levelName, int lives) {
        this.scoreCounters = scoreCounters;
        this.levelName = levelName;
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        //set color
        surface.setColor(Color.WHITE);
        //draw the rectangle
        surface.fillRectangle(0, 0, 800, 20);
        surface.setColor(Color.WHITE);
        surface.setColor(Color.BLACK);
        surface.drawText(50, 15, "Starting balls: " + this.lives + "                        Score: "
                + this.scoreCounters.getValue() + "            " + "Level name: " + this.levelName, 20);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        drawOn(g.getEnvironment().getSurface());
    }

    @Override
    public Color getColor() {
        return null;
    }
}
