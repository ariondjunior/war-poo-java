package models;

import java.util.ArrayList;
import java.util.List;

/** 
 * A classe <i>Bag</i> representa o inventário ou mochila do guerreiro no jogo.
 * Esta classe gerencia uma coleção de itens que o guerreiro pode carregar,
 * permitindo equipar e desequipar itens conforme necessário.
 * <ul>
 * <li>Mantém uma lista de itens que o guerreiro possui
 * <li>Permite adicionar novos itens ao inventário (equipar)
 * <li>Permite remover itens do inventário (desequipar)
 * <li>Fornece acesso controlado à lista de itens
 * <li>Inicialização flexível com lista vazia ou pré-populada
 * </ul>  
 */
public class Bag {
    private List<Item> items = new ArrayList<>();

    /**
     * Construtor padrão da classe Bag.
     * Cria uma mochila vazia, inicializando a lista de itens como ArrayList vazio.
     */
    public Bag () {}

    /**
     * Construtor parametrizado da classe Bag.
     * Inicializa a mochila com uma lista pré-definida de itens.
     * 
     * @param items lista inicial de itens para popular a mochila
     */
    public Bag(List<Item> items) {
        this.items = items;
    }

    /**
     * Adiciona um item à mochila (equipar item).
     * O item é adicionado à lista de itens que o guerreiro possui.
     * 
     * @param item objeto Item que será adicionado à mochila
     */
    public void toEquip(Item item) {
        items.add(item);
    }

    /**
     * Remove um item da mochila (desequipar item).
     * O item é removido da lista de itens que o guerreiro possui.
     * 
     * @param item objeto Item que será removido da mochila
     */
    public void toUnequip(Item item) {
        items.remove(item);
    }

    /**
     * Obtém a lista completa de itens na mochila.
     * Retorna uma referência à lista de itens que o guerreiro possui.
     * 
     * @return List&lt;Item&gt; contendo todos os itens na mochila
     */
    public List<Item> getItems() {
        return items;
    }
}
