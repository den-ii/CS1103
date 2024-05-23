package unit6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Catalog Class
 * Stores library items and contains library functionalities
 * @param <U> type of ID(Integer or String)
 * @param <T> a class that inherits from {@code LibraryItem}
 * @author Deni Wisdom Ochiche
 */
public class Catalog <U, T extends LibraryItem<U>>{
    private final ArrayList<T> library = new ArrayList<>();

    /**
     * Adds an item into library.
     * @param item a library item.
     * @return true if item id does not exist, else false.
     */
    public boolean addItem(T item){
        if (findItem((U) item.getItemID()) == null){
            library.add(item);
            return true;
        }
        return false;
    }

    /**
     * Finds an item in the library.
     * @param itemID a library item's id.
     * @return null if item id does not exist, else item.
     */
    public T findItem(U itemID){
        for (var libItem: library){
            if (libItem.getItemID().equals(itemID)){
                return libItem;
            }
        }
        return null;
    }

    /**
     * Removes an item from library.
     * @param itemID a library item's id.
     * @return true if item id exist, else false.
     */
    public boolean removeItem(U itemID){
        T item = findItem(itemID);
        if (item == null) return false;
        library.remove(item);
        return true;
    }
    /**
     * Returns a view of the library.
     * @return library.
     */

    public List<T> viewCatalog(){
        return Collections.unmodifiableList(library);
    }

}
