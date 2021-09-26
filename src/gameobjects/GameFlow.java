/**
 * @author Omer Aplatony <omerap12@gmail.com>
 * @version 1.0
 * @since 13.06.2021
 */
package gameobjects;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import screens.AnimationRunner;
import screens.GameOver;
import screens.KeyPressStoppableAnimation;
import screens.YouWin;

import java.util.List;

/**
 * Game flow class - manage levels in game.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner animationRunner;
    private Counter score;
    private boolean isWin;
    private HighScoreUtility highScoreUtility;
    private KeyboardSensor keyboardSensor;

    /**
     * constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Game", 800, 600);
        this.animationRunner = new AnimationRunner(this.gui);
        this.score = new Counter(0);
        this.isWin = true;
        this.highScoreUtility = new HighScoreUtility(System.getProperty("user.dir"));
        this.keyboardSensor = this.gui.getKeyboardSensor();


    }

    /**
     * @param levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        highScoreUtility.setHighScore();
        int currentHighScore = highScoreUtility.getHighScore();
        for (LevelInformation levelInfo : levels) {
            GameLevel gameLevel = new GameLevel(levelInfo, this.gui, this.animationRunner, score);
            gameLevel.initialize();

            while (!gameLevel.shouldStop()) {
                gameLevel.run();
            }
            if (gameLevel.isDead()) {
                this.isWin = false;
                break;
            } else {
                this.score.increase(100);
            }
        }
        KeyPressStoppableAnimation endScreen;
        if (!this.isWin) {
            endScreen = new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space",
                     new GameOver(this.score.getValue()));
        } else {
            endScreen = new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space",
                     new YouWin(this.score.getValue(), currentHighScore));

        }
        this.animationRunner.run(endScreen);
        if (this.score.getValue() > currentHighScore) {
            this.highScoreUtility.writeHighScore(this.score.getValue());
        }
        gui.close();
    }

}
