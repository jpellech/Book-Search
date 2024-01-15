import java.util.ArrayList;
import java.util.List;

/**
 * The BookLookup class matches {@link BookData} objects with a lookup string so
 * we can lookup book information using different types of information (e.g.,
 * authors, title, publisher, isbn).
 * 
 * @author CS Professors
 */
public class BookLookup implements Comparable<Object> {
    /** The value we will use to lookup the book's information. */
    private final String lookup;

    /** The books that share the same lookup string (e.g., have the same author). */
    private final List<BookData> books;

    /**
     * Constructs a new BookLookup object.
     * 
     * @param lookup The value we will use to lookup the book's information
     */
    public BookLookup(String lookup) {
        this.lookup = lookup;
        this.books = new ArrayList<BookData>();
    }

    /** 
     * Adds a new {@link BookData} in our list.
     * 
     * @param The {@link BookData} to add
     */
    public void addBook(BookData bookData) {
        this.books.add(bookData);
    }

    /**
     * Gets the list of books.
     * 
     * @return Gets the list of books
     */
    public List<BookData> getBooks() {
        return this.books;
    }

    /**
     * Compares a BookLookup by its {@code lookup} instance variable with something 
     * else. The something else could be either a String or another BookLookup 
     * (in which case we compare with the {@code lookup} instance variable of the 
     * other BookLookup object)
     * 
     * @param other The object to compare to
     * 
     * @return -1 if {@code this.lookup} is earlier alphabetically than {@code other}
     *         0 if {@code this.lookup} is equal to {@code other}
     *         0 if {@code this.lookup} is later alphabetically than {@code other}
     */
    @Override
    public int compareTo(Object other) {
        if (other instanceof String) {
            String s = (String) other;
            return this.lookup.compareTo(s);
        } else if (other instanceof BookLookup) {
            BookLookup b = (BookLookup) other;
            return this.lookup.compareTo(b.lookup);
        } else {
            return -1;
        }
    }
}
