package by.smelova.bookshop;
import by.smelova.bookshop.library.*;
import by.smelova.exceptions.EmptyShelf;
import by.smelova.exceptions.NoMatch;

import java.util.ArrayList;
import java.util.Comparator;

public class Bookshop {
    public static ArrayList<Book> bookshelf;
    public static ArrayList<Journal> journalshelf;
    public static ArrayList<PostCard> postcards;
    public String shopname;
    public class BookSailer implements Work {
        public String fullname;

        public BookSailer(String fullname) {
            this.fullname = fullname;
        }

        @Override
        public void SaleBook(Book b) throws EmptyShelf, NoMatch {
            if(bookshelf.size() == 0) throw new EmptyShelf("bookshelf is empty");
            int i = bookshelf.indexOf(b);
            if(i == -1) throw new NoMatch("no such book found in library");
            bookshelf.remove(i);
        }

        @Override
        public void SaleJournal(Journal j) throws EmptyShelf, NoMatch {
            if(journalshelf.size() == 0) throw new EmptyShelf("journalshelf is empty");
            int i = journalshelf.indexOf(j);
            if(i == -1) throw new NoMatch("no such journal found in library");
            journalshelf.remove(i);
        }

        @Override
        public void SaleCard(PostCard p) throws EmptyShelf, NoMatch {
            if(postcards.size() == 0) throw new EmptyShelf("no postcards left");
            int i = postcards.indexOf(p);
            if(i == -1) throw new NoMatch("no such postcards in the shop");
            postcards.remove(i);
        }

        @Override
        public void AddToLibrary(Literature b) {
            Bookshop.AddToLib(b);
        }

        @Override
        public void SortByPrice()
        {
            Comparator<Book> bookComparator = new Comparator<Book>() {
                @Override
                public int compare(Book bk1, Book bk2) {
                    if(bk1.getPrice() > bk2.getPrice()) return 1;
                    else if(bk1.getPrice() < bk2.getPrice()) return -1;
                    else return 0;
                }
            };
            bookshelf.sort(bookComparator);
            journalshelf.sort(bookComparator);
            postcards.sort(bookComparator);
            int k = 0;
        }
    }

    public Bookshop() {
        this.bookshelf = new ArrayList<Book>();
        this.journalshelf = new ArrayList<Journal>();
        this.postcards = new ArrayList<PostCard>();
    }

    public Bookshop(String name) {
        this.bookshelf = new ArrayList<Book>();
        this.journalshelf = new ArrayList<Journal>();
        this.postcards = new ArrayList<PostCard>();
        this.shopname = name;
    }

    public static ArrayList<Literature> getAllLit() {
        ArrayList<Literature> rc = new ArrayList<Literature>();
        for (Book b: bookshelf
             ) {
            rc.add(b);
        }
        for (Journal j: journalshelf
             ) {
            rc.add(j);
        }
        for (PostCard p: postcards
             ) {
            rc.add(p);
        }
        return rc;
    }

    public static void AddToLib(Literature b) {
        if(b.getClass() == Book.class) bookshelf.add((Book)b);
        else if(b.getClass() == Journal.class) journalshelf.add((Journal) b);
        else postcards.add((PostCard)b);
    }
}
