package model.dao;

import java.util.List;

public interface ListDaoItemProvider<ItemType extends Item> {

    void saveItems(List<ItemType> items);

    List<ItemType> loadItems();
}
