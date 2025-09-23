package models;

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
        InOut.MsgDeAviso("Você perdeu!", "Não foi dessa vez, tente novamente!");
        return "";
    }

    public String winningPrologue() {
        InOut.MsgDeAviso("Você venceu", "Parabéns pela sua conquista, você foi incrível!");
        return "";
    }

    public boolean loadLevel01() {
        int max = 100;
        int min = 1;
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
        int chute = 0;

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
        }

        InOut.MsgDeAviso("Não foi dessa vez", "Você perdeu, tente novamente!");

        return false;
    }

    public boolean loadLevel02() {
        InOut.MsgSemIcone("sdf", "asdfasdf");
        return true;
    }

    public boolean decideExtraLife(String frase) {
        InOut.leString("Digite uma frase: ");
        return true;
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
