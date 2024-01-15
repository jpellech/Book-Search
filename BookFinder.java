/* 
 * This uses a dataset from the GoodReads website. 
 * You can find the original data file at 
 *     https://www.kaggle.com/jealousleopard/goodreadsbooks 
 * I cut this down to 5 columns: isbn, authors, title, publisher, GoodReads rating.
 * I also cleaned up the data a bit; there were a few broken entries.
 * Note that the file is written with the UTF-8 character encoding. This is the default
 * for Python but needs to be specified for Java, as in 
 * 	new Scanner( new File(foo), "UTF-8" )
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A BookFinder class holds mappings between ISBNs, titles, authors, and
 * publishers to books represented by a {@link BookData}.
 * 
 * @author Bob Geitz
 * @author Stephen Checkoway
 * @author Adam Eck
 * @author Your Name Goes Here
 * @version 2023-11-12
 * 
 */
public class BookFinder {
    /** The binary search tree for saving book information by title. */
    private MyBinarySearchTreeNode<BookLookup> isbnToData;

    /** The binary search tree for saving book information by title. */
    private MyBinarySearchTreeNode<BookLookup> titleToData;

    /** The binary search tree for saving book information by author. */
    private MyBinarySearchTreeNode<BookLookup> authorToData;

    /** The binary search tree for saving book information by publisher. */
    private MyBinarySearchTreeNode<BookLookup> publisherToData;

    /**
     * Creates a BookFinder object. by reading the data file at path.
     * 
     * This will be a comma-separated text file with 5 fields per line:
     * isbn,authors,title,publisher,rating
     * 
     * Multiple authors are separated by '/' characters: Frank Herbert/Domingo
     * Santos
     * 
     * @param path The file path for the input data file.
     */
    public BookFinder(String path) {
        // start the trees as null, we will create the roots in the addBook method.
        this.isbnToData = null;
        this.titleToData = null;
        this.authorToData = null;
        this.publisherToData = null;

        // create the books using the file at the given path
        this.createBooks(path);
    }
    
    /**
     * Adds a {@link BookData} object to {@link BookLookup} items in each
     * of the four binary search trees.
     * 
     * @param bookData The {@link BookData} to add to the trees
     */
    private void addBook(BookData bookData) {
        // first add to the ISBN BST
        if (this.isbnToData == null) {
            // create the book lookup
            BookLookup lookup = new BookLookup(bookData.isbn);
            lookup.addBook(bookData);

            // create the title BST
            this.isbnToData = new MyBinarySearchTreeNode<BookLookup>(lookup);
        } else {
            // find the relevant node
            MyBinarySearchTreeNode<BookLookup> node = this.isbnToData.find(bookData.isbn);

            if (node == null) {
                // create a new node if needed
                BookLookup lookup = new BookLookup(bookData.isbn);
                lookup.addBook(bookData);
                this.isbnToData.insert(lookup);
            } else {
                // add this book to the BookLookup item that we found
                node.getItem().addBook(bookData);
            }
        }

        // second add to the title BST
        if (this.titleToData == null) {
            // create the book lookup
            BookLookup lookup = new BookLookup(bookData.title);
            lookup.addBook(bookData);

            // create the title BST
            this.titleToData = new MyBinarySearchTreeNode<BookLookup>(lookup);
        } else {
            // find the relevant node
            MyBinarySearchTreeNode<BookLookup> node = this.titleToData.find(bookData.title);

            if (node == null) {
                // create a new node if needed
                BookLookup lookup = new BookLookup(bookData.title);
                lookup.addBook(bookData);
                this.titleToData.insert(lookup);
            } else {
                // add this book to the BookLookup item that we found
                node.getItem().addBook(bookData);
            }
        }

        // next add to the authors BST
        for (String author : bookData.authors) {
            if (this.authorToData == null) {
                // create the book lookup
                BookLookup lookup = new BookLookup(author);
                lookup.addBook(bookData);

                // create the authors BST
                this.authorToData = new MyBinarySearchTreeNode<BookLookup>(lookup);
            } else {
                // find the relevant node
                MyBinarySearchTreeNode<BookLookup> node = this.authorToData.find(author);

                if (node == null) {
                    // create a new node if needed
                    BookLookup lookup = new BookLookup(author);
                    lookup.addBook(bookData);
                    this.authorToData.insert(lookup);
                } else {
                    // add this book to the BookLookup item that we found
                    node.getItem().addBook(bookData);
                }
            }
        }

        // finally add to the publisher BST
        if (this.publisherToData == null) {
            // create the book lookup
            BookLookup lookup = new BookLookup(bookData.publisher);
            lookup.addBook(bookData);

            // create the title BST
            this.publisherToData = new MyBinarySearchTreeNode<BookLookup>(lookup);
        } else {
            // find the relevant node
            MyBinarySearchTreeNode<BookLookup> node = this.publisherToData.find(bookData.publisher);

            if (node == null) {
                // create a new node if needed
                BookLookup lookup = new BookLookup(bookData.publisher);
                lookup.addBook(bookData);
                this.publisherToData.insert(lookup);
            } else {
                // add this book to the BookLookup item that we found
                node.getItem().addBook(bookData);
            }
        }
    }

