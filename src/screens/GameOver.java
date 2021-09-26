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
    private int currentHighScore;

    /**
     * constructor.
     * @param score of the game.
     * @param cs - current high score of the game.
     */
    public GameOver(int score, int cs) {
        this.stop = false;
        this.score = score;
        this.currentHighScore = cs;

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        if (this.currentHighScore < this.score) {
            d.drawText(220, d.getHeight() / 2, "You died! New High score is " + this.score, 32);
        } else{
            d.drawText(230, d.getHeight() / 2, "You died! Your score is " + this.score, 32);
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop; }
}
