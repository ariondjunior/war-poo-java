import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import InOut.InOut;
import models.Bag;
import models.Item;
import models.Oracle;
import models.Warrior;

public class Main {

    private static Clip backgroundMusic;
    public static void main(String[] args) {

        JFrame w = new JFrame("Janela principal");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        w.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        w.setVisible(true);

        playBackgroundMusic("src/sounds/music.wav");

        // InOut.MsgDeAviso("qqq", "bbb", "pacman.png");

        Item item = new Item(1L, "Sword", true);
        Bag bag = new Bag(Arrays.asList(item));
        Warrior warrior1 = new Warrior("Arion", bag, "guerreiro1.png");
        warrior1.setLife(warrior1.sortLife());
        System.out.println(warrior1.getLife());
        Oracle oracle = new Oracle("Destroier", warrior1);
        int initLife = warrior1.getLife();
        int jogo = 0;

        // while para executar 2 vezes :)
        while (jogo < 2) {
            // resetar vida antes de cada jogo
            warrior1.setLife(warrior1.sortLife());
            initLife = warrior1.getLife();
            
            oracle.prologueIntroduction();

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

                InOut.MsgDeAviso("Aviso",
                        "" + warrior1.getName() + ", nos mostre sua verdadeira força e jogue novamente!", "oraculo.png");
            }

            jogo++;
        }

        stopBackgroundMusic();
    }

        private static void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop infinito
            backgroundMusic.start();
        } catch (Exception e) {
            System.err.println("Erro ao reproduzir música: " + e.getMessage());
        }
    }

    private static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }
}
