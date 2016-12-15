package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Sine on 18-11-2016.
 */
public class AlphaWorldStrategy implements WorldStrategy {
    public int worldAging (Game game){
        int age = game.getAge();
        return age += 100;
    }
}
