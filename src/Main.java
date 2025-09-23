import java.util.Arrays;

import models.Bag;
import models.Item;
import models.Oracle;
import models.Warrior;

public class Main {
    public static void main(String[] args) {
        Item item = new Item(1L, "Sword", true);
        Bag bag = new Bag(Arrays.asList(item));
        Warrior warrior1 = new Warrior("Arion", bag);
        warrior1.setLife(warrior1.sortLife());
        System.out.println(warrior1.getLife());
        Oracle oracle = new Oracle("Terra", warrior1);


        while (warrior1.getLife() != 0) {
            boolean nextLevel = oracle.loadLevel01();

            if(nextLevel == true) {
                oracle.loadLevel02();
            } else {
                warrior1.setLife(warrior1.sortLife());
            }
        }
        
    }

}
