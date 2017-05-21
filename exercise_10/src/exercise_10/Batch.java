package exercise_10;

/**
 * A group of books, contains books or other batches
 * @author Pascal Gerig
 *
 */
public class Batch implements OrderComponent {
	
	/**
	 * Constructs an empty Batch
	 */
	public Batch(String name)
	{
		
	}
	
	/**
	 * Add book(s) to Batch
	 * @param book
	 * @param quantity
	 */
	public void addBook(Book book, int quantity) {
		throw new NotImplementedException();
	}

	/* Nested batches are also possible! */
	/**
	 * Add other Batch(es) to current Batch
	 * @param batch
	 * @param quantity
	 */
	public void addBatch(OrderComponent batch, int quantity) {
		throw new NotImplementedException();
	}
}
