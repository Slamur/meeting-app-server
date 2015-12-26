package model.dao;

import model.dao.function.ItemPredicate;

import java.util.ArrayList;
import java.util.List;

public abstract class ListDao<ItemType extends Item> {

    protected final ListDaoItemProvider<ItemType> itemProvider;
    protected final List<ItemType> items;
    protected final List<ListDaoListener<ItemType>> listeners;

    protected ListDao(ListDaoItemProvider<ItemType> itemProvider) {
        this.itemProvider = itemProvider;
        this.items = itemProvider.loadItems();
        this.listeners = new ArrayList<>();
    }

    public abstract Class<ItemType> getItemClass();

    protected long getNewInstanceId() {
        return getItemCount();
    }

    public void addListener(ListDaoListener<ItemType> listener) {
        listeners.add(listener);
        listener.setProducer(this);
    }

    public void removeListener(ListDaoListener<ItemType> listener) {
        listeners.remove(listener);
        listener.setProducer(null);
    }

    public List<ItemType> getItems() {
        return items;
    }

    public ItemType getItem(int index) {
        return items.get(index);
    }

    public int getItemCount() {
        return items.size();
    }

    public List<ItemType> filterItems(ItemPredicate<ItemType> predicate) {
        List<ItemType> filtered = new ArrayList<>();
        for (ItemType item : items) {
            if (predicate.check(item)) {
                filtered.add(item);
            }
        }

        return filtered;
    }

    public ItemType filterItem(ItemPredicate<ItemType> predicate) {
        List<ItemType> filtered = filterItems(predicate);
        if (filtered.isEmpty()) {
            return null;
        } else {
            return filtered.get(0);
        }
    }

    public ItemType getItemById(final long id) {
        return filterItem(new ItemPredicate<ItemType>() {
            @Override
            public boolean check(ItemType item) {
                return (item.getId() == id);
            }
        });
    }

    public void addItem(ItemType item) {
        items.add(item);
        itemProvider.saveItems(items);

        for (ListDaoListener<ItemType> listener : listeners) {
            listener.onItemAdded(item);
        }
    }

    public void insertItem(int index, ItemType item) {
        items.add(index, item);
        itemProvider.saveItems(items);

        for (ListDaoListener<ItemType> listener : listeners) {
            listener.onItemInserted(item, index);
        }
    }

    public void removeItem(int index) {
        items.remove(index);
        itemProvider.saveItems(items);

        for (ListDaoListener<ItemType> listener : listeners) {
            listener.onItemRemoved(index);
        }
    }
}
