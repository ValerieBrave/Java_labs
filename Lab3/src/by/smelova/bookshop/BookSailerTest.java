package by.smelova.bookshop;

import by.smelova.bookshop.library.Book;
import by.smelova.exceptions.EmptyShelf;
import by.smelova.exceptions.NoMatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class BookSailerTest {

    @Before
    public void setUp() {
        System.out.println("before booksailer test");
    }

    @Test(timeout = 200)
    public void SaleBook() throws EmptyShelf, NoMatch {
        Bookshop bookshop = new Bookshop("Book City");
        Book bk = new Book(9, "War and Peace", 1050, "L.N.Tolstoi", bookshop.shopname);
        bookshop.bookshelf.add(new Book(9, "War and Peace", 1050, "L.N.Tolstoi", bookshop.shopname));
        Bookshop.BookSailer sailer = bookshop.new BookSailer("Петя Сидоров");
        sailer.SaleBook(bk);
        System.out.println("BookSailerTest");
        assertEquals(0, bookshop.bookshelf.size());
    }

    @After
    public void tearDown() {
        System.out.println("after booksailer test");
    }
}