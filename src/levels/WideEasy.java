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
 * Wide easy level.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int max = 3;
        int min = 1;
        for (int i = 0; i < numberOfBalls(); i++) {
            int dx = (int) Math.floor(Math.random() * (max - min + 1) + min);
            int dy = (int) Math.floor(Math.random() * (max - min + 1) + min);
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
        return 350;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        rectangle.setColor(Color.CYAN);
        return new Block(rectangle);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int xFirstValue;
        int width = 50;
        int height = 30;
        for (int i = 0; i < 15; i++) {
            xFirstValue = 30 + i * width;
            Rectangle rectangle = new Rectangle(new Point(xFirstValue, 300), width, height);
            if (i < 2) {
                rectangle.setColor(Color.red);
            } else if (i < 4) {
                rectangle.setColor(Color.ORANGE);
            } else if (i < 6) {
                rectangle.setColor(Color.YELLOW);
            } else if (i < 9) {
                rectangle.setColor(Color.green);
            } else if (i < 11) {
                rectangle.setColor(Color.blue);
            } else if (i < 13) {
                rectangle.setColor(Color.pink);
            } else {
                rectangle.setColor(Color.CYAN);
            }
            Block block = new Block(rectangle);
            blockList.add(block);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int radios = 70;
        int pointVal = 150;
        d.setColor(Color.YELLOW);
        d.fillCircle(pointVal, pointVal, radios);
        for (int i = 0; i < radios; i++) {
            d.drawLine(pointVal, pointVal, i * 12, 600 / 2);
        }
    }

    @Override
    public Color ballColor() {
        return Color.gray;
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
        List<Point> pointsList = new ArrayList<Point>();
        double maxX = 300;
        double minX = 100;
        double y = 350;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            double x = Math.floor(Math.random() * (maxX - minX + 1) + minX);
            pointsList.add(new Point(x, y));
        }
        return pointsList;
    }
}
