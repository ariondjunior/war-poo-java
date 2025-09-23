package models;

import InOut.*;

public class Item {
    private Long id;
    private String itemType;
    private Boolean equipped;

    public Item() {}

    public Item(Long id, String itemType, Boolean equipped) {
        this.id = id;
        this.itemType = itemType;
        this.equipped = equipped;
    }

    public void printData() {
        InOut.MsgDeInformacao("Item information",
                "idItem: " + id + "\n" +
                        "itemType: " + itemType + "\n" +
                        "equipped: " + equipped
                );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(Boolean equipped) {
        this.equipped = equipped;
    }
}
