package unit6;

import java.util.Scanner;

/**
 * UserInterface Class
 * The View of Catalog Program, interacts with the library
 * @author Deni Wisdom Ochiche
 */
public class UserInterface {

    /*
     * a library for books
     */
    private static final Catalog<String, Book> bookLibrary =  new Catalog<>();

    /**
     * Entry point to our program
     * @param args cmd-line arguments
     */
    public static void main(String[] args) {
        System.out.println("""
                _ _ _                         \s
                | (_) |                        \s
                | |_| |__  _ __ __ _ _ __ _   _\s
                | | | '_ \\| '__/ _` | '__| | | |
                | | | |_) | | | (_| | |  | |_| |
                |_|_|_.__/|_|  \\__,_|_|   \\__, |
                                           __/ |
                                          |___/\s
                """);

        boolean shouldLoop = false;

        do {
            System.out.println("Enter 'v' to view book library, 'a'" +
                    " to add a book, 'i' to get info on a " +
                    "book, 'r' to remove a book and 'e' to exit");

            shouldLoop = trigger();

        } while (shouldLoop);
    }

    /**
     * Calls methods based on user's command.
     * @return true if user wants to exit, else false.
     */
    private static boolean trigger() {
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine().toLowerCase();

        return switch (cmd) {
            case "v" -> viewLibrary();
            case "a" -> addToLibrary(scanner);
            case "i" -> info(scanner);
            case "r" -> removeFromLibrary(scanner);
            case "e" -> exitLibrary();
            default -> {
                System.out.println("Invalid command");
                yield true;
            }
        };
    }

    /**
     * Displays all the items in a library
     * @return true
     */
    private static boolean viewLibrary(){
        if (bookLibrary.viewCatalog().isEmpty()){
            System.out.println("No book in the library");
            return true;
        }
       for (Book item : bookLibrary.viewCatalog()){
           viewABook(item);
           System.out.println("-".repeat(20));
       }
       return true;
    }

    /**
     * Adds an item to the library
     * @param scanner Scanner instance
     * @return true
     */
    private static boolean addToLibrary(Scanner scanner){
        System.out.println("Enter Item Id:");
        String id = scanner.nextLine().toLowerCase();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine().toLowerCase();
        System.out.println("Enter Book Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Book Author:");
        String author = scanner.nextLine();
        System.out.println("Enter Amount Of Pages:");
        if (id.isBlank() || title.isBlank() || author.isBlank() || isbn.isBlank()){
            System.out.println("Please fill in the information required");
            return true;
        }
        int pages;
        try {
            pages = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            System.out.print("Not a valid input, must be an integer");
            return true;
        }
        Book newBook = new Book(id, author, title, isbn, pages);
        if (bookLibrary.addItem(newBook)) System.out.println("Book Added");
        else System.out.println("Book with that ID already exists");
        return true;
    }

    /**
     * Displays information about an item
     * @param scanner Scanner instance
     * @return true
     */
    private static boolean info(Scanner scanner){
        System.out.println("Enter Item Id:");
        String id = scanner.nextLine().toLowerCase();
        Book book = bookLibrary.findItem(id);
        if (book == null) {
            System.out.println("Book with this ID is not available in" +
                    "the book library");
            return true;
        }
        else System.out.println("Here's your book");
        viewABook(book);
        return true;
    }

    /**
     * Helper function - displays a book details
     * @param book a library item(Book)
     */
    private static void viewABook(Book book){
        System.out.printf("ID: %s%n", book.getItemID());
        System.out.printf("Title: %s%n", book.getTitle());
        System.out.printf("Author: %s%n", book.getAuthor());
        System.out.printf("Amount of Pages: %d%n", book.getPageNum());
        System.out.printf("ISBN: %s%n", book.getIsbn());
    }

    /**
     * Removes an item from the library.
     * @param scanner Scanner instance.
     * @return true
     */
    private static boolean removeFromLibrary(Scanner scanner){
        System.out.println("Enter Item Id:");
        String id = scanner.nextLine().toLowerCase();
        if (bookLibrary.removeItem(id)){
            System.out.println("Book has been removed from library");
            return true;
        }
        System.out.println("Book with that ID oes not exist in the library");
        return true;
    }

    /**
     * Exit from the interface.
     * @return false
     */
    private static boolean exitLibrary(){
        System.out.println("See you soon!");
        System.out.println("exiting...");
        return false;
    }
}
