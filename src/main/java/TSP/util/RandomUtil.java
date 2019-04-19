package TSP.util;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();
    public static int RandomInt(int bound){
        return random.nextInt(bound);
    }
}
