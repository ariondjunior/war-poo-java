
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

    //Programa principal
    public static void main(String[] args) {

        //Código responsável por inserir a tela de Backround do jogo
        JFrame w = new JFrame("Janela principal");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        w.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Código resposável por adicionar e redimensionar a imagem conforme o tamanho da tela
        try {
            ImageIcon originalImage = new ImageIcon("war-poo-java/background.png");
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

        //Se a tela estiver minimizada, ele abre a "Janela Principal" novamente
        w.setVisible(true);

        //Instanciando objetos(item,bag,warrior,oráculo)
        Item item = new Item(1L, "Sword", true);
        Bag bag = new Bag(Arrays.asList(item));
        Warrior warrior1 = new Warrior("Arion", bag, "war-poo-java/guerreiro1.png");
        warrior1.setLife(warrior1.sortLife());
        System.out.println(warrior1.getLife());
        Oracle oracle = new Oracle("Destroier", warrior1);
        int initLife = warrior1.getLife();
        int jogo = 0;

        //Iniciando o jogo em um laço de repetição, forçando o jogo a ser jogado 2 vezes
        while (jogo < 2) {
            //Sorteio de vida do guerreiro
            warrior1.setLife(warrior1.sortLife());
            initLife = warrior1.getLife();
            
            //Chamando o método de prólogo de introdução na classe "Oracle"
            oracle.prologueIntroduction();

            //Laço que se repete enquanto a vida do guerreiro for != 0
            while (warrior1.getLife() != 0) {
                boolean nextLevel = oracle.loadLevel01();
                //Se o método .loadLevel01 retornar true, o jogo continua
                if (nextLevel) {
                    //Se o método .loadLevel02 retornar true, o jogo finaliza
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
                    //Se a vida do guerreiro for = 0, o jogo finaliza e o relatório é apresentado
                    oracle.loserPrologue();
                    oracle.RelatorioFimGame(initLife, jogo);
                    break;
                }
            }

            //Verifica se o jogo já foi jogado mais de uma vez, se for a primeira vez, o jogo se repete
            if (jogo != 1) {
                InOut.MsgDeAviso("Aviso",
                        "" + warrior1.getName() + ", nos mostre sua verdadeira força e jogue novamente!", "war-poo-java/oraculo.png");
            }
            //Se o jogo foi jogado apenas 1 vez, a variavel recebe +1
            jogo++;
        }

        // Para toda a música ao finalizar o jogo
        oracle.stopGameMusic();
    }

}
