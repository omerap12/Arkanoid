/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package screens;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.SpriteCollection;
import interfaces.Animation;
import interfaces.Sprite;
import java.awt.Color;

/**
 * Countdown animation class.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private final SpriteCollection gameScreen;
    private final Sprite background;

    /**
     * constructor.
     * @param numOfSeconds number of seconds
     * @param countFrom when start the count
     * @param gameScreen game screen
     * @param background background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.stop = false;
        this.gameScreen = gameScreen;
        this.background = background;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        if (background.getColor().equals(Color.BLACK)) {
            d.setColor(Color.white);
        } else {
            d.setColor(Color.black);
        }
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + this.countFrom + "", 32);
        if (countFrom != 3) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = ((long) numOfSeconds - usedTime);
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.countFrom = countFrom - 1;
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
