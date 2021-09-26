package gameobjects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Omer Aplatony <omerap12@gmail.com>
 * @version 1.0
 * @since 14.06.2021
 * */
public class HighScoreUtility {
    private String path;
    private String fileName;
    private int highScore;

    /**
     * The class reads and writes the high score of the game.
     */

    /**
     * constructor.
     * @param aPath - the path of the txt file.
     */
    public HighScoreUtility(String aPath) {
        this.path = aPath;
        fileName = "highscores.txt";
    }

    /**
     * set the current high score.
     */
    public void setHighScore() {
        //trying reading from the existing file
        try {
            File highScoreFile = new File(fileName);
            Scanner myReader = new Scanner(highScoreFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                highScore = scoreFromLineGetter(data);
            }
            myReader.close();
        // if file not found, create one
        } catch (FileNotFoundException e) {
            File file = new File(path + "\\" + fileName);
            try {
                file.createNewFile();
                highScore = 0;
            //error catching
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
   }

    /**
     * The function writes the high score to the file.
     * @param userScore - user score.
     */
   public void writeHighScore(int userScore) {
        if (userScore > highScore) {
            try {
                FileWriter fileWriter = new FileWriter(path + "\\" + fileName);
                fileWriter.write("The highest score so far is: " + Integer.toString(userScore));
                System.out.println(userScore);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }

    /**
     * String manipulation - get the score number.
     * @param line - the line of the text file.
     * @return - the score number.
     */
    public int scoreFromLineGetter(String line) {
        String[] seg =  line.split(":");
        return Integer.parseInt(seg[1].replaceAll("\\s", ""));
    }

    /**
     * getter.
     * @return - high score number.
     */
    public int getHighScore() {
        return this.highScore;
    }
}
