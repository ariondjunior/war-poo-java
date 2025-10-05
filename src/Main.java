package src;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import InOut.InOut;
import models.Bag;
import models.Item;
import models.Oracle;
import models.Warrior;

public class Main {

    public static void main(String[] args) {

        JFrame w = new JFrame("Janela principal");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        w.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());

        try {
            ImageIcon originalImage = new ImageIcon("background.png");
            // altera o tamnho da tela
            Image scaledImage = originalImage.getImage().getScaledInstance(
                    (int) screenSize.getWidth(),
                    (int) screenSize.getHeight(),
                    Image.SCALE_SMOOTH
            );
            ImageIcon backgroundImage = new ImageIcon(scaledImage);
            JLabel background = new JLabel(backgroundImage);
            w.setContentPane(background);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de fundo: " +  e.getMessage());
        }

        w.setVisible(true);

        Item item = new Item(1L, "Sword", true);
        Bag bag = new Bag(Arrays.asList(item));
        Warrior warrior1 = new Warrior("Arion", bag, "guerreiro1.png");
        warrior1.setLife(warrior1.sortLife());
        System.out.println(warrior1.getLife());
        Oracle oracle = new Oracle("Destroier", warrior1);
        int initLife = warrior1.getLife();
        int jogo = 0;

        while (jogo < 2) {
            warrior1.setLife(warrior1.sortLife());
            initLife = warrior1.getLife();
            
            oracle.prologueIntroduction();

            while (warrior1.getLife() != 0) {
                boolean nextLevel = oracle.loadLevel01();
                if (nextLevel) {
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
                InOut.MsgDeAviso("Aviso",
                        "" + warrior1.getName() + ", nos mostre sua verdadeira força e jogue novamente!", "oraculo.png");
            }

            jogo++;
        }

        // Para toda a música ao finalizar o jogo
        oracle.stopGameMusic();
    }

}
