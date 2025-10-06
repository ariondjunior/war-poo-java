package models;

import InOut.*;

/** 
 * A classe <i>Item</i> representa um item do jogo que pode ser equipado pelo guerreiro.
 * Esta classe encapsula as propriedades de um item, incluindo seu identificador único,
 * tipo de item e status de equipamento.
 * <ul>
 * <li>Permite criação de itens com propriedades específicas
 * <li>Controla o estado de equipamento do item (equipado/não equipado)
 * <li>Fornece métodos para visualização das informações do item
 * <li>Implementa getters e setters para acesso controlado aos atributos
 * </ul>  
 */
public class Item {
    private Long id;
    private String itemType;
    private Boolean equipped;

    /**
     * Construtor padrão da classe Item.
     * Cria um item vazio sem inicializar os atributos.
     */
    public Item() {}

    /**
     * Construtor parametrizado da classe Item.
     * Inicializa um item com todos os atributos especificados.
     * 
     * @param id identificador único do item
     * @param itemType tipo do item (ex: "Sword", "Shield", "Armor")
     * @param equipped status indicando se o item está equipado ou não
     */
    public Item(Long id, String itemType, Boolean equipped) {
        this.id = id;
        this.itemType = itemType;
        this.equipped = equipped;
    }

    /**
     * Exibe as informações do item em uma janela de diálogo.
     * Mostra o ID, tipo e status de equipamento do item.
     */
    public void printData() {
        InOut.MsgDeInformacao("Item information",
                "idItem: " + id + "\n" +
                        "itemType: " + itemType + "\n" +
                        "equipped: " + equipped
                );
    }

    /**
     * Obtém o identificador único do item.
     * 
     * @return Long representando o ID do item
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único do item.
     * 
     * @param id novo identificador para o item
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o tipo do item.
     * 
     * @return String representando o tipo do item
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Define o tipo do item.
     * 
     * @param itemType novo tipo para o item (ex: "Sword", "Shield")
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Verifica se o item está equipado.
     * 
     * @return Boolean indicando se o item está equipado (true) ou não (false)
     */
    public Boolean getEquipped() {
        return equipped;
    }

    /**
     * Define o status de equipamento do item.
     * 
     * @param equipped novo status de equipamento (true para equipado, false para não equipado)
     */
    public void setEquipped(Boolean equipped) {
        this.equipped = equipped;
    }
}
