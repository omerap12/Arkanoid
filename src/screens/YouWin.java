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
 * You win class.
 */
public class YouWin implements Animation {
    private boolean stop;
    private int score;
    private int currentHighScore;

    /**
     * constructor.
     * @param s of the level
     * @param cs as the current high score
     */
    public YouWin(int s, int cs) {
        this.stop = false;
        this.score = s;
        this.currentHighScore = cs;
    }
@Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        if (score < currentHighScore) {
            d.drawText(230, d.getHeight() / 2, "You Won! Your score is " + this.score, 32);
            d.drawText(300, d.getHeight() / 2 + 30, "High Score is: " + this.currentHighScore, 32);
        } else if (score == currentHighScore) {
            d.drawText(230, d.getHeight() / 2, "You Won! Your score is " + this.score, 32);
            d.drawText(170, d.getHeight() / 2 + 30, "Exactly as the previous High score: " + this.currentHighScore, 32);

        } else {
            d.drawText(300, d.getHeight() / 2,  "New Record!", 32);
            d.drawText(170, d.getHeight() / 2 + 30,  "Youre score is the new High Score: " + this.score, 32);
        }

    }
    @Override
    public boolean shouldStop() {
        return this.stop; }
}