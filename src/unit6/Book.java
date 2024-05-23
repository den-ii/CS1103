package unit6;

/**
 * Book Class
 * The Book Class extends from LibraryItem<String>.
 * It contains information required to store a book.
 * @author Deni Wisdom Ochiche
 */
public class Book extends LibraryItem<String>{

    private String isbn;
     /*
      * Number of Pages a Book has.
      */
    private int pageNum;

    /**
     * Book Constructor
     * @param itemID ID of Book
     * @param author writer of Book
     * @param title  title of Book
     * @param isbn   isbn of Book
     * @param pageNum Number of pages in Book
     */
    Book(String itemID, String author, String title, String isbn, int pageNum) {
        super(itemID, author, title);
        this.isbn = isbn;
        this.pageNum = pageNum;
    }

    /**
     * Retrieve Book's isbn
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set a Book's isbn
     * @param isbn a Book's isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Retrieve Number of pages in a Book
     * @return pageNum - Number of pages
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Set a Book's number of pages
     * @param pageNum Number of pages a Book has.
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
