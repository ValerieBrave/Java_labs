package by.smelova.bookshop;

import by.smelova.bookshop.library.Book;
import by.smelova.exceptions.InvalidPriceValue;
import com.alibaba.fastjson.JSON;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.*;
import java.util.ArrayList;

public class BookshelfArchivator {
    public static boolean validateXML(String sch, String filename) {     // проверка документа на валидность через SAX
        boolean rc = true;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schems = null;
        try {
            schems = factory.newSchema(new File(sch));
            Validator validator = schems.newValidator();
            Source source = new StreamSource(filename);
            validator.validate(source);
        } catch (SAXException | IOException e) {
            rc = false;
        }
        return rc;
    }
    public static void saveFromXML(ArrayList<Book> bookshelf, String sch, String filename) {
        if(validateXML(sch, filename)) {
            Book bk = null;
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            try {
                XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("files/books.xml"));
                while(xmlEventReader.hasNext()) {   // пока есть следующий элемент в файле
                    XMLEvent xmlEvent = xmlEventReader.nextEvent();     // встреча элемента - событие
                    if (xmlEvent.isStartElement()) {            //если открывающий тег
                        StartElement startElement = xmlEvent.asStartElement();
                        if(startElement.getName().getLocalPart().equals("book")) {      // видим новую книгу - создаем экземпляр
                            bk = new Book();
                        }
                        else if(startElement.getName().getLocalPart().equals("price")) {
                            xmlEvent = xmlEventReader.nextEvent();
                            bk.setPrice(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                        else if(startElement.getName().getLocalPart().equals("title")) {
                            xmlEvent = xmlEventReader.nextEvent();
                            bk.setTitle(xmlEvent.asCharacters().getData());
                        }
                        else if(startElement.getName().getLocalPart().equals("pages")) {
                            xmlEvent = xmlEventReader.nextEvent();
                            bk.setPages(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                        else if(startElement.getName().getLocalPart().equals("author")) {
                            xmlEvent = xmlEventReader.nextEvent();
                            bk.setAuthor(xmlEvent.asCharacters().getData());
                        }
                        else if(startElement.getName().getLocalPart().equals("owner")) {
                            xmlEvent = xmlEventReader.nextEvent();
                            bk.setOwner(xmlEvent.asCharacters().getData());
                        }
                    }
                    if(xmlEvent.isEndElement()) {       // если закрывающий тег
                        EndElement endElement = xmlEvent.asEndElement();
                        if(endElement.getName().getLocalPart().equals("book")) {
                            bookshelf.add(bk);
                        }
                    }
                }

            }
            catch(FileNotFoundException | XMLStreamException | InvalidPriceValue e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("xml source file is not valid");
        }
    }
    public static void saveFromJson(ArrayList<Book> bookshelf, String filename) throws IOException {
        File file = new File(filename);
        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader(file);
        //создаем BufferedReader с существующего FileReader для построчного считывания
        BufferedReader reader = new BufferedReader(fr);
        // считаем сначала первую строку
        Book deser = new Book();
        String line = reader.readLine();
        while (line != null) {
            deser =JSON.parseObject(line, Book.class);
            bookshelf.add(deser);
            // считываем остальные строки в цикле
            line = reader.readLine();
        }
    }
    public static void saveToJson(ArrayList<Book> bookshelf, String file) throws IOException {
        Book ser = new Book();
        FileWriter writer = new FileWriter(file);
        for(Book book: bookshelf)
        {
            ser = book;
            String jsonstring = JSON.toJSONString(ser);
            writer.write(jsonstring + System.getProperty("line.separator"));
        }
        writer.close();
    }
}
