/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package screens;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * Key press decorator.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean doesStop;
     /**
     * constructor.
     * @param sensor keyboard
     * @param key string
     * @param animation animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.doesStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                this.doesStop = true;
                return;
            }
            this.isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.doesStop;
    }
}