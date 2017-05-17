import exercise_10.Batch;
import exercise_10.Book;
import exercise_10.BookOrder;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Integration tests cover, as opposed to unit tests, parts of or even the whole system.
 * They can be used to test scenarios of the full working system and complement unit tests.
 * Often, integration tests are more "high level" and tests the overall behaviour of the system.
 *
 * This class contains some usage scenarios and can be a start for designing your implementation.
 * However, you do not need to keep everything the same, you are free to add, remove, and modify classes
 * and interfaces.
 */
public class IntegrationTests {
	Book patterns = new Book(
			"Design patterns : elements of reusable object-oriented software", // title
			"Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", // authors
			40.2 // price
	);


	@Test
	public void singleBookSummary() {
		/*
		Create an order with a single book and make sure the output is correct.
		 */

		Calendar orderDate = Calendar.getInstance();
		orderDate.set(2017, 05, 16);
		BookOrder order = new BookOrder(
				"Anonymous", // client name
				orderDate // order date
		);
		order.addBook(patterns, 2);

		String expected = "Date: 16 May, 2017\n" +
				"Client: Anonymous\n" +
				"Number of books: 2\n" +
				"Final Price: 80.4 CHF\n" +
				"\n" +
				"Books\n" +
				"1.  Title: Design patterns : elements of reusable object-oriented software\n" +
				"    Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides\n" +
				"    Price: 40.2 CHF\n" +
				"    Quantity: 2";

		assertEquals(expected, order.summary());
	}

	@Test
	public void batchOrder() {
		/*
		Create an order with an empty batch and another batch containing the patterns book.
		 */

		Calendar orderDate = Calendar.getInstance();
		orderDate.set(2017, 05, 16);
		BookOrder order = new BookOrder(
				"Anonymous", // client name
				orderDate // order date
		);

		// create batches through the order, so there is no need to add the batch to the
		// order in an extra step
		Batch emptyBatch = order.newBatch("Empty batch");
		Batch patternsBatch = order.newBatch("Design Patterns");

		patternsBatch.addBook(patterns, 3);

		String expected = "Date: 16 May, 2017\n" +
				"Client: Anonymous\n" +
				"Number of books: 1\n" +
				"Total Price: 40.2 CHF\n" +
				"Discount: 0 CHF\n" +
				"Final Price: 40.2 CHF\n" +
				"\n" +
				"Batch 'Empty batch'\n" +
				"\n" +
				"Batch 'Design Patterns'\n" +
				"\n" +
				"\tTitle: Design patterns : elements of reusable object-oriented software\n" +
				"    Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides\n" +
				"    Publisher:  Addison Wesley\n" +
				"    Publication Date: 31 October 1994\n" +
				"    Number of pages: 416\n" +
				"    Category: Software Engineering\n" +
				"    Price: 40.2 CHF\n" +
				"    Quantity: 1";

		assertEquals(expected, order.details());
	}
}

/*
The raw representation of the two examples are below.

Note that you do not have to print the orders exactly (e.g., exact same whitespace) as shown below,
but all the information should be present and nested batches should be indented.
 */

/*
Date: 16 May, 2017
Client: Anonymous
Number of books: 2
Final Price: 80.4 CHF


Books

1.  Title: Design patterns : elements of reusable object-oriented software
    Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides
    Price: 40.2 CHF
	Quantity: 2
 */


/*
Date: 16 May, 2017
Client: Anonymous
Number of books: 1
Total Price: 40.2 CHF
Discount: 0 CHF
Final Price: 40.2 CHF

Batch 'Empty batch'

Batch 'Design Patterns'

	Title: Design patterns : elements of reusable object-oriented software
    Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides
    Publisher:  Addison Wesley
    Publication Date: 31 October 1994
    Number of pages: 416
    Category: Software Engineering
    Price: 40.2 CHF
    Quantity: 1
 */