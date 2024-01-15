import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The {@link Contact} class represents the contact information
 * for a faculty or staff member in the College.
 * 
 * @author CS Professors
 */
public class Contact implements Comparable<Contact> {
    /** The name of the faculty or staff member. */
    private String name;

    /** The office location of the faculty or staff member. */
    private String office;

    /**
     * Constructs a new Contact.
     * 
     * @param name The name of the faculty or staff member
     * @param office The office location of the faculty or staff member
     */
    public Contact(String name, String office) {
        this.name = name;
        this.office = office;

    }

    /**
     * Gets the name of the faculty or staff member.
     * 
     * @return The name of the faculty or staff member
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the office location of the faculty or staff member.
     * 
     * @return The office location of the faculty or staff member
     */
    public String getOffice() {
        return office;
    }

    /**
     * Compares a Contact by its {@code name} instance variable with something else.
     * The something else could be either a String or another Contact (in which case
     * we compare with the {@code name} instance variable of the other Contact)
     * 
     * @param other The object to compare to
     * 
     * @return -1 if {@code this.name} is earlier alphabetically than {@code other}
     *         0 if {@code this.name} is equal to {@code other}
     *         0 if {@code this.name} is later alphabetically than {@code other}
     */
    @Override
    public int compareTo(Contact other) {
        if (this.name.compareTo(other.name) == 0){
            return 0;
        }
        else if (this.name.compareTo(other.name) < 0){
            return -1;
        }
        else{
            return 1;
        }
    }

    /**
     * The main method of this program.  Used for testing the compareTo method
     * and its uses.
     * 
     * @param args The command line arguments (unused)
     */
    public static void main(String[] args) {
        /* This first set of lines helps us see what happens when we call compareTo. */
        
    //    /* [REMOVE THIS LINE TO TEST]
        // create a couple contacts
        Contact c1 = new Contact("Adam", "King 125B");
        Contact c2 = new Contact("Howard", "King 139C");
        
        // check what is returned by the compareTo method
        System.out.println("c1 compareTo c2: " + c1.compareTo(c2));
        System.out.println("c1 compareTo c1: " + c1.compareTo(c1));
        System.out.println("c2 compareTo c1: " + c2.compareTo(c1));
        System.out.println(); // add a blank line of output
    //    [REMOVE THIS LINE TO TEST] */

        /* The rest of this code investigates what happens when we use 
         * compareTo to help us sort data. */
        
    //    /* [REMOVE THIS LINE TO TEST]
        // create some more Contacts
        Contact c3 = new Contact("Jackie", "King 139");
        Contact c4 = new Contact("Emily", "King 139A");
        Contact c5 = new Contact("Cynthia", "King 139B");
        Contact c6 = new Contact("Josh", "King 129");
        Contact c7 = new Contact("Steve", "King 131");        
        Contact c8 = new Contact("Molly", "King 223A");
        Contact c9 = new Contact("Michael", "King 223B");
        Contact c10 = new Contact("Lucas", "King 223C");
        
        // create a list of all of the Contacts
        Contact[] array = new Contact[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        List<Contact> csOffices = Arrays.asList(array);

        // print the contacts as we added them
        for (Contact contact : csOffices) {
            System.out.println(contact.getName() + " is in " + contact.getOffice());
        }
        System.out.println(); // add a blank line of output

        // sort the list
        Collections.sort(csOffices);

        // print the contacts again
        for (Contact contact : csOffices) {
            System.out.println(contact.getName() + " is in " + contact.getOffice());
        }
    //    [REMOVE THIS LINE TO TEST] */
    }
}
