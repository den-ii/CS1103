package unit6;

/**
 * Library Item Class
 * Signifies an item in a library
 * @param <T> type of ID
 * @author Deni Wisdom Ochiche
 */
public class LibraryItem<T>{
    private T itemID;
    private String author;
    private String title;

    /**
     * LibraryItem Constructor
     * @param itemID id of an item
     * @param author author of an item
     * @param title  title of an item
     */
    LibraryItem(T itemID, String author, String title){
        this.itemID = itemID;
        this.author = author;
        this.title = title;
    }

    /**
     * Retrieves an item's id
     * @return itemID - id of an item
     */
    public T getItemID(){
        return this.itemID;
    }

    /**
     * Set an item's ID
     * @param itemID ID of the item
     */
    public void setItemID(T itemID) {
        this.itemID = itemID;
    }

    /**
     * Retrieves the author of an item.
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of an item
     * @param author author/owner of an item
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the title of an item
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of an item
     * @param title title of an item
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