    /**
     * Fills the binary search trees by reading the data file at path and
     * saving the books in each tree.
     * 
     * @param path The file path for the input data file
     */
    private void createBooks(String path) {
        /*
         * First, create a Scanner to read in the lines of the file stored at path.
         * You will need to open the data file with a "UTF-8" flag using:
         *     Scanner scan = new Scanner( new File(path), "UTF-8");
         *
         * For each line of the data file, you should create a new BookData object. 
         * - The file will be a comma-separated text file with 5 fields per line:
         *   isbn,authors,title,publisher,rating
         * - Multiple authors are separated by '/' characters: 
         *   For example: Frank Herbert/Domingo Santos
         * 
         * Then call the addBook method to add the new BookData object to all 
         * three of our Binary Search Trees.
         */

        
        try {
            Scanner scan = new Scanner( new File(path), "UTF-8");
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                String[] fields = line.split(","); // splits by comma
                String[] authors = fields[1].split("/"); // splits by /
                Float rating = Float.parseFloat(fields[4]);
                BookData bookData = new BookData(fields[0], authors, fields[2], fields[3], rating);
                addBook(bookData);
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            System.err.println("File not found: " + path);
        }
    }

    /**
     * Returns a list of books matching a given ISBN.
     * 
     * @param isbn The ISBN to search for.
     * 
     * @return A list of {@link BookData} corresponding to the isbn (should 
     *         have at most one item)
     */
    public List<BookData> searchByISBN(String isbn) {
        /*
         * First, find the MyBinarySearchTreeNode from the this.isbnToData
         * Binary Search Tree that matches isbn.
         * - If the node is null, return an empty ArrayList.
         * - Else, return the list of BookData from inside the BookLookup 
         *   stored in the node.
         */
        MyBinarySearchTreeNode<BookLookup> search = this.isbnToData.find(isbn);
        if (search == null){
            return new ArrayList<BookData>();
        }
        else{
            return search.getItem().getBooks();
        }
        
    }

    /**
     * Returns a list of books written by the author.
     * 
     * @param author The author to search for.
     * 
     * @return A list of {@link BookData} objects written by author.
     */
    public List<BookData> searchByAuthor(String author) {
        /*
         * First, find the MyBinarySearchTreeNode from the this.authorToData
         * Binary Search Tree that matches author.
         * - If the node is null, return an empty ArrayList.
         * - Else, return the list of BookData from inside the BookLookup 
         *   stored in the node.
         */
        MyBinarySearchTreeNode<BookLookup> search = this.authorToData.find(author);
        if (search == null){
            return new ArrayList<BookData>();
        }
        else{
            return search.getItem().getBooks();
        }
        
    }

    /**
     * Returns a list of books with the exact title.
     * 
     * @param title The title to search for.
     * 
     * @return A list of {@link BookData} objects with the given title.
     */
    public List<BookData> searchByTitle(String title) {
        /*
         * First, find the MyBinarySearchTreeNode from the this.titleToData
         * Binary Search Tree that matches title.
         * - If the node is null, return an empty ArrayList.
         * - Else, return the list of BookData from inside the BookLookup 
         *   stored in the node.
         */
        MyBinarySearchTreeNode<BookLookup> search = this.titleToData.find(title);
        if (search == null){
            return new ArrayList<BookData>();
        }
        else{
            return search.getItem().getBooks();
        }
    }

    /**
     * Returns a list of books published by publisher.
     * 
     * @param publisher The publisher to search for.
     * 
     * @return A list of {@link BookData} published by the publisher.
     */
    public List<BookData> searchByPublisher(String publisher) {
        /*
         * First find the MyBinarySearchTreeNode from the this.publisherToData
         * Binary Search Tree that matches publisher.
         * - If the node is null, return an empty ArrayList.
         * - Else, return the list of BookData from inside the BookLookup 
         *   stored in the node.
         */
        MyBinarySearchTreeNode<BookLookup> search = this.publisherToData.find(publisher);
        if (search == null){
            return new ArrayList<BookData>();
        }
        else{
            return search.getItem().getBooks();
        }
    }
}
