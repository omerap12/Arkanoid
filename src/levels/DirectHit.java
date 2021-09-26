/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package levels;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.DirectHitListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Direct hit level.
 */
public class DirectHit implements LevelInformation {
    private boolean isSucess;

    /**
     * if was direct hit.
     */
    public DirectHit() {
        this.isSucess = false;
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        Velocity velocity = new Velocity(3, 2.25);
        list.add(velocity);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return  "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(new Point(30, 0), 770, 600);
        rectangle.setColor(Color.BLACK);
        return new Block(rectangle);
    }

    /**
     * @param drawSurface to draw on
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.yellow);
        drawSurface.drawCircle(370, 200, 50);
        drawSurface.setColor(Color.green);
        drawSurface.drawCircle(370, 200, 100);
        drawSurface.setColor(Color.BLUE);
        drawSurface.drawCircle(370, 200, 150);
        drawSurface.setColor(Color.pink);
        drawSurface.drawLine(220, 200, 520, 200);
        drawSurface.drawLine(370, 50, 370, 350);
    }

    @Override
    public Color ballColor() {
        return Color.WHITE;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Rectangle rectangle = new Rectangle(new Point(350, 185), 40, 40);
        rectangle.setColor(Color.red);
        Block block = new Block(rectangle);
        block.addHitListener(new DirectHitListener(this));
        blockList.add(block);
        return blockList;
    }

    /**
     * @param drawSurface to draw text
     */
    public void drawText(DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawText(355, 220, "◎", 35);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public boolean doesNeedBlockRemover() {
        return false;
    }

    @Override
    public boolean isSuccess() {
        return this.isSucess;
    }

    @Override
    public void setSuccess(boolean value) {
        this.isSucess = true;
    }

    @Override
    public List<Point> startingPointsOfBalls() {
        List<Point> pointsList = new ArrayList<Point>();
        double maxX = 200;
        double minX = 100;
        double maxY = 300;
        double minY = 200;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            double x = Math.floor(Math.random() * (maxX - minX + 1) + minX);
            double y = Math.floor(Math.random() * (maxY - minY + 1) + minY);
            pointsList.add(new Point(x, y));
        }
        return pointsList;
    }
}
