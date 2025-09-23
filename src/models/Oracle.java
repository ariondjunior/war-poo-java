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
        InOut.MsgDeInformacao("Prologue introduction", "O jogo se passa em uma época de guerra. Aqui possui "
                + warrior.getName() + " ele possui " + warrior.getLife() + " pontos de vida.");
        return "";
    }

    public String loserPrologue() {
        InOut.MsgDeAviso("O " + warrior.getName() +", perdeu!", "Não foi dessa vez, tente novamente!");
        return "";
    }

    public String winningPrologue() {
        InOut.MsgDeAviso("O " + warrior.getName() +", venceu", "Parabéns pela sua conquista, você foi incrível!");
        return "";
    }

    public boolean loadLevel01() {
        int max = 100;
        int min = 1;
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
        int chute = 0;
        boolean loop = false; 
        InOut.MsgDeInformacao("Desafio",
                "Um número foi sorteado, você deve adivinha-lo. Ele está dentro do intervalo [1, 100]");

        System.out.println(numeroAleatorio);

        while (chute != numeroAleatorio && warrior.getLife() != 0) {
            chute = InOut.leInt("Qual é o número?");

            if (chute == numeroAleatorio) {
                InOut.MsgDeInformacao("Parabéns", "Você acertou o número secreto, ele era " + numeroAleatorio);

                InOut.MsgDeAviso("Próximo desafio", "Agora você irá para a próxima fase.");
                return true;
            }
            if (chute < numeroAleatorio) {
                warrior.setDamage(1);
                if (warrior.getLife() != 0) {
                    InOut.MsgDeAviso("Aviso",
                            "O número secreto é maior que o chute. Você ainda possui " + warrior.getLife() + " vidas.");
                }
            }
            if (chute > numeroAleatorio) {
                warrior.setDamage(1);
                if (warrior.getLife() != 0) {
                    InOut.MsgDeAviso("Aviso",
                            "O número secreto é menor que o chute. Você ainda possui " + warrior.getLife() + " vidas.");
                }

            }
            if(warrior.getLife() == 0 && loop == false){
                loop = true;
                //Chama o metodo decideExtraLife()dentro do if e verifica se te 5 palavras retornando true or false
                if(decideExtraLife(InOut.leString("Pedido de misericórdia: ")) == true){
                    warrior.extraLife();
                }else{
                    InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!");
                    return false;
                }
            }
            
        }
        return false;
    }

    public boolean loadLevel02() {
        List<String> charadas = Arrays.asList(
            "O que é, o que é: Tem cabeça e tem dente, mas não é bicho nem gente?",
          "O que é, o que é: Tem boca, mas não fala; tem leito, mas não dorme; corre, mas não anda?",
          "O que é, o que é: Quatro patas de manhã, duas ao meio-dia e três à noite?"
          );
        List<String> respostas = Arrays.asList("alho","rio","humano");
          
        int randomIndex = (int) (Math.random() * 3);
        System.out.println(respostas.get(randomIndex));
        String chute2 = "";
        boolean acerto = false;
        boolean loop = false;

        while(acerto == false && warrior.getLife() != 0) {
            chute2 = InOut.leString(charadas.get(randomIndex));
            System.out.println(chute2);
            if(chute2.equalsIgnoreCase(respostas.get(randomIndex))) {
                return true;            
            }else{
                warrior.setDamage(1);
                if(warrior.getLife() > 0){
                InOut.MsgDeAviso("Errou!",
                           charadas.get(randomIndex) + ", Você ainda possui " + warrior.getLife() + " vidas!");
                } else if(loop == false) {
                    loop = true;
                    //Chama o metodo decideExtraLife()dentro do if e verifica se te 5 palavras retornando true or false
                    if(decideExtraLife(InOut.leString("Pedido de misericórdia: ")) == true){
                        warrior.extraLife();
                    }else{
                        InOut.MsgDeAviso("GAME OVER", "O seu pedido foi negado!");
                        return false;
                    }
                }
            }
 
        }
    InOut.MsgDeAviso("Não foi dessa vez", "Você perdeu, tente novamente!");
    return false;
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
