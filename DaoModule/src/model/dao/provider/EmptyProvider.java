package model.dao.provider;

import model.dao.Item;
import model.dao.ListDaoItemProvider;

import java.util.ArrayList;
import java.util.List;

public class EmptyProvider<ItemType extends Item>
implements ListDaoItemProvider<ItemType> {

    @Override
    public void saveItems(List<ItemType> items) {

    }

    @Override
    public List<ItemType> loadItems() {
        return new ArrayList<>();
    }
}
