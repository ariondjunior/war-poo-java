package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import InOut.InOut;
import Music.Music;
/**A classe "Oracle.java" é responsável por apresentar e dar as intruções básicas do jogo.
 * O objeto deve ser instanciado no inicio do progama recebendo o nome do oráculo junto ao nome do gurreiro */

public class Oracle {
    private String name;
    private Warrior warrior;
    private Music music;

    //Construtor
    public Oracle(String name, Warrior warrior) {
        this.name = name;
        this.warrior = warrior;
        this.music = new Music();
    }

    //Prológo de introdução
    public String prologueIntroduction() {
        // Inicia a música de fundo do jogo
        music.playBackgroundMusic("war-poo-java/src/sounds/music.wav");
        
        InOut.MsgDeInformacao("Introdução do jogo", "O jogo se passa em uma época de guerra. Aqui possui um guerreiro chamado "
                + warrior.getName() + " ele possui " + warrior.getLife() + " pontos de vida.\nSeu maior desafio é passar de dois desafios que serão propostos pelo Oráculo. Se vencer salva o mundo, agora\nse perder a humanidade será destruida para SEMPRE. \n \n Para avança no jogo aperte 'OK'", warrior.getStringImage());
        return "";
    }
    //Prológo de derrota
    public String loserPrologue() {
        InOut.MsgDeAviso("O " + warrior.getName() +", perdeu!", "Não foi dessa vez, tente novamente!", "war-poo-java/oraculo.png");
        return "";
    }

    //Prológo de vitória
    public String winningPrologue() {
        // Para a música ao vencer
        music.stopBackgroundMusic();
        InOut.MsgDeAviso("O " + warrior.getName() +", venceu", "Parabéns pela sua conquista, você foi incrível!", "war-poo-java/oraculo.png");
        return "";
    }

    /**
     * Para toda a música do jogo
     */
    public void stopGameMusic() {
        music.stopAllSounds();
    }

    //lista para auxiliar no relatorio final
    List<Integer> chutes = new ArrayList<>();

    //Método que carrega o level 2
    public boolean loadLevel01() {
        //Variáveis 
        int max = 100;
        int min = 1;
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
        int chute = 0;
        boolean loop = false; 

        //Apresentação do Oráculo, junto ao objetivo do jogo
        InOut.MsgDeInformacao("Desafio",
                "Olá, sou o Oráculo! Meu nome é " + this.getName() + ". A partir de agora você enfrentarar 2 grandes desafios para salvar a humanidade, BOA SORTE! \n\n [DESAFIO 1]:\nUm número foi sorteado, você deve adivinha-lo. Ele está dentro do intervalo [1, 100]\n\n", "war-poo-java/oraculo.png");

        System.out.println(numeroAleatorio);

        //Conferindo os chutes do usário com o número sorteado
        while (chute != numeroAleatorio && warrior.getLife() != 0) {
            chute = InOut.leInt("Qual é o número?", "war-poo-java/oraculo.png");
            chutes.add(chute);

            if (chute == numeroAleatorio) {
                InOut.MsgDeInformacao("Parabéns", "Você acertou o número secreto, ele era " + numeroAleatorio, "war-poo-java/oraculo.png");

                InOut.MsgDeAviso("Próximo desafio", "Agora você irá para a próxima fase.", "war-poo-java/oraculo.png");
                return true;
            }
            if (chute < numeroAleatorio) {
                warrior.setDamage(1);
                if (warrior.getLife() != 0) {
                    
                    InOut.MsgDeAviso("Aviso",
                            "O número secreto é maior que o chute. Você ainda possui " + warrior.getLife() + " vidas.", "war-poo-java/oraculo.png");
                }
            }
            if (chute > numeroAleatorio) {
                warrior.setDamage(1);
                if (warrior.getLife() != 0) {
                    InOut.MsgDeAviso("Aviso",
                            "O número secreto é menor que o chute. Você ainda possui " + warrior.getLife() + " vidas.", "war-poo-java/oraculo.png");
                }

            }
            if(warrior.getLife() == 0 && loop == false){
                loop = true;
                //Chama o metodo decideExtraLife()dentro do if e verifica se te 5 palavras retornando true or false
                String pedido = InOut.leString("Pedido de misericórdia: ", "war-poo-java/oraculo.png");
                
                if(decideExtraLife(pedido) == true){
                    InOut.MsgDeAviso("Vida extra", "Você recebeu vida extra!", "war-poo-java/oraculo.png");
                    warrior.extraLife();
                }else{
                    // Toca música de terror pausando a música de fundo
                    music.playEffectSoundWithPause("war-poo-java/src/sounds/terror.wav");
                    
                    // Mostra imagem assustadora com som de terror
                    InOut.MsgDeAviso("HAHAHAHHAHA", "", "war-poo-java/download.gif");
                    
                    InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!", "war-poo-java/oraculo.png");
                    return false;
                }
            } 
        }
        return false;
    }


