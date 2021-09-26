/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package screens;
import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;

/**
 * Game over class.
 */
public class GameOver implements Animation {
    private boolean stop;
    private int score;

    /**
     * constructor.
     * @param score of the game
     */
    public GameOver(int score) {
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        d.drawText(230, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop; }
}
