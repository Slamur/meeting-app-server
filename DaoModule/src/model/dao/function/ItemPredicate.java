package model.dao.function;

import model.dao.Item;

public interface ItemPredicate<ItemType extends Item> {

    boolean check(ItemType item);
}
