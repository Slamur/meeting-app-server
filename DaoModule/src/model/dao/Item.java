package model.dao;

public abstract class Item {

    private final long id;

    protected Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (null == obj) {
            return (null == this);
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item item = (Item) obj;

        if (id != item.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        final int intBits = (Integer.BYTES << 8);
        final long intMask = (1L << intBits) - 1;

        hash |= (id >> intBits);
        hash |= (id & intMask);

        return hash;
    }
}
