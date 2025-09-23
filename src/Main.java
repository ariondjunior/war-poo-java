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
        int initLife = warrior1.getLife();
        int jogo = 0 ;

        //while para executar 2 vezes :)
       while (jogo < 2) {
    // resetar vida antes de cada jogo
    warrior1.setLife(warrior1.sortLife());
    initLife = warrior1.getLife();

    while (warrior1.getLife() != 0) {
        boolean nextLevel = oracle.loadLevel01();
        if (nextLevel == true) {
            if (oracle.loadLevel02()) {
                oracle.RelatorioFimGame(initLife, jogo);
                oracle.winningPrologue();
                break; 

            } else {
                oracle.RelatorioFimGame(initLife, jogo);
                oracle.loserPrologue();
                break;
            }
        } else {
            oracle.loserPrologue();
            oracle.RelatorioFimGame(initLife, jogo);
            break;
        }
    }

    if (jogo != 1) {

        InOut.MsgDeAviso("Aviso", "" + warrior1.getName() + ", nos mostre sua verdadeira força e jogue novamente!");
    }

    jogo++;
}
    }
}




