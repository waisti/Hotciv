package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Sine on 18-11-2016.
 */
public class BetaWorldStrategy implements WorldStrategy {
    public int worldAging(Game game){
        int age = game.getAge();
        if (age < -100){
            age += 100;
        }
        else if (age == -100){
            age = -1;
        }
        else if (age == -1){
            age = 1;
        }
        else if (age == 1){
            age = 50;
        }

        else if (age >= 50 && age < 1750){
            age += 50;
        }

        else if (age >= 1750 && age < 1900){
            age += 25;
        }

        else if (age >= 1900 && age < 1970){
            age += 5;
        }

        else if (age >= 1970){
            age += 1;
        }
        return age;
    }
}
