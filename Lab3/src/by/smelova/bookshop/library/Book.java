package by.smelova.bookshop.library;
import java.io.Serializable;

public class Book  extends Literature  {

    public Book(int pr, String tit, int pag, String au, String ow) {
        this.price = pr;
        this.title = tit;
        this.pages = pag;
        this.author = au;
        this.owner = ow;
    }

    public Book() {
        this.price =0;
        this.title = "";
        this.pages =0;
        this.author = "";
        this.owner = "";
    }

    @Override
    public String toString() {
        return this.author + ", " + this.title + ", " + this.price + "$";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        else
        {
            Book bk = (Book)obj;
            if(this.price == bk.price
                    && this.pages == bk.pages
                    && this.owner.equals(bk.owner)
                    && this.title.equals(bk.title)
                    && this.author.equals(bk.author)) return true;
            else return false;
        }
    }
    @Override
    public  int hashCode() {
        return  this.hashCode();
    }

}
