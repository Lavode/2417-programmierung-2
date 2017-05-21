package exercise_10;
import java.util.Calendar;
/**
 * Representation of one specific book
 * @author Pascal Gerig
 *
 */
public class Book implements OrderComponent{
	private String title, authors, publisher, category;
	private Calendar publicationDate;
	private int pages;
	private double price;
	
	/**
	 * Constructor to initialize a complete book object
	 * @param title
	 * @param authors
	 * @param publisher
	 * @param publicationDate
	 * @param pages
	 * @param category
	 * @param price
	 */
	public Book(String title, String authors, String publisher, Calendar publicationDate, int pages, String category, double price) {
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.category = category;
		this.pages = pages;
		this.price = price;
	}
}
