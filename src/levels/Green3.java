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
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Green 3 level.
 */
public class Green3 implements LevelInformation {
    private int blocksNumber;
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
    public static List<Block> createBlocksRow(List<Block> blocks, int x, int y,
                                              int width, int height, Color color, int size) {
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
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        rectangle.setColor(Color.GREEN);
        return new Block(rectangle);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.white);
        colors.add(Color.blue);
        colors.add(Color.yellow);
        colors.add(Color.red);
        colors.add(Color.gray);
        int x = 770;
        int y = 250;
        int width = 50;
        int height = 30;
        for (int i = 6; i <= 10; i++) {
            createBlocksRow(blockList, x, y, width, height, colors.get(i - 6), i);
            y = y - height;
        }
        this.blocksNumber = blockList.size();
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksNumber;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(50, 400, 110, 200);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(60 + (j * 20), 410 + (i * 38), 10, 28);
            }
        }
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(90, 330, 30, 70);
        d.setColor(java.awt.Color.GRAY);
        d.fillRectangle(100, 160, 10, 170);
        d.setColor(java.awt.Color.yellow);
        d.fillCircle(105, 148, 12);
        d.setColor(java.awt.Color.RED);
        d.fillCircle(105, 148, 8);
        d.setColor(java.awt.Color.WHITE);
        d.fillCircle(105, 148, 4);
    }
    @Override
    public Color ballColor() {
        return Color.WHITE;
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
