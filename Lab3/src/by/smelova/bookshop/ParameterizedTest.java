package by.smelova.bookshop;
import by.smelova.bookshop.library.Book;
import by.smelova.exceptions.EmptyShelf;
import by.smelova.exceptions.NoMatch;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {
    Book bookToSale;
    int expected;
    static Bookshop bookshop;
    static Bookshop.BookSailer bookSailer;

    @BeforeClass
    public static void initialize() {
        bookshop = new Bookshop("Book City");
        bookSailer = bookshop.new BookSailer("Петя Сидоров");
        bookshop.bookshelf.add(new Book(9, "War and Peace", 1050, "L.N.Tolstoi", bookshop.shopname));
        bookshop.bookshelf.add(new Book(5, "Carry", 589, "S.King", bookshop.shopname));
        bookshop.bookshelf.add(new Book(8, "The Hundred", 426, "K.Morgan", bookshop.shopname));
    }

    public ParameterizedTest(Book param, int expected) {
        this.bookToSale = param;
        this.expected = expected;

    }

    @Parameterized.Parameters
    public static Collection params() {

        Book bk1 = new Book(9, "War and Peace", 1050, "L.N.Tolstoi", "Book City");
        Book bk2 = new Book(5, "Carry", 589, "S.King", "Book City");
        Book bk3 = new Book(8, "The Hundred", 426, "K.Morgan", "Book City");
        return Arrays.asList(new Object[][] {
                { bk1, 2 },
                { bk2, 1 },
                { bk3, 0 }
        });
    }

    @Test
    public void sellBook() throws EmptyShelf, NoMatch {
        System.out.println("parameter book "+ bookToSale.toString());
        bookSailer.SaleBook(bookToSale);
        assertEquals(expected, bookshop.bookshelf.size());
    }

}
