package by.smelova.bookshop;

import by.smelova.bookshop.library.Book;
import by.smelova.bookshop.library.Journal;
import by.smelova.bookshop.library.Literature;
import by.smelova.bookshop.library.PostCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class BookshopTest {

    @Before
    public void setUp() {
        System.out.println("before bookshop test");
    }

    @After
    public  void tearDown() {
        System.out.println("after bookshop test");
    }



    @Ignore
    @Test
    public void getAllLit() {
        Bookshop bookshop = new Bookshop("Book City");
        Book bk1 = new Book(9, "War and Peace", 1050, "L.N.Tolstoi", bookshop.shopname);
        Book bk2 = new Book(5, "Carry", 589, "S.King", bookshop.shopname);
        Book bk3 = new Book(8, "The Hundred", 426, "K.Morgan", bookshop.shopname);
        PostCard p = new PostCard(3, "Happy Birthday", 1, "Walmark", bookshop.shopname, "Vintage");
        Bookshop.BookSailer sailer = bookshop.new BookSailer("Петя Сидоров");
        sailer.AddToLibrary(bk1);
        sailer.AddToLibrary(bk2);
        sailer.AddToLibrary(bk3);
        sailer.AddToLibrary(p);
        ArrayList<Literature> expected = new ArrayList<Literature>();
        expected.add(bk1);
        expected.add(bk2);
        expected.add(bk3);
        expected.add(p);
        assertEquals(expected, bookshop.getAllLit());
    }

    @Test
    public void AddToLib() {
        Bookshop bookshop = new Bookshop("Book City");
        PostCard p = new PostCard(3, "Happy Birthday", 1, "Walmark", bookshop.shopname, "Vintage");
        ArrayList<Literature> expected = new ArrayList<>();
        expected.add(p);
        bookshop.AddToLib(p);
        System.out.println("BookshopTest");
        assertEquals(expected, bookshop.postcards);
    }
}