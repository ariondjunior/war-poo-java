package models;

/** 
 * A classe <i>Warrior</i> representa o guerreiro protagonista do jogo de guerra.
 * Esta classe gerencia todas as características e funcionalidades do personagem principal,
 * incluindo vida, equipamentos e mecânicas de combate.
 * <ul>
 * <li>Controla os pontos de vida do guerreiro com sistema de dano e regeneração
 * <li>Gerencia o inventário de itens através da classe Bag
 * <li>Implementa sistema de sorteio aleatório para vida inicial
 * <li>Fornece funcionalidade de vida extra em situações especiais
 * <li>Mantém informações visuais do personagem (imagem)
 * <li>Oferece acesso controlado a todos os atributos via getters e setters
 * </ul>  
 */
//Classe "Warrior" que é responsável pelo controle básico das informações do guerreiro
public class Warrior {
    private String name;
    private int life;
    private Bag bag;
    private String stringImage;

    /**
     * Construtor da classe Warrior.
     * Inicializa um guerreiro com nome, inventário e aparência visual.
     * A vida não é definida no construtor, devendo ser sorteada posteriormente.
     * 
     * @param name nome do guerreiro
     * @param bag objeto Bag representando o inventário do guerreiro
     * @param stringImage caminho para a imagem que representa o guerreiro
     */
    //Construtor que deve receber os parâmetros nome, Object Bag e aparência do personagem. 
    public Warrior(String name, Bag bag, String stringImage) {
        this.name = name;
        // this.life = life;
        this.bag = bag;
        this.stringImage = stringImage;
    }

    /**
     * Sorteia a vida inicial do guerreiro.
     * Gera um valor aleatório entre 9 e 12 pontos de vida para o início do jogo.
     * 
     * @return int valor aleatório de vida entre 9 e 12 (inclusive)
     */
    //Sorteio da vida do guerreiro no intervaldo de [9,12]
    public int sortLife() {
        int max = 12;
        int min = 9;
        
        int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
        return numeroAleatorio;
    }

    /**
     * Concede uma vida extra ao guerreiro.
     * Adiciona 1 ponto de vida ao guerreiro, geralmente usado em situações especiais
     * como pedidos de misericórdia aceitos pelo Oráculo.
     * 
     * @return String vazia (mantido por compatibilidade)
     */
    //Méotodo que adiciona 1 vida estra para o guerreiro
    public String extraLife() {
        this.setLife(1);
        return "";
    }

    /**
     * Obtém o nome do guerreiro.
     * 
     * @return String representando o nome do guerreiro
     */
    //GETTERS E SETTERS
    public String getName() {
        return name;
    }

    /**
     * Define o nome do guerreiro.
     * 
     * @param name novo nome para o guerreiro
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém os pontos de vida atuais do guerreiro.
     * 
     * @return int representando os pontos de vida restantes
     */
    public int getLife() {
        return life;
    }

    /**
     * Define os pontos de vida do guerreiro.
     * 
     * @param life novo valor de pontos de vida
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Aplica dano ao guerreiro.
     * Reduz os pontos de vida do guerreiro pela quantidade de dano especificada.
     * 
     * @param damage quantidade de dano a ser aplicada
     */
    public void setDamage(int damage) {
        this.life -= damage;
    }

    /**
     * Obtém o inventário do guerreiro.
     * 
     * @return Bag representando a mochila/inventário do guerreiro
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * Define o inventário do guerreiro.
     * 
     * @param bag novo objeto Bag para ser o inventário do guerreiro
     */
    public void setBag(Bag bag) {
        this.bag = bag;
    }

    /**
     * Obtém o caminho da imagem do guerreiro.
     * 
     * @return String representando o caminho para a imagem do guerreiro
     */
    public String getStringImage() {
        return stringImage;
    }

    /**
     * Define o caminho da imagem do guerreiro.
     * 
     * @param stringImage novo caminho para a imagem do guerreiro
     */
    public void setStringImage(String stringImage) {
        this.stringImage = stringImage;
    }
}
