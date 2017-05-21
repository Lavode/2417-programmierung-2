package exercise_10;

import java.util.Calendar;
import java.util.List;
import java.util.LinkedList;
/**
 * 
 * @author Pascal Gerig
 *
 */
public class BookOrder {
	private String clientName;
	private Calendar orderDate;
	private List<OrderComponent> components;
	
	public BookOrder(String clientName, Calendar orderDate) {
		this.clientName = clientName;
		this.orderDate = orderDate;
		this.components = new LinkedList<OrderComponent>();
	}

	public void addBook(Book book, int copies) {
		throw new NotImplementedException();
	}

	/**
	 * Create a new batch with the given name as part of this order.
	 */
	public Batch newBatch(String name) {
		return new Batch(name);
	}

	public String summary() {
		throw new NotImplementedException();
	}

	public long details() {
		throw new NotImplementedException();
	}
}
