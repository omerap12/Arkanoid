import gameobjects.GameFlow;
import interfaces.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.WideEasy;
import levels.Green3;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Aplatony <omerap12@gmail.com>
 * @version 1.0
 * @since 14.06.2021
 * */

public class Ass6Game {
    /** the main method.
     * @param args - Game levels.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        List<LevelInformation> levels2 = new ArrayList<>();
        List<Integer> levelsNumbers = new ArrayList<>();
        GameFlow gameFlow = new GameFlow();

        //check if every argument is a legal and enters to the levels array.
        for (String s : args) {
            try {
                if (Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 4) {
                    //for the first level
                    if (Integer.parseInt(s) == 1) {
                        levels2.add(new DirectHit());
                        levelsNumbers.add(Integer.parseInt(s));
                    }
                    //for the second level
                    if (Integer.parseInt(s) == 2) {
                        levels2.add(new WideEasy());
                        levelsNumbers.add(Integer.parseInt(s));
                    }
                    //for the third level
                    if (Integer.parseInt(s) == 3) {
                        levels2.add(new Green3());
                        levelsNumbers.add(Integer.parseInt(s));
                    }
                    //for the fourth level
                    if (Integer.parseInt(s) == 4) {
                        levels2.add(new FinalFour());
                        levelsNumbers.add(Integer.parseInt(s));
                    }
                }
            } catch (Exception ignored) {
            }
        }
        //if there are no arguments accepted.
        if (levels2.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
            gameFlow.runLevels(levels);
        } else {
            gameFlow.runLevels(levels2);
        }
    }
}
