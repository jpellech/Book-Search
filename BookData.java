/**
 * An immutable class containing information about a book.
 * 
 * @author Bob Geitz
 * @author Stephen Checkoway
 * @version 2021-06-30
 * 
 */
public class BookData {
    /** The book's ISBN. */
    public final String isbn;

    /** The book's authors. */
    public final String[] authors;

    /** The book's title. */
    public final String title;

    /** The book's publisher. */
    public final String publisher;
    
    /** The book's GoodReads rating. */
    public final float rating;

    /**
     * Create a new BookData.
     * 
     * @param isbn      The book's ISBN.
     * @param authors   An array of authors.
     * @param title     The book's title.
     * @param publisher The book's publisher.
     * @param rating    The book's GoodReads rating.
     */
    BookData(String isbn, String[] authors, String title, String publisher, float rating) {
        this.isbn = isbn;
        this.authors = authors.clone();
        this.title = title;
        this.publisher = publisher;
        this.rating = rating;
    }
}
