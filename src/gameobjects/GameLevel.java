/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 22.4.2021
 */
package gameobjects;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.EdgesHit;
import listeners.ScoreTrackingListener;
import screens.AnimationRunner;
import screens.CountdownAnimation;
import screens.KeyPressStoppableAnimation;
import screens.PauseScreen;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The class stores all game objects, the objects can communicate with each other.
 */
public class GameLevel implements Animation {
    static final int THIRTY = 30;
    static final int MAX_WIDTH = 800;
    static final int MAX_HEIGHT = 600;
    static final int START_BALL_X  = 225;
    static final int START_BALL_Y  = 300;
    static final int HUNDRED = 100;
    /**
     * function that create random color.
     * @param rand - random object.
     * @return new color
     */
    public static Color createRandomColor(Random rand) {
        //create three random floating numbers
        float a = rand.nextFloat();
        float b = rand.nextFloat();
        float c = rand.nextFloat();
        //return the new color
        return new Color(a, b, c);
    }

    /**
     * create ball.
     * @param x point
     * @param y point
     * @param color of the ball
     * @param velocity of the ball
     * @param env of the ball
     * @param gameLevel to ball
     */
    public void createBall(int x, int y, Color color, Velocity velocity, GameEnvironment env, GameLevel gameLevel) {
        Ball ball = new Ball(new Point(x, y), 5, color);
        ball.setVelocity(velocity.getDx(), velocity.getDy());
        ball.setGameEnvironment(env);
        ball.addToGame(gameLevel);
    }


    /**
     * The function create an array list of rectangles, for drawing a line of rectangles to the screen.
     * @param location - the x location of the rectangles line.
     * @param numberOfRectangles - number of rectangles per line.
     * @param height - the height of each rectangle.
     * @param width - the width of each rectangle.
     * @param color - the color of each rectangle.
     * @return - array list of rectangle objects.
     */

    public static ArrayList<Rectangle> rectanglesLine(double location, int numberOfRectangles, int height, int width,
                                                      Color color) {
        //creating new array list
        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

        //creating rectangles
        for (int i = 0; i < numberOfRectangles; i++) {
            //create rectangle
            Rectangle rectangle = new Rectangle(new Point(MAX_WIDTH - THIRTY - ((width) * (i + 1)), location), width,
                    height);
            //set color
            rectangle.setColor(color);
            //add to the list
            rectangles.add(rectangle);
        }
        return rectangles;
    }

    /**
     * function that creates single rectangle and add it to the blocks list.
     * @param blocks - list of game blocks.
     * @param x - x value of the upper-left point of the rectangle.
     * @param y - y value of the upper-left point of the rectangle.
     * @param width - width value of the rectangle.
     * @param height - height value of the rectangle.
     * @param color - the color of the rectangle.
     * @param listener - the listener hit
     */
    public void createReactangle(List<Block> blocks, int x, int y, int width, int height, Color color,
                                 HitListener listener) {
        //create new rectangle
        Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
        //set color
        rectangle.setColor(color);
        //create new Block using the rectangle
        Block block = new Block(rectangle);
        if (listener != null) {
            block.addHitListener(listener);
        }
        //add the block to the list
        blocks.add(block);
    }
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private boolean isDead;

