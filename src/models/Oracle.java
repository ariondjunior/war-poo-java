package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import InOut.InOut;
import Music.Music;

/** 
 * A classe <i>Oracle</i> é o cérebro do jogo, responsável por gerenciar toda a lógica
 * de gameplay, desafios e interações com o jogador. Esta classe controla o fluxo
 * do jogo desde a introdução até a conclusão, incluindo os dois níveis principais.
 * <ul>
 * <li>Gerencia a narrativa do jogo através de prólogos de introdução, vitória e derrota
 * <li>Controla o sistema de música de fundo e efeitos sonoros durante o jogo
 * <li>Implementa o Nível 1: jogo de adivinhação numérica com sistema de vidas
 * <li>Implementa o Nível 2: jogo de charadas com múltiplas opções
 * <li>Sistema de pedido de misericórdia com critério de 5 palavras mínimas
 * <li>Geração de relatórios detalhados do desempenho do jogador
 * <li>Integração completa com as classes Warrior e Music para experiência imersiva
 * </ul>  
 */
/**A classe "Oracle.java" é responsável por apresentar e dar as intruções básicas do jogo.
 * O objeto deve ser instanciado no inicio do progama recebendo o nome do oráculo junto ao nome do gurreiro */

public class Oracle {
    private String name;
    private Warrior warrior;
    private Music music;

    /**
     * Construtor da classe Oracle.
     * Inicializa o Oráculo com um nome e associa um guerreiro ao jogo.
     * Também inicializa o sistema de música para controle de áudio durante o jogo.
     * 
     * @param name nome do Oráculo que conduzirá os desafios
     * @param warrior objeto Warrior representando o guerreiro que enfrentará os desafios
     */
    //Construtor
    public Oracle(String name, Warrior warrior) {
        this.name = name;
        this.warrior = warrior;
        this.music = new Music();
    }

    /**
     * Apresenta a introdução narrativa do jogo.
     * Inicia a música de fundo e exibe uma janela informativa com a história
     * do jogo, apresentando o guerreiro e explicando os desafios que ele enfrentará.
     * 
     * @return String vazia (mantido por compatibilidade)
     */
    //Prológo de introdução
    public String prologueIntroduction() {
        // Inicia a música de fundo do jogo
        music.playBackgroundMusic("src/sounds/music.wav");
        
        InOut.MsgDeInformacao("Introdução do jogo", "O jogo se passa em uma época de guerra. Aqui possui um guerreiro chamado "
                + warrior.getName() + " ele possui " + warrior.getLife() + " pontos de vida.\nSeu maior desafio é passar de dois desafios que serão propostos pelo Oráculo. Se vencer salva o mundo, agora\nse perder a humanidade será destruida para SEMPRE. \n \n Para avança no jogo aperte 'OK'", warrior.getStringImage());
        return "";
    }
    
    /**
     * Apresenta o prólogo de derrota do guerreiro.
     * Exibe uma mensagem de consolação quando o guerreiro falha nos desafios.
     * 
     * @return String vazia (mantido por compatibilidade)
     */
    //Prológo de derrota
    public String loserPrologue() {
        InOut.MsgDeAviso("O " + warrior.getName() +", perdeu!", "Não foi dessa vez, tente novamente!", "oraculo.png");
        return "";
    }

    /**
     * Apresenta o prólogo de vitória do guerreiro.
     * Para a música de fundo e exibe uma mensagem de parabéns pela conquista.
     * 
     * @return String vazia (mantido por compatibilidade)
     */
    //Prológo de vitória
    public String winningPrologue() {
        // Para a música ao vencer
        music.stopBackgroundMusic();
        InOut.MsgDeAviso("O " + warrior.getName() +", venceu", "Parabéns pela sua conquista, você foi incrível!", "oraculo.png");
        return "";
    }

    /**
     * Para toda a música do jogo.
     * Interrompe tanto a música de fundo quanto os efeitos sonoros ativos.
     */
    public void stopGameMusic() {
        music.stopAllSounds();
    }

