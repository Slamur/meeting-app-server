package model.dao;

public interface ListDaoListener<ItemType extends Item> {

    void onItemAdded(ItemType item);
    void onItemInserted(ItemType item, int index);
    void onItemChanged(ItemType item, int index);
    void onItemRemoved(int index);

    void setProducer(ListDao<ItemType> listDao);
    ListDao<ItemType> getProducer();
}
