package by.smelova;
import by.smelova.bookshop.*;
import by.smelova.bookshop.library.*;
import by.smelova.exceptions.InvalidPriceValue;
import com.alibaba.fastjson.JSON;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import java.io.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws XMLStreamException, IOException {
        Bookshop bookshop = new Bookshop("Book City");
        Bookshop.BookSailer sailer = bookshop.new BookSailer("Петя Сидоров");
        try
        {
            sailer.AddToLibrary(new Book(9, "War and Peace", 1050, "L.N.Tolstoi", bookshop.shopname));
            sailer.AddToLibrary(new Book(5, "Carry", 589, "S.King", bookshop.shopname));
            sailer.AddToLibrary(new Book(8, "The Hundred", 426, "K.Morgan", bookshop.shopname));
            sailer.AddToLibrary(new PostCard(3, "Happy Birthday", 1, "Walmark", bookshop.shopname, "Vintage"));
        }
	    catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        for (Literature l: bookshop.getAllLit()
             ) {
            System.out.println(l.toString());

        }
        //BookshelfArchivator.saveFromXML(bookshop.bookshelf, "files/books-schema.xsd", "files/books.xml");


        //BookshelfArchivator.saveToJson(bookshop.bookshelf, "files/bookshelf.json");
//        BookshelfArchivator.saveFromJson(bookshop.bookshelf, "files/bookshelf.json");
//        for(Book b:bookshop.bookshelf) {
//            System.out.println(b.toString());
//        }



        // сериализация в json
       /* Book ser = new Book(5, "Carry", 589, "S.King", "Book City");
        String jsonstring = JSON.toJSONString(ser);
        System.out.println(jsonstring);
        FileWriter writer = new FileWriter("files/bookshelf.json");
        writer.write(jsonstring + System.getProperty("line.separator"));

        Book deser =JSON.parseObject(jsonstring, Book.class);
        writer.close();*/
    }
}