        //CHARADAS MANEIRAS
        List<String> charadas = Arrays.asList(
            "O que é, o que é: Tem cabeça e tem dente, mas não é bicho nem gente?",
          "O que é, o que é: Tem boca, mas não fala; tem leito, mas não dorme; corre, mas não anda?",
          "O que é, o que é: Quatro patas de manhã, duas ao meio-dia e três à noite?"
          );
          //Respostas das charadas com indíces correspondentes
        List<String> respostas = Arrays.asList("Alho","Rio","Humano");
        int randomIndex = (int) (Math.random() * 3);

    //lista para auxiliar no relatorio final
    List<String> palpites = new ArrayList<>();

    // Método que carrega o level 2
    public boolean loadLevel02() {
        System.out.println(respostas.get(randomIndex));
        String chute2 = "";
        boolean acerto = false;
        boolean loop = false;

        //verificacao de acerto e vida do guerreiro para continuar no loop
        while(acerto == false && warrior.getLife() != 0) {
            chute2 = InOut.leString(charadas.get(randomIndex), "war-poo-java/oraculo.png");
            palpites.add(chute2);

            System.out.println(chute2);
            if(chute2.equalsIgnoreCase(respostas.get(randomIndex))) {
                return true;            
            }else{
                warrior.setDamage(1);
                if(warrior.getLife() > 0){
                InOut.MsgDeAviso("Errou!",
                           charadas.get(randomIndex) + ", Você ainda possui " + warrior.getLife() + " vidas!", "war-poo-java/oraculo.png");
                } else if(loop == false) {
                    loop = true;
                    //Chama o metodo decideExtraLife()dentro do if e verifica se te 5 palavras retornando true or false
                    String pedido = InOut.leString("Pedido de misericórdia: ", "war-poo-java/oraculo.png");
                    
                    if(decideExtraLife(pedido) == true){
                        warrior.extraLife();
                    }else{
                        // Toca música de terror pausando a música de fundo
                        music.playEffectSoundWithPause("war-poo-java/src/sounds/terror.wav");
                        
                        // Mostra imagem assustadora com som de terror
                        InOut.MsgDeAviso("HAHAHAHHAHA", "", "war-poo-java/download.gif");

                        InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!", "war-poo-java/oraculo.png");
                        return false;
                    }
                }
            }
        }

    //Mesnagem de aviso caso o usuário não tenha capacidade de completar a fase
    InOut.MsgDeAviso("Não foi dessa vez", "Você perdeu, tente novamente!", "war-poo-java/oraculo.png");
    return false;
    } 



    //Relatório do final do jogo conténdo as vidas perdidas, palpites do número secreto e charada junto à respectiva resposta de cada
    public void RelatorioFimGame(int vidaInicial, int repGame) {
    String relatorio = String.format(
        "Vidas Perdidas: %d\n" +
        "----------------LEVEL 1----------------\n" +
        "Número: %d\n" +
        "Palpites: %s\n" +
        "----------------LEVEL 2----------------\n" +
        "Charada: %s\n" +
        "Palpites: %s",
        (vidaInicial - warrior.getLife()),
        chutes.get(chutes.size() - 1),
        chutes.toString(),
        respostas.get(randomIndex),
        palpites.toString()
    );
    //Relatório contendo o número respectivo do jogo 
    InOut.MsgDeInformacao("Relatório " + (repGame + 1), relatorio, "war-poo-java/oraculo.png");
    
    // Resetando os valores para o segundo relatório 
    palpites.clear();
    chutes.clear();
    randomIndex = (int) (Math.random() * 3);
}
    
    //Método que calcula a quantidade de palavras para validar a vida extra
    public boolean decideExtraLife(String frase) {
        String[] palavras = frase.trim().split("\\s+");
        return palavras.length >= 5;
    }

    //GETTERS E SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public void setWarrior(Warrior warrior) {
        this.warrior = warrior;
    }


}
