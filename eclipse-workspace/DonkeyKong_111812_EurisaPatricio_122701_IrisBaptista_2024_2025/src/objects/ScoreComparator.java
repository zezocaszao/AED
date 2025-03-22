package objects;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score s1, Score s2) {
        return Integer.compare(s2.getScore(), s1.getScore());
    }
}
