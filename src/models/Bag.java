package models;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private List<Item> items = new ArrayList<>();

    public Bag () {}

    public Bag(List<Item> items) {
        this.items = items;
    }

    public void toEquip(Item item) {
        items.add(item);
    }

    public void toUnequip(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
