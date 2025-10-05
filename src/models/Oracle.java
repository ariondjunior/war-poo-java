package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import InOut.InOut;

public class Oracle {
    private String name;
    private Warrior warrior;

    public Oracle() {
    }

    public Oracle(String name, Warrior warrior) {
        this.name = name;
        this.warrior = warrior;
    }

    public String prologueIntroduction() {
        InOut.MsgDeInformacao("Introdução do jogo", "O jogo se passa em uma época de guerra. Aqui possui um guerreiro chamado "
                + warrior.getName() + " ele possui " + warrior.getLife() + " pontos de vida.\nSeu maior desafio é passar de dois desafios que serão propostos pelo Oráculo. Se vencer salva o mundo, agora\nse perder a humanidade será destruida para SEMPRE. \n \n Para avança no jogo aperte 'OK'", warrior.getStringImage());
        return "";
    }

    public String loserPrologue() {
        InOut.MsgDeAviso("O " + warrior.getName() +", perdeu!", "Não foi dessa vez, tente novamente!", "oraculo.png");
        return "";
    }

    public String winningPrologue() {
        InOut.MsgDeAviso("O " + warrior.getName() +", venceu", "Parabéns pela sua conquista, você foi incrível!", "oraculo.png");
        return "";
    }

    //lista para auxiliar no relatorio final
    List<Integer> chutes = new ArrayList<>();

    public boolean loadLevel01() {
        int max = 100;
        int min = 1;
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
        int chute = 0;
        boolean loop = false; 

        InOut.MsgDeInformacao("Desafio",
                "Olá, sou o Oráculo! Meu nome é " + this.getName() + ". A partir de agora você enfrentarar 2 grandes desafios para salvar a humanidade, BOA SORTE! \n\n [DESAFIO 1]:\nUm número foi sorteado, você deve adivinha-lo. Ele está dentro do intervalo [1, 100]\n\n", "oraculo.png");

        System.out.println(numeroAleatorio);

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
                if(decideExtraLife(InOut.leString("Pedido de misericórdia: ", "oraculo.png")) == true){
                    InOut.MsgDeAviso("Vida extra", "Você recebeu vida extra!", "oraculo.png");
                    warrior.extraLife();
                }else{
                    InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!", "oraculo.png");
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
        List<String> respostas = Arrays.asList("Alho","Rio","Humano");
        int randomIndex = (int) (Math.random() * 3);

    //lista para auxiliar no relatorio final
    List<String> palpites = new ArrayList<>();

    public boolean loadLevel02() {
        System.out.println(respostas.get(randomIndex));
        String chute2 = "";
        boolean acerto = false;
        boolean loop = false;

        //verificacao de acerto e vida do guerreiro para continuar o loop
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
                    if(decideExtraLife(InOut.leString("Pedido de misericórdia: ", "oraculo.png")) == true){
                        warrior.extraLife();
                    }else{
                        InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!", "oraculo.png");
                        return false;
                    }
                }
            }
        }
    InOut.MsgDeAviso("Não foi dessa vez", "Você perdeu, tente novamente!", "oraculo.png");
    return false;
    } 

    // public void RelatorioFimGame(int vidaInicial, int repGame) {
    // InOut.MsgDeInformacao("Relatório " + (repGame + 1),
    // "Vidas Perdidas: " + (vidaInicial - warrior.getLife()) + 
    // "\n----------------LEVEL 1----------------" +
    // "\nNúmero: " + chutes.get(chutes.size() - 1) +
    // "\nPalpites: " 
    // + chutes + 
    // "\n----------------LEVEL 2----------------" + 
    // "\nCharada:"+ respostas.get(randomIndex)+
    // "\nPalpites: " + palpites, "oraculo.png");
    // //resetando os valores para o segundo relatorio 
    // palpites.clear();
    // chutes.clear();
    // randomIndex = (int) (Math.random() * 3);

    // }


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

    InOut.MsgDeInformacao("Relatório " + (repGame + 1), relatorio, "oraculo.png");
    
    // resetando os valores para o segundo relatorio 
    palpites.clear();
    chutes.clear();
    randomIndex = (int) (Math.random() * 3);
}
    

    public boolean decideExtraLife(String frase) {
        String[] palavras = frase.trim().split("\\s+");
        return palavras.length >= 5;
    }


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