    /**
     * Constructor.
     * @param levelInformation level of the game
     * @param gui gui
     * @param runner animation runner
     * @param score of the level
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, AnimationRunner runner, Counter score) {
        this.gui = gui;
        this.sprites = new SpriteCollection(new ArrayList<>());
        this.environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(levelInformation.numberOfBalls());
        this.running = true;
        this.runner  = runner;
        this.score = score;
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.isDead = false;
    }


    /**
     * function that add Collidable to the list of Collidables.
     * @param c - the new Collidable.
     */


    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param c collidable to remove
     */
    void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * @param s sprite remove
     */
    void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }



    /**
     * funtion that add sprite object to the sprites list.
     * @param s - the new object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * function that initialize the game - create the Blocks and Ball (and Paddle) and add them to the game.
     */

    public void initialize() {
        //creating new gui object
        DrawSurface d = this.gui.getDrawSurface();
        // set environment surface and keyboard sensor
        this.environment.setSurface(d);
        this.environment.setKeyboardSensor(this.gui.getKeyboardSensor());
        //calling auxiliary to set gui
        setGui(this.gui);
        //creating random object
        Random rand = new Random();
        //initialize the score of the game
        //creating score-tracking-listener
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);

        //creating balls
        List<Velocity> ballsVelocity = this.levelInformation.initialBallVelocities();
        Color ballColor = this.levelInformation.ballColor();
        int numberOfBalls = this.levelInformation.numberOfBalls();
        List<Point> pointListStartBalls = this.levelInformation.startingPointsOfBalls();
        for (int i = 0; i < numberOfBalls; i++) {
            Point startPoint = pointListStartBalls.get(i);
            createBall((int) startPoint.getX(), (int) startPoint.getY(), ballColor, ballsVelocity.get(i),
                    this.environment, this);
        }
        //set values
        int height = 30;;
        //creating list of blocks
        BlockRemover blockRemover = new BlockRemover(this);

        List<Block> blocks = this.levelInformation.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            if (this.levelInformation.doesNeedBlockRemover()) {
                blocks.get(i).addHitListener(blockRemover);
            }
            blocks.get(i).addHitListener(scoreListener);
        }
        //setting block counter size
        this.blockCounter.setCount(blocks.size());

        //using auxiliary function to create the sides
        BallRemover ballRemover = new BallRemover(this);
        //creating Death block
        createReactangle(blocks, height + 1, MAX_HEIGHT - height + 30, MAX_WIDTH - (height * 2) - 2,
                height, Color.red, ballRemover);

        //side blocks
        EdgesHit edgesHit = new EdgesHit();
        createReactangle(blocks, 0, height, height, MAX_WIDTH - height, Color.gray, edgesHit);
        createReactangle(blocks, 0, 0, MAX_WIDTH, height + 20, Color.gray, edgesHit);
        createReactangle(blocks, MAX_WIDTH - 30, height + 20, height, MAX_WIDTH - height, Color.gray, edgesHit);

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).addToGame(this);
        }

        //crating the the paddle
        //creating rectangle object
        Rectangle rectangle = new Rectangle(new Point(HUNDRED, MAX_HEIGHT - 2 * (height)),
                this.levelInformation.paddleWidth(), height);
        //set yellow color
        rectangle.setColor(Color.YELLOW);
        //create paddle object
        Paddle paddle = new Paddle(rectangle, this.gui.getKeyboardSensor());
        paddle.setPaddleSpeed(this.levelInformation.paddleSpeed());
        //set gui to the paddle using auxiliary function
        paddle.setGuiAndDrawSurface(this.gui);
        //add paddle to the game
        paddle.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, this.levelInformation.levelName(),
                ballCounter.getValue());
        sprites.addSprite(scoreIndicator);
    }

    /**
     * function that set gui.
     * @param guiObject - gui object
     */
    public void setGui(GUI guiObject) {
        this.gui = guiObject;
    }

    /**
     * function that return the game environment object.
     * @return environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @return block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * @return ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw background
        this.levelInformation.getBackground().drawOn(d);
        this.levelInformation.drawOn(d);
        //if user pressed p
        if (this.gui.getKeyboardSensor().isPressed("p") || this.gui.getKeyboardSensor().isPressed("P")) {
            //stop animation
            this.runner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(),
                    "space", new PauseScreen()));
        }
        this.environment.setSurface(d);
        //draw all sprites
        this.sprites.drawAllOn(this.environment.getSurface());
        this.levelInformation.drawText(d);
        this.sprites.notifyAllTimePassed();
        //if user lost the game
        if (this.ballCounter.getValue() == 0) {
            this.isDead = true;
            this.running = false;
        }

        //if user manage to win the game
        if (this.blockCounter.getValue() == 0 || this.levelInformation.isSuccess()) {
            this.running = false;
        }
    }

    /**
     * @return if is dead
     */
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * run the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(0.8, 3, sprites, this.levelInformation.getBackground()));
        this.runner.run(this);
    }
}