package server.service;

import com.google.gson.Gson;
import model.dao.Item;
import model.dao.ListDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public abstract class ItemJsonService
        <ItemType extends Item, ItemDao extends ListDao<ItemType>> {

    protected static final String ITEM_ADDED_RESPONSE = "Item has been added";
    protected static final String ITEM_DELETED_RESPONSE = "Item has been deleted";

    protected abstract ItemDao createDao();

    protected final ItemDao dao = createDao();
    protected final Gson gson = new Gson();

    // TODO check
    @GET
    //@Produces("text/html")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{index}")
    public ItemType getItem(@PathParam("index") int index) {
        return dao.getItem(index);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/id/{id}")
    public ItemType getItemById(@PathParam("id") long id) {
        return dao.getItemById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/items")
    public String getItems() {
        List<ItemType> items = dao.getItems();
        String itemsString = gson.toJson(items);

        return itemsString;
    }

    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put/{item}")
//    @Path("put/item")
    public String addItem(@PathParam("item") String itemString) {
        ItemType item = gson.fromJson(itemString, dao.getItemClass());
        dao.addItem(item);
        return gson.toJson(ITEM_ADDED_RESPONSE);
    }

    @DELETE
    @Path("/delete/{index}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteItem(@PathParam("index") int index) {
        dao.removeItem(index);
        return gson.toJson(ITEM_DELETED_RESPONSE);
    }
}