    //lista para auxiliar no relatorio final
    List<Integer> chutes = new ArrayList<>();

    /**
     * Executa o primeiro nível do jogo: adivinhação numérica.
     * O jogador deve adivinhar um número aleatório entre 1 e 100. A cada erro,
     * perde 1 ponto de vida. Se a vida chegar a 0, pode fazer um pedido de misericórdia.
     * Se o pedido tiver 5 ou mais palavras, ganha uma vida extra.
     * 
     * @return boolean true se passou de nível, false se perdeu o jogo
     */
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
                "Olá, sou o Oráculo! Meu nome é " + this.getName() + ". A partir de agora você enfrentarar 2 grandes desafios para salvar a humanidade, BOA SORTE! \n\n [DESAFIO 1]:\nUm número foi sorteado, você deve adivinha-lo. Ele está dentro do intervalo [1, 100]\n\n", "oraculo.png");

        System.out.println(numeroAleatorio);

        //Conferindo os chutes do usário com o número sorteado
        while (chute != numeroAleatorio && warrior.getLife() != 0) {
            chute = InOut.leInt("Qual é o número?", "oraculo.png");
            chutes.add(chute);

            if (chute == numeroAleatorio) {
                InOut.MsgDeInformacao("Parabéns", "Você acertou o número secreto, ele era " + numeroAleatorio, "oraculo.png");

                InOut.MsgDeAviso("Próximo desafio", "Agora você irá para a próxima fase.", "oraculo.png");
                return true;
            }
            if (chute < numeroAleatorio) {
                warrior.setDamage(1);
                if (warrior.getLife() != 0) {
                    
                    InOut.MsgDeAviso("Aviso",
                            "O número secreto é maior que o chute. Você ainda possui " + warrior.getLife() + " vidas.", "oraculo.png");
                }
            }
            if (chute > numeroAleatorio) {
                warrior.setDamage(1);
                if (warrior.getLife() != 0) {
                    InOut.MsgDeAviso("Aviso",
                            "O número secreto é menor que o chute. Você ainda possui " + warrior.getLife() + " vidas.", "oraculo.png");
                }

            }
            if(warrior.getLife() == 0 && loop == false){
                loop = true;
                //Chama o metodo decideExtraLife()dentro do if e verifica se te 5 palavras retornando true or false
                String pedido = InOut.leString("Pedido de misericórdia: ", "oraculo.png");
                
                if(decideExtraLife(pedido) == true){
                    InOut.MsgDeAviso("Vida extra", "Você recebeu vida extra!", "oraculo.png");
                    warrior.extraLife();
                }else{
                    // Toca música de terror pausando a música de fundo
                    music.playEffectSoundWithPause("src/sounds/terror.wav");
                    
                    // Mostra imagem assustadora com som de terror
                    InOut.MsgDeAviso("HAHAHAHHAHA", "", "download.gif");
                    
                    InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!", "oraculo.png");
                    return false;
                }
            } 
        }
        return false;
    }


        //CHARADAS MANEIRAS
        /** Lista de charadas disponíveis para o segundo nível */
        List<String> charadas = Arrays.asList(
            "O que é, o que é: Tem cabeça e tem dente, mas não é bicho nem gente?",
          "O que é, o que é: Tem boca, mas não fala; tem leito, mas não dorme; corre, mas não anda?",
          "O que é, o que é: Quatro patas de manhã, duas ao meio-dia e três à noite?"
          );
          //Respostas das charadas com indíces correspondentes
        /** Lista de respostas correspondentes às charadas */
        List<String> respostas = Arrays.asList("Alho","Rio","Humano");
        /** Índice aleatório para selecionar charada e resposta */
        int randomIndex = (int) (Math.random() * 3);

    //lista para auxiliar no relatorio final
    /** Lista para armazenar palpites do segundo nível para relatório */
    List<String> palpites = new ArrayList<>();

    /**
     * Executa o segundo nível do jogo: charadas.
     * O jogador deve responder corretamente uma charada selecionada aleatoriamente.
     * A cada erro, perde 1 ponto de vida. Se a vida chegar a 0, pode fazer um
     * pedido de misericórdia com sistema de música de terror.
     * 
     * @return boolean true se passou de nível, false se perdeu o jogo
     */
    // Método que carrega o level 2
    public boolean loadLevel02() {
        System.out.println(respostas.get(randomIndex));
        String chute2 = "";
        boolean acerto = false;
        boolean loop = false;

        //verificacao de acerto e vida do guerreiro para continuar no loop
        while(acerto == false && warrior.getLife() != 0) {
            chute2 = InOut.leString(charadas.get(randomIndex), "oraculo.png");
            palpites.add(chute2);

            System.out.println(chute2);
            if(chute2.equalsIgnoreCase(respostas.get(randomIndex))) {
                return true;            
            }else{
                warrior.setDamage(1);
                if(warrior.getLife() > 0){
                InOut.MsgDeAviso("Errou!",
                           charadas.get(randomIndex) + ", Você ainda possui " + warrior.getLife() + " vidas!", "oraculo.png");
                } else if(loop == false) {
                    loop = true;
                    //Chama o metodo decideExtraLife()dentro do if e verifica se te 5 palavras retornando true or false
                    String pedido = InOut.leString("Pedido de misericórdia: ", "oraculo.png");
                    
                    if(decideExtraLife(pedido) == true){
                        warrior.extraLife();
                    }else{
                        // Toca música de terror pausando a música de fundo
                        music.playEffectSoundWithPause("src/sounds/terror.wav");
                        
                        // Mostra imagem assustadora com som de terror
                        InOut.MsgDeAviso("HAHAHAHHAHA", "", "download.gif");

                        InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!", "oraculo.png");
                        return false;
                    }
                }
            }
        }

    //Mesnagem de aviso caso o usuário não tenha capacidade de completar a fase
    InOut.MsgDeAviso("Não foi dessa vez", "Você perdeu, tente novamente!", "oraculo.png");
    return false;
    } 



    /**
     * Gera e exibe o relatório final de uma partida.
     * Mostra estatísticas detalhadas incluindo vidas perdidas, palpites de ambos
     * os níveis e respostas corretas. Limpa os dados para a próxima partida.
     * 
     * @param vidaInicial quantidade de vida que o guerreiro tinha no início
     * @param repGame número da repetição do jogo (0 para primeira vez, 1 para segunda)
     */
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
    InOut.MsgDeInformacao("Relatório " + (repGame + 1), relatorio, "oraculo.png");
    
    // Resetando os valores para o segundo relatório 
    palpites.clear();
    chutes.clear();
    randomIndex = (int) (Math.random() * 3);
}
    
    //Método que calcula a quantidade de palavras para validar a vida extra
    

    /**
     * Decide se o pedido de misericórdia deve conceder vida extra.
     * Analisa o texto do pedido e concede vida extra se contiver 5 ou mais palavras.
     * 
     * @param frase texto do pedido de misericórdia do jogador
     * @return boolean true se o pedido tem 5+ palavras (vida extra concedida), false caso contrário
     */
    public boolean decideExtraLife(String frase) {
        String[] palavras = frase.trim().split("\\s+");
        return palavras.length >= 5;
    }    //GETTERS E SETTERS


    /**
     * Obtém o nome do Oráculo.
     * 
     * @return String representando o nome do Oráculo
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do Oráculo.
     * 
     * @param name novo nome para o Oráculo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o guerreiro associado ao Oráculo.
     * 
     * @return Warrior objeto representando o guerreiro do jogo
     */
    public Warrior getWarrior() {
        return warrior;
    }

    /**
     * Define o guerreiro associado ao Oráculo.
     * 
     * @param warrior novo objeto Warrior para o jogo
     */
    public void setWarrior(Warrior warrior) {
        this.warrior = warrior;
    }
}
