package models;

import InOut.InOut;

public class Warrior {
    private String name;
    private int life;
    private Bag bag;

    public Warrior() {}

    public Warrior(String name, Bag bag) {
        this.name = name;
        // this.life = life;
        this.bag = bag;
    }

    public int sortLife() {
        int max = 12;
        int min = 9;
        
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
        return numeroAleatorio;
    }

    public String extraLife() {
        InOut.MsgDeAviso("Vida extra", "Você recebeu vida extra!");
        this.setLife(1);
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

   
    public void setLife(int life) {
        this.life = life;
    }

    public void setDamage(int damage) {
        this.life -= damage;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
}
