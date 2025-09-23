import java.util.Arrays;

import InOut.InOut;
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
                if(oracle.loadLevel02() == true){
                    oracle.winningPrologue();
                // Prologo de vitoria, com nomes, dano levado etc...    
                }else{
                // Prologo de derrota, com nomes, dano levado etc...  
                System.out.println("prologo de derrota");
                }
                break;
        }else{
            // Prologo de derrota, com nomes, dano levado etc... 
            System.out.println("Prologo de derrota");
        }      
        }
    }
}




