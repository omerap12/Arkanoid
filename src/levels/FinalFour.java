/**
 * @author Omer Aplatony <omerap12@gmail.com>.
 * @version 1.0
 * @since 14.06.2021
 */
package levels;
import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import basicgeometry.Point;
import basicgeometry.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;

/**
 * Final four level.
 */
public class FinalFour implements LevelInformation {
    private int blocks;

    /**
     * create block row.
     * @param blocks list
     * @param x point
     * @param y point
     * @param width of block
     * @param height of block
     * @param color of block
     * @param size number of blocks
     * @return list blocks
     */
    public static List<Block> createBlocksRow(List<Block> blocks, int x, int y, int width, int height,
                                              Color color, int size) {
        for (int i = 0; i < size; i++) {
            x = x - width;
            Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
            rectangle.setColor(color);
            blocks.add(new Block(rectangle));
        }
        return blocks;
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        double max = 3;
        double min = 1.5;
        for (int i = 0; i < numberOfBalls(); i++) {
            double dx = Math.floor(Math.random() * (max - min + 1) + min);
            double dy = Math.floor(Math.random() * (max - min + 1) + min);
            Velocity velocity = new Velocity(dx, dy);
            list.add(velocity);
        }
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
        return "Final four in Space";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        rectangle.setColor(Color.BLACK);
        return new Block(rectangle);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int x = 770;
        int y = 150;
        int width = 50;
        int height = 30;
        for (int i = 0; i < 4; i++) {
            createBlocksRow(blockList, x, y + i * height, width, height, Color.green, 15);
        }
        this.blocks = blockList.size();
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Random random = new Random();
        d.setColor(Color.white);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(750);
            int y = random.nextInt(750);
            d.fillCircle(x, y, 5);
        }
    }

    @Override
    public Color ballColor() {
        return Color.RED;
    }

    @Override
    public void drawText(DrawSurface d) {
        return;
    }

    @Override
    public boolean doesNeedBlockRemover() {
        return true;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public void setSuccess(boolean value) {
        return;
    }

    @Override
    public List<Point> startingPointsOfBalls() {
        List<Point> pointsList = new ArrayList<>();
        double maxX = 200;
        double minX = 100;
        double maxY = 400;
        double minY = 300;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            double x = Math.floor(Math.random() * (maxX - minX + 1) + minX);
            double y = Math.floor(Math.random() * (maxY - minY + 1) + minY);
            pointsList.add(new Point(x, y));
        }
        return pointsList;
    }
}
